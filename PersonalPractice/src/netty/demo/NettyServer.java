package netty.demo;

import java.util.ArrayList;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class NettyServer {
	
	public static void main(String[] args) {
		int port = 1966;
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	    EventLoopGroup workerGroup = new NioEventLoopGroup();
	    try {
	        ServerBootstrap b = new ServerBootstrap();
	        b.group(bossGroup, workerGroup)
	        .channel(NioServerSocketChannel.class)
	        .option(ChannelOption.SO_BACKLOG, 100)
            .handler(new LoggingHandler(LogLevel.INFO))
	        .childHandler(new ChannelInitializer<SocketChannel>() {

				@Override
				protected void initChannel(SocketChannel ch) {
					ChannelPipeline pipeline = ch.pipeline();
					pipeline.addLast(new ChannelInboundHandlerAdapter() {
						@Override
						public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
							System.out.println("channelRead0:==============" + ctx.channel().id());
							ByteBuf result = (ByteBuf) msg;
							byte[] byteArr = new byte[result.readableBytes()];
							result.readBytes(byteArr);
							for(byte b : byteArr) {
								System.out.print((char) b);
							}
							System.out.println();
							
						}
					});
				}
	        	
	        });
	            
	        ArrayList<Channel> channels = new ArrayList<Channel>();
            Channel serverChannel = b.bind(port).sync().channel();
            channels.add(serverChannel);
            
	        for (Channel ch : channels) {
	            ch.closeFuture();//.sync();
	        }
	
	    } catch (Exception e) {
			e.printStackTrace();
		} finally {
	//        Shut down all event loops to terminate all threads.
	//        bossGroup.shutdownGracefully();
	//        workerGroup.shutdownGracefully();
	    }
	}
	
}
