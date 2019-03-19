
public class SimpleCal2 {
	static int resultNumber;//�޸𸮸� �Ƴ���, �����͸� ������ �� �ִ�.

	/**
	 * num������ ���丮���� ���Ѵ�.
	 * @param num ���丮�� ������ ���� �ǿ�����
	 * @return num������ ���丮�� ���� ��� ���� ��ȯ
	 */
	public static int factorial(int num) {
		resultNumber=1;
		for (int i=num;i>=1;i--) {
			resultNumber*=i;//���丮�� ����
		}
		return resultNumber;
	}
	
	/**
	 * num1�� num2���� ���� ���Ѵ�.
	 * @param num1 �ŵ����� ������ ���� ù��° �ǿ�����
	 * @param num2 �ŵ����� ������ ���� �ι�° �ǿ�����
	 * @return num1�� num2���� �� ��� ���� ��ȯ
	 */
	public static int power(int num1, int num2) {
		resultNumber=1;
		for (int i=1;i<=num2;i++) {
			resultNumber*=num1;//�ŵ����� ����
		}
		return resultNumber;
	}
	public static void main(String[] args) {
		//�ǽ� ����[2/2]
		System.out.println("�ǽ� ���� [2/2]\n");

		int facNum=factorial(5);
		int powNum=power(3, 5);
		
		System.out.println("Factorial Result: "+facNum);
		System.out.println("Power Result: "+powNum);
	}
}
