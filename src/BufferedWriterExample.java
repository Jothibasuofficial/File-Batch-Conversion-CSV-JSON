import java.io.*;

public class BufferedWriterExample {
	public static void main(String[] args) throws Exception {
		FileWriter writer = new FileWriter("testout.txt");
		BufferedWriter buffer = new BufferedWriter(writer);
		buffer.write("Sample Writer.");
		buffer.close();
		System.out.println("Success");
	}
}