package com.qizhi.socket.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qizhi.socket.util.ParseByteUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelInboundHandlerAdapter {
	private Logger logger = LoggerFactory.getLogger(DiscardServerHandler.class);
	private static int count = 1;
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
	    ByteBuf buf = (ByteBuf) msg;
	    try {
	        while (buf.isReadable()) { // (1)
	        	count++;
	        	// 获得缓冲区可读的字节数 
	            byte[] bytes = new byte[buf.readableBytes()];
	            buf.readBytes(bytes);
	            logger.info("接收请求数count={}",count);
	            try {
	            ParseByteUtil.iteratorBytes(bytes,ctx);
				} catch (Exception e) {
					logger.error("数据包处理异常:",e);
				}
	        }
	    } finally {
	    	logger.info("释放资源");
	        ReferenceCountUtil.release(msg); // (2)
	    }
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
		// 将消息发送队列中的消息写入到SocketChannel中发送给对方。
		ctx.flush();
	}
	
	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
		logger.error("socket error:",cause);
        ctx.close();
    }
}
