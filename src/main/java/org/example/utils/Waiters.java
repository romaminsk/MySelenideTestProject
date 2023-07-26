package org.example.utils;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Waiters {

    public static void sleep() {
        log.info("Sleep");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
