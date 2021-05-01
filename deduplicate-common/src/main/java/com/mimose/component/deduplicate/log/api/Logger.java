package com.mimose.component.deduplicate.log.api;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import java.util.Objects;

/**
 * @author mimose
 * @description abstract Logger
 * @date 2021/3/28
 */
public abstract class Logger {
    private static final String MODULE_SURROUND_BEGIN = "[";
    private static final String MODULE_SURROUND_END = "]";
    private static final String MODULE_SPLIT_STR = ": ";

    private org.slf4j.Logger logger;

    private String module;
    private String message;
    private Object[] arguments;
    private Throwable throwable;

    public void initialize(org.slf4j.Logger realLogger) {
        this.logger = realLogger;
    }

    public Logger module(String module) {
        this.module = module;
        return this;
    }

    public Logger message(String message) {
        this.message = message;
        return this;
    }

    public Logger args(Object... arguments) {
        this.arguments = arguments;
        return this;
    }

    public Logger throwable(Throwable throwable) {
        this.throwable = throwable;
        return this;
    }

    public void build() {
        if(!StringUtils.isEmpty(this.module)) {
            message = MODULE_SURROUND_BEGIN + this.module + MODULE_SURROUND_END + MODULE_SPLIT_STR + this.message;
        }
        final FormattingTuple tuple = MessageFormatter.arrayFormat(this.message, this.arguments);
        if(Objects.isNull(throwable)) {
            output(tuple.getMessage());
        } else {
            output(tuple.getMessage(), this.throwable);
        }
    }

    /**
     * output log
     * @param message
     */
    protected abstract void output(String message);

    /**
     * output log with throwable
     * @param message
     * @param throwable
     */
    protected abstract void output(String message, Throwable throwable);

    protected org.slf4j.Logger getLogger() {
        return logger;
    }

    protected String getModule() {
        return module;
    }

    protected String getMessage() {
        return message;
    }

    protected Object[] getArguments() {
        return arguments;
    }

    protected Throwable getThrowable() {
        return throwable;
    }

    private void setLogger(org.slf4j.Logger logger) {
        this.logger = logger;
    }

    private void setModule(String module) {
        this.module = module;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    private void setArguments(Object[] arguments) {
        this.arguments = arguments;
    }

    private void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
