/*Editor 클래스의 기능을 구현하시오.
단어 검색 기능 – 텍스트파일에서 단어를 검색, 단어는 스페이스로만 구분됨. 줄,버퍼 사이의 단어 걸침 처리 필요  
단어 변환 기능 – 검색된 단어를 변환할 단어로 바꿔 새로운 파일로 출력
단어 삽입 기능 – 삽입 할 단어와 행과 열을 입력한 후 ‘추가’ 버튼을 클릭하면 입력한 행과 열에 단어가 삽입된다.
검색결과 출력 기능
*/
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Editor {
	public static final int BUFFER_SIZE = 28755968;// text file size
	static BufferedWriter bufferedWriter;
	static Scanner sc = new Scanner(System.in);

	protected String find = "";
	protected String resultString = "";
	protected String newWord = "";
	protected String findText = "";
	protected BufferedInputStream bis = null;
	protected FileInputStream in = null;

	String getFile(String findText) {
		this.findText = findText;// 텍스트파일명 입력
		try {
			in = new FileInputStream(this.findText);
		} catch (FileNotFoundException e) {
			return " 파일이 존재하지 않습니다.";
		}
		return "검색결과: 완료되었습니다.";
	}

	/* 단어 검색 기능 – 텍스트파일에서 단어를 검색, 단어는 스페이스로만 구분됨. 줄,버퍼 사이의 단어 걸침 처리 필요 */
	ArrayList<String> findWord(String find) {
		bis = new BufferedInputStream(in);
		String strTemp = "";
		ArrayList<String> al=new ArrayList<String>();
		String buf="";
		byte[] readBuffer = new byte[BUFFER_SIZE];

		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// 읽어온 버퍼의 내용을 스트링으로 저장한다.
				strTemp = new String(readBuffer);
			}
		} catch (IOException e) {
			return al;
		}
		String[] words = strTemp.split("\n");
		for (int i = 0; i < words.length; i++) {
			String[] oneLine = words[i].split(" ");// 한 줄에서 단어 저장
			for (int j = 0; j < oneLine.length; j++) {
				if (oneLine[j].indexOf(find) != -1) {// 문자를 찾은 경우
					resultString += (i + 1) + "번째 줄 " + words[i].indexOf(find) + "번째\r\n";
					al.add((i + 1) + "번째 줄 " + words[i].indexOf(find) + "번째");
				}
			}
		}
		/* 단어 걸침 처리 */
		for (int k = 1; k < find.length(); k++) {
			buf = find.substring(0, k).concat("\r\n");
			buf = buf.concat(find.substring(k));
			if(strTemp.contains(buf)) {
				resultString+=strTemp.indexOf(buf)+"번째 단어\r\n";
				al.add(strTemp.indexOf(buf)+"번째 단어");
			}
		}

		return al;
	}
	/* 단어 변환 기능 – 검색된 단어를 변환할 단어로 바꿔 새로운 파일로 출력 */
	String replaceWord(String find, String newWord) {
		bis = new BufferedInputStream(in);
		String buf;
		byte[] readBuffer = new byte[BUFFER_SIZE];

		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// 읽어온 버퍼의 내용을 스트링으로 저장한다.
				resultString = new String(readBuffer);
			}
		} catch (IOException e) {
			return "검색결과: 입출력오류";
		}
		resultString = resultString.replace(find, newWord);
		/* 엔터를 포함하는 단어가 검색 및 수정이 가능하게 하기 위한 코드 */
		for (int i = 1; i < find.length(); i++) {
			buf = find.substring(0, i).concat("\r\n");
			buf = buf.concat(find.substring(i));
			resultString = resultString.replace(buf, newWord);
		}
		return "검색결과: 완료되었습니다.";
	}

	/* 단어 삽입 기능 – 삽입 할 단어와 행과 열을 입력한 후 ‘추가’ 버튼을 클릭하면 입력한 행과 열에 단어가 삽입된다. */
	String append(String str, int col, int row) {
		bis = new BufferedInputStream(in);
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		try {
			while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
				// 읽어온 버퍼의 내용을 스트링으로 저장한다.
				strTemp = new String(readBuffer);
			}
			String[] words = strTemp.split("\n");// \n를 기준으로 텍스트 파일을 자름
			String[] oneLines = words[col].split(" ");// 공백을 기준으로 한 줄을 자름
			oneLines[row] += str;
			words[col] = "";// 초기화 후
			for (int i = 0; i < oneLines.length; i++)
				words[col] += oneLines[i];// 저장
			for (int i = 0; i < words.length; i++)
				this.resultString += (words[i] + "\r\n");
		} catch (IOException e) {
			return "검색결과: 입출력오류";
		} catch (ArrayIndexOutOfBoundsException e) {
			return "검색결과: 해당 행, 열이 없습니다.";
		}
		return "검색결과: 완료되었습니다.";
	}

	/* 검색결과 출력 기능 */
	String printToFile(String path) {
		File textFile = new File(path);
		FileWriter fw = null;

		if (textFile.exists()) {
			try {
				fw = new FileWriter(textFile, false);// 파일에 덮어씀
			} catch (IOException e) {
				return "검색결과: 파일 열기 오류";
			}
		} else {
			try {
				fw = new FileWriter(textFile);
			} catch (IOException e) {
				return "검색결과: 파일 생성 오류";
			}
		}
		bufferedWriter = new BufferedWriter(fw);
		try {
			bufferedWriter.write(resultString);
			bufferedWriter.close();
		} catch (IOException e) {
			return "검색결과: 파일 입력 오류";
		}
		return "검색결과: 완료되었습니다.";
	}
}
