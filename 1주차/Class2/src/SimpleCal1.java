
public class SimpleCal1 {
	static int resultNumber;//�޸𸮸� �Ƴ���, �����͸� ������ �� �ִ�.
	 
	/**
	 * 1���� num������ ���ڸ� ���Ѵ�.
	 * @param num ���ϱ� ������ ���� �ǿ�����
	 * @return 1���� num������ ���� ��� ���� ��ȯ
	 */
	public static int addNumber(int num) {
		resultNumber=0;
		for (int i=1;i<=num;i++) {
			resultNumber+=i;//���� ����
		}
		return resultNumber;
	}
	
	/**
	 * 1���� num������ ���ڸ� ����.
	 * @param num ���� ������ ���� �ǿ�����
	 * @return 1���� num������ ���� ��� ���� ��ȯ
	 */
	public static int subNumber(int num) {
		resultNumber=1;
		for (int i=2;i<=num;i++) {
			resultNumber-=i;//���� ����
		}
		return resultNumber;
	}
	
	/**
	 * 1���� num������ ���ڸ� ���Ѵ�.
	 * @param num ���ϱ� ������ ���� �ǿ�����
	 * @return 1���� num������ ���� ��� ���� ��ȯ
	 */
	public static int mulNumber(int num) {
		resultNumber=1;
		for (int i=1;i<=num;i++) {
			resultNumber*=i;//���� ����
		}
		return resultNumber;
	}
	
	/**
	 * 1���� num������ ���ڸ� ������.
	 * @param num ������ ������ ���� �ǿ�����
	 * @return 1���� num������ ������ ��� ���� ��ȯ
	 */
	public static int divNumber(int num) {
		resultNumber=1;
		for (int i=1;i<=num;i++) {
			resultNumber/=i;//������ ����
		}
		return resultNumber;
	}
	public static void main(String[] args) {
		//�ǽ� ���� [1/2]
		System.out.println("�ǽ� ���� [1/2]\n");
		
		int addNum=addNumber(5);
		int subNum=subNumber(5);
		int mulNum=mulNumber(5);
		int divNum=divNumber(5);

		System.out.println("Add Result: "+addNum);
		System.out.println("Sub Result: "+subNum);
		System.out.println("Mul Result: "+mulNum);
		System.out.println("Div Result: "+divNum);
	}
}
