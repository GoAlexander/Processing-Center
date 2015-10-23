
public class FrontOffice {
	//structures
	Sum() { //TODO why mistake???
		double sum;
		int type_of_money; //TODO договориться какая валюта - какой номер (в документацию)
	}
	UserDataBase() {
		int pin;
		long deadline_of_card;
		Sum oper_sum;
		boolean stop_listl; //0 -> in the stop list
		//TODO что-то ещё?
	}
	Bond() {
		//TODO (жду ответа преподавателя) описать какие будут деньги
	}
	
	
	//methods
	Sum commissionComputing(Sum oper_sum, int type_of_commission){
		return oper_sum;
	}
	Sum sumConversion(/*double commission,*/ Sum oper_sum) { // TODO HIGH PRIORITY или вообще ничего на вход не нужно?
		
	}
	
	//transactionControl begin	//TODO HIGH PRIORITY  добавить эти методы в один метод?
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
			return 1;
		else
			return 0;
		
	}
	//transactionControl end
	
	Bond bondSelection(Bond bonds_sum){	//int or Mystruct???
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
