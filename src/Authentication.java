
public class Authentication {
	//������ ��� �����
	static String pin = "1234";
	
	public static boolean authenticatePIN(String enteredPin) {
		return (enteredPin.equals(pin));
	}

}
