import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class ContinueSearch extends Editor{

	private String myString;
	private boolean tf;
	ContinueSearch(String find,String resultString, String newWord, String findText, String path, BufferedInputStream bis, FileInputStream in){
		super(find, resultString, newWord, findText, path, bis, in);
		myString="";
		tf=true;
	}
	@Override
	/* 기존의 단어 검색 기능 및 검색 결과 파일로 저장 */
	String searchWord() {
		while(tf) {
			System.out.println("검색할 단어를 입력하세요:");
			this.find = sc.next();// 검색할 단어
			this.resultString += findWord(in, find);
			if (resultString.contains("번째")) {
				System.out.println("단어를 찾았습니다.");
				myString+=resultString;
			} else {
				System.out.println("단어를 찾지 못했습니다.");
				myString+=("\""+find+"\" 단어를 찾지 못했습니다.");
			}
			System.out.print("계속 검색하시겠습니까?(true/false)>>");
			tf=sc.nextBoolean();
			//초기화
			this.resultString="";
		}
		//System.out.println(myString);
		return myString;
	}
}
