
public class CreateTableDailyTimer extends TimerManager{

	public CreateTableDailyTimer(String time) {
		super(time);
		
	}
	
	public int task() {
		Executor e = new Executor();
		e.createNextDayTable();
		return 0;
	}
	
}
