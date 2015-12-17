
public class Converion {
	public static double fromDtoR(double transaction) {
		double dollar_course = 70.9320471;
		double result = transaction * dollar_course;
		return result;
	}
	
	public static double fromRtoD(double transaction) {
		double dollar_exchange_rate = 70.9320471;
		double result = transaction / dollar_exchange_rate;
		return result;
	}
	
	public static double fromRtoE(double transaction) {
		double euro_exchange_rate = 76.6420769;
		double result = transaction / euro_exchange_rate;
		return result;
	}
	
	public static double fromEtoR(double transaction) {
		double euro_exchange_rate = 76.6420769;
		double result = transaction * euro_exchange_rate;
		return result;
	}
}
