import java.util.Scanner;

public class PerfectNum {
	int num;
	int countSum;
	PerfectNum(int num){
		this.num=num;
	}
	boolean mSum() {
		for(int i=num-1;i>0;i--) {
			if(num%i==0) {//����̴�
				//System.out.println(i);
				countSum+=i;
			}
		}
		if(countSum==num)//����� ���� �ڽŰ� �����Ѱ�? ������
			return true;
		else return false;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		PerfectNum pn=new PerfectNum(num);
		if(pn.mSum())
			System.out.println("�������Դϴ�.");
		else
			System.out.println("�������� �ƴմϴ�.");
	}

}
