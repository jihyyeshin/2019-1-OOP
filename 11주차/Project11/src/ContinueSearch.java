import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class ContinueSearch extends Editor{

	private String myString;
	private boolean tf;
	ContinueSearch(String find,String resultString, String newWord, String findText, String path, BufferedInputStream bis, FileInputStream in){
		super(find, resultString, newWord, findText, path, bis, in);
		myString="";
		tf=true;
	}
	@Override
	/* ������ �ܾ� �˻� ��� �� �˻� ��� ���Ϸ� ���� */
	String searchWord() {
		while(tf) {
			System.out.println("�˻��� �ܾ �Է��ϼ���:");
			this.find = sc.next();// �˻��� �ܾ�
			this.resultString += findWord(in, find);
			if (resultString.contains("��°")) {
				System.out.println("�ܾ ã�ҽ��ϴ�.");
				myString+=resultString;
			} else {
				System.out.println("�ܾ ã�� ���߽��ϴ�.");
				myString+=("\""+find+"\" �ܾ ã�� ���߽��ϴ�.");
			}
			System.out.print("��� �˻��Ͻðڽ��ϱ�?(true/false)>>");
			tf=sc.nextBoolean();
			//�ʱ�ȭ
			this.resultString="";
		}
		//System.out.println(myString);
		return myString;
	}
}
