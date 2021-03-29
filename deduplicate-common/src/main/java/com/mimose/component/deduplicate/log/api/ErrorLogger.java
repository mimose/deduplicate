package com.mimose.component.deduplicate.log.api;

/**
 * @author mimose
 * @description error logger
 * @date 2021/3/28
 */
public final class ErrorLogger extends Logger {

    @Override
    protected void output(String message) {
        if(this.getLogger().isErrorEnabled()) {
            this.getLogger().error(message);
        }
    }

    @Override
    protected void output(String message, Throwable throwable) {
        if(this.getLogger().isErrorEnabled()) {
            this.getLogger().error(message, throwable);
        }
    }
}
