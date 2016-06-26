package io.github.kuyer.jbase.sort.tree;

/**
 * AVL树 (高度平衡的二叉树)
 * @author Rory.Zhang
 */
public class AVLTree<T extends Comparable<T>> {
	
	private AVLNode<T> root;
	
	public class AVLNode<N extends Comparable<N>> {
		
		N key;
		int height;
		AVLNode<N> left;
		AVLNode<N> right;
		
		public AVLNode(N key, AVLNode<N> left, AVLNode<N> right) {
			this.key = key;
			this.height = 0;
			this.left = left;
			this.right = right;
		}
		
	}
	
	public AVLTree() {
		this.root = null;
	}
	
}
