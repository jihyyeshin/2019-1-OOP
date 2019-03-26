import java.lang.Math;

public class DemicalNum {

	void demicalCheck() {
		int measure;
		boolean find;
		for (int i = 2; i <= 100; i++) {// 1은 소수가 아니다.
			measure = (int) Math.sqrt(i);
			find = true;
			for (int j = measure; j > 1; j--) {
				if (i % j == 0) {// 나누어 떨어지는 것들이 있나?
					find = false;
				}
			}
			if(find)//true이면 소수
				System.out.println(i);
		}
	}

	public static void main(String[] args) {
		DemicalNum id = new DemicalNum();
		System.out.println("1부터 100까지의 소수를 출력하세요.");
		id.demicalCheck();
	}

}
