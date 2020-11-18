package com.brianixia.miniseata.server.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author brianxia
 * @version 1.0
 * @date 2020/11/18 11:33
 */
public class NettyServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServer.class);
    private final NettyServerBootstrap serverBootstrap;

    public NettyServer(int listenPort){
        serverBootstrap = new NettyServerBootstrap(listenPort);
    }

    public void start(){
        serverBootstrap.start();
    }
}
