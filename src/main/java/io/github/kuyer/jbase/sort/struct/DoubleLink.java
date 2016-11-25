package io.github.kuyer.jbase.sort.struct;

/**
 * 双向链表，Java自带参考：{@link java.util.LinkedList}
 * @author rory.zhang
 * @param <T>
 */
public class DoubleLink<T> {
	
	/** 链表表头 **/
	private Node<T> head;
	/** 节点个数 **/
	private int count;
	
	public DoubleLink() {
		this.head = new Node<>(null, null, null);
		this.head.prev = this.head.next = this.head;
		this.count = 0;
	}
	
	/**
	 * 返回节点个数
	 * @return
	 */
	public int size() {
		return this.count;
	}
	
	/**
	 * 链表是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return this.count<=0;
	}
	
	/**
	 * 获取第index位置的元素
	 * @param index
	 * @return
	 */
	public T get(int index) {
		return getNode(index).value;
	}
	
	/**
	 * 获取第一个位置的元素
	 * @return
	 */
	public T getFirst() {
		return getNode(0).value;
	}
	
	/**
	 * 获取最后一个位置的元素
	 * @return
	 */
	public T getLast() {
		return getNode(this.count-1).value;
	}
	
	/**
	 * 获取第index位置的节点
	 * @param index
	 * @return
	 */
	private Node<T> getNode(int index) {
		if(index<=-1 || index>=this.count) {
			throw new IndexOutOfBoundsException();
		}
		//正向查找
		if(index <= this.count/2) {
			Node<T> node = this.head.next;
			for(int i=0; i<index; i++) {
				node = node.next;
			}
			return node;
		}
		// 反向查找
		Node<T> node = this.head.prev;
		index = this.count-index-1;
		for(int i=0; i<index; i++) {
			node = node.prev;
		}
		return node;
	}
	
	/**
	 * 在index添加value元素
	 * @param index
	 * @param value
	 */
	public void insert(int index, T value) {
		if(index <= 0) {
			Node<T> node = new Node<>(value, this.head, this.head.next);
			this.head.next.prev = node;
			this.head.next = node;
			this.count ++;
			return;
		}
		Node<T> inode = getNode(index);
		Node<T> tnode = new Node<>(value, inode.prev, inode);
		inode.prev.next = tnode;
		inode.prev = tnode;
		this.count ++;
		return;
	}
	
	/**
	 * 把value添加到第一个元素
	 * @param value
	 */
	public void prepend(T value) {
		insert(0, value);
	}
	
	/**
	 * 把value添加到最后一个元素
	 * @param value
	 */
	public void append(T value) {
		Node<T> node = new Node<>(value, this.head.prev, this.head);
		this.head.prev.next = node;
		this.head.prev = node;
		this.count ++;
	}
	
	/**
	 * 删除第index个节点
	 * @param index
	 */
	public void delete(int index) {
		Node<T> node = getNode(index);
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node = null;
		this.count --;
	}
	
	/**
	 * 删除第一个节点
	 */
	public void deleteFirst() {
		delete(0);
	}
	
	/**
	 * 删除最后一个节点
	 */
	public void deleteLast() {
		delete(this.count - 1);
	}
	
	/**
	 * 打印所有元素
	 */
	public void print() {
		Node<T> node = this.head.next;
		for(int i=0; i<this.count; i++) {
			System.out.print(node.value+" ");
			node = node.next;
		}
		System.out.println();
	}
	
	/**
	 * 节点结构体
	 * @author rory.zhang
	 * @param <N>
	 */
	private class Node<N> {
		public Node<N> prev;
		public Node<N> next;
		public N value;
		
		public Node(N value, Node<N> prev, Node<N> next) {
			this.value = value;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public static void main(String[] args) {
		DoubleLink<String> dl = new DoubleLink<>();
		dl.append("rory");
		dl.prepend("coolrl");
		dl.append("zhang");
		dl.print();
		System.out.println("first: "+dl.get(1)+" "+dl.getFirst());
		dl.deleteFirst();
		dl.print();
		System.out.println("last: "+dl.get(1)+" "+dl.getLast());
	}

}
