
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import com.google.gson.JsonObject;

public class BatchConversionOld implements Runnable {

	ConcurrentHashMap<Long, String> batch = new ConcurrentHashMap<Long, String>();
	FileConfiguration fileConfig;
	boolean isFinalBatch;

	BatchConversionOld(HashMap<Long, String> batch, FileConfiguration fileConfig, boolean isFinalBatch) {
		this.batch = new ConcurrentHashMap<>(batch);
		this.fileConfig = fileConfig;
		this.isFinalBatch = isFinalBatch;
	}

	public void run() {

		// System.out.println(Thread.currentThread().getName() + " Start.");

		Long lineStart = Collections.min(batch.keySet());

		String fileName = "Batch(" + lineStart + "-" + (lineStart - 1 + batch.size()) + ")";

		PrintWriter writer = null;
		try {
			writer = new PrintWriter("./batches/" + fileName + ".txt", "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {

			e.printStackTrace();
		}

		for (ConcurrentHashMap.Entry<Long, String> lineValue : batch.entrySet()) {

			JsonObject obj = new JsonObject();

			String line = lineValue.getValue();

			String[] fields = line.split(",");

			if (fields.length != fileConfig.getFieldsCount()) {
				System.out.println("Missing Fields");
			} else {

				for (int j = 0; j < fileConfig.getFieldsCount(); j++) {
					obj.addProperty(fileConfig.fields[j], fields[j]);
				}

				FileValues.fieldValues.add(obj);

				writer.println(obj);
			}

		}

		writer.close();

		if (this.isFinalBatch) {
			ThreadPool.shutdownThreadPool();
			// System.out.println(FileValues.getFieldValues());
		}

	}

}
