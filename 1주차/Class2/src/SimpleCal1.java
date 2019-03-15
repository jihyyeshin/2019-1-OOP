
public class SimpleCal1 {
	static int resultNumber;//메모리를 아끼고, 데이터를 공유할 수 있다.
	 
	/**
	 * 1부터 num까지의 숫자를 더한다.
	 * @param num 더하기 연산을 위한 피연산자
	 * @return 1부터 num까지의 덧셈 결과 값을 반환
	 */
	public static int addNumber(int num) {
		resultNumber=0;
		for (int i=1;i<=num;i++) {
			resultNumber+=i;//덧셈 연산
		}
		return resultNumber;
	}
	
	/**
	 * 1부터 num까지의 숫자를 뺀다.
	 * @param num 빼기 연산을 위한 피연산자
	 * @return 1에서 num까지의 뺄셈 결과 값을 반환
	 */
	public static int subNumber(int num) {
		resultNumber=1;
		for (int i=2;i<=num;i++) {
			resultNumber-=i;//뺄셈 연산
		}
		return resultNumber;
	}
	
	/**
	 * 1부터 num까지의 숫자를 곱한다.
	 * @param num 곱하기 연산을 위한 피연산자
	 * @return 1부터 num까지의 곱셈 결과 값을 반환
	 */
	public static int mulNumber(int num) {
		resultNumber=1;
		for (int i=1;i<=num;i++) {
			resultNumber*=i;//곱셈 연산
		}
		return resultNumber;
	}
	
	/**
	 * 1부터 num까지의 숫자를 나눈다.
	 * @param num 나누기 연산을 위한 피연산자
	 * @return 1부터 num까지의 나눗셈 결과 값을 반환
	 */
	public static int divNumber(int num) {
		resultNumber=1;
		for (int i=1;i<=num;i++) {
			resultNumber/=i;//나눗셈 연산
		}
		return resultNumber;
	}
	public static void main(String[] args) {
		//실습 과제 [1/2]
		System.out.println("실습 과제 [1/2]\n");
		
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
