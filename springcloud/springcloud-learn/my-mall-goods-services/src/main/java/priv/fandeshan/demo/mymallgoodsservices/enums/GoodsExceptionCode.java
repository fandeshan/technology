package priv.fandeshan.demo.mymallgoodsservices.enums;

import lombok.extern.slf4j.Slf4j;
import priv.fandeshan.demo.tools.enums.IBundleEnum;

@Slf4j
public enum GoodsExceptionCode implements IBundleEnum {


    ITEMS_NOT_FOUND("itemsNotFound"),
    SOME_ITEMS_NOT_EXIST("SomeItemsNotExist"),
    ITEMS_NOT_ENOUGH("ItemsNotEnough"),
    ;

    private String code;

    GoodsExceptionCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    private static final String EXCEPTION_CODE_PREFIX = "priv.fandeshan.demo.goods.ExceptionCode.";

    @Override
    public String getMessage() {
        try {
            return getMessage(EXCEPTION_CODE_PREFIX + this.code);
        }catch (Exception e){
            log.warn("case error when convert code: {}, exception:{}",this.code,e.getMessage());
            return "";
        }

    }

    @Override
    public String getMessage(Object[] args) {
        try {
            return getMessage(EXCEPTION_CODE_PREFIX + this.code,args);
        }catch (Exception e){
            log.warn("case error when convert code: {}, exception:{}",this.code,e.getMessage());
            return "";
        }
    }

}
