package com.mimose.component.deduplicate.log;

import com.mimose.component.deduplicate.log.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mimose
 * @description Fluent Logger
 * @date 2021/3/28
 */
public final class FluentLogger {

    private FluentLogger(){}

    private org.slf4j.Logger realLogger;

    public static FluentLogger getLogger(Class clazz) {
        final FluentLogger fluentLogger = new FluentLogger();
        fluentLogger.setRealLogger(LoggerFactory.getLogger(clazz));
        return fluentLogger;
    }

    public TraceLogger trace() {
        TraceLogger logger = new TraceLogger();
        logger.initialize(this.realLogger);
        return logger;
    }

    public InfoLogger info() {
        InfoLogger logger = new InfoLogger();
        logger.initialize(this.realLogger);
        return logger;
    }

    public DebugLogger debug() {
        DebugLogger info = new DebugLogger();
        info.initialize(this.realLogger);
        return info;
    }

    public WarnLogger warn() {
        WarnLogger info = new WarnLogger();
        info.initialize(this.realLogger);
        return info;
    }

    public ErrorLogger error() {
        ErrorLogger info = new ErrorLogger();
        info.initialize(this.realLogger);
        return info;
    }

    private void setRealLogger(Logger realLogger) {
        this.realLogger = realLogger;
    }
}
