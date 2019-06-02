import java.io.*;
import java.util.Scanner;

public class Editor {
	public static final int BUFFER_SIZE = 1024;// default value
	static BufferedWriter bufferedWriter;
	static Scanner sc = new Scanner(System.in);

	protected String find;
	protected String resultString;
	protected String newWord;
	protected String findText;
	protected String path;
	protected BufferedInputStream bis;
	protected FileInputStream in;
	
	Editor(String find,String resultString, String newWord, String findText, String path, BufferedInputStream bis, FileInputStream in){
		this.find=find;
		this.resultString=resultString;
		this.newWord=newWord;
		this.findText=findText;
		this.path=path;
		this.bis=bis;
		this.in=in;
	}

	void getFile() {
		System.out.println("�ҷ��� ���ϸ��� �Է��ϼ���:");
		findText = sc.nextLine();// �ؽ�Ʈ���ϸ� �Է�
		try {
			in = new FileInputStream(findText);
			System.out.println("������ ã�ҽ��ϴ�.");
		} catch (FileNotFoundException e) {
			System.out.println("������ �������� �ʽ��ϴ�.");
		}
	}
	/* ������ �ܾ� �˻� ��� �� �˻� ��� ���Ϸ� ���� */
	void searchWord() {
		System.out.println("�˻��� �ܾ �Է��ϼ���:");
		find = sc.nextLine();// �˻��� �ܾ�

		resultString += findWord(in, find);
		if (resultString.contains("��°")) {// �ܾ ã�� ��� "����"
			System.out.println("�ܾ ã�ҽ��ϴ�.");
		} else {
			System.out.println("�ܾ ã�� ���߽��ϴ�.");
			resultString="�ܾ ã�� ���߽��ϴ�.";
		}
	}

	String findWord(FileInputStream in, String find) {
		bis = new BufferedInputStream(in);
		String bufStr = '"' + find + '"' + "�� �˻� ����� ������ �����ϴ�.\r\n";
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];

		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// �о�� ������ ������ ��Ʈ������ �����Ѵ�.
				strTemp = new String(readBuffer);
			}
		} catch (IOException e) {
			System.out.println("�ܾ�˻� : ����¿���");
			return "�ܾ�˻�: ����¿���\r\n";
		}
		String[] words = strTemp.split("\n");
		for (int i = 0; i < words.length; i++) {
			String[] oneLine = words[i].split(" ");// �� �ٿ��� �ܾ� ����
			for (int j = 0; j < oneLine.length; j++) {
				if (oneLine[j].indexOf(find) != -1) {// ���ڸ� ã�� ���
					bufStr += (i + 1) + "��° �� " + words[i].indexOf(find) + "��°(index)\r\n";
				}
			}
		}
		return bufStr;
	}

	/* �ش� word�� �˻��� ���ϴ� word�� �ٲ��ִ� �޼ҵ� */
	void replaceWord() {
		bis = new BufferedInputStream(in);
		System.out.println("�˻��� �ܾ �Է��ϼ���:");
		find = sc.nextLine();
		System.out.println("��ü�� �ܾ �Է��ϼ���:");
		newWord = sc.nextLine();

		String buf;
		byte[] readBuffer = new byte[BUFFER_SIZE];

		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// �о�� ������ ������ ��Ʈ������ �����Ѵ�.
				resultString = new String(readBuffer);
			}
		} catch (IOException e) {
			System.out.println("�ܾ�˻� : ����¿���");
			
		}

		resultString = resultString.replace(find, newWord);
		/* ���͸� �����ϴ� �ܾ �˻� �� ������ �����ϰ� �ϱ� ���� �ڵ� */
		for (int i = 1; i < find.length(); i++) {
			buf = find.substring(0, i).concat("\r\n");
			buf = buf.concat(find.substring(i));
			resultString = resultString.replace(buf, newWord);
		}
		// ��� print
		this.PrintWord(resultString);
		if (this.printToFile()) {// ������ ã��, ���Ͽ� ���
			System.out.println("������ ���������� ����Ǿ����ϴ�.");
		}
		System.out.println("��������� �Ϸ�Ǿ����ϴ�.");
	}

	/* ��� ��� : console */
	void PrintWord(String str) {
		System.out.println("����� ������ ������ �����ϴ�.");
		System.out.println(str);
	}

	/* replaceWord()�� ����� ���Ϸ� ���� */
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



