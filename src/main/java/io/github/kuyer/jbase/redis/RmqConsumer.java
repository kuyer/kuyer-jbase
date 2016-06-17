package io.github.kuyer.jbase.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class RmqConsumer {

	public static void main(String[] args) {
		Jedis jedis = new Jedis("192.168.56.110", 6379);
		
		jedis.subscribe(new JedisPubSub() {
			@Override
			public void onMessage(String channel, String message) {
				System.out.println(channel+": "+message);
			}}, "rmqchannel");
		
		jedis.close();
	}

}
