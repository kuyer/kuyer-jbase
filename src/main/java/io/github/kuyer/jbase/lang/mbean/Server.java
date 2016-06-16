package io.github.kuyer.jbase.lang.mbean;

import java.util.concurrent.atomic.AtomicLong;

import javax.management.AttributeChangeNotification;
import javax.management.NotificationBroadcasterSupport;

public class Server extends NotificationBroadcasterSupport implements ServerMBean {

	private AtomicLong seq = new AtomicLong(1);
	private String address;
	
	@Override
	public void setAddress(String address) {
		String oldAddress = this.address;
		this.address = address;
		AttributeChangeNotification notification = new AttributeChangeNotification(this, 
				seq.getAndIncrement(), 
				System.currentTimeMillis(),
				AttributeChangeNotification.ATTRIBUTE_CHANGE,
				"Server Address Changed",
				"java.lang.String",
				oldAddress,
				this.address);
		super.sendNotification(notification);
	}

	@Override
	public String getAddress() {
		return address;
	}

}
