package priv.fandeshan.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import priv.fandeshan.demo.dubbo.server.IloginService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        IloginService loginService = null;
        ApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:META-INF/spring/application.xml");
        loginService = (IloginService) context.getBean("loginService");
        System.out.println( loginService.login("admin","admin"));
    }
}
