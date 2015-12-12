﻿//Класс, в котором все методы относящиеся к Фронт Офису

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
		Bond() {
		}

		// denomination of notes in cassettes
		int[] denomination = { 1, 10, 50, 100, 500, 1000, 5000 };
		// number of notes in cassettes
		int[] quantity = { 5000, 1000, 500, 500, 500, 500, 500 };
		// previous result of bonds` selection
		int[] result = new int[denomination.length];

		@Override
		public String toString() {
			String s = new String();
			for (int j = denomination.length - 1; j >= 0; j--)
				s = s + ("Denomination:" + denomination[j] + "; result: " + result[j] + '\n');
			return s;
		}
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

	public static int findRowUser(String numCard) throws IOException {
		FileInputStream fis = new FileInputStream("C:/Processing-Center/excel/DB.xls");
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

	public static int findColUser(String phrase) throws IOException {
		FileInputStream fis = new FileInputStream("C:/Processing-Center/excel/DB.xls");
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

	public static boolean pinChecking(String numCard, String pinCode) throws IOException {
		try {
			FileInputStream fis = new FileInputStream("C:/Processing-Center/excel/DB.xls");
			Workbook wb = new HSSFWorkbook(fis);
			int row = findRowUser(numCard);
			int col = findColUser("Пин код");
			if (pinCode.equals(getCellText(wb.getSheetAt(0).getRow(row).getCell(col)))) {
				fis.close();
				return true;
			} else {
				fis.close();
				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static boolean usefulTimeOfCardChecking(String numCard) throws IOException {
		try {
			FileInputStream fis = new FileInputStream("C:/Processing-Center/excel/DB.xls");
			Workbook wb = new HSSFWorkbook(fis);
			int row = findRowUser(numCard);
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
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static boolean moneyOnCardChecking(String numCard, String desireSum) throws IOException {
		try {
			FileInputStream fis = new FileInputStream("C:/Processing-Center/excel/DB.xls");
			Workbook wb = new HSSFWorkbook(fis);
			int row = findRowUser(numCard);
			int col = findColUser("Остаток");
			Integer desSum = Integer.valueOf(desireSum);
			Integer factSum = (int) wb.getSheetAt(0).getRow(row).getCell(col).getNumericCellValue();
			if ((desSum.compareTo(factSum) == -1) || (desSum.compareTo(factSum) == 0)) {
				fis.close();
				return true;
			} else {
				fis.close();
				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static boolean cardInStoplistChecking(String numCard, String device) throws IOException {//device:terminal or atm
		try {
			FileInputStream fis = new FileInputStream("C:/Processing-Center/excel/DB.xls");
			Workbook wb = new HSSFWorkbook(fis);
			int row = findRowUser(numCard);
			int col = 0;
			if(device.equals("terminal"))
				col = findColUser("Стоп-лист (терминал)");
			else if(device.equals("atm"))
				col = findColUser("Стоп-лист (банкомат)");
			else
				return false;
			if ((int) wb.getSheetAt(0).getRow(row).getCell(col).getNumericCellValue() == 0) {
				fis.close();
				return true;
			} else {
				fis.close();
				return false;
			}
		} catch (NullPointerException e) {
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

	public static boolean transactionControl(String numCard, String pinCode, String desireSum, String device) throws IOException {
		if ((pinChecking(numCard, pinCode) == true) && (usefulTimeOfCardChecking(numCard) == true) && (moneyOnCardChecking(numCard, desireSum) == true)
				&& (cardInStoplistChecking(numCard, device) == true)) {
			return true;
		} else
			return false;
	}
	//Transaction control. End.
	
	//Подбор купюр для выдачи
	public static Bond bondSelection(Bond curr, int sum) throws Exception {
		int j = curr.denomination.length - 1;
		int tempSum, count;
		do {
			/* finds which denomination is closest to sum and how many notes we need 
			then removes the first digit of the number and does it again */

			// removes the first digit of the number
			tempSum = sum % curr.denomination[j];
			if (tempSum >= curr.denomination[0] || tempSum == 0) {
				// takes the first digit of the number
				count = sum / curr.denomination[j];
				sum = tempSum;
			} else {
				// takes the first digit of the number and reduces it by one
				count = sum / curr.denomination[j] - 1;
				sum = tempSum + curr.denomination[j];
			}
			curr.result[j] = count;
			--j;
		} while (sum > 0);


		// checks if there are enough notes and substitutes denominations with lower ones
		for (j = curr.denomination.length - 1; j >= 0; j--) {
			while (curr.quantity[j] < curr.result[j]) {
				if (j == 6) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 5;
				}
				if (j == 5) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 2;
				}
				if (j == 4) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 5;
				}
				if (j == 3) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 2;
				}
				if (j == 2) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 5;
				}
				if (j == 1) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 10;
				}
				if (j == 0)
					throw new Exception();
			}
		}
		// reduces the quantity of notes in cassettes
		for (j = curr.denomination.length - 1; j >= 0; j--)
			curr.quantity[j] = curr.quantity[j] - curr.result[j];
		return curr;
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
