package com.khaki.taxi.infrastructure.exception;

import com.khaki.taxi.infrastructure.BusinessException;

public class CacheKeyNotFoundException extends BusinessException {
    private static final long serialVersionUID=1L;

    public CacheKeyNotFoundException() {
    }

    public CacheKeyNotFoundException(String message) {
        super(message);
    }

    public CacheKeyNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheKeyNotFoundException(Throwable cause) {
        super(cause);
    }

    public CacheKeyNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
