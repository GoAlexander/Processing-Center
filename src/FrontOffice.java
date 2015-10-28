
public class FrontOffice {
	//structures 
	//TODO правильно я сделал подобие структуры?
	class Sum { 
		double sum;
		int type_of_money; //TODO договориться какая валюта - какой номер (в документацию)
	}
	class UserDataBase {
		int pin;
		long deadline_of_card;
		Sum oper_sum;
		boolean stop_listl; //0 -> in the stop list
		//TODO что-то ещё?
	}
	class Bond {
		//TODO (жду ответа преподавателя) описать какие будут деньги
	}
	class TransactionJournal {
		long transaction_number;
		int kind_of_transaction; //(1-покупка, 2- выдача наличных, 3-электронная коммерция, ...(?))
		int  type_of_transaction; //(1-авторизация, 2-реверсал (отмена транзакции))
		//? long time; //с датой и временем
		//... TODO (см. там не всё понятно)
	}
	
	
	//methods
	Sum commissionComputing(Sum oper_sum, int type_of_commission){
		return oper_sum;
	}
	Sum sumConversion(/*double commission,*/ Sum oper_sum) { // TODO HIGH PRIORITY или вообще ничего на вход не нужно?
		return oper_sum; //см. преподаватель писал об этом (Email)
	}
	
	boolean transactionControl(UserDataBase  user_data)
	{
		boolean pinChecking(){ //TODO why mistake???
			return true;
		}
		boolean usefulTimeOfCardChecking(){
			return true;
		}
		boolean moneyOnCardCheckin(){
			return true;
		}
		boolean cardInStoplistChecking(){
			return true;
		}
		//Больше проверок??? (методов)

		if( pinChecking()==1 && usefulTimeOfCardChecking()==1 && moneyOnCardCheckin()==1 && cardInStoplistChecking()==1 )
		{
			return true;
		}
		else
			return false;
	}
	
	Bond bondSelection(Bond bonds_sum){	//int or Mystruct???
		return bonds_sum;
	}
	
	//void???
	void authorizationExportFoToBoOff(){
	}
	void authorizationExportFoToBoOn(){	//Дурной тон? Название различается на букву!
	}
	void authorizationExportBoFromFoOff(){
	}
	void authorizationExportBoFromFoOn(){
	}
	
	
	
}
