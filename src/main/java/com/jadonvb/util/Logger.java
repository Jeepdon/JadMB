package com.jadonvb.util;

import com.jadonvb.util.ConsoleColors;

public class Logger {

    public void log(String message) {
        System.out.println(
                ConsoleColors.PURPLE +
                "[JadMB]" + ConsoleColors.RESET + ": " +
                message);
    }

    public void error(String message) {
        System.out.println(ConsoleColors.RED_BOLD + message);
    }

    public void debug(String message) {
        System.out.println(ConsoleColors.YELLOW + message);
    }
}
