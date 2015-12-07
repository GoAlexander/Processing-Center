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
		//int pin;
		//long deadline_of_card;
		Sum oper_sum;
		//boolean stop_listl; //0 -> in the stop list
		//TODO что-то ещё?
		String numCard;
		String pinCode;
		String desireSum;
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
	public static SimpleDateFormat data = new SimpleDateFormat("yyyy/MM/dd");

	int findRowUser(String numCard) throws IOException {
		FileInputStream fis = new FileInputStream("C:/Users/Артём/Desktop/test.xls");
		Workbook wb = new HSSFWorkbook(fis);
		int row = 0;
		for (int i = 0;; i++) {
			if (numCard.equals(getCellText(wb.getSheetAt(0).getRow(i).getCell(0)))) {
				row = i;
				break;
			}
		}
		fis.close();
		return row;
	}

	int findColUser(String phrase) throws IOException {
		FileInputStream fis = new FileInputStream("C:/Users/Артём/Desktop/test.xls");
		Workbook wb = new HSSFWorkbook(fis);
		int col = 0;
		for (int i = 0;; i++) {
			if (phrase.equals(getCellText(wb.getSheetAt(0).getRow(0).getCell(i)))) {
				col = i;
				break;
			}
		}
		fis.close();
		return col;
	}

	boolean pinChecking() throws IOException {
		UserDataBase user = new UserDataBase();
		FileInputStream fis = new FileInputStream("C:/Users/Артём/Desktop/test.xls");
		Workbook wb = new HSSFWorkbook(fis);
		int row = findRowUser(user.numCard);
		int col = findColUser("Пин код");
		if (user.pinCode.equals(getCellText(wb.getSheetAt(0).getRow(row).getCell(col)))) {
			fis.close();
			return true;
		} else {
			fis.close();
			return false;
		}
	}

	boolean usefulTimeOfCardChecking() throws IOException {
		UserDataBase user = new UserDataBase();
		FileInputStream fis = new FileInputStream("C:/Users/Артём/Desktop/test.xls");
		Workbook wb = new HSSFWorkbook(fis);
		int row = findRowUser(user.numCard);
		int col = findColUser("Срок карты");
		Date d = new Date();
		String factData = getCellText(wb.getSheetAt(0).getRow(row).getCell(col));
		String currData = data.format(d);
		String factDataArr[] = factData.split("/");
		String currDataArr[] = currData.split("/");
		if ((Integer.valueOf(currDataArr[0]).compareTo(Integer.valueOf(factDataArr[0])) == -1)) {
			fis.close();
			return true;
		} else if (Integer.valueOf(currDataArr[0]).compareTo(Integer.valueOf(factDataArr[0])) == 0) {
			if (Integer.valueOf(currDataArr[1]).compareTo(Integer.valueOf(factDataArr[1])) == -1) {
				fis.close();
				return true;
			} else if (Integer.valueOf(currDataArr[1]).compareTo(Integer.valueOf(factDataArr[1])) == 0) {
				if ((Integer.valueOf(currDataArr[2]).compareTo(Integer.valueOf(factDataArr[2])) == -1)
						|| (Integer.valueOf(currDataArr[2]).compareTo(Integer.valueOf(factDataArr[2])) == 0)) {
					fis.close();
					return true;
				} else {
					fis.close();
					return false;
				}
			} else {
				fis.close();
				return false;
			}
		} else {
			fis.close();
			return false;
		}
	}

	boolean moneyOnCardChecking() throws IOException {
		UserDataBase user = new UserDataBase();
		FileInputStream fis = new FileInputStream("C:/Users/Артём/Desktop/test.xls");
		Workbook wb = new HSSFWorkbook(fis);
		int row = findRowUser(user.numCard);
		int col = findColUser("Остаток");
		Integer desSum = Integer.valueOf(user.desireSum);
		Integer factSum = (int) wb.getSheetAt(0).getRow(row).getCell(col).getNumericCellValue();
		if ((desSum.compareTo(factSum) == -1) || (desSum.compareTo(factSum) == 0)) {
			fis.close();
			return true;
		} else {
			fis.close();
			return false;
		}
	}

	boolean cardInStoplistChecking() throws IOException {
		UserDataBase user = new UserDataBase();
		FileInputStream fis = new FileInputStream("C:/Users/Артём/Desktop/test.xls");
		Workbook wb = new HSSFWorkbook(fis);
		int row = findRowUser(user.numCard);
		int col = findColUser("Стоп лист");
		if ((int) wb.getSheetAt(0).getRow(row).getCell(col).getNumericCellValue() == 0) {
			fis.close();
			return true;
		} else {
			fis.close();
			return false;
		}
	}

	public static String getCellText(Cell cell) {
		String result = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			result = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				result = data.format(cell.getDateCellValue());
			} else {
				result = Integer.toString((int) cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			result = Boolean.toString(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
			break;
		default:
			break;
		}
		return result;
	}

	boolean transactionControl() throws IOException {
		if ((pinChecking() == true) && (usefulTimeOfCardChecking() == true) && (moneyOnCardChecking() == true)
				&& (cardInStoplistChecking() == true)) {
			return true;
		} else
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
