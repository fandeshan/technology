package priv.fandeshan.demo.ribbonuserservice.biz;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import priv.fandeshan.demo.ribbonuserservice.dto.AuthLoginDto;
import priv.fandeshan.demo.ribbonuserservice.mapper.entitys.TbMember;
import priv.fandeshan.demo.ribbonuserservice.utils.JwtGeneratorUtil;
import priv.fandeshan.demo.tools.api.ResultInfo;
import priv.fandeshan.demo.tools.exception.BizException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 登录模板类
 */
@Slf4j
public abstract class AbstractLogin implements Login{

    public static ConcurrentHashMap<Integer,AbstractLogin> loginMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init (){
        loginMap.put(getLoginType(),this);
    }

    @Override
    public ResultInfo doLogin(AuthLoginDto authLoginDto) throws BizException {
        log.info("begin AbstractLogin.doLogin :"+authLoginDto);
        validate(authLoginDto);
        TbMember tbMember = doProcessor(authLoginDto);
        HashMap<String, Object> payload = new HashMap<>();
        payload.put("uid",tbMember.getId());
        payload.put("exp", DateTime.now().plusHours(1).toDate().getTime()/1000);
        String token = JwtGeneratorUtil.generatorToken(payload);
        return new ResultInfo.Builder<>().setData(token).buildOk();
    }

    /**
     * 获取子类申明的登录类型
     * @return
     */
    public abstract int getLoginType();

    /**
     * 子类完成校验
     * @param authLoginDto
     */
    public abstract void validate(AuthLoginDto authLoginDto);

    /**
     *  登录校验
     * @param authLoginDto
     */
    public abstract TbMember doProcessor(AuthLoginDto authLoginDto);
}
