package com.mimose.component.deduplicate.exceptions;

/**
 * @author mimose
 * @description exception about argument
 * @date 2021/3/25
 */
public final class ArgumentException extends RuntimeException {

    public ArgumentException() {
        super();
    }

    public ArgumentException(String message) {
        super(message);
    }

}
