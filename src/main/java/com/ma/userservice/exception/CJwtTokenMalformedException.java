package com.ma.userservice.exception;

import javax.naming.AuthenticationException;

public class CJwtTokenMalformedException extends AuthenticationException {
    public CJwtTokenMalformedException(String message){
        super(message);
    }
}
