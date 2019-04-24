import java.util.Scanner;

public class Fraction {
	Scanner sc = new Scanner(System.in);
	public int numerator;
	public int denominator;

	public void add(Fraction operand) {
		// 분모, 분자 입력
		System.out.println("더하기 연산을 합니다.");
		System.out.println("분자를 입력하세요.");
		operand.numerator = sc.nextInt();
		System.out.println("분모를 입력하세요.");
		operand.denominator = sc.nextInt();
		this.denominator += operand.denominator;
		this.numerator += operand.numerator;
		irreFrac();
		System.out.println(this);
	}

	public void sub(Fraction operand) {
		// 분모, 분자 입력
		System.out.println("빼기 연산을 합니다.");
		System.out.println("분자를 입력하세요.");
		operand.numerator = sc.nextInt();
		System.out.println("분모를 입력하세요.");
		operand.denominator = sc.nextInt();

		if (this.numerator * operand.denominator > this.denominator * operand.numerator)
			this.numerator = this.numerator * operand.denominator - this.denominator * operand.numerator;// 분자를 만든다.
		else {
			System.out.println("빼기 연산을 할 수 없습니다. : 현재 변수가 더 작음.");
			return;
		}
		this.denominator *= operand.denominator;// 분모를 만든다.

		irreFrac();
		System.out.println(this);
	}

	public void mul(Fraction operand) {
		// 분모, 분자 입력
		System.out.println("곱하기 연산을 합니다.");
		System.out.println("분자를 입력하세요.");
		operand.numerator = sc.nextInt();
		System.out.println("분모를 입력하세요.");
		operand.denominator = sc.nextInt();
		this.denominator *= operand.denominator;
		this.numerator *= operand.numerator;
		irreFrac();
		System.out.println(this);
	}

	public void div(Fraction operand) {
		// 분모, 분자 입력
		System.out.println("나누기 연산을 합니다.");
		System.out.println("분자를 입력하세요.");
		operand.numerator = sc.nextInt();
		System.out.println("분모를 입력하세요.");
		operand.denominator = sc.nextInt();
		// 분수의 나눗셈 연산은 피연산자의 분모, 분자를 뒤집은 후 곱한다.
		this.denominator *= operand.numerator;
		this.numerator *= operand.denominator;
		irreFrac();
		System.out.println(this);
	}

	public void irreFrac() {
		int div;
		System.out.println(this + "\n기약변수로 변환합니다...");
		if (numerator > denominator)
			div = denominator;// 작은 애로 나눈다.
		else
			div = numerator;
		for (int i = div; i > 0; i--) {
			if (numerator % i == 0 && denominator % i == 0) {// 둘 다 나누어 떨어지면
				numerator /= i;
				denominator /= i;// 나누어서 저장
			}
		}
	}

	@Override
	public String toString() {
		return "현재 분수는 " + this.numerator + "/" + this.denominator + "입니다.";
	}

	public static void main(String[] args) {
		Fraction f = new Fraction();
		f.numerator = 2;
		f.denominator = 5;
		Fraction oper = new Fraction();
		f.add(oper);
		f.sub(oper);
		f.mul(oper);
		f.div(oper);
	}
}
