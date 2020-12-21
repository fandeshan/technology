package priv.fandeshan.demo.ribbonuserservice.controller;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import priv.fandeshan.demo.ribbonuserservice.biz.AbstractLogin;
import priv.fandeshan.demo.ribbonuserservice.biz.Login;
import priv.fandeshan.demo.ribbonuserservice.dto.AuthLoginDto;
import priv.fandeshan.demo.ribbonuserservice.utils.JwtGeneratorUtil;
import priv.fandeshan.demo.tools.api.ResultInfo;
import priv.fandeshan.demo.tools.enums.ResultCode;
import priv.fandeshan.demo.tools.exception.BizException;
import priv.fandeshan.demo.tools.exception.ValidException;

@RestController
@Slf4j
@RequestMapping
public class LoginController {

    @PostMapping("/login")
    public ResultInfo loginAuth(@RequestBody @Validated AuthLoginDto authLoginDto, BindingResult bindingResult){
        authLoginDto.validData(bindingResult);
        Login login = AbstractLogin.loginMap.get(authLoginDto.getLoginType());
        if (login == null) {
            throw new BizException("暂不支持该登录方式");
        }
        return login.doLogin(authLoginDto);
    }
    @GetMapping("/token")
    public ResultInfo<String> validToken(@RequestParam("token") String token){
        if (StringUtils.isBlank(token)){
            throw new ValidException("token为空");
        }
        try {
            Claims claims = JwtGeneratorUtil.parseToken(token);
            return new ResultInfo.Builder<>().setData(claims.get("uid").toString()).buildOk();
        } catch (ExpiredJwtException e) {
            return new ResultInfo.Builder<>().buildCustomize(ResultCode.FAIL.getCode(), "token已过期");
        } catch (SignatureException e) {
            return new ResultInfo.Builder<>().buildCustomize(ResultCode.FAIL.getCode(), "签名校验失败");

        }

    }
}
