package io.github.kuyer.jbase.sort.tree;

/** 
 * 二叉树
 * @author Rory.Zhang
 */
public class BinaryTree<T extends Comparable<T>> {
	
	/** 二叉树节点 **/
	public class Node<N extends Comparable<N>> {
		
		/** 节点键值 **/
		N key;
		/** 左节点 **/
		Node<N> left;
		/** 右节点 **/
		Node<N> right;
		/** 父节点 **/
		Node<N> parent;
		
		public Node(N key, Node<N> parent, Node<N> left, Node<N> right) {
			this.key = key;
			this.parent = parent;
			this.left = left;
			this.right = right;
		}
		
		public N getKey() {
			return this.key;
		}
		
		@Override
		public String toString() {
			return "node key: "+this.key;
		}
		
	}
	
	/** 根节点 **/
	private Node<T> root;
	
	public BinaryTree() {
		this.root = null;
	}
	
	/** 前序遍历 **/
	public void preOrder() {
		preOrder(this.root);
	}
	private void preOrder(Node<T> node) {
		if(null != node) {
			System.out.print(node.key+" ");
			preOrder(node.left);
			preOrder(node.right);
		}
	}
	
	/** 中序遍历 **/
	public void inOrder() {
		inOrder(this.root);
	}
	private void inOrder(Node<T> node) {
		if(null != node) {
			inOrder(node.left);
			System.out.print(node.key+" ");
			inOrder(node.right);
		}
	}
	
	/** 后序遍历 **/
	public void postOrder() {
		postOrder(this.root);
	}
	private void postOrder(Node<T> node) {
		if(null != node) {
			postOrder(node.left);
			postOrder(node.right);
			System.out.print(node.key+" ");
		}
	}
	
	/** 递归搜索二叉树 **/
	public Node<T> search(T key) {
		return search(this.root, key);
	}
	private Node<T> search(Node<T> node, T key) {
		if(null == node) {
			return node;
		}
		int cmp = key.compareTo(node.key);
		if(cmp < 0) {
			return search(node.left, key);
		} else if(cmp > 0) {
			return search(node.right, key);
		} else {
			return node;
		}
	}
	
	/** 查询二叉树，非递归方式 **/
	public Node<T> query(T key) {
		return query(this.root, key);
	}
	private Node<T> query(Node<T> node, T key) {
		while(node != null) {
			int cmp = key.compareTo(node.key);
			if(cmp < 0) {
				node = node.left;
			} else if(cmp > 0) {
				node = node.right;
			} else {
				return node;
			}
		}
		return node;
	}
	
	/** 查找最小节点 **/
	public T minimum() {
		Node<T> node = minimum(this.root);
		if(null != node) {
			return node.key;
		}
		return null;
	}
	private Node<T> minimum(Node<T> node) {
		if(null == node) {
			return null;
		}
		while(null != node.left) {
			node = node.left;
		}
		return node;
	}
	
	/** 查找最大节点 **/
	public T maximum() {
		Node<T> node = maximum(this.root);
		if(null != node) {
			return node.key;
		}
		return null;
	}
	private Node<T> maximum(Node<T> node) {
		if(null == node) {
			return null;
		}
		while(null != node.right) {
			node = node.right;
		}
		return node;
	}
	
	/** 查找node的后继节点。即：查找二叉树中数值大于该节点的最小节点 **/
	public Node<T> successor(Node<T> node) {
		if(null == node) {
			return null;
		}
		// 如果node存在右子节点，则node的后继节点为其右子节点最小节点
		if(null != node.right) {
			return minimum(node.right);
		}
		// 如果node没有右子节点，则：
		// 1. node是一个左子节点，则node的后继节点为它的父节点
		// 2. node是一个右子节点，则查找node的最低的父节点，并且该父节点要有左孩子，找到的这个最低的父节点就是node的后继节点
		Node<T> pnode = node.parent;
		while(null!=pnode && node==pnode.right) {
			node = pnode;
			pnode = pnode.parent;
		}
		return pnode;
	}
	
	/** 查找node的前驱节点 **/
	public Node<T> predecessor(Node<T> node) {
		if(null == node) {
			return null;
		}
		// 如果node存在左子节点，则node的前驱节点为其左子节点的最大节点
		if(null != node.left) {
			return maximum(node.left);
		}
		// 如果node没有左子节点，则：
		// 1. node是右子节点，则node的前驱节点为它的父节点
		// 2. node是左子节点，则查找node的最低父节点，并且该父节点要具有右子节点
		Node<T> pnode = node.parent;
		while(null!=pnode && node==pnode.left) {
			node = pnode;
			pnode = pnode.parent;
		}
		return pnode;
	}
	
	/** 插入节点 **/
	public void insert(T key) {
		Node<T> node = new Node<T>(key, null, null, null);
		insert(this, node);
	}
	private void insert(BinaryTree<T> tree, Node<T> node) {
		Node<T> temp = null;
		Node<T> root = tree.root;
		while(null != root) {
			temp = root;
			int cmp = node.key.compareTo(root.key);
			if(cmp < 0) {
				root = root.left;
			} else {
				root = root.right;
			}
		}
		
		node.parent = temp;
		if(null == temp) {
			tree.root = node;
		} else {
			int cmp = node.key.compareTo(temp.key);
			if(cmp < 0) {
				temp.left = node;
			} else {
				temp.right = node;
			}
		}
	}
	
	/** 删除节点 **/
	public void delete(T key) {
		Node<T> node = search(key);
		if(null != node) {
			Node<T> n = delete(this, node);
			if(null != n) {
				n = null;
			}
		}
	}
	private Node<T> delete(BinaryTree<T> btree, Node<T> node) {
		Node<T> x = null;
		Node<T> y = null;
		if(null==node.left || null==node.right) {
			y = node;
		} else {
			y = successor(node);
		}
		if(null != y.left) {
			x = y.left;
		} else {
			x = y.right;
		}
		if(null != x) {
			x.parent = y.parent;
		}
		if(y.parent == null) {
			btree.root = x;
		} else if(y == y.parent.left) {
			y.parent.left = x;
		} else {
			y.parent.right = x;
		}
		if(y != node) {
			node.key = y.key;
		}
		return y;
	}

	/** 打印二叉树 **/
	public void print() {
		print(this.root, 0, 0);
	}
	private void print(Node<T> node, int direction, int level) {
		if(null != node) {
			String direct = "root";
			if(direction == 1) {
				direct = "left";
			} else if(direction == 2) {
				direct = "right";
			}
			String parent = "";
			if(null != node.parent) {
				parent = node.parent.key.toString();
			}
			System.out.printf("key: %s; parent: %s; direct: %s; level: %d.\n", node.key.toString(), parent, direct, level);
			
			int next = level + 1;
			print(node.left, 1, next);
			print(node.right, 2, next);
		}
	}
	
	/** 清空二叉树 **/
	public void clear() {
		clear(this.root);
	}
	private void clear(Node<T> node) {
		if(null == node) {
			return;
		}
		if(null != node.left) {
			clear(node.left);
		}
		if(null != node.right) {
			clear(node.right);
		}
		node = null;
	}

	public static void main(String[] args) {
		BinaryTree<Integer> btree = new BinaryTree<Integer>();
		btree.insert(10);
		btree.insert(20);
		btree.insert(12);
		btree.insert(8);
		btree.insert(15);
		btree.insert(3);
		btree.insert(13);
		btree.delete(10);
		btree.print();
		System.out.println("max node: "+btree.maximum());
		System.out.println("min node: "+btree.minimum());
	}

}
