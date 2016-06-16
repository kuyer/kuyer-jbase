package io.github.kuyer.jbase.lang.mbean;

import javax.management.Notification;
import javax.management.NotificationListener;

public class ServerListener implements NotificationListener {

	@Override
	public void handleNotification(Notification notification, Object handback) {
		System.out.println("sequence: "+notification.getSequenceNumber());
		System.out.println("type: "+notification.getType());
		System.out.println("message: "+notification.getMessage());
		System.out.println("source: "+notification.getSource());
		System.out.println("timestamp: "+notification.getTimeStamp());
		System.out.println("userdata: "+notification.getUserData());
		System.out.println("handback: "+handback);
	}

}
