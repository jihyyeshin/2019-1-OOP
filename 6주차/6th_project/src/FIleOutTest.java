import java.io.*;
import java.util.Scanner;

public class FIleOutTest {
	static BufferedWriter bufferedWriter;
	public static final String FILE_OUT_PATH="C:\\Users\\tlsjh\\OneDrive\\문서\\2019-1-OOP\\6주차\\6th_project\\src\\text.txt";
	public static void main(String[] args) throws IOException{
		//System.out.println("hello");
		Scanner sc=new Scanner(System.in);
		String findWord=sc.nextLine();
		if(loadWritingFile(FILE_OUT_PATH, false)) {
			bufferedWriter.write(findWord);
			bufferedWriter.close();
		}
		sc.close();
	}
	public static boolean loadWritingFile(String path, boolean isAppend) {
		File textFile=new File(path);
		try {
			FileWriter fw=new FileWriter(textFile, isAppend);
			bufferedWriter = new BufferedWriter(fw);
		}catch(IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
