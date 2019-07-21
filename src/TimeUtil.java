
public class TimeUtil {

	private static long startTime;

	private static long endTime;

	public static long getStartTime() {
		return startTime;
	}

	public static void setStartTime(long startTime) {
		TimeUtil.startTime = startTime;
	}

	public static long getEndTime() {
		return endTime;
	}

	public static void setEndTime(long endTime) {
		TimeUtil.endTime = endTime;
	}

	public static long getTimeElapsed() {
		return endTime - startTime;
	}

}
