package com.mimose.component.deduplicate.exceptions;

/**
 * @author mimose
 * @description exception in cache
 * @date 2021/3/25
 */
public final class CacheException extends RuntimeException {

    public CacheException() {
        super();
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }
}
