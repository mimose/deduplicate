package com.mimose.component.deduplicate.log.api;

/**
 * @author mimose
 * @description trace logger
 * @date 2021/3/28
 */
public final class TraceLogger extends Logger {

    @Override
    protected void output(String message) {
        if(this.getLogger().isTraceEnabled()) {
            this.getLogger().trace(message);
        }
    }

    @Override
    protected void output(String message, Throwable throwable) {
        throw new UnsupportedOperationException();
    }
}
