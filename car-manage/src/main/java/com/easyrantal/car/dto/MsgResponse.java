package com.easyrantal.car.dto;

import lombok.Data;

/**
 * @Description
 * @Author Peter Ke
 * @Date $ $
 */
@Data

public class MsgResponse<T> {

    T data;

    String errorCode;

    String errorMessage;

    public  static MsgResponse response(Object data,String errorCode,String errorMessage){
        MsgResponse msg=new MsgResponse();
        msg.data=data;
        msg.errorCode=errorCode;
        msg.errorMessage=errorMessage;
        return msg;
    }
    public static MsgResponse  toSuccess(Object  data,String errorMessage){
        MsgResponse msg=new MsgResponse();
        msg.data=data;
        msg.errorCode=Constant.SUCCESS_CODE;
        msg.errorMessage=errorMessage;
        return msg;
    }
}
