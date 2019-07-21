public class FileConfiguration {

	private long batchLinesCount;
	private long fieldsCount;
	private char fieldSeperator;
	private boolean isFileHaveHeader;

	static long BATCH_LINES_COUNT = 10;
	static long FIELDS_COUNT = 14;
	static char FIELD_SEPERATOR = ',';
	static boolean IS_FILE_HAVE_HEADER = true;

	//String[] fields = { "Date", "Open", "High", "Low", "Close", "Adj Close", "Volume" };

	String[] fields = { "Region", "Country", "Item Type", "Sales Channel", "Order Priority", "Order Date", "Order ID",
			"Ship Date", "Units Sold", "Unit Price", "Unit Cost", "Total Revenue", "Total Cost", "Total Profit" };

	static String fileName = "";
	
	/*
	 * int byteSize = 1024; int kiloByte = byteSize; int megaByte = 1024 * kiloByte;
	 */

	static int bufferSizeInMb = 4;
	static int bufferSize = bufferSizeInMb * 1024 * 1024;
	

	public static int getBufferSize() {
		return bufferSize;
	}

	public static void setBufferSize(int bufferSize) {
		FileConfiguration.bufferSize = bufferSize;
	}

	public static String getFileName() {
		return fileName;
	}

	public static void setFileName(String fileName) {
		FileConfiguration.fileName = fileName;
	}

	public FileConfiguration(long batchLinesCount, long fieldsCount, char fieldSeperator, boolean isFileHaveHeader) {
		this.batchLinesCount = batchLinesCount;
		this.fieldsCount = fieldsCount;
		this.fieldSeperator = fieldSeperator;
		this.isFileHaveHeader = isFileHaveHeader;
	}

	public long getBatchLinesCount() {
		return batchLinesCount;
	}

	public void setBatchLinesCount(long batchLinesCount) {
		this.batchLinesCount = batchLinesCount;
	}

	public long getFieldsCount() {
		return fieldsCount;
	}

	public void setFieldsCount(long fieldsCount) {
		this.fieldsCount = fieldsCount;
	}

	public char getFieldSeperator() {
		return fieldSeperator;
	}

	public void setFieldSeperator(char fieldSeperator) {
		this.fieldSeperator = fieldSeperator;
	}

	public boolean isFileHaveHeader() {
		return isFileHaveHeader;
	}

	public void setFileHaveHeader(boolean isFileHaveHeader) {
		this.isFileHaveHeader = isFileHaveHeader;
	}

	public static FileConfiguration getFileConfiguration() {

		FileConfiguration fileConfig = new FileConfiguration(BATCH_LINES_COUNT, FIELDS_COUNT, FIELD_SEPERATOR,
				IS_FILE_HAVE_HEADER);

		return fileConfig;
	}

}
