import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public abstract class TimerManager {
	int hour;
	int min;
	int sec;
	public TimerManager(String time){
		time = time.trim();
		System.out.println(time.substring(0,2) + ":" + time.substring(3,5) 
				+ ":" + time.substring(6,8));
		this.hour = Integer.parseInt(time.substring(0,2));
		this.min = Integer.parseInt(time.substring(3,5));
		this.sec = Integer.parseInt(time.substring(6,8));
	}
	public void setTask() {
		timer4();
	}

	public abstract int task();

	/*
	 * // 第一种方法：设定指定任务task在指定时间time执行 schedule(TimerTask task, Date time) public
	 * static void timer1() { Timer timer = new Timer(); timer.schedule(new
	 * TimerTask() { public void run() {
	 * System.out.println("-------设定要指定任务--------"); } }, 2000);//
	 * 设定指定的时间time,此处为2000毫秒 }
	 * 
	 * // 第二种方法：设定指定任务task在指定延迟delay后进行固定延迟peroid的执行 // schedule(TimerTask task,
	 * long delay, long period) public static void timer2() { Timer timer = new
	 * Timer(); timer.schedule(new TimerTask() { public void run() {
	 * System.out.println("-------设定要指定任务--------"); } }, 1000, 5000); }
	 * 
	 * // 第三种方法：设定指定任务task在指定延迟delay后进行固定频率peroid的执行。 //
	 * scheduleAtFixedRate(TimerTask task, long delay, long period) public
	 * static void timer3() { Timer timer = new Timer();
	 * timer.scheduleAtFixedRate(new TimerTask() { public void run() {
	 * System.out.println("-------设定要指定任务--------"); } }, 1000, 2000); }
	 */

	// 第四种方法：安排指定的任务task在指定的时间firstTime开始进行重复的固定速率period执行．
	// Timer.scheduleAtFixedRate(TimerTask task,Date firstTime,long period)
	public void timer4() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, this.hour); // 控制时
		calendar.set(Calendar.MINUTE, this.min); // 控制分
		calendar.set(Calendar.SECOND, this.sec); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的12：00：00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务--------");
				task();
			}
		}, time, 1000 * 60 * 60 * 24);// 这里设定将延时每天固定执行
	}
}