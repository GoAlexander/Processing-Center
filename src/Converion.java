public class Converion {

	public static double conv(int typeFrom, int typeTo, double transaction) {
		// 1-ruble, 2-dollar, 3-euro
		double dollar_exchange_rate = 70.9320471;
		double euro_exchange_rate = 76.6420769;
		if (typeFrom == 1 && typeTo == 2) {
			double result = transaction * dollar_exchange_rate;
			return result;
		}
		
		if (typeFrom == 2 && typeTo == 1) {
			double result = transaction / dollar_exchange_rate;
			return result;
		}
		
		if (typeFrom == 1 && typeTo == 3) {
			double result = transaction * euro_exchange_rate;
			return result;
		}
		
		if (typeFrom == 3 && typeTo == 1) {
			double result = transaction / euro_exchange_rate;
			return result;
		}
		return 0;
	}
}