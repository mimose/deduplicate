package com.mimose.component.deduplicate.exceptions;

import org.apache.commons.lang3.StringUtils;

/**
 * @author mimose
 * @description exception when there is a duplication
 * @date 2021/3/26
 */
public final class DuplicateException extends Exception {
    private static final String DUPLICATE_EX_DEFAULT_MESSAGE = "A duplicated exception occurred";

    public DuplicateException() {
        super();
    }

    public DuplicateException(String message) {
        super(message);
    }

    public DuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        final String message = super.getMessage();
        if(StringUtils.isEmpty(message)) {
            return DUPLICATE_EX_DEFAULT_MESSAGE;
        }
        return message;
    }
}
