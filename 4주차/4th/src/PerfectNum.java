import java.util.Scanner;

public class PerfectNum {
	int num;
	int countSum;
	PerfectNum(int num){
		this.num=num;
	}
	boolean mSum() {
		for(int i=num-1;i>0;i--) {
			if(num%i==0) {//약수이다
				//System.out.println(i);
				countSum+=i;
			}
		}
		if(countSum==num)//약수의 합이 자신과 동일한가? 완전수
			return true;
		else return false;
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		PerfectNum pn=new PerfectNum(num);
		if(pn.mSum())
			System.out.println("완전수입니다.");
		else
			System.out.println("완전수가 아닙니다.");
	}

}
