import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class AccountingSystem {
	private java.sql.Connection myConn;
	private String url = "jdbc:mysql://127.0.0.1:3306/accountingdatabase";
	private String user = "root";
	private String password = "RomanNumberOne";
	AccountingSystem() throws SQLException{
		myConn = DriverManager.getConnection(url, user, password);
	}

	public void removeCard(String number) throws SQLException {
		java.sql.Statement myStm = myConn.createStatement();
		String sql = "delete from accountingdatabase.database where id=" + number;
		myStm.executeUpdate(sql);
	}
	
	public double getBalance(String number) throws SQLException{
		java.sql.Statement myStm = myConn.createStatement();
		ResultSet myRs = myStm.executeQuery("SELECT * FROM accountingdatabase.database");
		Double balance = new Double(0);
		while(myRs.next()){
			if(myRs.getString("id").equals(number)) {
				balance = myRs.getDouble("balance");
				break;
			}
		}
		return balance;
	}
	
	public void addCard(String number, double value) throws SQLException{
		java.sql.Statement myStm = myConn.createStatement();
		Double balance = new Double(value);
		String sql = "insert into accountingdatabase.database " + 
		" (id, balance, transactions, status) " + 
				" values('" + number + "', '" + balance.toString() + "', 'Transactions:' , '0')";
		myStm.executeUpdate(sql);
	}
	
	public void setBalance(String number, double value) throws SQLException {
		java.sql.Statement myStm = myConn.createStatement();
		Double balance = new Double(value);
		String sql = "update accountingdatabase.database " + " set balance='" + balance.toString() + "' where id=" + number;
		myStm.executeUpdate(sql);
	}

	public double onlineRestatement(String number, double transaction) throws SQLException{
		java.sql.Statement myStm = myConn.createStatement();
		ResultSet myRs = myStm.executeQuery("SELECT * FROM accountingdatabase.database");
		Double balance = new Double(transaction);
		String transactionBuf = balance.toString();
		while(myRs.next()){
			if(myRs.getString("id").equals(number)) {
				transactionBuf = transactionBuf + " " + myRs.getString("transactions");
				balance = myRs.getDouble("balance");
				break;
			}
		}
		String sql = "update accountingdatabase.database " + " set transactions='" + transactionBuf + "' where id=" + number;
		myStm.executeUpdate(sql);
		balance -= transaction;
		sql = "update accountingdatabase.database " + " set balance='" + balance.toString() + "' where id=" + number;
		myStm.executeUpdate(sql);
		sql = "update accountingdatabase.database " + " set status='1' where id=" + number;
		myStm.executeUpdate(sql);
		return balance;
	}
	
	public void offlineRestatement(String filePath) throws SQLException, IOException{
		File file = new File(filePath);
		FileReader fr = new FileReader(file);
		long fileLength = file.length();
		char[]totalBuf = new char[(int)fileLength];
		fr.read(totalBuf);
		String total = new String(totalBuf);
		total.replaceAll("\r", "");
		String[] lines = total.split("\n");
		fr.close();
		
		for (int i = 0; i < lines.length; i++) {
            String fields[] = lines[i].split(";");
            Double balance = new Double(onlineRestatement(fields[0], Double.parseDouble(fields[1])));
            String sql = "update accountingdatabase.database " + " set status=1 where id=" + fields[0];
            java.sql.Statement myStm = myConn.createStatement();
            myStm.executeUpdate(sql);
            lines[i] += balance.toString() + ";+;";
		}
		
		FileWriter fw = new FileWriter(file);
		PrintWriter pw = new PrintWriter(fw);
		for(int i = 0; i < lines.length; i++){
			pw.println(lines[i]);
		}
		pw.close();
	}
}
