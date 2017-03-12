package com.qizhi.socket.exception;




public class IotServiceBizException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4540588052427941409L;
	private String code;
    private String msg;

    public IotServiceBizException() {
    }


    public IotServiceBizException(IotServiceExceptionEnum bizEnum) {
        this.code = bizEnum.getCode();
        this.msg = bizEnum.getMsg();
    }

    public IotServiceBizException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
