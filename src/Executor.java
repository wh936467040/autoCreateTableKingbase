import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.kedong.dbconnection.conn;

public class Executor {

	public Executor() {

	}

	public int createCurDayTable() {
		String time = getToday();
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		createTable(time, Parameter.sqlFileDaily, Connection);
		Connection.destoryConnection();
		return 1;
	}

	public int createCurDayIndex() {
		String time = getToday();
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		createIndex(time, Parameter.sqlIndexDaily, Connection);
		Connection.destoryConnection();
		return 1;
	}

	public int createNextDayTable() {
		String time = getNextDay();
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		createTable(time, Parameter.sqlFileDaily, Connection);
		Connection.destoryConnection();
		return 1;
	}

	public int createNextDayIndex() {
		String time = getNextDay();
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		createIndex(time, Parameter.sqlIndexDaily, Connection);
		Connection.destoryConnection();
		return 1;
	}

	public int createCurMonthTable() {
		String time = getCurMonth();
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		createTable(time, Parameter.sqlFileMonthly, Connection);
		Connection.destoryConnection();
		return 1;
	}

	public int createCurMonthIndex() {
		String time = getCurMonth();
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		createIndex(time, Parameter.sqlIndexMonthly, Connection);
		Connection.destoryConnection();
		return 1;
	}

	public int createNextMonthTable() {
		String time = getNextMonth();
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		createTable(time, Parameter.sqlFileMonthly, Connection);
		Connection.destoryConnection();
		return 1;
	}

	public int createNextMonthIndex() {
		String time = getNextMonth();
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		createIndex(time, Parameter.sqlIndexMonthly, Connection);
		Connection.destoryConnection();
		return 1;
	}

	public int dropTablePeriod() {
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		Date today = new Date();
		String endTime = date2String(getDay(today, -Parameter.storgePeriod));

		String startTime = "20170201";

		LinkedList<String> days = getPeriodDay(startTime, endTime);
		System.out.println(startTime + "****" + endTime);
		System.out.println("----------------");
		for (int i = 0; i < days.size(); i++) {
			System.out.println(days.get(i));
			this.dropTable(days.get(i), Parameter.sqlFileDaily, Connection);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {

			}
		}
		System.out.println("----------------");
		System.out.println("***" + endTime);
		System.out.println("***---" + Parameter.storgePeriod);
		Connection.destoryConnection();
		return 1;
	}

	public int dropTableOneDay() {
		conn Connection = new conn(Parameter.dbUrl, Parameter.dbUser,
				Parameter.dbPassword);
		Connection.initJdbcConnection();
		Date today = new Date();
		String time = date2String(getDay(today, -Parameter.storgePeriod));
		this.dropTable(time, Parameter.sqlFileDaily, Connection);
		Connection.destoryConnection();
		return 1;
	}

