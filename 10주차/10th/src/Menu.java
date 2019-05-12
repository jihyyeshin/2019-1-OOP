import java.io.*;
import java.util.Scanner;

public class Menu {
	public static final String MENU_TEXT = "\t\t===콘솔메뉴테스트===\n" + "파일불러오기 (O), 단어검색 (S),"
			+ " 검색 후 결과 출력 (P), 수정 후 결과 출력(R), 프로그램 종료 (Q) ";
	static BufferedWriter bufferedWriter;

	// 메뉴 인터페이스
	public static void startConsoleMenu() {
		Editor editor = new Editor();
		Scanner sc = new Scanner(System.in);
		String findWord;// 메뉴 명령어
		String resultString = "";

		boolean isExitProgram = false;// 프로그램 종료 플래그
		while (!isExitProgram) {
			System.out.println(MENU_TEXT);
			findWord = sc.nextLine();// 메뉴 명령어 입력
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
				if (editor.printToFile(resultString)) {// 파일을 찾고, 파일에 출력
					System.out.println("파일이 정상적으로 저장되었습니다.");
				}
				System.out.println("파일출력이 완료되었습니다.");
				break;
			case "R":// 새로 추가 된 코드
			case "r":
				editor.replaceWord();
				break;
			case "Q":
			case "q":
				System.out.println("프로그램을 종료합니다.");
				isExitProgram = true;
				break;
			default:
				System.out.println("알 수 없는 명령입니다. 한번 더 입력해주세요");
				break;
			}
		}
		sc.close();
	}
}
