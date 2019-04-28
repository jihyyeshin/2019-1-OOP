
public class ArmNum {
	public static void main(String[] args) {
		int buf;
		for(int num=100;num<1000;num++) {
			buf=(num%10)*(num%10)*(num%10)+(num/10%10)*(num/10%10)*(num/10%10)+(num/100)*(num/100)*(num/100);
			if(buf==num)
				System.out.print(num+" ");
		}
	}
}
