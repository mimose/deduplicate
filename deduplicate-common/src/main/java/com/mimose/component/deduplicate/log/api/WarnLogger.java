package com.mimose.component.deduplicate.log.api;

/**
 * @author mimose
 * @description warn logger
 * @date 2021/3/28
 */
public final class WarnLogger extends Logger {

    @Override
    protected void output(String message) {
        if(this.getLogger().isWarnEnabled()) {
            this.getLogger().warn(message);
        }
    }

    @Override
    protected void output(String message, Throwable throwable) {
        if(this.getLogger().isWarnEnabled()) {
            this.getLogger().warn(message, throwable);
        }
    }
}
