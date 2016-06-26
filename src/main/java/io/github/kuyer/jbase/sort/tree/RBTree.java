package io.github.kuyer.jbase.sort.tree;

/**
 * 红黑树
 * @author Rory.Zhang
 */
public class RBTree<T extends Comparable<T>> {
	
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	
	private RBNode<T> root;//根节点
	
	public class RBNode<N extends Comparable<N>> {
		boolean color;//颜色
		N key;//键
		RBNode<N> left;//左节点
		RBNode<N> right;//右节点
		RBNode<N> parent;//父节点
		
		public RBNode(N key, boolean color, RBNode<N> parent, RBNode<N> left, RBNode<N> right) {
			this.key = key;
			this.color = color;
			this.parent = parent;
			this.left =left;
			this.right = right;
		}
		
		public N getKey() {
			return this.key;
		}
		
		@Override
		public String toString() {
			return this.key + (this.color==RED?"(R)":"(B)");
		}
		
	}
	
	public RBTree() {
		this.root = null;
	}
	
	private RBNode<T> parentOf(RBNode<T> node) {
		return node!=null ? node.parent : null;
	}
	
	private boolean colorOf(RBNode<T> node) {
		return node!=null ? node.color : BLACK;
	}

}
