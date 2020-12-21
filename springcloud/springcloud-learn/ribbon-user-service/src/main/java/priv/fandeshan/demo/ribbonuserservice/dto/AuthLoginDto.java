package priv.fandeshan.demo.ribbonuserservice.dto;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import priv.fandeshan.demo.tools.exception.ValidException;

import javax.validation.constraints.NotNull;

@Data
public class AuthLoginDto {

    private String username;
    private String password;

    private String phone;
    private String code;

    private String openId;

    /**
     * @see priv.fandeshan.demo.ribbonuserservice.enums.LoginTypeEnum
     */

    @NotNull(message = "登录类型不能为空")
    private Integer loginType;

    public void validData(BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            StringBuilder stringBuilder = new StringBuilder();
            for (ObjectError objectError:bindingResult.getAllErrors()){
                stringBuilder.append(objectError.getDefaultMessage()+"\n");
            }
            throw new ValidException(stringBuilder.toString());
        }
    }
}
