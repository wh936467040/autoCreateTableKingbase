
public class CreateTableMonthlyTimer extends TimerManager{

	public CreateTableMonthlyTimer(String time) {
		super(time);
		
	}
	
	public int task() {
		Executor e = new Executor();
		e.createNextMonthTable();
		return 0;
	}
	
}
