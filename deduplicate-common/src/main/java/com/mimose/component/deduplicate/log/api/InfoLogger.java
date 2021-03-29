package com.mimose.component.deduplicate.log.api;

/**
 * @author mimose
 * @description info logger
 * @date 2021/3/28
 */
public final class InfoLogger extends Logger {

    @Override
    protected void output(String message) {
        if(this.getLogger().isInfoEnabled()) {
            this.getLogger().info(message);
        }
    }

    @Override
    protected void output(String message, Throwable throwable) {
        throw new UnsupportedOperationException();
    }
}
