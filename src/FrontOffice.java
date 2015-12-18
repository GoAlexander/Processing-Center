import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
//Класс, в котором все методы относящиеся к Фронт Офису

public class FrontOffice {
	//structures 
	//"Структура" денег

	double sum = 0; //Количество денег (к примеру 1000)
	String cardNum = "";
	int Fcvv = 0;
	boolean stop_list = false;
	
	//Купюры
	static class Bond {
		public Bond() {
		}

		// denomination of notes in cassettes
		final int[] denomination = { 10, 50, 100, 500, 1000, 5000 };
		// number of notes in cassettes
		int[] quantity = { 500, 500, 500, 500, 500, 500 };
		// previous result of bonds` selection
		int[] result = new int[denomination.length];
		// codes of bonds in ATM
		final int[] code = { 6, 5, 4, 3, 2, 1 };

		@Override
		public String toString() {
			String s = new String();
			for (int j = denomination.length - 1; j >= 0; j--)
				s = s + ("Code " + code[j] + "; result: " + result[j] + '\n');
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
	/*
	Sum commissionComputing(Sum oper_sum, int type_of_commission){
		return oper_sum;
	}
	//Конверсионная операция (task #7)
	Sum sumConversion( Sum oper_sum) { // TODO HIGH PRIORITY или вообще ничего на вход не нужно?
		return oper_sum; //см. преподаватель писал об этом (Email)
	}
	*/
	
	//Обработка транзакций (task #8)
	//Transaction control. Begin.
	//true -> означает, что метод выполнен успешно, всё правильно
	public static SimpleDateFormat data = new SimpleDateFormat("yyyy/MM/dd");

	public static int findRowUser(String numCard) throws IOException {
		FileInputStream fis = new FileInputStream("../Processing-Center/excel/DB.xls");
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
		FileInputStream fis = new FileInputStream("../Processing-Center/excel/DB.xls");
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

	public static boolean cvvChecking(String numCard, String cvv) throws IOException {
		try {
			FileInputStream fis = new FileInputStream("../Processing-Center/excel/DB.xls");
			Workbook wb = new HSSFWorkbook(fis);
			int row = findRowUser(numCard);
			int col = findColUser("CVV");
			if (cvv.equals(getCellText(wb.getSheetAt(0).getRow(row).getCell(col)))) {
				Fcvv = Integer.parseInt(cvv);
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

	public static boolean pinChecking(String numCard, String pinCode) throws IOException {
		try {
			FileInputStream fis = new FileInputStream("../Processing-Center/excel/DB.xls");
			Workbook wb = new HSSFWorkbook(fis);
			int row = findRowUser(numCard);
			int col = findColUser("Пин код");
			if (pinCode.equals(getCellText(wb.getSheetAt(0).getRow(row).getCell(col)))) {
				fis.close();
				 cardNum = numCard;
				return true;
			} else {
				cardNum = numCard;
				fis.close();
				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static boolean usefulTimeOfCardChecking(String numCard) throws IOException {
		try {
			FileInputStream fis = new FileInputStream("../Processing-Center/excel/DB.xls");
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
			FileInputStream fis = new FileInputStream("../Processing-Center/excel/DB.xls");
			Workbook wb = new HSSFWorkbook(fis);
			int row = findRowUser(numCard);
			int col = findColUser("Остаток");
			Integer desSum = Integer.valueOf(desireSum);
			Integer factSum = (int) wb.getSheetAt(0).getRow(row).getCell(col).getNumericCellValue();
			if ((desSum.compareTo(factSum) == -1) || (desSum.compareTo(factSum) == 0)) {
				sum = Double.parseDouble(desireSum);
				fis.close();
				return true;
			} else {
				sum = Double.parseDouble(desireSum);
				fis.close();
				return false;
			}
		} catch (NullPointerException e) {
			return false;
		}
	}

	public static boolean cardInStoplistChecking(String numCard, String device) throws IOException {//device:terminal or atm
		try {
			FileInputStream fis = new FileInputStream("../Processing-Center/excel/DB.xls");
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

	public static int transactionControl(String numCard, String pinCode, String desireSum, String device)
			throws IOException {
		if (pinChecking(numCard, pinCode) == false)
			return 0;
		if (usefulTimeOfCardChecking(numCard) == false)
			return -1;
		if (moneyOnCardChecking(numCard, desireSum) == false)
			return -2;
		if (cardInStoplistChecking(numCard, device) == false)
			return -3;
		return 1;
	}

	public static int transactionControl(String numCard, String pinCode, String desireSum, String device, String cvv)
			throws IOException {
		if (pinChecking(numCard, pinCode) == false)
			return 0;
		if (usefulTimeOfCardChecking(numCard) == false)
			return -1;
		if (moneyOnCardChecking(numCard, desireSum) == false)
			return -2;
		if (cardInStoplistChecking(numCard, device) == false)
			return -3;
		if (cvvChecking(numCard, cvv) == false)
			return -4;
		return 1;
	}

	public static String answerMessage(String numCard, String pinCode, String desireSum, String device)
			throws IOException {
		String s = new String();
		int a = transactionControl(numCard, pinCode, desireSum, device);
		if (a == 1)
			return "Authorization completed. ";
		if (a == 0)
			return "Wrong pin code. ";
		if (a == -1)
			s = s + "Your card expired. ";
		if (a == -2)
			s = s + "Not enough money on card. ";
		if (a == -3)
			s = s + "Your card is in the stoplist. ";
		return s;
	}

	public static String answerMessage(String numCard, String pinCode, String desireSum, String device, String cvv)
			throws IOException {
		String s = new String();
		int a = transactionControl(numCard, pinCode, desireSum, device);
		if (a == 1)
			return "Authorization completed. ";
		if (a == 0)
			return "Wrong pin code. ";
		if (a == -1)
			s = s + "Your card expired. ";
		if (a == -2)
			s = s + "Not enough money on card. ";
		if (a == -3)
			s = s + "Your card is in the stoplist. ";
		if (a == -4)
			s = s + "Wrong cvv. ";
		return s;
	}
	//Transaction control. End.
	
	//Подбор купюр для выдачи
	public static Bond bondSelection(Bond curr, int sum) throws Exception {
		if (sum % 10 != 0)
			throw new Exception();
		int j = curr.denomination.length - 1;
		int tempSum, count;
		do {
			/* find which note is closest to sum */

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

		// checks if there are enough notes and substitutes them for lower ones
		for (j = curr.denomination.length - 1; j >= 0; j--) {
			while (curr.quantity[j] < curr.result[j]) {
				if (j == 5) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 5;
				}
				if (j == 4) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 2;
				}
				if (j == 3) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 5;
				}
				if (j == 2) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 2;
				}
				if (j == 1) {
					curr.result[j]--;
					curr.result[j - 1] = curr.result[j - 1] + 5;
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
