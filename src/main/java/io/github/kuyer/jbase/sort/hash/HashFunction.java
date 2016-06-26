package io.github.kuyer.jbase.sort.hash;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.google.common.hash.Hashing;

/** Hash算法 **/
public class HashFunction {
	
	/** MurmurHash算法，是非加密Hash算法，性能高，碰撞率低 **/
	public Long hash(String key) {
		if(null == key) {
			return null;
		}
		key = key.trim();
		ByteBuffer buf = ByteBuffer.wrap(key.getBytes());
		int seed = 0x1234ABCD;
		
		ByteOrder bo = buf.order();
		buf.order(ByteOrder.LITTLE_ENDIAN);
		
		long m = 0xc6a4a7935bd1e995L;
		int r = 47;
		
		long h = seed ^ (buf.remaining()*m);
		long k;
		while(buf.remaining() >= 8) {
			k = buf.getLong();
			k *= m;
			k ^= k >>> r;
			k *= m;
			h ^= k;
			h *= m;
		}
		if(buf.remaining() > 0) {
			ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);
			finish.put(buf).rewind();
			h ^= finish.getLong();
			h *= m;
		}
		
		h ^= h >>> r;
		h *= m;
		h ^= h >>> r;
		buf.order(bo);
		return h;
	}
	
	public static void main(String[] args) {
		System.out.println(new HashFunction().hash("x4"));
		com.google.common.hash.HashFunction hf = Hashing.murmur3_128();
		System.out.println(hf.hashString("x4").asLong());
	}

}
