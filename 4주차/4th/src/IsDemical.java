import java.util.Scanner;
import java.lang.Math;

public class IsDemical {
	int num;
	IsDemical(int num){
		this.num=num;
	}
	boolean demicalCheck() {
		int measure=(int)Math.sqrt(num);
		if(num==1) return false;//1�� �Ҽ��� �ƴϴ�.
		boolean find=true;
		for(int i=measure;i>1;i--) {
			if(num%i==0) {//������ �������� �͵��� �ֳ�?
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
			System.out.println("�Ҽ��Դϴ�.");
		else
			System.out.println("�Ҽ��� �ƴմϴ�.");
	}

}
