import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileTest {
	public static final String TEXT_PATH = "C:\\Users\\tlsjh\\OneDrive\\����\\2019-1-OOP\\5����\\OOP5\\src\\text.txt";// ���
	public static final int BUFFER_SIZE = 1024;// default value

	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream(TEXT_PATH);
		BufferedInputStream bis = new BufferedInputStream(in, BUFFER_SIZE);
		Scanner sc = new Scanner(System.in);
		String find = sc.nextLine();// �Է¹��� ���ڿ�
		String strTemp = "";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
			// �о�� ������ ������ ��Ʈ������ �����Ѵ�.
			strTemp = new String(readBuffer);
		}
		for (int i = 0; i < find.length(); i++) {
			if (find.charAt(i) != ' ')
				if(strTemp.indexOf(find.charAt(i))!=-1)
						System.out.println(find.charAt(i) + " ������ ��ġ: " + strTemp.indexOf(find.charAt(i)));
				else
					System.out.println(find.charAt(i) + " ���ڸ� ã�� �� �����ϴ�.");
		}
		bis.close();
	}
}