	int createTable(String time, String filePath, conn Connection) {
		InputStream in = null;
		InputStreamReader inReader = null;
		BufferedReader instream = null;
		File file = null;
		file = new File(filePath);
		System.out.println(file.getAbsoluteFile());
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		inReader = new InputStreamReader(in);
		instream = new BufferedReader(inReader);
		String tmp = "";
		String sql = "";
		String regex = "";
		String result = "";
		System.out.println(time);
		Pattern p = Pattern.compile(regex);
		regex = "CREATE TABLE \".*?\\(";
		p = Pattern.compile(regex);
		try {
			while ((tmp = instream.readLine()) != null) {
				Matcher m = p.matcher(tmp);
				m = p.matcher(tmp);
				if (m.find()) {
					if (sql.length() > 2) {
						System.out.println("***********");
						System.out.println(sql);
						try {
							Connection.ExecuteSql(sql);
						} catch (SQLException e) {
							System.out.println("crete table error");
							e.printStackTrace();
						}
						sql = "";
					}
					result = m.group();
					System.out.println("Result: " + result);
					String last = tmp.substring(result.length(), tmp.length());
					result = result.substring(0, result.length() - 2);
					result = result + "_" + time + "\" ";
					result = result + last + "(" + '\n';
					sql = sql + result;
				} else {
					sql = sql + tmp + '\n';
				}
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (sql.length() > 2) {
			System.out.println("--------------------");
			System.out.println(sql);
			System.out.println("--------end---------");
			try {
				Connection.ExecuteSql(sql);
			} catch (SQLException e) {
				System.out.println("crete table error");
				e.printStackTrace();
			}
			sql = "";
		}
		try {
			instream.close();
			inReader.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Connection.destoryConnection();
		return 0;
	}

	private int createIndex(String time, String filePath, conn Connection) {

		InputStream in = null;
		InputStreamReader inReader = null;
		BufferedReader instream = null;
		File file = null;
		file = new File(filePath);
		System.out.println(file.getAbsoluteFile());
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		inReader = new InputStreamReader(in);
		instream = new BufferedReader(inReader);
		String tmp = "";
		String sql = "";
		String regex = "";
		System.out.println(time);
		Pattern p = Pattern.compile(regex);
		regex = "CREATE INDEX *";
		p = Pattern.compile(regex);
		String tableName = "";
		String indexName = "";
		try {
			while ((tmp = instream.readLine()) != null) {
				Matcher m = p.matcher(tmp);
				m = p.matcher(tmp);
				if (m.find()) {
					if (sql.length() > 2) {
						try {
							Connection.ExecuteSql(sql);
						} catch (SQLException e) {
							System.out.println("crete index " + tableName
									+ " failed ");
							e.printStackTrace();
						}
						sql = "";
					}
					tmp = tmp.replace("CREATE INDEX", "").trim();
					indexName = tmp.replaceAll(" on (.*)", "");
					indexName = indexName.trim();
					tmp = tmp.trim().replace(indexName, "").replace(" on ", "")
							.trim();
					tableName = tmp.replaceAll("\\(.*", "");
					String indexValue = tmp.replace(tableName, "");
					tableName = tableName + "_" + time;
					indexName = indexName + "_" + time;
					// System.out.println(tableName);
					// System.out.println(indexName);
					// System.out.println(indexValue);
					sql = "CREATE INDEX " + indexName + " on " + tableName
							+ indexValue;
					System.out.println(sql);
				} else {
					sql = sql + tmp + '\n';
				}
			}
			if (sql.length() > 2) {
				try {
					Connection.ExecuteSql(sql);
				} catch (SQLException e) {
					System.out.println(sql + "crete index error");
				}
				sql = "";
			}
			instream.close();
			inReader.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	private int dropTable(String time, String filePath, conn Connection) {
		InputStream in = null;
		InputStreamReader inReader = null;
		BufferedReader instream = null;
		File file = null;
		file = new File(filePath);
		try {
			in = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		inReader = new InputStreamReader(in);
		instream = new BufferedReader(inReader);
		String tmp = "";
		String sql = "";
		String regex = "";
		String result = "";
		System.out.println(time);
		Pattern p = Pattern.compile(regex);
		regex = "CREATE TABLE \".*?\" ";
		p = Pattern.compile(regex);
		String tableName = "";
		try {
			while ((tmp = instream.readLine()) != null) {
				Matcher m = p.matcher(tmp);
				m = p.matcher(tmp);
				if (m.find()) {
					if (sql.length() > 2) {
						try {
							System.out.println(sql);
							Connection.ExecuteSql(sql);
							System.out.println("DROP TABLE  " + tableName
									+ " success ");
						} catch (SQLException e) {
							System.out.println("DROP TABLE  " + tableName
									+ " failed ");
							e.printStackTrace();
						}
						sql = "";
					}
					result = m.group();
					String last = tmp.substring(result.length(), tmp.length());
					result = result.substring(0, result.length() - 2);
					result = result + "_" + time + "\" ";
					tableName = result;
					tableName = tableName.replace("CREATE TABLE ", "");

					result = result + last + '\n';
					sql = "drop table  " + tableName;
				}
			}
			if (sql.length() > 2) {
				try {
					Connection.ExecuteSql(sql);
				} catch (SQLException e) {
					System.out.println("drop table error");
				}
				sql = "";
			}
			instream.close();
			inReader.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private String getNextDay() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		String time = null;
		cal.setTime(date);
		cal.add(Calendar.DATE, 1);
		Date nextDay = cal.getTime();
		cal.setTime(nextDay);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String mon = "";
		if (month < 10) {
			mon = "0" + String.valueOf(month);
		} else {
			mon = String.valueOf(month);
		}
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String d = "";
		if (day < 10) {
			d = "0" + String.valueOf(day);
		} else {
			d = String.valueOf(day);
		}
		time = String.valueOf(year) + mon + d;
		return time;
	}

	private String getToday() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		String time = null;
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String mon = "";
		if (month < 10) {
			mon = "0" + String.valueOf(month);
		} else {
			mon = String.valueOf(month);
		}
		int day = cal.get(Calendar.DAY_OF_MONTH);
		String d = "";
		if (day < 10) {
			d = "0" + String.valueOf(day);
		} else {
			d = String.valueOf(day);
		}
		time = String.valueOf(year) + mon + d;
		return time;
	}

	private String getCurMonth() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		String time = null;
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		String mon = "";
		if (month < 10) {
			mon = "0" + String.valueOf(month);
		} else {
			mon = String.valueOf(month);
		}
		time = String.valueOf(year) + mon;
		return time;
	}

	private String getNextMonth() {
		Calendar cal = Calendar.getInstance();
		Date date = new Date();
		cal.setTime(date);
		String time = null;
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 12) {
			month = 1;
			year = year + 1;
		} else {
			month = month + 1;
		}
		String mon = "";
		if (month < 10) {
			mon = "0" + String.valueOf(month);
		} else {
			mon = String.valueOf(month);
		}
		time = String.valueOf(year) + mon;
		return time;
	}

	private LinkedList<String> getPeriodDay(String stratTime, String endTime) {
		LinkedList<String> days = new LinkedList<String>();
		Date date = string2Date(stratTime);
		while (true) {
			String day = date2String(getNextDay(date));
			date = string2Date(day);
			days.add(day);
			if (day.compareTo(endTime) >= 0) {
				break;
			}
		}
		return days;
	}

	private LinkedList<String> getDaySBefore(Date date, int n) {
		LinkedList<String> days = new LinkedList<String>();
		date = getDay(date, -n);
		int i = 0;
		while (true) {
			date = getDay(date, -1);
			String day = date2String(date);
			System.out.println("***" + day);
			days.add(day);
			i++;
			if (i > 31) {
				break;
			}
		}
		return days;
	}

	private Date getNextDay(Date date) {
		return getDay(date, 1);
	}

	private Date getDay(Date date, int n) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, n);
		Date nextDay = cal.getTime();
		return nextDay;
	}

	private Date string2Date(String time) {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = null;
		try {
			date = format.parse(time);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	private String date2String(Date date) {
		DateFormat format = new SimpleDateFormat("yyyyMMdd");
		String time = format.format(date);
		return time;
	}

}
