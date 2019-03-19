
public class Trans {
	int[][] transMatrix;

	public void transpose(int matrix[][]) {
		this.transMatrix = new int[matrix[0].length][matrix.length];// ��ȯ�� ����� ����� ���
		int i, j;
		System.out.println("����� ��ȯ �����");
		for (i = 0; i < matrix[0].length; i++) {
			for (j = 0; j < matrix.length; j++) {
				// ��ȯ �� ����
				transMatrix[i][j] = matrix[j][i];
				System.out.print(transMatrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void multiple(int matrix[][]) {
		int i, j, k, mul = 0;
		System.out.println("����� ���� �����");
		for (i = 0; i < matrix.length; i++) {// 2
			for (j = 0; j < transMatrix[0].length; j++) {// 2
				for (k = 0; k < matrix[0].length; k++) {// 3
					//������ ����� mul�� �ӽ� ����
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
				matrix[i][j] = i * matrix[i].length + j + 1;// �ʱ� �� ����
			}
		}
		/* �ǽ� ���� [1/1] */
		tr.transpose(matrix);
		/* �ǽ� ����[1/2] */
		tr.multiple(matrix);
	}
}
