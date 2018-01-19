

public class ThreadCreateIndexMonthly  extends Thread{
	CreateIndexMonthlyTimer timer;
	public ThreadCreateIndexMonthly(){
		this.timer = new CreateIndexMonthlyTimer(Parameter.time);
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
		e.createCurMonthIndex();
		return 0;
	}
}
