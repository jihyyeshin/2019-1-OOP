import java.lang.Math;

public class DemicalNum {

	void demicalCheck() {
		int measure;
		boolean find;
		for (int i = 2; i <= 100; i++) {// 1�� �Ҽ��� �ƴϴ�.
			measure = (int) Math.sqrt(i);
			find = true;
			for (int j = measure; j > 1; j--) {
				if (i % j == 0) {// ������ �������� �͵��� �ֳ�?
					find = false;
				}
			}
			if(find)//true�̸� �Ҽ�
				System.out.println(i);
		}
	}

	public static void main(String[] args) {
		DemicalNum id = new DemicalNum();
		System.out.println("1���� 100������ �Ҽ��� ����ϼ���.");
		id.demicalCheck();
	}

}
