import java.io.*;
import java.util.Scanner;

public class Editor {
	public static final int BUFFER_SIZE = 1024;// default value
	static BufferedWriter bufferedWriter;
	static Scanner sc= new Scanner(System.in);

	private String find="";
	private String resultString="";
	private String newWord="";
	private String findText="";
	private String newText="";
	private FileInputStream in = null;
	private BufferedInputStream bis=null;

	void getFile() {
		System.out.println("불러올 파일명을 입력하세요:");
		findText = sc.nextLine();// 텍스트파일명 입력
		try {
			in = new FileInputStream(findText);
			System.out.println("파일을 찾았습니다.");
		} catch (FileNotFoundException e) {
			System.out.println("파일이 존재하지 않습니다.");
		}
	}
	/*기존의 단어 검색 기능 및 검색 결과 파일로 저장*/
	void searchWord() {
		System.out.println("검색할 단어를 입력하세요:");
		find = sc.nextLine();// 검색할 단어
		
		resultString += findWord(in, find);
		System.out.println(resultString);
		if (resultString.contains("입출력오류"))// 단어를 찾는 경우 "입출력오류"라는 문자열을 포함하지 못함
			System.out.println("단어를 찾지 못했습니다.");
		else
			System.out.println("단어를 찾았습니다.");

	}

	String findWord(FileInputStream in, String find) {
		bis = new BufferedInputStream(in);
		String resultString = '"' + find + '"' + "의 검색 결과는 다음과 같습니다.\r\n";
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];

		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// 읽어온 버퍼의 내용을 스트링으로 저장한다.
				strTemp = new String(readBuffer);
			}
		} catch (IOException e) {
			System.out.println("단어검색 : 입출력오류");
		}
		String[] words = strTemp.split("\n");
		for (int i = 0; i < words.length; i++) {
			String[] oneLine = words[i].split(" ");// 한 줄에서 단어 저장
			for (int j = 0; j < oneLine.length; j++) {
				if (oneLine[j].indexOf(find) != -1) {// 문자를 찾은 경우
					resultString += (i + 1) + "번째 줄 " + words[i].indexOf(find) + "번째(index)\r\n";
				}
			}
		}
		return resultString;
	}
	/*해당 word를 검색해 원하는 word로 바꿔주는 메소드*/
	void replaceWord(FileInputStream in, String find) {
		System.out.println("검색할 단어를 입력하세요:");
		find=sc.nextLine();
		System.out.println("교체할 단어를 입력하세요:");
		newWord=sc.nextLine();
		
		String str = "";//파일에 입력할 문자열ㄴ
		byte[] readBuffer = new byte[BUFFER_SIZE];

		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// 읽어온 버퍼의 내용을 스트링으로 저장한다.
				str = new String(readBuffer);
			}
		} catch (IOException e) {
			System.out.println("단어검색 : 입출력오류");
		}
		String[] words = str.split("\n");
		for (int i = 0; i < words.length; i++) {
			String[] oneLine = words[i].split(" ");// 한 줄에서 단어 저장
			for (int j = 0; j < oneLine.length; j++) {
				if (oneLine[j].indexOf(find) != -1) {// 문자를 찾은 경우
					str.replace(oneLine[j], find);
				}
			}
		}

		System.out.println("출력할 파일 경로를 입력하세요:");
		newText = sc.nextLine();// 파일명 입력
		if (this.printToFile(resultString, newText)) {// 파일을 찾고, 파일에 출력
			System.out.println("파일이 정상적으로 저장되었습니다.");
			resultString = "";
		}
		System.out.println("파일출력이 완료되었습니다.");
	}
	/*결과 출력 : console*/
	void PrintWord() {
		
	}
	/*replaceWord()한 결과를 파일로 저장*/
	boolean printToFile(String resultString, String path) {
		sc = new Scanner(System.in);
		boolean isAppend;
		File textFile = new File(path);
		FileWriter fw = null;
		if (textFile.exists()) {
			System.out.println("파일이 이미 존재합니다. 붙여넣으시겠습니까?(true, false)");
			isAppend = sc.nextBoolean();
			// filewriter append true: 파일의 끝에 데이터 붙임, false: 파일에 덮어씀
			try {
				fw = new FileWriter(textFile, isAppend);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("파일 열기 오류");
			}
		} else {
			System.out.println("파일이 존재하지 않습니다.\n새로운 파일을 만듭니다.");
			try {
				fw = new FileWriter(textFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("파일 생성 오류");
			}
		}
		bufferedWriter = new BufferedWriter(fw);
		try {
			bufferedWriter.write(resultString);
			bufferedWriter.close();
		} catch (IOException e) {
			System.out.println("파일 입력 오류");
		}

		return true;
	}
}
