import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileInformation {

	private long linesCount;

	public long getLinesCount(String filePath) throws IOException {

		File file = new File(filePath);
		Path path = Paths.get(filePath);

		boolean isFileExists = file.exists();
		linesCount = isFileExists ? Files.lines(path).count() : 0;

		return linesCount;

	}

}
