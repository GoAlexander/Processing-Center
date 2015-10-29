//Класс, в котором все методы относящиеся к Фронт Офису

public class FrontOffice {
	//structures 
	//TODO правильно я сделал подобие структуры?
	//"Структура" денег
	class Sum { 
		double sum; //Количество денег (к примеру 1000)
		int type_of_money; //валюта //TODO договориться какая валюта - какой номер (в документацию)
	}
	
	//"структура" юзера, здесь хранится вся инфа, которая должна храниться во Фронт Офисе
	class UserDataBase { //нужно немного отредактировать с учётом задания
		int pin;
		long deadline_of_card;
		Sum oper_sum;
		boolean stop_listl; //0 -> in the stop list
		//TODO что-то ещё?
	}
	
	//Купюры
	class Bond {
		//TODO (жду ответа преподавателя) описать какие будут деньги
	}
	
	//Журнал транзакций
	class TransactionJournal {
		long transaction_number;
		int kind_of_transaction; //(1-покупка, 2- выдача наличных, 3-электронная коммерция, ...(?))
		int  type_of_transaction; //(1-авторизация, 2-реверсал (отмена транзакции))
		//? long time; //с датой и временем
		//... TODO (см. там не всё понятно)
	}
	
	
	//methods
	//Расчёт комиссии (task #6) (+Backoffice!)
	Sum commissionComputing(Sum oper_sum, int type_of_commission){
		return oper_sum;
	}
	//Конверсионная операция (task #7)
	Sum sumConversion(/*double commission,*/ Sum oper_sum) { // TODO HIGH PRIORITY или вообще ничего на вход не нужно?
		return oper_sum; //см. преподаватель писал об этом (Email)
	}
	
	//Обработка транзакций (task #8)
	//Transaction control. Begin.
	//true -> означает, что метод выполнен успешно, всё правильно
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
	//Больше проверок??? (методов)
	
	boolean transactionControl(UserDataBase  user_data)
	{
		if( pinChecking()==true && usefulTimeOfCardChecking()==true && moneyOnCardCheckin()==true && cardInStoplistChecking()==true )
		{
			return true;
		}
		else
			return false;
	}
	//Transaction control. End.
	
	//Подбор купюр для выдачи (task #9)
	Bond bondSelection(Bond bonds_sum){
		return bonds_sum;
	}
	
	
	
	//Блок экспорта авторизации (task#10+11)
	void authorizationExportFoToBoOff(){ //экспорт из Фронт офиса в Бэк Офис оффлайн
	}
	void authorizationExportFoToBoOn(){	//экспорт из Фронт офиса в Бэк Офис онлайн
	}
	
	/* Ошибка? Лишнее? Проверить!
	void authorizationExportBoFromFoOff(){ //import?
	}
	void authorizationExportBoFromFoOn(){
	}
	*/
	
	
	
}
