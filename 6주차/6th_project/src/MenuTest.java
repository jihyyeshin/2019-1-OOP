import java.io.*;
import java.util.Scanner;

public class MenuTest {
	public static final String MENU_TEXT = "\t\t===�ָܼ޴��׽�Ʈ===\n" + "���Ϻҷ����� (O), �ܾ�˻� (S),"
			+ " �Ļ������� (P), ���α׷� ���� (Q) ";
	static BufferedWriter bufferedWriter;
	public static final int BUFFER_SIZE = 1024;// default value

	public static void main(String[] args) throws IOException {
		startConsoleMenu();
	}

	// �޴� �������̽�
	public static void startConsoleMenu() throws IOException {
		Scanner sc = new Scanner(System.in);
		FileInputStream in = null;
		String resultString = "";
		String findWord;// �޴� ��ɾ�
		String findText;// �ؽ�Ʈ���ϸ�
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
				} catch (FileNotFoundException e) {
					System.out.println("������ �������� �ʽ��ϴ�.");
				}
				break;
			case "S":
			case "s":
				System.out.println("�˻��� �ܾ �Է��ϼ���:");
				find = sc.nextLine();// �˻��� �ܾ�
				resultString = findWord(in, find);
				break;
			case "P":
			case "p":
				System.out.println("������ ���ϸ��� �Է��ϼ���:");
				String newText = sc.nextLine();// ���ϸ� �Է�
				if (save(resultString, newText))// ������ ã��, ���Ͽ� ���
					System.out.println("������ ���������� ����Ǿ����ϴ�.");
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
	
	static String findWord(FileInputStream in, String find) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(in);
		String resultString = find + "�� �˻� ����� ������ �����ϴ�.\n";
		String strTemp = "";
		int totalCount = 0;// �� �˻�����
		int row = 1, count = 0;// row��° ��, count��°
		byte[] readBuffer = new byte[BUFFER_SIZE];

		while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
			// �о�� ������ ������ ��Ʈ������ �����Ѵ�.
			strTemp = new String(readBuffer);
		}
		for (int i = 0; i < strTemp.length(); i++) {

		}
		resultString += "�̻� �˻������ �� " + count + "���� �˻��� �Ϸ�Ǿ����ϴ�.";
		return resultString;
	}

	static boolean save(String resultString, String path) throws IOException {
		resultString = path + "���Ͽ���" + resultString;
		if (loadWritingFile(path)) {//������ �����ϸ�
			bufferedWriter.write(resultString);
			bufferedWriter.close();
		}
		else {//������ �������� ������
			System.out.println("����");
		}

		return true;
	}
	
	public static boolean loadWritingFile(String path) {
		File textFile = new File(path);
		boolean isAppend;
		if(textFile.exists()) {
			System.out.println("������ �̹� �����մϴ�. �ٿ������ðڽ��ϱ�?(true/false)");
			
			return true;
		}
		else return false;
//		try {
//			
//			FileWriter fw = new FileWriter(textFile, isAppend);
//			//filewriter append true: ������ ���� ������ ����, false: ���Ͽ� ��� 
//			bufferedWriter = new BufferedWriter(fw);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
	}

}
