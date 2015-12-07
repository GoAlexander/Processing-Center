//не интерфейс, а сама программа
public class Terminal {
	private Cards card;
	private int sum;
	private int triesLimit;
	private boolean nextOperation;
	private boolean commitAnOperation;

	public Terminal(InterfClass a) {

		card.number = a.someCard.number;
		triesLimit = 3;
		nextOperation = true;
		commitAnOperation = true;
		try {

			if (nextOperation == false)
				throw new Exception();
			pinInput(a);
			checkValidity();
			checkIfNotInTheStopList();
			sum = a.sumOfOperationInput();
			proofOfAuthorization();

		} catch (Exception e) {
			commitAnOperation = false;
		}

	}

	// сравнение с базой
	// real- реальный пин-код карты
	void pinInput(InterfClass a) {
		// из бд
		int real = 0;// поиск по номеру карты
		for (int i = 0; i < triesLimit; i++) {
			a.interfPinCode();
			if (a.someCard.pin == real) {
				card.pin = a.someCard.pin;
				break;
			} else if (i == 2) {
				// вывод в интерфейс
				System.out.println("No more tries.Your card will be blocked for one day");
				nextOperation = false;
			} else
				System.out.println("incorrect pin-code");
		}
	}

	private boolean checkValidity() {
		boolean res = false;
		if (commitAnOperation == true) {
			// card.validity=;//срок действия карты из бд
			if (card.validity == false)
				nextOperation = false;
		}
		return res;
	}
	
	private boolean checkIfNotInTheStopList(){
		if (commitAnOperation == true);
		//card.BL=false;//проверка по базе
		return false;
	}

	void commitAuthorization() {
		if (proofOfAuthorization() == true)
			authorizationRequest();
		else
			;// совершить транзакцию без авторизации
			//совершать сразу здесь?
			//предполагается, что позже данные будут изменены (остаток по счету)
	}

	// сравнение с данными бд. проверка, нужна ли авторизация вообще
	boolean proofOfAuthorization() {
		boolean res = false;
		int DBSum = 0;// для этого нужен номер карты
		if (commitAnOperation == true) {
			if (sum <= DBSum)
				res = false;
		}
		return res;
	}

	void authorizationRequest() {//какие параметры передавать?
		if (commitAnOperation == true)
			receivingProcessingOfAnswer();
	}

	void receivingProcessingOfAnswer() {//нужны ли параметры?
		if (commitAnOperation == true)
			;
	}

	// вывод чека в интерфейсе
	void checkPrinting() {
		if (commitAnOperation == true)
			;

	}

	public static void main(String[] args) {
		InterfClass someInterf=new InterfClass();
		Terminal t = new Terminal(someInterf);
	}
}
