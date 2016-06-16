package io.github.kuyer.jbase.redis;

import redis.clients.jedis.Jedis;

public class RedisDemo {
	
	private static Jedis connect(String host, int port) {
		return new Jedis(host, port);
	}
	
	private static void close(Jedis jedis) {
		jedis.close();
	}

	public static void main(String[] args) {
		Jedis jedis = connect("192.168.56.110", 6379);
		//jedis.auth("111111");
		if(!jedis.exists("name")) {
			System.out.println(jedis.set("name", "rory"));
		}
		System.out.println(jedis.get("name"));
		close(jedis);
	}

}
