package priv.fandeshan.demo.mvcframework.v2.servlet;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import priv.fandeshan.demo.mvcframework.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.logging.Logger;

@Slf4j
public class FDSDispatcherServlet extends HttpServlet {

    private Properties contextConfig = new Properties();

    private List<String> classNames = new ArrayList<>();

    //
    private Map<String,Object> ioc = new HashMap<>();

    //
    private Map<String,Method> handleMapping = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //6、委派，根据URL去找到对应的Method并通过response返回
        try {
            doDispatcher(req,resp);
        }catch (Exception e){
            log.error("doDispatcher cause Exception:",e);
            resp.getWriter().write("500 Exception,Detail:" + Arrays.toString(e.getStackTrace()));
        }

    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, InvocationTargetException, IllegalAccessException {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath, "").replaceAll("/+", "/");
        if (!this.handleMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found");
            return ;
        }
        Map<String,String[]> parameterMap = req.getParameterMap();
        Method method = this.handleMapping.get(url);

        //获取当前方法的形参列表
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] paramValues = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length ; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (HttpServletRequest.class == parameterType){
                paramValues[i] = req;
            } else if (HttpServletResponse.class == parameterType) {
                paramValues[i] = resp;
            } else if (String.class == parameterType) {
                //通过运行时的状态去拿到注解的值
                Annotation[][] pa = method.getParameterAnnotations();
                for (int j = 0; j < pa.length ; j++) {
                    for (Annotation a : pa[j]){
                        if (a instanceof FDSRequestParam){
                            String paramName = ((FDSRequestParam) a).value();
                            if (!"".equals(paramName.trim())){
                                String value = Arrays.toString(parameterMap.get(paramName))
                                        .replaceAll("\\[|\\]", "")
                                        .replaceAll("\\s+","");
                                paramValues[i] = value;
                            }
                        }
                    }
                }
//                FDSRequestParam requestParam = parameterType.getAnnotation(FDSRequestParam.class);
//                String paramName = requestParam.value();

            }
        }

        String beanName = method.getDeclaringClass().getSimpleName().substring(0,1).toLowerCase() + method.getDeclaringClass().getSimpleName().substring(1);
        Object returnValue = method.invoke(ioc.get(beanName), paramValues);
        if(returnValue == null || returnValue instanceof Void){ return; }
        resp.getWriter().write(returnValue.toString());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));

        // 2、扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));

        //===========IOC部分===========

        //3、初始化IOC容器，将扫描到的相关的类实例化，保存到IOC
        doInstance();

        ///=============DI部分===========

        //4、完成依赖注入
        doAutowired();

        //=============MVC部分============
        //5、初始化handlerMapping
        doInitHandlerMappring();
        log.info("fds demo springframework init success!");
    }

    private void doLoadConfig(String contextConfigLocation){
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try {
            contextConfig.load(inputStream);
        } catch (IOException e) {
            log.error("加载配置文件异常：",e);
        }finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("加载配置文件关闭流异常：",e);
                }
            }
        }
    }


    private void doScanner(String scanPackage){
        URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()){
            if (file.isDirectory()){
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (!file.getName().endsWith(".class")){
                    continue;
                }
                String className = scanPackage + "." + file.getName().replace(".class", "");
                classNames.add(className);
            }
        }
    }

    private void doInstance(){
        if (classNames.isEmpty()){
            return;
        }
        try {
            for (String className : classNames){
                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(FDSController.class)){
                    String beanName = clazz.getSimpleName().substring(0,1).toLowerCase() + clazz.getSimpleName().substring(1);
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);


                } else if (clazz.isAnnotationPresent(FDSService.class)){
                    //1、在多个包下出现相同的类名，只能自己起一个全局唯一的名字
                    String beanName = clazz.getAnnotation(FDSService.class).value();
                    if ("".equals(beanName)){
                        beanName = clazz.getSimpleName().substring(0,1).toLowerCase() + clazz.getSimpleName().substring(1);
                    }
                    //2、默认的类名首字母小写
                    Object instance = clazz.newInstance();
                    ioc.put(beanName,instance);

                    //3、如果是接口
                    //判断有多少个实现类，如果只有一个，默认选择这个实现类
                    //如果有多个，抛出异常
                    for (Class i : clazz.getInterfaces()){
                        if (ioc.containsKey(i.getName())){
                            throw new RuntimeException("The " + i.getName() + "is exists! ");
                        }
                        ioc.put(i.getName(),instance);
                    }
                }
            }
        }catch (Exception e) {
            log.error("加载类失败：",e);
        }

    }
    private void doAutowired() {
        if (ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String,Object> entry : ioc.entrySet()) {
            //获取所有申明的属性，private,public,protected等等
            for (Field field : entry.getValue().getClass().getDeclaredFields()) {
                if (!field.isAnnotationPresent(FDSAutowired.class)){
                    continue;
                }
                FDSAutowired autowired = field.getAnnotation(FDSAutowired.class);
                //如果没有在注解设置beanName，则默认根据类型名称注入
                String beanName = autowired.value().trim();
                if ("".equals(beanName)){
                    //获取字段的类型
                    beanName = field.getType().getName();
                }
                //设置强制访问属性
                field.setAccessible(true);
                try {
                    //设置属性的对应的示例注入
                    field.set(entry.getValue(),ioc.get(beanName));
                } catch (IllegalAccessException e) {
                    log.error("注入bean对象失败：",e);
                }
            }
        }
    }

    private void doInitHandlerMappring(){
        if (ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String,Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            //获取public 的方法
            if (!clazz.isAnnotationPresent(FDSController.class)){
                continue;
            }
            // 提取类上面的配置的url
            String baseUrl = "";
            if (clazz.isAnnotationPresent(FDSRequestMapping.class)){
                FDSRequestMapping requestMapping = clazz.getAnnotation(FDSRequestMapping.class);
                baseUrl = requestMapping.value();
            }
            for (Method method : clazz.getMethods()){
                if (!method.isAnnotationPresent(FDSRequestMapping.class)){
                    continue;
                }
                FDSRequestMapping requestMapping = method.getAnnotation(FDSRequestMapping.class);
                String url = ("/" + baseUrl + "/" + requestMapping.value()).replaceAll("/+","/");
                handleMapping.put(url,method);
                log.info("Mapped :"+url+"Method :" + method);
            }
        }

    }


}
