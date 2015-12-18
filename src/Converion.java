public class Converion {

	public static double conv(int typeFrom, int typeTo, double transaction) {
		// 1-ruble, 2-dollar, 3-euro
		double dollar_exchange_rate = 70.9320471;
		double euro_exchange_rate = 76.6420769;
		if (typeFrom == 1 && typeTo == 2) {
			double result = transaction / dollar_exchange_rate;
			return result;
		}
		
		if (typeFrom == 2 && typeTo == 1) {
			double result = transaction * dollar_exchange_rate;
			return result;
		}
		
		if (typeFrom == 1 && typeTo == 3) {
			double result = transaction / euro_exchange_rate;
			return result;
		}
		
		if (typeFrom == 3 && typeTo == 1) {
			double result = transaction * euro_exchange_rate;
			return result;
		}
		return 0;
	}

public static int convInt(int typeFrom, double transaction) {
		// 1-dollar, 2-euro
		if (typeFrom == 1) {
			double result = conv(2, 1, transaction) - (conv(2, 1, transaction) % 10);	
			return (int) result;
		}
		if (typeFrom == 2) {
			double result = conv(3, 1, transaction) - (conv(3, 1, transaction) % 10);
			return (int) result;
		}
		return 0;
	}
}