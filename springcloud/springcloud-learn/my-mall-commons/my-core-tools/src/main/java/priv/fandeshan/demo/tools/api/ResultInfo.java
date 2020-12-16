package priv.fandeshan.demo.tools.api;

import lombok.Data;
import priv.fandeshan.demo.tools.enums.ResultCode;

@Data
public class ResultInfo<T>{
    private String code;
    private String message;
    private T data;

    public ResultInfo(){

    }
    public ResultInfo(Builder<T> builder){
        this.code = builder.code;
        this.message = builder.message;
        this.data = builder.data;
    }
    public static class Builder<T>{
        private String code;
        private String message;
        private T data;

        public Builder(){}

        public ResultInfo buildCustomize(String code,String message){
            this.code = code;
            this.message = message;
            return new ResultInfo(this);
        }

        public ResultInfo buildOk(){
            this.code = ResultCode.SUCCESS.getCode();
            this.message = ResultCode.SUCCESS.getMessage();
            return new ResultInfo(this);
        }

        public ResultInfo buildFail(){
            this.code = ResultCode.FAIL.getCode();
            this.message = ResultCode.FAIL.getMessage();
            return new ResultInfo(this);
        }

        public Builder setData(T data){
            this.data = data;
            return this;
        }

        @Override
        public String toString() {
            return "Builder{" +
                    "code='" + code + '\'' +
                    ", message='" + message + '\'' +
                    ", data=" + data +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


}
