package priv.fandeshan.demo.tools.exception;

import priv.fandeshan.demo.tools.enums.IBundleEnum;

public class BizException extends BaseException {

    public BizException(){
        super();
    }

    public BizException(String message){
        super(message);
        this.message = message;
    }

    public BizException(String code, String message) {
        super(code, message);
        this.code = code;
        this.message = message;
    }

    public BizException(IBundleEnum bundleEnum){
        super(bundleEnum.getMessage());
        this.message = bundleEnum.getMessage();
    }
    public BizException(IBundleEnum bundleEnum,Object... args){
        super(bundleEnum.getMessage(args));
        this.message = bundleEnum.getMessage(args);
    }
}
