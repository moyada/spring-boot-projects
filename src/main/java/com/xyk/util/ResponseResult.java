package com.xyk.util;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Http请求结果
 * Created by linlei on 16/5/20.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public final class ResponseResult {

    final static public int FAILURE = 0;
    final static public int SUCCESS = 1;

    final static public int WARNING = 2;
    final static public int INFO = 3;

    private Integer status;//	执行的状态：1成功，0失败，2警告，3提示
    private Integer errorCode;//	错误或者失败的编码(先预留)
    private String message;//	消息，系统返回的提示消息。无论哪种状态都有可能返回消息。
    private Object data;//	如果是查询请求，返回的是集合（List）；如果是保存编辑请求，返回的是一个对象。
    private Long total;//	本次查询请求的总记录数，用于分页计算。

    public ResponseResult ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
        return this;
    }

    /**
     * @param status
     * @param paras(message,data,total)
     * @return ResponseResult
     */
    public static ResponseResult allResult(int status, final Object... paras) {
        ResponseResult r = new ResponseResult();
        r.status = status;
        if (paras != null && paras.length > 0) {
            if (paras.length == 1) {
                r.message = (String) paras[0];
            }
            if (paras.length == 2) {
                r.message = (String) paras[0];
                r.data = paras[1];
            }
            if (paras.length == 3) {
                r.message = (String) paras[0];
                r.data = paras[1];
                r.total = (Long) paras[2];
            }
        }
        return r;
    }

/*    public static ResponseResult toResult(ExtDirectStoreResult<?> result){
        ResponseResult r = new ResponseResult();
        r.status = result.isSuccess() ? 1 : 0;
        r.data = result.getRecords();
        if(result.getTotal() != null){
            r.total = result.getTotal();
        }else{
            r.total = result.getRecords() != null ? (long) result.getRecords().size() : 0l;
        }
        return r;
    }*/

    public static ResponseResult successResult(Object data) {
        ResponseResult r = new ResponseResult();
        r.setStatus(SUCCESS);
        r.setData(data);
        return r;
    }

    public static ResponseResult failResult(String message) {
        ResponseResult r = new ResponseResult();
        r.setStatus(FAILURE);
        r.setData(null);
        r.setMessage(message);
        return r;
    }


    public static ResponseResult nullUserResult() {
        ResponseResult r = new ResponseResult();
        r.setStatus(401);
        r.setData(null);
        r.setMessage("登录超时,请重新登录");
        return r;
    }

    public ResponseResult() {
        super();
    }

    public ResponseResult(int status) {
        super();
        this.status = status;
    }


    public Integer getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }


}
