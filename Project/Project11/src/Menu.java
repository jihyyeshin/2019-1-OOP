import java.io.*;
import java.util.Scanner;

public class Menu {
	public static final String MENU_TEXT = "\t\t===�ָܼ޴��׽�Ʈ===\n" + "���Ϻҷ����� (O), �ܾ�˻� (S),"
			+ " �˻� �� ��� ��� (P), ���� �� ��� ���(R), ���α׷� ���� (Q)";
	static BufferedWriter bufferedWriter;

	// �޴� �������̽�
	public static void startConsoleMenu() {
		Scanner sc = new Scanner(System.in);
		String findWord;// �޴� ��ɾ�	
		boolean isExitProgram = false;// ���α׷� ���� �÷���
		System.out.println("�ǽ� ���� 11���� 11-1 or 11-2");
		String buf=sc.nextLine();
		if(buf.equals("11-1")) {//ContinueSearch
			ContinueSearch cs = new ContinueSearch("","","","","",null,null);
			while (!isExitProgram) {
				System.out.println(MENU_TEXT);
				findWord = sc.nextLine();// �޴� ��ɾ� �Է�
				switch (findWord) {
				case "O":
				case "o":
					cs.getFile();
					break;
				case "S":
				case "s":
					cs.searchWord();
					break;
				case "P":
				case "p":
					if (cs.printToFile()) {// ������ ã��, ���Ͽ� ���
						System.out.println("������ ���������� ����Ǿ����ϴ�.");
					}
					System.out.println("��������� �Ϸ�Ǿ����ϴ�.");
					break;
				case "R":// ���� �߰� �� �ڵ�
				case "r":
					cs.replaceWord();
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
		else if(buf.equals("11-2")) {//ImproveEditor
			ImproveEditor ie=new ImproveEditor("","","","","",null,null);
			
			while (!isExitProgram) {
				System.out.println(MENU_TEXT+", �ܾ� append (A)");
				findWord = sc.nextLine();// �޴� ��ɾ� �Է�
				switch (findWord) {
				case "O":
				case "o":
					ie.getFile();
					break;
				case "S":
				case "s":
					System.out.println("�˻��� �ܾ �Է��ϼ���:");
					String find = sc.nextLine();// �˻��� �ܾ�
					System.out.println("�˻��� ������ ������ �Է��ϼ���:");
					int count=sc.nextInt();
					System.out.println("�˻��� ������ �Է��ϼ���:");
					int[] line=new int[count];
					for(int i=0;i<count;i++)
						line[i]=sc.nextInt();
					ie.searchWord(find, line);
					sc.nextLine();//���� ����
					break;
				case "P":
				case "p":
					if (ie.printToFile()) {// ������ ã��, ���Ͽ� ���
						System.out.println("������ ���������� ����Ǿ����ϴ�.");
					}
					System.out.println("��������� �Ϸ�Ǿ����ϴ�.");
					break;
				case "R":// ���� �߰� �� �ڵ�
				case "r":
					ie.replaceWord();
					break;
				case "Q":
				case "q":
					System.out.println("���α׷��� �����մϴ�.");
					isExitProgram = true;
					break;
				case "A":
				case "a":
					System.out.println("Append�� ���ڸ� �Է��ϼ���:");
					String bufStr=sc.nextLine();
					System.out.println("�Է��� ������ �����ϼ���:");
					int bufLine=sc.nextInt();
					System.out.println("�Է��� row�� �����ϼ���:");
					int bufRow=sc.nextInt();
					ie.appen(bufStr, bufLine, bufRow);
					sc.nextLine();//���� ����
					break;
				default:
					System.out.println("�� �� ���� ����Դϴ�. �ѹ� �� �Է����ּ���");
					break;
				}
			}
			sc.close();
		}
	}
}
