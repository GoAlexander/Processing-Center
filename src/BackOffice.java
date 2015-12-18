//import FrontOffice.Sum;

public class BackOffice {
	
	class Sum { 
		double sum; //Количество денег (к примеру 1000)
		int type_of_money; //валюта //TODO договориться какая валюта - какой номер (в документацию)
	}

	//Расчёт комиссии (task #6) (+Frontoffice!)
	
          public static String commision(String cardNumber, String sum) {
		Double com = 0.00;
		Double min = 0.00;
		Double max = 0.00;
		int percentage = 0;
		String cardNumb = cardNumber.substring(5, 9);
		int diapasonCard = Integer.parseInt(cardNumb);
		int summa = Integer.parseInt(sum);
		if (diapasonCard >= 1000 && diapasonCard <= 3000) {
			percentage = 1;
			min = 10.0;
			max = 100.0;
		}
           if (diapasonCard >= 3001 && diapasonCard <= 6000) {
			percentage = 2;
			min = 10.0;
			max = 110.0;
		}
		if (diapasonCard >= 6001 && diapasonCard <= 7000) {
			percentage = 3;
			min = 0.0;
			max = 0.0;
		}
		if (diapasonCard >= 7001 && diapasonCard <= 9000) {
			percentage = 4;
			min = 12.0;
			max = 100.0;
		}
		if (diapasonCard >= 9001 && diapasonCard <= 9999) {
			percentage = 5;
			min = 10.0;
			max = 100.0;
		}

		if ((min.compareTo(0.0)==0) && (max.compareTo(0.0)==0)) {
			com = summa * (percentage * 0.01);
		}
		if ((min.compareTo(0.0)==0) && (max.compareTo(0.0)!=0)) {
			com = summa * (percentage * 0.01);
			if (com < max) {
	                com = com;
			} else {
	                 com = max;
			}
		}
                  if ((min.compareTo(0.0)!=0) && (max.compareTo(0.0)==0)) {
			com = summa * (percentage * 0.01);
			if (com < min) {
                        com = min;
			} else {
	                com = com;
			}
		}
                     if ((min.compareTo(0.0)!=0) && (max.compareTo(0.0)!=0)) {
			com = summa * (percentage * 0.01);
			if (min < com && com < max) {
                        com = com;
			} else {
			    if (com < min) {
	                          com = min;
				}
				if (com > max) {
		                   com = max;
				}
			}
		}

		return Double.toString(com);
	}
        /*Sum commissionComputing(Sum oper_sum, int type_of_commission){
		return oper_sum;
	}*/
	//Here is unknown method (task #12)
	
	//Экспорт тразакций (task #13+14)
	void transactionExportBoToMsOff(){	//ms->Metering System (учетная система)
	}
	void transactionExportBoToMsOn(){
	}
}
