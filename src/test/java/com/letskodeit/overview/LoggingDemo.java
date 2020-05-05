package com.letskodeit.overview;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingDemo {
    public static Logger log = LogManager.getLogger(LoggingDemo.class.getName());

    public static void main(String[] args){
        log.trace("Trace message printed");
        log.debug("Debug message printed");
        log.info("Info message printed");
        log.error("Error message printed");
        log.fatal(("Fatal message printed"));
    }

}
