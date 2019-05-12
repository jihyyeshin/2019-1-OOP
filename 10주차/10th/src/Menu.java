import java.io.*;
import java.util.Scanner;

public class Menu {
	public static final String MENU_TEXT = "\t\t===�ָܼ޴��׽�Ʈ===\n" + "���Ϻҷ����� (O), �ܾ�˻� (S),"
			+ " �˻� �� ��� ��� (P), ���� �� ��� ���(R), ���α׷� ���� (Q) ";
	static BufferedWriter bufferedWriter;

	// �޴� �������̽�
	public static void startConsoleMenu() {
		Editor editor = new Editor();
		Scanner sc = new Scanner(System.in);
		String findWord;// �޴� ��ɾ�
		String resultString = "";

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
				resultString = editor.searchWord();
				break;
			case "P":
			case "p":
				if (editor.printToFile(resultString)) {// ������ ã��, ���Ͽ� ���
					System.out.println("������ ���������� ����Ǿ����ϴ�.");
				}
				System.out.println("��������� �Ϸ�Ǿ����ϴ�.");
				break;
			case "R":// ���� �߰� �� �ڵ�
			case "r":
				editor.replaceWord();
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
