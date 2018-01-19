
public class CreateIndexMonthlyTimer extends TimerManager{

	public CreateIndexMonthlyTimer(String time) {
		super(time);
		
	}
	
	public int task() {
		Executor e = new Executor();
		try {
			Thread.sleep(30*1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		e.createNextMonthIndex();
		return 0;
	}
	
}
