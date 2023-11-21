package com.jadonvb;


import com.jadonvb.util.Logger;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Logger logger = new Logger();

        Server server = new Server();

        logger.log("JadMB started up correctly!");
    }

}