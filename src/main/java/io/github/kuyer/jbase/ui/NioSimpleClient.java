package io.github.kuyer.jbase.ui;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

//http://my.oschina.net/fhd/blog/378849
public class NioSimpleClient {
	
	private int number = 0;
	private Selector selector;
	
	private void init(String host, int port) throws Exception {
		// 获得一个Socket通道
		SocketChannel sc = SocketChannel.open();
		// 设置通道为非阻塞
		sc.configureBlocking(false);
		// 客户端连接服务器，其实方法执行并没有实现连接，需要在listen()方法中
        // 调用channel.finishConnect()才能完成连接
		sc.connect(new InetSocketAddress(host, port));
		// 获得一个通道管理器
		this.selector = Selector.open();
		// 将通道管理器和该通道绑定，并为该通道注册SelectionKey.OP_CONNECT事件
		sc.register(this.selector, SelectionKey.OP_CONNECT);
	}
	
	private void listen() throws Exception {
		// 轮询访问selector
		while(true) {
			// 选择一组可以进行I/O操作的事件，放在selector中，客户端的该方法不会阻塞，
            // 这里和服务端的方法不一样，查看api注释可以知道，当至少一个通道被选中时，
            // selector的wakeup方法被调用，方法返回，而对于客户端来说，通道一直是被选中的
			int event = this.selector.select();
			//获得selector中选中的项的迭代器
			Iterator<SelectionKey> iter = this.selector.selectedKeys().iterator();
			while(iter.hasNext()) {
				SelectionKey sk = iter.next();
				// 删除已选的key，以防重复处理
				iter.remove();
				if(sk.isConnectable()) {
					// 连接事件
					SocketChannel sc = (SocketChannel) sk.channel();
					// 如果正在连接，则完成连接
					if(sc.isConnectionPending()) {
						sc.finishConnect();
					}
					// 设置成非阻塞
					sc.configureBlocking(false);
					// 在这里可以给服务端发送信息
					sc.write(ByteBuffer.wrap(new String("hello, this is client.").getBytes()));
					// 在和服务端连接成功之后，为了可以接收到服务端的信息，需要给通道设置读的权限
					sc.register(this.selector, SelectionKey.OP_READ);
				} else if(sk.isReadable()) {
					SocketChannel sc = (SocketChannel) sk.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					sc.read(buffer);
					byte[] datas = buffer.array();
					String message = new String(datas);
					System.out.println("nsc received: "+message);
					number ++;
					if(number>20) {
						continue;
					}
					sc.write(ByteBuffer.wrap(("this is my "+number+" message.").getBytes()));
				} else {
					System.out.println("sc other event: "+event);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		NioSimpleClient nsc = new NioSimpleClient();
		nsc.init("127.0.0.1", 9018);
		nsc.listen();
	}

}
