
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
	
	
	//methods
	Sum commissionComputing(Sum oper_sum, int type_of_commission){
		return oper_sum;
	}
	Sum sumConversion(/*double commission,*/ Sum oper_sum) { // TODO HIGH PRIORITY или вообще ничего на вход не нужно?
		
	}
	
	//transactionControl begin	//TODO HIGH PRIORITY  добавить эти методы в один метод?
	boolean pinChecking(){
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
	//Больше проверок???
	//transactionControl end
	
	void bondSelection(){	//int or Mystruct???
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
