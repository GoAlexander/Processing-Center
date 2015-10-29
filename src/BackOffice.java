//import FrontOffice.Sum;

public class BackOffice {
	
	class Sum { 
		double sum; //Количество денег (к примеру 1000)
		int type_of_money; //валюта //TODO договориться какая валюта - какой номер (в документацию)
	}

	//Расчёт комиссии (task #6) (+Frontoffice!)
	Sum commissionComputing(Sum oper_sum, int type_of_commission){
		return oper_sum;
	}
	//Here is unknown method (task #12)
	
	//Экспорт тразакций (task #13+14)
	void transactionExportBoToMsOff(){	//ms->Metering System (учетная система)
	}
	void transactionExportBoToMsOn(){
	}
}
