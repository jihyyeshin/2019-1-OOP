import java.util.Scanner;

public class Fraction {
	Scanner sc = new Scanner(System.in);
	public int numerator;
	public int denominator;

	public void add(Fraction operand) {
		// �и�, ���� �Է�
		System.out.println("���ϱ� ������ �մϴ�.");
		System.out.println("���ڸ� �Է��ϼ���.");
		operand.numerator = sc.nextInt();
		System.out.println("�и� �Է��ϼ���.");
		operand.denominator = sc.nextInt();
		this.denominator += operand.denominator;
		this.numerator += operand.numerator;
		irreFrac();
		System.out.println(this);
	}

	public void sub(Fraction operand) {
		// �и�, ���� �Է�
		System.out.println("���� ������ �մϴ�.");
		System.out.println("���ڸ� �Է��ϼ���.");
		operand.numerator = sc.nextInt();
		System.out.println("�и� �Է��ϼ���.");
		operand.denominator = sc.nextInt();

		if (this.numerator * operand.denominator > this.denominator * operand.numerator)
			this.numerator = this.numerator * operand.denominator - this.denominator * operand.numerator;// ���ڸ� �����.
		else {
			System.out.println("���� ������ �� �� �����ϴ�. : ���� ������ �� ����.");
			return;
		}
		this.denominator *= operand.denominator;// �и� �����.

		irreFrac();
		System.out.println(this);
	}

	public void mul(Fraction operand) {
		// �и�, ���� �Է�
		System.out.println("���ϱ� ������ �մϴ�.");
		System.out.println("���ڸ� �Է��ϼ���.");
		operand.numerator = sc.nextInt();
		System.out.println("�и� �Է��ϼ���.");
		operand.denominator = sc.nextInt();
		this.denominator *= operand.denominator;
		this.numerator *= operand.numerator;
		irreFrac();
		System.out.println(this);
	}

	public void div(Fraction operand) {
		// �и�, ���� �Է�
		System.out.println("������ ������ �մϴ�.");
		System.out.println("���ڸ� �Է��ϼ���.");
		operand.numerator = sc.nextInt();
		System.out.println("�и� �Է��ϼ���.");
		operand.denominator = sc.nextInt();
		// �м��� ������ ������ �ǿ������� �и�, ���ڸ� ������ �� ���Ѵ�.
		this.denominator *= operand.numerator;
		this.numerator *= operand.denominator;
		irreFrac();
		System.out.println(this);
	}

	public void irreFrac() {
		int div;
		System.out.println(this + "\n��ຯ���� ��ȯ�մϴ�...");
		if (numerator > denominator)
			div = denominator;// ���� �ַ� ������.
		else
			div = numerator;
		for (int i = div; i > 0; i--) {
			if (numerator % i == 0 && denominator % i == 0) {// �� �� ������ ��������
				numerator /= i;
				denominator /= i;// ����� ����
			}
		}
	}

	@Override
	public String toString() {
		return "���� �м��� " + this.numerator + "/" + this.denominator + "�Դϴ�.";
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
