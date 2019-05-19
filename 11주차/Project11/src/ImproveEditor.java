import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ImproveEditor extends Editor {
	private String bufStr;
	ImproveEditor(String find, String resultString, String newWord, String findText, String path,
			BufferedInputStream bis, FileInputStream in) {
		super(find, resultString, newWord, findText, path, bis, in);
		bufStr="";
	}

	/* �ش� ���ο� str�� Append�� ���� */
	void appen(String str, int line) {
		bis = new BufferedInputStream(in);
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// �о�� ������ ������ ��Ʈ������ �����Ѵ�.
				strTemp = new String(readBuffer);
			}
			String[] words = strTemp.split("\n");//\n�� �������� �ؽ�Ʈ ������ �ڸ�
			words[line]+=str;//�ش� ���ο� ���ο� ���ڿ��� ���δ�.
			
			for(int i=0;i<words.length;i++)
				this.resultString+=(words[i]+"\r\n");
		} catch (IOException e) {
			System.out.println("�ܾ�˻� : ����¿���");
			this.resultString = "�ܾ�˻�: ����¿���";
		}
	}
	/* �ش� ���ο����� �˻� */
	void searchWord(String word, int[] line) {
		bis = new BufferedInputStream(in);
		bufStr = '"' + word + '"' + "�� �˻� ����� ������ �����ϴ�.\r\n";
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// �о�� ������ ������ ��Ʈ������ �����Ѵ�.
				strTemp = new String(readBuffer);
			}
			String[] words = strTemp.split("\n");

			for (int i = 0; i < line.length; i++) {
				String[] oneLine = words[line[i]].split(" ");// �� �ٿ��� �ܾ� ����
				for (int j = 0; j < oneLine.length; j++) {
					if (oneLine[j].indexOf(word) != -1) {// ���ڸ� ã�� ���
						bufStr += (line[i] + 1) + "��° �� " + words[line[i]].indexOf(word) + "��°(index)\r\n";
					}
				}
			}
			if (bufStr.contains("��°")) {
				System.out.println("�ܾ ã�ҽ��ϴ�.");
				this.resultString += bufStr;
			} else {
				System.out.println("�ܾ ã�� ���߽��ϴ�.");
			}
		} catch (IOException e) {
			System.out.println("�ܾ�˻� : ����¿���");
			this.resultString = "�ܾ�˻�: ����¿���";
		}
	}
}