package com.woori;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvalidException extends RuntimeException{
	private int ResultCode;
	
    public InvalidException(String message, int code) {
        super(message);
        this.ResultCode = code;
    }
}
