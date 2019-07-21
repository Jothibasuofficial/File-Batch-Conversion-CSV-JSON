import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class MyFileReader {

	static FileConfiguration fileConfig = FileConfiguration.getFileConfiguration();
	static long numberOfBatches = 1;
	static long totalBatches = 1;

	ConcurrentHashMap<Long, String> batch = new ConcurrentHashMap<Long, String>();

	public static void startFileProcessing(String filePath, long linesCount) throws IOException {

		MyFileWriter.initializeFileBuffer();

		BufferedReader fileContent = new BufferedReader(new FileReader(filePath));

		String line;

		long count = 0; // For batch processing, reset purpose
		long currentLine = 1;

		long batchLinesCount = fileConfig.getBatchLinesCount();
		linesCount = fileConfig.isFileHaveHeader() ? linesCount - 1 : linesCount;

		HashMap<Long, String> lines = new HashMap<Long, String>();

		if (linesCount <= batchLinesCount) {
			totalBatches = 1;
		} else {

			totalBatches = (linesCount % batchLinesCount == 0) ? (linesCount / batchLinesCount)
					: (linesCount / batchLinesCount) + 1;
		}

		// Reading the entire file
		while ((line = fileContent.readLine()) != null) {

			// Skipping the header, if have any
			if (!(fileConfig.isFileHaveHeader() && currentLine == 1)) {
				lines.put(fileConfig.isFileHaveHeader() ? currentLine - 1 : currentLine, line);
				count++;
			}

			if (count == batchLinesCount) {
				processBatch(lines);
				lines.clear();
				count = 0;
			} else if ((count < batchLinesCount) && (numberOfBatches == totalBatches)
					&& (count == linesCount % batchLinesCount)) { // For last batch, if not having exact lines
				processBatch(lines);
				lines.clear();
				count = 0;
			}

			currentLine++;

		}

		fileContent.close();

	}

	public static void processBatch(HashMap<Long, String> batch) {

		numberOfBatches++;

		Runnable batchObject = new BatchConversion(batch, fileConfig);
		ThreadPool.threadPoolExecutor.submit(batchObject);

	}

}
