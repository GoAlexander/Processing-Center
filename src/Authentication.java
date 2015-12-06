
public class Authentication {
	//Данные пин кодов
	static String pin = "1234";
	
	public static boolean authenticatePIN(String enteredPin) {
		return (enteredPin.equals(pin));
	}

}
