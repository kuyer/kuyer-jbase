package io.github.kuyer.jbase.redis;

import redis.clients.jedis.Jedis;

public class RmqPublisher {

	public static void main(String[] args) throws Exception {
		Jedis jedis = new Jedis("192.168.56.110", 6379);
		
		jedis.publish("rmqchannel", "rmq v-0.0.1 was released.");
		
		jedis.close();
	}

}
