package com.daishaowen.test.chinaMobile.exception;

import java.io.Serializable;

/**
 * Created by disvenk.dai on 2018-09-11 16:03
 */
public class AppException extends BusinessException{

    public AppException(final ExceptionType exceptionType) {
        super(exceptionType);
    }


}
