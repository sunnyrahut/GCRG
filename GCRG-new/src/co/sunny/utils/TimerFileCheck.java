package co.sunny.utils;

import java.util.Timer;

public class TimerFileCheck {
	public static void main(String args[]) throws InterruptedException {
		Timer time = new Timer(); // Instantiate Timer Object
		ScheduledTask st = new ScheduledTask(); // Instantiate SheduledTask
												// class
		time.schedule(st, 0, 86400000); // Create Repetitively task for every 24
										// hours

		// for (;;) {
		// System.out.println("Execution in Main Thread....");
		// Thread.sleep(3000);
		// }
	}
}