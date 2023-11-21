package com.jadonvb;


import com.jadonvb.util.Logger;

public class Main {

    public static void main(String[] args)  {
        Logger logger = new Logger();

        Server server = new Server();

        logger.log("JadMB started up correctly!");
    }

}