package com.mimose.component.deduplicate.log.api;

/**
 * @author mimose
 * @description debug logger
 * @date 2021/3/28
 */
public final class DebugLogger extends Logger {

    @Override
    protected void output(String message) {
        if(this.getLogger().isDebugEnabled()) {
            this.getLogger().debug(message);
        }
    }

    @Override
    protected void output(String message, Throwable throwable) {
        throw new UnsupportedOperationException();
    }
}
