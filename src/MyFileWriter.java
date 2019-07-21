import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyFileWriter {

	static FileWriter fileWriter;
	static BufferedWriter buffer;

	public static BufferedWriter getBuffer() {
		return buffer;
	}

	public static void setBuffer(BufferedWriter buffer) {
		MyFileWriter.buffer = buffer;
	}

	public static void initializeFileWriter() {
		try {
			String fName = FileConfiguration.getFileName();
			String[] name = fName.split("\\.");
			fileWriter = new FileWriter(name[0]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void closeFileWriter() throws IOException {
		fileWriter.close();
	}

	public static void initializeFileBuffer() {
		try {
			String fName = FileConfiguration.getFileName();
			String[] name = fName.split("\\.");
			fileWriter = new FileWriter(name[0]);
			buffer = new BufferedWriter(fileWriter, FileConfiguration.getBufferSize());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void closeBufferedWriter() throws IOException {
		buffer.close();
	}
	
	public static void flushBufferedWriter() throws IOException {
		buffer.flush();
	}
}
