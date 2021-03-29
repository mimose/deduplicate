package com.mimose.component.deduplicate.exceptions;

/**
 * @author mimose
 * @description exception in action
 * @date 2021/3/25
 */
public final class ActionException extends RuntimeException {

    public ActionException() {
        super();
    }

    public ActionException(String message) {
        super(message);
    }

    public ActionException(String message, Throwable cause) {
        super(message, cause);
    }
}
