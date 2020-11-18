package com.brianixia.miniseata.server;

import com.brianixia.miniseata.server.netty.NettyServer;
import com.brianxia.mini.seata.common.utils.PortHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.brianxia.mini.seata.common.constant.ConfigurationKeys.SERVER_PORT;

public class Server {

    public static void main(String[] args) {

        //获取端口，可以使用-p参数
        int port = PortHelper.getPort(args);
        System.setProperty(SERVER_PORT,Integer.toString(port));
        // create logger
        final Logger logger = LoggerFactory.getLogger(Server.class);
        logger.info("The server is running in container.");
        NettyServer nettyServer = new NettyServer(port);
        nettyServer.start();
    }
}
