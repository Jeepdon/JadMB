package com.jadonvb;


import com.jadonvb.instances.Server;

public class Main {

    public static void main(String[] args)  {
        Logger logger = new Logger("JadMB");

        new Server();

        logger.log("JadMB started up correctly!");
    }
}