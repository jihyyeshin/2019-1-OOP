import java.util.Scanner;
import java.lang.Math;

public class IsDemical {
	int num;
	IsDemical(int num){
		this.num=num;
	}
	boolean demicalCheck() {
		int measure=(int)Math.sqrt(num);
		if(num==1) return false;//1은 소수가 아니다.
		boolean find=true;
		for(int i=measure;i>1;i--) {
			if(num%i==0) {//나누어 떨어지는 것들이 있나?
				find=false;
			}
		}
		return find;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		IsDemical id=new IsDemical(num);
		if(id.demicalCheck())
			System.out.println("소수입니다.");
		else
			System.out.println("소수가 아닙니다.");
	}

}
