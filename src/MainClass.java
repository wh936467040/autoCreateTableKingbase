

public class MainClass {

	public static void main(String[] args) {
		String path = System.getenv("D5000_HOME");
		path = path + "/conf/auto_monitor.conf";
		//String path = "C:\\Users\\WH\\Desktop\\auto_monitor.conf";
		Parameter para = new Parameter(path);
		para.readConf();
		System.out.println(Parameter.sqlFileDaily);
		System.out.println(Parameter.sqlFileMonthly);
		System.out.println(Parameter.time);

		ThreadCreateTableDaily t1 = new ThreadCreateTableDaily();
		ThreadCreateTableMonthly t2 = new ThreadCreateTableMonthly();
		ThreadDropTable t3 = new ThreadDropTable();
		ThreadCreateIndexDaily t4 = new ThreadCreateIndexDaily();
		ThreadCreateIndexMonthly t5 = new ThreadCreateIndexMonthly();

		t1.run();
		t2.run();
		t4.run();
		t5.run();
		//t3.run();	
	}
}