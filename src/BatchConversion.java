
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.google.gson.JsonObject;

public class BatchConversion implements Runnable {

	ConcurrentHashMap<Long, String> batch = new ConcurrentHashMap<Long, String>();
	FileConfiguration fileConfig;

	BatchConversion(HashMap<Long, String> batch, FileConfiguration fileConfig) {
		this.batch = new ConcurrentHashMap<>(batch);
		this.fileConfig = fileConfig;
	}

	public void run() {

		// System.out.println(Thread.currentThread().getName() + " Start.");

		String jsonLines = "";

		BufferedWriter buffer = MyFileWriter.getBuffer();

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

				jsonLines += obj.toString() + "\n";
			}

		}

		try {
			buffer.write(jsonLines);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			ThreadPool.stopBatchProcess();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
