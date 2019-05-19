import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ImproveEditor extends Editor{

	ImproveEditor(String find, String resultString, String newWord, String findText, String path,
			BufferedInputStream bis, FileInputStream in) {
		super(find, resultString, newWord, findText, path, bis, in);
	}

	/*해당 라인에 str을 Append해 저장*/
	void appen(String str, int line) {
		
	}
	/* 해당 라인에서만 검색
	 * 
	 * */
	void searchWord(String word, int[] line) {
		
	}
	/*
	 * searchWord의 매개변수 및 return형이 달라져서 printToFIle 메소드 또한 overload*/
	boolean printToFile() {
		sc = new Scanner(System.in);
		boolean isAppend;

		System.out.println("출력할 파일 경로를 입력하세요:");
		path = sc.nextLine();// 파일명 입력
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
