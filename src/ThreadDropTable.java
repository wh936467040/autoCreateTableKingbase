

public class ThreadDropTable extends Thread{
	//drop daily create table;
	DropTableTimer timer;
	public ThreadDropTable(){
		this.timer = new DropTableTimer(Parameter.time);
	}
	public void run(){
		this.init();
		timer.setTask();
	}
	public int init(){
		Executor e = new Executor();
		e.dropTablePeriod();
		return 0;
	}
	
}
