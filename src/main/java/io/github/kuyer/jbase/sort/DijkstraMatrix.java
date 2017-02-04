package io.github.kuyer.jbase.sort;

public class DijkstraMatrix {
	
	private static final int INF = Integer.MAX_VALUE;
	
	/** 边的数量 **/
	private int mEdgNum;
	/** 顶点集合 **/
	private char[] mVexs;
	/** 邻接矩阵 **/
	private int[][] mMatrix;

	public DijkstraMatrix(char[] vexs, int[][] matrix) {
		// 初始顶点
		int vlen = vexs.length;
		this.mVexs = new char[vlen];
		for(int i=0; i<vlen; i++) {
			this.mVexs[i] = vexs[i];
		}
		// 初始化边
		this.mMatrix = new int[vlen][vlen];
		for(int i=0; i<vlen; i++) {
			for(int j=0; j<vlen; j++) {
				this.mMatrix[i][j] = matrix[i][j];
			}
		}
		//统计边数
		this.mEdgNum = 0;
		for(int i=0; i<vlen; i++) {
			for(int j=0; j<vlen; j++) {
				if(this.mMatrix[i][j] != INF) {
					this.mEdgNum ++;
				}
			}
		}
	}
	
	public void print() {
		System.out.println("Martix Graph:");
		for(int i=0; i<this.mVexs.length; i++) {
			for(int j=0; j<this.mVexs.length; j++) {
				System.out.printf("%10d ", this.mMatrix[i][j]);
			}
			System.out.println();
		}
	}
	
	private void dijkstra(int vs) {
		boolean[] flags = new boolean[this.mVexs.length];
		int[] prev = new int[this.mVexs.length];
		int[] dist = new int[this.mVexs.length];
		for(int i=0; i<this.mVexs.length; i++) {
			flags[i] = false;
			prev[i] = 0;
			dist[i] = this.mMatrix[vs][i];
		}
		
		flags[vs] = true;
		dist[vs] = 0;
		
		int k = 0;
		for(int i=1; i<this.mVexs.length; i++) {
			
			int min = INF;
			for(int j=0; j<this.mVexs.length; j++) {
				if(flags[j]==false && dist[j]<min) {
					min = dist[j];
					k = j;
				}
			}
			flags[k] = true;
			
			for(int j=0; j<this.mVexs.length; j++) {
				int tmp = (this.mMatrix[k][j]==INF ? INF : (min+this.mMatrix[k][j]));
				if(flags[j]==false && tmp<dist[j]) {
					dist[j] = tmp;
					prev[j] = k;
				}
			}
		}
		
		System.out.printf("Dijkstra(%c): \n", this.mVexs[vs]);
		for(int i=0; i<this.mVexs.length; i++) {
			System.out.printf("	shortDijkstra(%c, %c)=%d\n", this.mVexs[vs], this.mVexs[i], dist[i]);
		}
	}

	public static void main(String[] args) {
		char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
		int[][] matrix = {
				/**   A    B    C    D    E    F    G   **/
				/*A*/{  0,  12, INF, INF, INF,  16,  14},
				/*B*/{ 12,   0,  10, INF, INF,   7, INF},
				/*C*/{INF,  10,   0,   3,   5,   6, INF},
				/*D*/{INF, INF,   3,   0,   4, INF, INF},
				/*E*/{INF, INF,   5,   4,   0,   2,   8},
				/*F*/{ 16,   7,   6, INF,   2,   0,   9},
				/*G*/{ 14, INF, INF, INF,   8,   9,   0}};
		
		DijkstraMatrix dm = new DijkstraMatrix(vexs, matrix);
		dm.print();
		System.out.println("#################################");
		dm.dijkstra(4);
	}

}
