import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ContinueSearch extends Editor{

	private boolean tf;
	ContinueSearch(String find,String resultString, String newWord, String findText, String path, BufferedInputStream bis, FileInputStream in){
		super(find, resultString, newWord, findText, path, bis, in);
		tf=true;
	}
	@Override
	/* 기존의 단어 검색 기능 및 검색 결과 파일로 저장 */
	void searchWord() {
		bis = new BufferedInputStream(in);
		String bufStr = "";
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// 읽어온 버퍼의 내용을 스트링으로 저장한다.
				strTemp = new String(readBuffer);
			}
			String[] words = strTemp.split("\n");
			while(tf) {
				System.out.println("검색할 단어를 입력하세요:");
				this.find = sc.next();// 검색할 단어
				bufStr+='"' + find + '"' + "의 검색 결과는 다음과 같습니다.\r\n";
				for (int i = 0; i < words.length; i++) {
					String[] oneLine = words[i].split(" ");// 한 줄에서 단어 저장
					for (int j = 0; j < oneLine.length; j++) {
						if (oneLine[j].indexOf(find) != -1) {// 문자를 찾은 경우
							bufStr += (i + 1) + "번째 줄 " + words[i].indexOf(find) + "번째(index)\r\n";
						}
					}
				}
				if (bufStr.contains("번째")) {
					System.out.println("단어를 찾았습니다.");
					this.resultString+=bufStr;
				} else {
					System.out.println("단어를 찾지 못했습니다.");
					this.resultString+=("\""+find+"\" 단어를 찾지 못했습니다.\r\n");
				}
				System.out.print("계속 검색하시겠습니까?(true/false)>>");
				tf=sc.nextBoolean();
				//초기화
				bufStr="";
			}
		} catch (IOException e) {
			System.out.println("단어검색 : 입출력오류");
			this.resultString="단어검색: 입출력오류";
		}
	}
}