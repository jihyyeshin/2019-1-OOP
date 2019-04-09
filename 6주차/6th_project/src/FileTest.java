import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileTest {
	public static final String TEXT_PATH = "C:\\Users\\tlsjh\\OneDrive\\문서\\2019-1-OOP\\5주차\\OOP5\\src\\text.txt";// 경로
	public static final int BUFFER_SIZE = 1024;// default value

	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream(TEXT_PATH);
		BufferedInputStream bis = new BufferedInputStream(in, BUFFER_SIZE);
		Scanner sc = new Scanner(System.in);
		String find = sc.nextLine();// 입력받은 문자열
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
			// 읽어온 버퍼의 내용을 스트링으로 저장한다.
			strTemp = new String(readBuffer);
		}
		for (int i = 0; i < find.length(); i++) {
			if (find.charAt(i) != ' ')
				if(strTemp.indexOf(find.charAt(i))!=-1)
						System.out.println(find.charAt(i) + " 문자의 위치: " + strTemp.indexOf(find.charAt(i)));
				else
					System.out.println(find.charAt(i) + " 문자를 찾을 수 없습니다.");
		}
		bis.close();
	}
}
