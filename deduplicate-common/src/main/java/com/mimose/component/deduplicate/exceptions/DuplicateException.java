package com.mimose.component.deduplicate.exceptions;

/**
 * @author mimose
 * @description exception when there is a duplication
 * @date 2021/3/26
 */
public final class DuplicateException extends Exception {

    public DuplicateException() {
        super();
    }

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }
}
