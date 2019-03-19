
public class Ex02 {
	public static void main(String[] args) {
		int i, j;
		int[][] matrix=new int[9][9];
		
		for (i = 0; i < matrix.length; i++) {
			for (j = 0; j < matrix[i].length; j++) {
				matrix[i][j]=(i+1)*(j+1);
			}
		}
		
		for(i=0;i<matrix.length;i++) {
			for(j=0;j<matrix[i].length;j++) {
				System.out.print("\t"+matrix[i][j]);
			}
			System.out.println();
		}
	}
}
