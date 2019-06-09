import java.io.*;
import java.util.Scanner;

public class Menu {
	public static final String MENU_TEXT = "\t\t===콘솔메뉴테스트===\n" + "파일불러오기 (O), 단어검색 (S),"
			+ " 검색 후 결과 출력 (P), 수정 후 결과 출력(R), 프로그램 종료 (Q)";
	static BufferedWriter bufferedWriter;

	// 메뉴 인터페이스
	public static void startConsoleMenu() {
		Scanner sc = new Scanner(System.in);
		String findWord;// 메뉴 명령어	
		boolean isExitProgram = false;// 프로그램 종료 플래그
		System.out.println("실습 과제 11주차 11-1 or 11-2");
		String buf=sc.nextLine();
		if(buf.equals("11-1")) {//ContinueSearch
			ContinueSearch cs = new ContinueSearch("","","","","",null,null);
			while (!isExitProgram) {
				System.out.println(MENU_TEXT);
				findWord = sc.nextLine();// 메뉴 명령어 입력
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
					if (cs.printToFile()) {// 파일을 찾고, 파일에 출력
						System.out.println("파일이 정상적으로 저장되었습니다.");
					}
					System.out.println("파일출력이 완료되었습니다.");
					break;
				case "R":// 새로 추가 된 코드
				case "r":
					cs.replaceWord();
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
		else if(buf.equals("11-2")) {//ImproveEditor
			ImproveEditor ie=new ImproveEditor("","","","","",null,null);
			
			while (!isExitProgram) {
				System.out.println(MENU_TEXT+", 단어 append (A)");
				findWord = sc.nextLine();// 메뉴 명령어 입력
				switch (findWord) {
				case "O":
				case "o":
					ie.getFile();
					break;
				case "S":
				case "s":
					System.out.println("검색할 단어를 입력하세요:");
					String find = sc.nextLine();// 검색할 단어
					System.out.println("검색할 라인의 개수를 입력하세요:");
					int count=sc.nextInt();
					System.out.println("검색할 라인을 입력하세요:");
					int[] line=new int[count];
					for(int i=0;i<count;i++)
						line[i]=sc.nextInt();
					ie.searchWord(find, line);
					sc.nextLine();//버퍼 비우기
					break;
				case "P":
				case "p":
					if (ie.printToFile()) {// 파일을 찾고, 파일에 출력
						System.out.println("파일이 정상적으로 저장되었습니다.");
					}
					System.out.println("파일출력이 완료되었습니다.");
					break;
				case "R":// 새로 추가 된 코드
				case "r":
					ie.replaceWord();
					break;
				case "Q":
				case "q":
					System.out.println("프로그램을 종료합니다.");
					isExitProgram = true;
					break;
				case "A":
				case "a":
					System.out.println("Append할 문자를 입력하세요:");
					String bufStr=sc.nextLine();
					System.out.println("입력할 라인을 선택하세요:");
					int bufLine=sc.nextInt();
					System.out.println("입력할 row를 선택하세요:");
					int bufRow=sc.nextInt();
					ie.appen(bufStr, bufLine, bufRow);
					sc.nextLine();//버퍼 비우기
					break;
				default:
					System.out.println("알 수 없는 명령입니다. 한번 더 입력해주세요");
					break;
				}
			}
			sc.close();
		}
	}
}
