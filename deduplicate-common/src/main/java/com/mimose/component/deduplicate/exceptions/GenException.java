package com.mimose.component.deduplicate.exceptions;

/**
 * @author mimose
 * @description exception in doing generating actions
 * @date 2021/3/25
 */
public final class GenException extends RuntimeException {

    public GenException() {
        super();
    }

    public GenException(String message) {
        super(message);
    }

    public GenException(String message, Throwable cause) {
        super(message, cause);
    }
}
