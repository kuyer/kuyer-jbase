package io.github.kuyer.jbase.redis;

import java.util.List;

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
		
		// list 操作
		String mylist = "mylist";
		jedis.del(mylist);
		jedis.lpush(mylist, "list1", "list2", "list3");
		jedis.lpush(mylist, "list4");
		jedis.rpush(mylist, "list5");
		jedis.lpop(mylist);
		List<String> mylistArr = jedis.lrange(mylist, 0l, -1l);
		for(String str : mylistArr) {
			System.out.print(str+" ");
		}
		System.out.println();
		
		close(jedis);
	}

}
