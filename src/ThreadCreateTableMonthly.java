

public class ThreadCreateTableMonthly  extends Thread{
	CreateTableMonthlyTimer timer;
	public ThreadCreateTableMonthly(){
		this.timer = new CreateTableMonthlyTimer(Parameter.time);
	}
	public void run(){
		this.init();
		timer.setTask();
	}
	public int init(){
		Executor e = new Executor();
		e.createCurMonthTable();
		return 0;
	}
	
}
