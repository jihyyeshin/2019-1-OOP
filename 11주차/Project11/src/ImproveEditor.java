import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ImproveEditor extends Editor{

	ImproveEditor(String find, String resultString, String newWord, String findText, String path,
			BufferedInputStream bis, FileInputStream in) {
		super(find, resultString, newWord, findText, path, bis, in);
	}

	/*�ش� ���ο� str�� Append�� ����*/
	void appen(String str, int line) {
		
	}
	/* �ش� ���ο����� �˻�
	 * 
	 * */
	void searchWord(String word, int[] line) {
		
	}
	/*
	 * searchWord�� �Ű����� �� return���� �޶����� printToFIle �޼ҵ� ���� overload*/
	boolean printToFile() {
		sc = new Scanner(System.in);
		boolean isAppend;

		System.out.println("����� ���� ��θ� �Է��ϼ���:");
		path = sc.nextLine();// ���ϸ� �Է�
		File textFile = new File(path);
		FileWriter fw = null;

		if (textFile.exists()) {
			System.out.println("������ �̹� �����մϴ�. �ٿ������ðڽ��ϱ�?(true, false)");
			isAppend = sc.nextBoolean();
			// filewriter append true: ������ ���� ������ ����, false: ���Ͽ� ���
			try {
				fw = new FileWriter(textFile, isAppend);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("���� ���� ����");
			}
		} else {
			System.out.println("������ �������� �ʽ��ϴ�.\n���ο� ������ ����ϴ�.");
			try {
				fw = new FileWriter(textFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("���� ���� ����");
			}
		}
		bufferedWriter = new BufferedWriter(fw);
		try {
			bufferedWriter.write(resultString);
			bufferedWriter.close();
		} catch (IOException e) {
			System.out.println("���� �Է� ����");
		}

		return true;
	}
}
