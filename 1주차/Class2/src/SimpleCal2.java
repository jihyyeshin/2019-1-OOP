
public class SimpleCal2 {
	static int resultNumber;//메모리를 아끼고, 데이터를 공유할 수 있다.

	/**
	 * num까지의 팩토리얼을 구한다.
	 * @param num 팩토리얼 연산을 위한 피연산자
	 * @return num까지의 팩토리얼 연산 결과 값을 반환
	 */
	public static int factorial(int num) {
		resultNumber=1;
		for (int i=num;i>=1;i--) {
			resultNumber*=i;//팩토리얼 연산
		}
		return resultNumber;
	}
	
	/**
	 * num1의 num2제곱 값을 구한다.
	 * @param num1 거듭제곱 연산을 위한 첫번째 피연산자
	 * @param num2 거듭제곱 연산을 위한 두번째 피연산자
	 * @return num1의 num2제곱 한 결과 값을 반환
	 */
	public static int power(int num1, int num2) {
		resultNumber=1;
		for (int i=1;i<=num2;i++) {
			resultNumber*=num1;//거듭제곱 연산
		}
		return resultNumber;
	}
	public static void main(String[] args) {
		//실습 과제[2/2]
		System.out.println("실습 과제 [2/2]\n");

		int facNum=factorial(5);
		int powNum=power(3, 5);
		
		System.out.println("Factorial Result: "+facNum);
		System.out.println("Power Result: "+powNum);
	}
}
