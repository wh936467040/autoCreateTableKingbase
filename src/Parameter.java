import java.io.BufferedReader;
import java.io.FileReader;


public class Parameter {
	private String filePath ;
	public static String time; //task run every day;
	public static String dbUrl;
	public static String dbUser;
	public static String dbPassword;
	public static String sqlFileMonthly;
	public static String sqlFileDaily;
	public static int storgePeriod = 730;  //default 2 years
	public static String sqlIndexDaily = "C:\\Users\\WH\\Desktop\\autoCreateIndexDaily.sql";
	public static String sqlIndexMonthly = "C:\\Users\\WH\\Desktop\\autoCreateIndexMonthly.sql";
	
	public Parameter(String file) {
		this.filePath = file;
	}
	
	public Parameter() {
		
	}
	
	@SuppressWarnings("static-access")
	public int readConf(){
		System.out.println(filePath);
		try {
			// read file content from file
			FileReader reader = new FileReader(filePath);
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			while ((str = br.readLine()) != null) {
				if(str.indexOf("serverdburl:") >= 0 || str.indexOf("SERVERDBURL:") > 0) {
					str = str.replace("serverdburl:", "");
					str = str.trim();
					System.out.println(str);
					this.dbUrl = str;
				} else if(str.indexOf("serverdbuser:") >= 0 || str.indexOf("DBUSER:") > 0) {
					str = str.replace("serverdbuser:", "");
					str = str.trim();
					System.out.println(str);
					this.dbUser = str;
				} else if(str.indexOf("serverdbpassword:") >= 0 || str.indexOf("dbpassword:") > 0) {
					str = str.replace("serverdbpassword:", "");
					str = str.trim();
					System.out.println(str);
					this.dbPassword = str;
				}else if(str.indexOf("tablecreatetime:") >= 0 || str.indexOf("TableCreateTime:") > 0) {
					str = str.replace("tablecreatetime:", "");
					str = str.trim();
					System.out.println(str);
					this.time = str;
				}
				else if(str.indexOf("sqlfilemonthly:") >= 0 || str.indexOf("sqlFileMonthly:") > 0) {
					str = str.replace("sqlfilemonthly:", "");
					str = str.trim();
					System.out.println(str);
					this.sqlFileMonthly = str;
				}
				else if(str.indexOf("sqlfiledaily:") >= 0 || str.indexOf("sqlfiledaily:") > 0) {
					str = str.replace("sqlfiledaily:", "");
					str = str.trim();
					System.out.println(str);
					this.sqlFileDaily = str;
				}else if(str.indexOf("sqlindexdaily:") >= 0 || str.indexOf("sqlindexdaily:") > 0) {
					str = str.replace("sqlindexdaily:", "");
					str = str.trim();
					System.out.println(str);
					this.sqlIndexDaily = str;
				}else if(str.indexOf("sqlindexmonthly:") >= 0 || str.indexOf("sqlindexmonthly:") > 0) {
					str = str.replace("sqlindexmonthly:", "");
					str = str.trim();
					System.out.println(str);
					this.sqlIndexMonthly = str;
				}else if(str.indexOf("storgeperiod:") >= 0 || str.indexOf("storgePeriod:") > 0) {
					str = str.replace("storgeperiod:", "");
					str = str.trim();
					System.out.println(str);
					this.storgePeriod = Integer.parseInt(str);
					if(this.storgePeriod <= 1 ){
						this.storgePeriod = 730;  // 2 years
					}
				}	
			}
			br.close();
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
}
