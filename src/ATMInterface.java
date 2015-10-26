
public class ATMInterface {
	//structures
	class Answer { //TODO Make it
		
	}
	class Bonds { //TODO Make it
		
	}
	class Sum {
		double sum;
		int type_of_money; //TODO договориться какая валюта - какой номер (в документацию)
	}
	
	//methods
	void dataCardInput(){	//повторяется с другим интерфейсом(терминал), нормально или что-то предпринять? (функции ниже тоже повторяются)
		System.out.println("Method <dataCardInput> is not implemented yet.");
	}
	int pinInput(int pin){
		//TODO pin будет только из цифр, верно?
		System.out.println("Method <pinInput> is not implemented yet.");
		return pin;
	}
	Sum sumOfOperationInput(Sum oper_sum){
		System.out.println("Method <sumOfOperationInput> is not implemented yet.");
		return oper_sum;
	}
	Bonds payment(Bonds bonds_sum, Bonds bonds_sum_changing){
		System.out.println("Method <payment> is not implemented yet.");
		return bonds_sum;
	}
	String checkPrinting(String check){
		System.out.println("Method <checkPrinting> is not implemented yet.");
		return check;
	}
	
	
	/*Это метод типа get, только посылаем все данные в ФО
	 */
	//TODO во ФО сделать метод, который будет приниматься все эти данные?
	//TODO Зачем вообще нужен authorizationRequest в данном случае???
	void authorizationRequest(){
		System.out.println("Method <authorizationRequest> is not implemented yet.");
	}
	void receivingProcessingOfAnswer(Answer receiving_answer){
		//при положительном ответе от ФО здесь все вызовы методов расписать (чтобы расплатиться с клиентом)
		System.out.println("Method <receivingProcessingOfAnswer> is not implemented yet.");
	}
	
}
