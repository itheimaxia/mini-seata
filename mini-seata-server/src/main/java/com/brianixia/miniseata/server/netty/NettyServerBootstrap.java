package com.brianixia.miniseata.server.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.NettyRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author brianxia
 * @version 1.0
 * @date 2020/11/18 11:48
 */
public class NettyServerBootstrap {

    private static final Logger LOGGER = LoggerFactory.getLogger(NettyServerBootstrap.class);
    private final ServerBootstrap serverBootstrap = new ServerBootstrap();
    private final EventLoopGroup eventLoopGroupWorker;
    private final EventLoopGroup eventLoopGroupBoss;
    private final int listenPort;
    private final AtomicBoolean initialized = new AtomicBoolean(false);

    public NettyServerBootstrap(int listenPort){
        this.eventLoopGroupBoss = new NioEventLoopGroup(1);
        this.eventLoopGroupWorker = new NioEventLoopGroup(NettyRuntime.availableProcessors() * 2);
        this.listenPort = listenPort;
    }

    public void start(){
        this.serverBootstrap.group(eventLoopGroupBoss,eventLoopGroupWorker)
                .channel(NioServerSocketChannel.class)
                //让端口释放后立即就可以被再次使用
                .option(ChannelOption.SO_REUSEADDR, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .localAddress(new InetSocketAddress(listenPort))
                .childOption(ChannelOption.SO_SNDBUF, 153600)
                .childOption(ChannelOption.SO_RCVBUF, 153600)
                .childOption(ChannelOption.WRITE_BUFFER_WATER_MARK,
                        new WriteBufferWaterMark(1048576,
                                67108864))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
//                        ch.pipeline().addLast(new IdleStateHandler(15, 0, 0))
//                                .addLast(new ProtocolV1Decoder())
//                                .addLast(new ProtocolV1Encoder());
//                        if (channelHandlers != null) {
//                            addChannelPipelineLast(ch, channelHandlers);
//                        }

                    }
                });

        try {
            ChannelFuture future = this.serverBootstrap.bind(listenPort).sync();
            LOGGER.info("Server started, listen port: {}", listenPort);
           // RegistryFactory.getInstance().register(new InetSocketAddress(XID.getIpAddress(), XID.getPort()));
            initialized.set(true);
            future.channel().closeFuture().sync();
        } catch (Exception exx) {
            throw new RuntimeException(exx);
        }
    }

}
