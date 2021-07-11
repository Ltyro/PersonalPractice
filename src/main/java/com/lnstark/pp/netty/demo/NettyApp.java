package netty.demo;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateEvent;

public class NettyApp {

	public static void main(String[] args) {
		String host = "127.0.0.1";
		int port = 1966;
		
		EventLoopGroup eventLoopGroup=new NioEventLoopGroup();
		Bootstrap bootstrap=new Bootstrap();
        bootstrap.channel(NioSocketChannel.class);
        bootstrap.option(ChannelOption.SO_KEEPALIVE,true);
        bootstrap.group(eventLoopGroup);
        bootstrap.remoteAddress(host,port);
        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
              // socketChannel.pipeline().addLast(new IdleStateHandler(20,10,0));
            	socketChannel.pipeline().addLast(new StringEncoder());
            	socketChannel.pipeline().addLast(new StringDecoder());
            	// socketChannel.pipeline().addLast(new EnvProtobufEncoder());
                //socketChannel.pipeline().addLast(new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                socketChannel.pipeline().addLast(new SimpleChannelInboundHandler() {
                	
                	@Override
                    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                        if (evt instanceof IdleStateEvent) {
                            IdleStateEvent e = (IdleStateEvent) evt;
                            switch (e.state()) {
                                case WRITER_IDLE:
//                                    PingMsg pingMsg=new PingMsg();
//                                    ctx.writeAndFlush(pingMsg);
                                    System.out.println("send ping to server----------");
                                    break;
                                default:
                                    break;
                            }
                        }
                    }

					@Override
					protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
						// TODO Auto-generated method stub
						System.out.println(msg);
					}
					
                });
            }
        });
        ChannelFuture future = null;
        try {
        	
			future = bootstrap.connect(host, port).sync();
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        if (future.isSuccess()) {
            System.out.println("connect server  成功---------");
        }
	}

}
