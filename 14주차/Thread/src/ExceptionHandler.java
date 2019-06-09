
public class ExceptionHandler {
	void rabbit() {
		int num=10, den=0;
		den=num/den;
		System.out.println("The end of rabbit method");
	}
	void turtle() {
		rabbit();
		System.out.println("The end of turtle method");
	}
	public static void main(String[] args) {
		ExceptionHandler p=new ExceptionHandler();
		try {
			p.turtle();
		}catch(ArithmeticException e){
			System.out.println("Arithmetic");
		}
		System.out.println("the end of the main method");
	}
}
