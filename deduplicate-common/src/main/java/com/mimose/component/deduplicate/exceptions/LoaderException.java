package com.mimose.component.deduplicate.exceptions;

/**
 * @author mimose
 * @description exception in doing the loading actions
 * @date 2021/3/25
 */
public final class LoaderException extends Exception {

    public LoaderException() {
        super();
    }

    public LoaderException(String message) {
        super(message);
    }

    public LoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
