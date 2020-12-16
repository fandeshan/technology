package priv.fandeshan.demo.mymallgoodsservices.enums;

import lombok.extern.slf4j.Slf4j;
import priv.fandeshan.demo.tools.enums.IBundleEnum;

@Slf4j
public enum GoodsResultCode implements IBundleEnum {
    ITEMID_NOT_NULL("G10001"),
    NUMS_NOT_NULL("G10002"),
    ;

    private String code;

    GoodsResultCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    private static final String RESULT_CODE_PREFIX = "priv.fandeshan.demo.goods.resultcode.";

    @Override
    public String getMessage() {
        try {
            return getMessage(RESULT_CODE_PREFIX + this.code);
        }catch (Exception e){
            log.warn("case error when convert code: {}, exception:{}",this.code,e.getMessage());
            return "";
        }

    }

    @Override
    public String getMessage(Object[] args) {
        try {
            return getMessage(RESULT_CODE_PREFIX + this.code,args);
        }catch (Exception e){
            log.warn("case error when convert code: {}, exception:{}",this.code,e.getMessage());
            return "";
        }

    }
}
