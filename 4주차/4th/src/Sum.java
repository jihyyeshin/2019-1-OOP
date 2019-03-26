import java.util.Scanner;

public class Sum {
	int num;
	int countSum;
	Sum(int num){
		this.num=num;
	}
	void mSum() {
		for(int i=num;i>0;i--) {
			if(num%i==0) {//약수이다
				//System.out.println(i);
				countSum+=i;
			}
		}
		System.out.println(countSum);
	}
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int num=sc.nextInt();
		Sum s=new Sum(num);
		s.mSum();
	}

}
