

public class ThreadCreateIndexDaily  extends Thread{
	CreateIndexDailyTimer timer;
	public ThreadCreateIndexDaily(){
		this.timer = new CreateIndexDailyTimer(Parameter.time);
	}
	public void run(){
		this.init();
		timer.setTask();
	}
	public int init(){
		try {
			Thread.sleep(30*1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Executor e = new Executor();
		e.createCurDayIndex();
		return 0;
	}
}

