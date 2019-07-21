import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {

		TimeUtil.setStartTime(System.currentTimeMillis());

		String filePath = "100000_Sales Records.csv";

		FileConfiguration.setFileName(filePath);

		FileInformation fileInfo = new FileInformation();
		long linesCount = fileInfo.getLinesCount(filePath);

		if (linesCount <= 0) {
			System.out.println("No lines to read");
		} else {

			MyFileReader.startFileProcessing(filePath, linesCount);
		}

	}

}
