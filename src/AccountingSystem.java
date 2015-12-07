import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class AccountingSystem {
	private java.sql.Connection myConn;
	private String url = "jdbc:mysql://127.0.0.1:3306/accountingdatabase";
	private String user = "root";
	private String password = "qwerty123";
	AccountingSystem() throws SQLException{
		myConn = DriverManager.getConnection(url, user, password);
	}

	public void removeCard(String number) throws SQLException {
		java.sql.Statement myStm = myConn.createStatement();
		String sql = "delete from accountingdatabase.database where id=" + number;
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
	
	public void setBalance(String number, double value) throws SQLException {
		java.sql.Statement myStm = myConn.createStatement();
		Double balance = new Double(value);
		String sql = "update accountingdatabase.database " + " set balance='" + balance.toString() + "' where id=" + number;
	}

	public double restatementOfBalances(String number, double transaction) throws SQLException{
		java.sql.Statement myStm = myConn.createStatement();
		ResultSet myRs = myStm.executeQuery("SELECT * FROM accountingdatabase.database");
		Double balance = new Double(0);
		while(myRs.next()){
			if(myRs.getString("id").equals(number)) {
				balance = myRs.getDouble("balance");
				break;
			}
		}
		
		balance -= transaction;
		
		String sql = "update accountingdatabase.database " + " set balance='" + balance.toString() + "' where id=" + number;
		myStm.executeUpdate(sql);
		return balance;
	}
}
