import java.io.*;
import java.util.Scanner;

public class MenuTest {
	public static final String MENU_TEXT = "\t\t===콘솔메뉴테스트===\n" + "파일불러오기 (O), 단어검색 (S),"
			+ " 컴색결과출력 (P), 프로그램 종료 (Q) ";
	static BufferedWriter bufferedWriter;
	public static final int BUFFER_SIZE = 1024;// default value

	public static void main(String[] args) throws IOException {
		startConsoleMenu();
	}

	// 메뉴 인터페이스
	public static void startConsoleMenu() throws IOException {
		Scanner sc = new Scanner(System.in);
		FileInputStream in = null;
		String resultString = "";
		String findWord;// 메뉴 명령어
		String findText;// 텍스트파일명
		String find = "";// 검색할 단어
		boolean isExitProgram = false;// 프로그램 종료 플래그
		while (!isExitProgram) {
			System.out.println(MENU_TEXT);
			findWord = sc.nextLine();// 메뉴 명령어 입력
			switch (findWord) {
			case "O":
			case "o":
				System.out.println("불러올 파일명을 입력하세요:");
				findText = sc.nextLine();// 텍스트파일명 입력
				try {
					in = new FileInputStream(findText);
					System.out.println("파일을 찾았습니다.");
				} catch (FileNotFoundException e) {
					System.out.println("파일이 존재하지 않습니다.");
				}
				break;
			case "S":
			case "s":
				System.out.println("검색할 단어를 입력하세요:");
				find = sc.nextLine();// 검색할 단어
				resultString = findWord(in, find);
				break;
			case "P":
			case "p":
				System.out.println("저장할 파일명을 입력하세요:");
				String newText = sc.nextLine();// 파일명 입력
				if (save(resultString, newText))// 파일을 찾고, 파일에 출력
					System.out.println("파일이 정상적으로 저장되었습니다.");
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
	
	static String findWord(FileInputStream in, String find) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(in);
		String resultString = find + "의 검색 결과는 다음과 같습니다.\n";
		String strTemp = "";
		int totalCount = 0;// 총 검색개수
		int row = 1, count = 0;// row번째 줄, count번째
		byte[] readBuffer = new byte[BUFFER_SIZE];

		while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
			// 읽어온 버퍼의 내용을 스트링으로 저장한다.
			strTemp = new String(readBuffer);
		}
		for (int i = 0; i < strTemp.length(); i++) {

		}
		resultString += "이상 검색결과로 총 " + count + "개의 검색이 완료되었습니다.";
		return resultString;
	}

	static boolean save(String resultString, String path) throws IOException {
		resultString = path + "파일에서" + resultString;
		if (loadWritingFile(path)) {//파일이 존재하면
			bufferedWriter.write(resultString);
			bufferedWriter.close();
		}
		else {//파일이 존재하지 않으면
			System.out.println("파일");
		}

		return true;
	}
	
	public static boolean loadWritingFile(String path) {
		File textFile = new File(path);
		boolean isAppend;
		if(textFile.exists()) {
			System.out.println("파일이 이미 존재합니다. 붙여넣으시겠습니까?(true/false)");
			
			return true;
		}
		else return false;
//		try {
//			
//			FileWriter fw = new FileWriter(textFile, isAppend);
//			//filewriter append true: 파일의 끝에 데이터 붙임, false: 파일에 덮어씀 
//			bufferedWriter = new BufferedWriter(fw);
//		} catch (IOException e) {
//			e.printStackTrace();
//			return false;
//		}
//		return true;
	}

}
