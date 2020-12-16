package priv.fandeshan.demo.eurekaorderservice.dto;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import priv.fandeshan.demo.tools.exception.ValidException;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class OrderDto {

    @NotNull( message = "name not null")
    private String name;
    @NotNull (message = "tel not null")
    private String tel;
    private String userId;
    @NotNull(message = "item list not null")
    private List<ItemDto> items;

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
