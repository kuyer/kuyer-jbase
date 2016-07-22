package io.github.kuyer.jbase.ui;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

//http://my.oschina.net/fhd/blog/378849
public class NioSimpleServer {
	
	// 通道管理器
	private Selector selector;
	
	private void init(int port) throws Exception {
		// 获得一个ServerSocket通道
		ServerSocketChannel ssc = ServerSocketChannel.open();
		// 设置通道为非阻塞
		ssc.configureBlocking(false);
		// 获得一个通道管理器
		ssc.socket().bind(new InetSocketAddress(port));
		// 获得一个通道管理器
		this.selector = Selector.open();
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_ACCEPT事件，注册
        // 该事件后，当该事件到达时，selector.select()会返回，如果该事件没到达selector.select()
        // 会一直阻塞。
		ssc.register(this.selector, SelectionKey.OP_ACCEPT);
	}
	
	/** 采用轮询的方式监听selector上是否有需要处理的事件，如果有，则进行处理  */
	private void listen() throws Exception {
		System.out.println("ssc start success.");
		// 轮询访问selector
		while(true) {
			// 当注册的事件到达时，方法返回；否则，该方法会一直阻塞
			int event = selector.select();
			// 获得selector中选中的项的迭代器，选中的项为注册的事件
			Iterator<SelectionKey> iter = this.selector.selectedKeys().iterator();
			while(iter.hasNext()) {
				SelectionKey sk = iter.next();
				// 删除已选的key，以防重复处理
				iter.remove();
				if(sk.isAcceptable()) {
					//客户请求连接事件
					ServerSocketChannel ssc = (ServerSocketChannel) sk.channel();
					//获得和客户端连接的通道
					SocketChannel sc = ssc.accept();
					//设置成非阻塞
					sc.configureBlocking(false);
					//在这里可以给客户端发送信息
					sc.write(ByteBuffer.wrap(new String("success").getBytes()));
					// 在和客户端连接成功之后，为了可以接收到客户端的信息，需要给通道设置读权限
					sc.register(this.selector, SelectionKey.OP_READ);
				} else if(sk.isReadable()) {
					//服务器可读取消息：得到事件发生的Socket通道
					SocketChannel sc = (SocketChannel) sk.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					sc.read(buffer);
					byte[] datas = buffer.array();
					String message = new String(datas);
					System.out.println("ssc received: "+message);
					sc.write(ByteBuffer.wrap("received".getBytes()));
				} else {
					System.out.println("ssc other event: "+event);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		NioSimpleServer nss = new NioSimpleServer();
		nss.init(9018);
		nss.listen();
	}

}
