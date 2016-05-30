package io.github.kuyer.jbase.sort;

/**
 * 邻接矩阵无向图（Matrix Undirected Graph）
 * @author Rory.Zhang
 */
public class MatrixUDG {
	
	private char[] mVexs;//顶点集合
	private int[][] mMatrix;//邻接矩阵
	
	public MatrixUDG(char[] vexs, char[][] edges) {
		// 初始化顶点数和边数
		int vlen = vexs.length;
		int elen = edges.length;
		
		// 初始化顶点
		mVexs = new char[vlen];
		for(int i=0; i<mVexs.length; i++) {
			mVexs[i] = vexs[i];
		}
		//初始化边
		mMatrix = new int[vlen][vlen];
		for(int i=0; i<elen; i++) {
			int p1 = getPosition(edges[i][0]);
			int p2 = getPosition(edges[i][1]);
			mMatrix[p1][p2] = 1;
			mMatrix[p2][p1] = 1;
		}
	}

	private int getPosition(char c) {
		for(int i=0; i<mVexs.length; i++) {
			if(mVexs[i] == c) {
				return i;
			}
		}
		return -1;
	}
	
	private void dfs() {
		boolean[] visited = new boolean[mVexs.length];
		// 初始化所有顶点都没有被访问
		for(int i=0; i<mVexs.length; i++) {
			visited[i] = false;
		}
		System.out.printf("dfs: ");
		for(int i=0; i<mVexs.length; i++) {
			if(!visited[i]) {
				dfs(i, visited);
			}
		}
		System.out.printf("\n");
	}
	
	private void dfs(int i, boolean[] visited) {
		visited[i] = true;
		System.out.printf("%c ", mVexs[i]);
		for(int j=firstVertex(i); j>=0; j=nextVertex(i, j)) {
			if(!visited[j]) {
				dfs(j, visited);
			}
		}
	}

	private int firstVertex(int v) {
		if(v<0 || v>(mVexs.length-1)) {
			return -1;
		}
		for(int i=0; i<mVexs.length; i++) {
			if(mMatrix[v][i] == 1) {
				return i;
			}
		}
		return -1;
	}
	
	private int nextVertex(int v, int w) {
		if(v<0 || v>(mVexs.length-1) || w<0 || w>(mVexs.length-1)) {
			return -1;
		}
		for(int i=w+1; i<mVexs.length; i++) {
			if(mMatrix[v][i] == 1) {
				return i;
			}
		}
		return -1;
	}

	private void print() {
		System.out.printf("Matrix Graph: \n");
		for(int i=0; i<mVexs.length; i++) {
			for(int j=0; j<mVexs.length; j++) {
				System.out.printf("%d", mMatrix[i][j]);
			}
			System.out.printf("\n");
		}
	}

	public static void main(String[] args) {
		char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		char[][] edges = new char[][] {
				{'A', 'C'},
				{'A', 'D'},
				{'A', 'F'},
				{'B', 'C'},
				{'C', 'D'},
				{'E', 'G'},
				{'F', 'G'}
			};
		MatrixUDG mudg = new MatrixUDG(vexs, edges);
		mudg.print();//打印图
		mudg.dfs();//深度优先遍历（Depth First Search）
	}

}
