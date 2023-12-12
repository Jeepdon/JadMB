package com.jadonvb;



public class Main {

    public static void main(String[] args)  {
        Logger logger = new Logger("JadMB");

        Server server = new Server();

        logger.log("JadMB started up correctly!");
    }

}