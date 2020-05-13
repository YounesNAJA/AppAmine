package com.younesnaja.app;

import java.util.logging.Logger;

public class Handler implements Thread.UncaughtExceptionHandler {

    private final static Logger LOGGER = Logger.getLogger(Handler.class.getName());

    public void uncaughtException(Thread t, Throwable e) {
        LOGGER.severe(e.getMessage());
    }
}