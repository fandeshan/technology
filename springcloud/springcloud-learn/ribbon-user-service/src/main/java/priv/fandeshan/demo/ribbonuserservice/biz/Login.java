package priv.fandeshan.demo.ribbonuserservice.biz;

import priv.fandeshan.demo.ribbonuserservice.dto.AuthLoginDto;
import priv.fandeshan.demo.tools.api.ResultInfo;
import priv.fandeshan.demo.tools.exception.BizException;

public interface Login {

    ResultInfo doLogin(AuthLoginDto authLoginDto) throws BizException;
}
