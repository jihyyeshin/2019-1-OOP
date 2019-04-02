import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileTest {
	public static final String TEXT_PATH = "C:\\Users\\tlsjh\\OneDrive\\문서\\2019-1-OOP\\5주차\\OOP5\\src\\text.txt";//경로
	public static final int BUFFER_SIZE = 1024;// default value

	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream(TEXT_PATH);
		BufferedInputStream bis = new BufferedInputStream(in, BUFFER_SIZE);
		Scanner sc = new Scanner(System.in);
		String find = sc.next();// 입력받은 문자열
		String strTemp="";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
			// 읽어온 버퍼의 내용을 스트링으로 저장한다.
			strTemp = new String(readBuffer);
		}
		if(strTemp.indexOf(find)!=-1)
			System.out.println(find+" 문자열의 위치는 "+strTemp.indexOf(find));//문자열 위치 출력
		else
			System.out.println("text.txt에 문자열이 없습니다.");
		
		bis.close();
	}
}
