import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ImproveEditor extends Editor {
	private String bufStr;
	ImproveEditor(String find, String resultString, String newWord, String findText, String path,
			BufferedInputStream bis, FileInputStream in) {
		super(find, resultString, newWord, findText, path, bis, in);
		bufStr="";
	}

	/* 2-1: 해당 라인에 str을 Append해 저장 */
	void appen(String str, int col, int row) {
		bis = new BufferedInputStream(in);
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// 읽어온 버퍼의 내용을 스트링으로 저장한다.
				strTemp = new String(readBuffer);
			}
			String[] words = strTemp.split("\n");//\n를 기준으로 텍스트 파일을 자름
			String[] oneLines=words[col].split(" ");//공백을 기준으로 한 줄을 자름
			oneLines[row]+=str;
			words[col]="";
			for(int i=0;i<oneLines.length;i++)
				words[col]+=oneLines[i];
			for(int i=0;i<words.length;i++)
				this.resultString+=(words[i]+"\r\n");
		} catch (IOException e) {
			System.out.println("단어검색 : 입출력오류");
			this.resultString = "단어검색: 입출력오류";
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("해당 행, 열이 없습니다.");
		}
	}
	/* 2-2: 해당 라인에서만 검색 */
	void searchWord(String word, int[] line) {
		bis = new BufferedInputStream(in);
		bufStr = '"' + word + '"' + "의 검색 결과는 다음과 같습니다.\r\n";
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// 읽어온 버퍼의 내용을 스트링으로 저장한다.
				strTemp = new String(readBuffer);
			}
			String[] words = strTemp.split("\n");

			for (int i = 0; i < line.length; i++) {
				String[] oneLine = words[line[i]].split(" ");// 한 줄에서 단어 저장
				for (int j = 0; j < oneLine.length; j++) {
					if (oneLine[j].indexOf(word) != -1) {// 문자를 찾은 경우
						bufStr += (line[i] + 1) + "번째 줄 " + words[line[i]].indexOf(word) + "번째(index)\r\n";
					}
				}
			}
			if (bufStr.contains("번째")) {
				System.out.println("단어를 찾았습니다.");
				this.resultString += bufStr;
			} else {
				System.out.println("단어를 찾지 못했습니다.");
			}
		} catch (IOException e) {
			System.out.println("단어검색 : 입출력오류");
			this.resultString = "단어검색: 입출력오류";
		}
	}
}