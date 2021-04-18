package com.mimose.component.deduplicate.exceptions;

/**
 * @author mimose
 * @description exception in action
 * @date 2021/3/25
 */
public final class ActionException {

    public static class NormalActionException extends RuntimeException {
        public NormalActionException() {
            super();
        }

        public NormalActionException(String message) {
            super(message);
        }

        public NormalActionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class UnsupportedActionException extends RuntimeException {
        public UnsupportedActionException() {
            super();
        }

        public UnsupportedActionException(String message) {
            super(message);
        }

        public UnsupportedActionException(String message, Throwable cause) {
            super(message, cause);
        }

    }
}
