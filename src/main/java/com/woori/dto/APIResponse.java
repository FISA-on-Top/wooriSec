package com.woori.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class APIResponse<T> {
    private final String resultCode;
    private final String resultMessage;
    private final T data;
    
    public APIResponse(String resultCode, String resultMessage, T data) {
    	this.resultCode = resultCode;
    	this.resultMessage = resultMessage;
    	this.data = data;
    }
    
    public static <T>APIResponse<T> success(T response){
    	return new APIResponse<>("0000","ok",response);
    }
    
    public static <T>APIResponse<T> failbyJsonFormat(T response){
    	return new APIResponse<>("1000","Invalid Json format",response);
    }
    public static <T>APIResponse<T> failbyValidation(T response){
    	return new APIResponse<>("1001","Authentication failed.",response);
    }
    public static <T>APIResponse<T> failbyDataFormat(T response){
    	return new APIResponse<>("1002","Invalid data format",response);
    }
}
