
public class DropTableTimer extends TimerManager{

	public DropTableTimer(String time) {
		super(time);
		
	}
	public int task() {
		Executor e = new Executor();
		e.dropTableOneDay();
		return 0;
	}
	
}
