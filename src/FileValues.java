import java.io.FileWriter;
import java.io.IOException;

import java.util.concurrent.CopyOnWriteArrayList;
import com.google.gson.JsonObject;

public class FileValues {

	static CopyOnWriteArrayList<JsonObject> fieldValues = new CopyOnWriteArrayList<JsonObject>();

	static FileWriter fileWriter;

	/*
	 * int byteSize = 1024; int kiloByte = byteSize; int megaByte = 1024 * kiloByte;
	 */

	static int bufferSizeInMb = 4;
	static int bufferSize = bufferSizeInMb * 1024 * 1024;
	static String fileName = "data.txt";

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		FileValues.fileName = fileName;
	}

	public static FileWriter getFileWriter() {
		return fileWriter;
	}

	public static void setFileWriter(FileWriter fileWriter) {
		FileValues.fileWriter = fileWriter;
	}

	public static void initializeFileWriter() {
		try {
			String fName = getFileName();
			String[] name = fName.split("\\.");
			fileWriter = new FileWriter(name[0]);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static CopyOnWriteArrayList<JsonObject> getFieldValues() {
		return fieldValues;
	}

	public static void closeFileWriter() throws IOException {
		fileWriter.close();
	}
}
