import java.io.*;
import java.util.Scanner;

public class MenuTest {
	public static final String MENU_TEXT = "\t\t===�ָܼ޴��׽�Ʈ===\n" + "���Ϻҷ����� (O), �ܾ�˻� (S),"
			+ " �Ļ������� (P), ���α׷� ���� (Q) ";
	static BufferedWriter bufferedWriter;
	public static final int BUFFER_SIZE = 1024;// default value

	public static void main(String[] args) {
		startConsoleMenu();
	}

	// �޴� �������̽�
	public static void startConsoleMenu() {
		Scanner sc = new Scanner(System.in);
		FileInputStream in = null;
		String resultString = "";
		String findWord;// �޴� ��ɾ�
		String findText = "";// �ؽ�Ʈ���ϸ�
		String find = "";// �˻��� �ܾ�
		boolean isExitProgram = false;// ���α׷� ���� �÷���
		while (!isExitProgram) {
			System.out.println(MENU_TEXT);
			findWord = sc.nextLine();// �޴� ��ɾ� �Է�
			switch (findWord) {
			case "O":
			case "o":
				System.out.println("�ҷ��� ���ϸ��� �Է��ϼ���:");
				findText = sc.nextLine();// �ؽ�Ʈ���ϸ� �Է�
				try {
					in = new FileInputStream(findText);
					System.out.println("������ ã�ҽ��ϴ�.");
					resultString = findText + "���Ͽ��� ";
				} catch (FileNotFoundException e) {
					System.out.println("������ �������� �ʽ��ϴ�.");
				}
				break;
			case "S":
			case "s":
				System.out.println("�˻��� �ܾ �Է��ϼ���:");
				find = sc.nextLine();// �˻��� �ܾ�
				resultString += findWord(in, find);
				//System.out.println(resultString);
				if (resultString.contains("�Ϸ�"))// �ܾ ã�� ���ϴ� ��� "�Ϸ�"��� ���ڿ��� �������� ����
					System.out.println("�ܾ ã�ҽ��ϴ�.");
				else
					System.out.println("�ܾ ã�� ���߽��ϴ�.");
				break;
			case "P":
			case "p":
				System.out.println("������ ���ϸ��� �Է��ϼ���:");
				String newText = sc.nextLine();// ���ϸ� �Է�
				if (save(resultString, newText)) {// ������ ã��, ���Ͽ� ���
					System.out.println("������ ���������� ����Ǿ����ϴ�.");
					resultString="";
				}
				break;
			case "Q":
			case "q":
				System.out.println("���α׷��� �����մϴ�.");
				isExitProgram = true;
				break;
			default:
				System.out.println("�� �� ���� ����Դϴ�. �ѹ� �� �Է����ּ���");
				break;
			}

		}
		sc.close();
	}

	static String findWord(FileInputStream in, String find) {
		BufferedInputStream bis = new BufferedInputStream(in);
		String resultString = '"' + find + '"' + "�� �˻� ����� ������ �����ϴ�.\r\n";
		String strTemp = "";
		int totalCount = 0;// �� �˻�����
		int count = 0;// row��° ��, count��°
		byte[] readBuffer = new byte[BUFFER_SIZE];

		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// �о�� ������ ������ ��Ʈ������ �����Ѵ�.
				strTemp = new String(readBuffer);
			}
		} catch (IOException e) {
			System.out.println("�ܾ�˻� : ����¿���");
		}
		String[] words = strTemp.split("\n");
		for (int i = 0; i < words.length; i++) {
			String[] oneLine = words[i].split(" ");//�� �ٿ��� �ܾ� ����
			for (int j = 0; j < oneLine.length; j++) {
				if (oneLine[j].indexOf(find) != -1) {// ���ڸ� ã�� ���
					resultString += (i + 1) + "��° �� " + words[i].indexOf(find) + "��°(index)\r\n";
					totalCount++;
				}
			}
		}
		resultString += "�̻� �˻������ �� " + totalCount + "���� �˻��� �Ϸ�Ǿ����ϴ�.";
		if (totalCount == 0) {// �ܾ ������
			resultString = find + " �ܾ ã�� �� �����ϴ�.\r\n";
		}
		return resultString;
	}

	static boolean save(String resultString, String path) {
		Scanner sc = new Scanner(System.in);
		boolean isAppend;
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
