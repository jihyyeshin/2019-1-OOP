import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class FileTest {
	public static final String TEXT_PATH = "C:\\Users\\tlsjh\\OneDrive\\����\\2019-1-OOP\\5����\\OOP5\\src\\text.txt";//���
	public static final int BUFFER_SIZE = 1024;// default value

	public static void main(String[] args) throws IOException {
		FileInputStream in = new FileInputStream(TEXT_PATH);
		BufferedInputStream bis = new BufferedInputStream(in, BUFFER_SIZE);
		Scanner sc = new Scanner(System.in);
		String find = sc.next();// �Է¹��� ���ڿ�
		String strTemp="";
		byte[] readBuffer = new byte[BUFFER_SIZE];
		while (bis.read(readBuffer, 0, readBuffer.length) != -1) {
			// �о�� ������ ������ ��Ʈ������ �����Ѵ�.
			strTemp = new String(readBuffer);
		}
		if(strTemp.indexOf(find)!=-1)
			System.out.println(find+" ���ڿ��� ��ġ�� "+strTemp.indexOf(find));//���ڿ� ��ġ ���
		else
			System.out.println("text.txt�� ���ڿ��� �����ϴ�.");
		
		bis.close();
	}
}
