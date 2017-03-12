package com.qizhi.socket;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.handler.DiscardServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;  

public class SocketServer {
	
	private static Logger logger = LoggerFactory.getLogger(SocketServer.class);
	private static final int PORT = 8088;
	
    protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors()*2; //Ĭ��  
    
    protected static final int BIZTHREADSIZE = 4;  
         
    private static final EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);  
    private static final EventLoopGroup workerGroup = new NioEventLoopGroup(BIZTHREADSIZE);  

    public static void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(20); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup(100);
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) // (3)
             .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                	 ch.pipeline().addLast(new DiscardServerHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 1024)          // (5)
             .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)

            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(PORT).sync(); // (7)
            
            logger.info("server is running on port 8088");

            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
        	logger.info("server socket is closed");
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
	
	public static void main(String[] args) throws Exception {
		
		try {
			SocketServer.run();
			logger.info("server socket end--------");
		} catch (Exception e) {
			logger.error("sever socket system error:",e);
		}
		
	}
	
}
