package priv.fandeshan.demo.tools.enums;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum ResultCode implements IBundleEnum{

    SUCCESS("000000"),
    FAIL("999999"),
    UNKNOWERROR("000001"),
    ;

    private String code;

    ResultCode(String code){
        this.code = code;
    }

    public String getCode(){
        return this.code;
    }

    private static final String RESULT_CODE_PREFIX = "priv.fandeshan.demo.resultcode.";

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
