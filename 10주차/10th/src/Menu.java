import java.io.*;
import java.util.Scanner;

public class Menu {
	public static final String MENU_TEXT = "\t\t===�ָܼ޴��׽�Ʈ===\n" + "���Ϻҷ����� (O), �ܾ�˻� (S),"
			+ " �˻� �� ��� ��� (P), ���� �� ��� ���(R), ���α׷� ���� (Q) ";
	static BufferedWriter bufferedWriter;
	
	// �޴� �������̽�
	public static void startConsoleMenu() {
		
		Editor editor =new Editor();
		
		Scanner sc = new Scanner(System.in);
		FileInputStream in = null;
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
				editor.getFile();
				break;
			case "S":
			case "s":
				editor.searchWord();
				break;
			case "P":
			case "p":
//				System.out.println("������ ���ϸ��� �Է��ϼ���:");
//				newText = sc.nextLine();
//				if (save(resultString, newText)) {// ������ ã��, ���Ͽ� ���
//					System.out.println("������ ���������� ����Ǿ����ϴ�.");
//					resultString = "";
//				}
				break;
			case "R"://���� �߰� �� �ڵ�
			case "r":
				editor.replaceWord(in, find);
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
}
