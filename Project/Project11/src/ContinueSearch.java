import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ContinueSearch extends Editor{

	private boolean tf;
	ContinueSearch(String find,String resultString, String newWord, String findText, String path, BufferedInputStream bis, FileInputStream in){
		super(find, resultString, newWord, findText, path, bis, in);
		tf=true;
	}
	@Override
	/* ������ �ܾ� �˻� ��� �� �˻� ��� ���Ϸ� ���� */
	void searchWord() {
		bis = new BufferedInputStream(in);
		String bufStr = "";
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// �о�� ������ ������ ��Ʈ������ �����Ѵ�.
				strTemp = new String(readBuffer);
			}
			String[] words = strTemp.split("\n");
			while(tf) {
				System.out.println("�˻��� �ܾ �Է��ϼ���:");
				this.find = sc.next();// �˻��� �ܾ�
				bufStr+='"' + find + '"' + "�� �˻� ����� ������ �����ϴ�.\r\n";
				for (int i = 0; i < words.length; i++) {
					String[] oneLine = words[i].split(" ");// �� �ٿ��� �ܾ� ����
					for (int j = 0; j < oneLine.length; j++) {
						if (oneLine[j].indexOf(find) != -1) {// ���ڸ� ã�� ���
							bufStr += (i + 1) + "��° �� " + words[i].indexOf(find) + "��°(index)\r\n";
						}
					}
				}
				if (bufStr.contains("��°")) {
					System.out.println("�ܾ ã�ҽ��ϴ�.");
					this.resultString+=bufStr;
				} else {
					System.out.println("�ܾ ã�� ���߽��ϴ�.");
					this.resultString+=("\""+find+"\" �ܾ ã�� ���߽��ϴ�.\r\n");
				}
				System.out.print("��� �˻��Ͻðڽ��ϱ�?(true/false)>>");
				tf=sc.nextBoolean();
				//�ʱ�ȭ
				bufStr="";
			}
		} catch (IOException e) {
			System.out.println("�ܾ�˻� : ����¿���");
			this.resultString="�ܾ�˻�: ����¿���";
		}
	}
}