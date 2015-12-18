import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class Export {

	public static void main(String[] args) throws Throwable {
		//String fileFrom = "Transactions.txt";
		//String fileTo = "tran.txt";
		//export(fileFrom, fileTo);

	}

	public static void export(String fileFrom, String fileTo) throws IOException {
		File file = new File(fileFrom);
		FileReader fr = new FileReader(file);
		long fileLength = file.length();
		char[] totalBuf = new char[(int) fileLength];
		fr.read(totalBuf);
		String total = new String(totalBuf);
		total.replaceAll("\r", "");
		String[] lines = total.split("\n");
		System.out.println(file.canWrite());
		fr.close();
		for (int i = 0; i < lines.length; i++) {
			System.out.println(lines[i]);
		}

		/////////////////////////////////////////////////////

		File file1 = new File(fileTo);
		FileReader fr1 = new FileReader(file);
		long fileLength1 = file1.length();
		char[] totalBuf1 = new char[(int) fileLength];
		fr1.read(totalBuf1);
		String total1 = new String(totalBuf1);
		total.replaceAll("\r", "");
		String[] lines1 = total1.split("\n");
		System.out.println(file1.canWrite());
		fr.close();
		PrintWriter out = new PrintWriter(file1.getAbsoluteFile());
		for (int i = 0; i < lines1.length - 1; i++) {
			lines1[i] += "\n";
		}
		for (int i = 0; i < lines1.length; i++) {
			out.print(lines1[i]);
		}
		out.close();
	}
}
