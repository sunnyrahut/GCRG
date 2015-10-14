package co.sunny.utils;

import java.util.Timer;

public class TimerFileCheck {
	public static void main(String args[]) throws InterruptedException {
		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTaskNoGap stNoGap = new ScheduledTaskNoGap(); // Instantiate
		// SheduledTask
		// class
		// ScheduledTaskMeteo stMeteo = new ScheduledTaskMeteo();
		time.schedule(stNoGap, 0, 86400000); // Create Repetitively task for
		// every 24 hours
		// time.schedule(stMeteo, 0, 86400000);

		// for (;;) {
		// System.out.println("Execution in Main Thread....");
		// Thread.sleep(3000);
		// }
	}
}