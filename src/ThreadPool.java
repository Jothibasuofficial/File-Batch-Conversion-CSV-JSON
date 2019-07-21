import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPool {

	static int corePoolSize = 5;
	static int maxPoolSize = 10;
	static long keepAliveTime = 5000;

	static ExecutorService threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize, keepAliveTime,
			TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

	static void stopBatchProcess() throws IOException {

		if (((ThreadPoolExecutor) threadPoolExecutor).getActiveCount() == 1) {
			shutdownThreadPool();
			MyFileWriter.flushBufferedWriter();
			MyFileWriter.closeFileWriter();
			TimeUtil.setEndTime(System.currentTimeMillis());
			System.out.println("Execution time in milliseconds: " + TimeUtil.getTimeElapsed());
			System.out.println("Execution time in seconds: " + TimeUtil.getTimeElapsed() / 1000);
		}

	}

	static void shutdownThreadPool() {
		threadPoolExecutor.shutdown();
	}

}
