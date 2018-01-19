

public class ThreadCreateTableDaily  extends Thread{
	CreateTableDailyTimer timer;
	public ThreadCreateTableDaily(){
		this.timer = new CreateTableDailyTimer(Parameter.time);
	}
	public void run(){
		this.init();
		timer.setTask();
	}
	public int init(){
		Executor e = new Executor();
		e.createCurDayTable();
		return 0;
	}
}

