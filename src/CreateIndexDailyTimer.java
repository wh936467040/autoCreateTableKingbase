
public class CreateIndexDailyTimer extends TimerManager{

	public CreateIndexDailyTimer(String time) {
		super(time);
		
	}
	
	public int task() {
		try {
			Thread.sleep(30*1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		Executor e = new Executor();
		e.createNextDayIndex();
		return 0;
	}
	
}
