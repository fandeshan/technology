package priv.fandeshan.demo.dubbo.server;

/**
 * @author fandeshan
 * @description //TODO 写点注释吧
 * @date 2021/7/2
 */
public class LoginServiceImpl implements IloginService{

    @Override
    public String login(String username, String password) {
        if ("admin".equals(username) && "admin".equals(password))
            return "success";
        return "fail";
    }
}
