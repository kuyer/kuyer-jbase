package io.github.kuyer.jbase.sort.data;

/**
 * 二叉查找树
 * @author rory.zhang
 * @param <T>
 */
public class BsTree<T extends Comparable<T>> {
	
	private BsNode<T> root;//根节点
	
	public BsTree() {
		this.root = null;//默认根节点为null
	}
	
	public void add(T key) {
		add(this.root, key);
	}
	
	private void add(BsNode<T> node, T key) {
		boolean isEqual = false;
		BsNode<T> tode = null;//key节点的父节点
		while(null != node) {//查找key节点的父节点
			tode = node;
			int cmp = key.compareTo(node.key);
			if(cmp < 0) {//key值小，到左节点查找父节点
				node = node.left;
			} else if(cmp > 0) {//key值大，到右节点查找父节点
				node = node.right;
			} else {//key值相等的情况，退出查找父节点
				isEqual = true;
				node = null;
			}
		}
		if(isEqual) {
			return;//key值相等，不做插入操作
		}
		BsNode<T> kode = new BsNode<>(key, tode, null, null);// key节点的两个子节点为null
		if(null == tode) {//父节点为null，则为根节点
			this.root = kode;
		} else {//父节点不为空
			int cmp = key.compareTo(tode.key);
			if(cmp < 0) {//key比父节点值小，key放在父节点的左节点
				tode.left = kode;
			} else {//key比父节点值大，key放在父节点的右节点
				tode.right = kode;
			}
		}
	}
	
	public void remove(T key) {
		//TODO 删除
	}
	
	public void destroy() {
		//TODO 销毁二叉树
	}
	
	public BsNode<T> search(T key) {
		BsNode<T> tode = null;
		BsNode<T> node = this.root;
		while(null != node) {
			int cmp = key.compareTo(node.key);
			if(cmp < 0) {
				node = node.left;
			} else if(cmp > 0) {
				node = node.right;
			} else {
				tode = node;
				node = null;
			}
		}
		return tode;
	}
	
	public BsNode<T> get(T key) {
		return get(this.root, key);
	}
	
	private BsNode<T> get(BsNode<T> node, T key) {
		if(null == node) {
			return node;
		}
		int cmp = key.compareTo(node.key);
		if(cmp < 0) {//key小，到左节点找
			return get(node.left, key);
		} else if(cmp > 0) {//key大，到右节点找
			return get(node.right, key);
		}
		return node;//key相等，找到节点
	}
	
	public BsNode<T> getMax() {
		if(null == this.root) {
			return null;
		}
		BsNode<T> node = this.root;
		while(null != node.right) {
			node = node.right;
		}
		return node;
	}
	
	public BsNode<T> getMin() {
		if(null == this.root) {
			return null;
		}
		BsNode<T> node = this.root;
		while(null != node.left) {
			node = node.left;
		}
		return node;
		
	}
	
	public void print() {
		print(this.root, 0);
	}
	private void print(BsNode<T> node, int direct) {
		if(node != null) {
			System.out.println(node.key + " : " + direct);
			print(node.left, -1);
			print(node.right, 1);
		}
	}
	
	public class BsNode<E extends Comparable<E>> {
		E key;//节点值
		BsNode<E> parent;//父节点
		BsNode<E> left;//左节点，值比key小
		BsNode<E> right;//右节点，值比key大
		
		public BsNode(E key, BsNode<E> parent, BsNode<E> left, BsNode<E> right) {
			this.key = key;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return "{key:"+key+"}";
		}
	}
	
	public static void main(String[] args) {
		int[] datas = {21, 18, 4, 39, 18, 12, 32};
		BsTree<Integer> bstree = new BsTree<>();
		for(int data : datas) {
			bstree.add(data);
		}
		bstree.print();
		System.out.println("#############################");
		System.out.println("min: "+bstree.getMin());
		System.out.println("max: "+bstree.getMax());
		System.out.println("get: "+bstree.get(32));
		System.out.println("search: "+bstree.search(32));
	}

}
