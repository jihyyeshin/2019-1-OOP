
public class matrix {
	public static void main(String[] args) {
		int m = 2;
		int n = 3;

		int[][] matrix = new int[m][n];

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = i * matrix[i].length + j + 1;
			}
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				System.out.print(" " + matrix[i][j]);
			}
			System.out.println();

		}

	}
}
