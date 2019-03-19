
public class Trans {
	int[][] transMatrix;

	public void transpose(int matrix[][]) {
		this.transMatrix = new int[matrix[0].length][matrix.length];// 변환의 결과가 저장될 행렬
		int i, j;
		System.out.println("행렬의 변환 결과는");
		for (i = 0; i < matrix[0].length; i++) {
			for (j = 0; j < matrix.length; j++) {
				// 변환 후 저장
				transMatrix[i][j] = matrix[j][i];
				System.out.print(transMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void multiple(int matrix[][]) {
		int i, j, k, mul = 0;
		System.out.println("행렬의 곱셈 결과는");
		for (i = 0; i < matrix.length; i++) {// 2
			for (j = 0; j < transMatrix[0].length; j++) {// 2
				for (k = 0; k < matrix[0].length; k++) {// 3
					//곱섬의 결과를 mul에 임시 저장
					mul += matrix[i][k] * transMatrix[k][j];
				}
				System.out.print(mul + " ");
				mul = 0;
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		int i, j;
		Trans tr = new Trans();
		int[][] matrix = new int[2][3];
		for (i = 0; i < matrix.length; i++) {
			for (j = 0; j < matrix[i].length; j++) {
				matrix[i][j] = i * matrix[i].length + j + 1;// 초기 값 저장
			}
		}
		/* 실습 과제 [1/1] */
		tr.transpose(matrix);
		/* 실습 과제[1/2] */
		tr.multiple(matrix);
	}
}
