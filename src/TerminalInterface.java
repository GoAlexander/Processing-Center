//GUI
package qwd;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

public class GUITerm {

	private JFrame frame;
	private JTextField cardNum;
	private JTextField pinCode;
	private JTextField sum;
	private JComboBox val;
	// private Terminal term;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUITerm window = new GUITerm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @return
	 */

	public static void Commit() {

	}

	public GUITerm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panCard = new JPanel();
		panCard.setBorder(new TitledBorder(null,
				"\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u043D\u043E\u043C\u0435\u0440 \u043A\u0430\u0440\u0442\u044B",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panCard.setBounds(10, 11, 414, 47);
		frame.getContentPane().add(panCard);
		panCard.setLayout(null);

		cardNum = new JTextField();
		cardNum.setBounds(10, 16, 394, 20);
		panCard.add(cardNum);
		cardNum.setColumns(10);

		JPanel panPin = new JPanel();
		panPin.setBorder(new TitledBorder(null, "\u0412\u0432\u0435\u0434\u0438\u0442\u0435 PIN-\u043A\u043E\u0434",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panPin.setBounds(10, 63, 414, 47);
		frame.getContentPane().add(panPin);
		panPin.setLayout(null);

		pinCode = new JTextField();
		pinCode.setBounds(10, 16, 394, 20);
		panPin.add(pinCode);
		pinCode.setColumns(10);

		JPanel panSum = new JPanel();
		panSum.setBorder(new TitledBorder(null,
				"\u0412\u0432\u0435\u0434\u0438\u0442\u0435 \u0441\u0443\u043C\u043C\u0443 \u0442\u0440\u0430\u043D\u0437\u0430\u043A\u0446\u0438\u0438 \u0438 \u0432\u044B\u0431\u0435\u0440\u0438\u0442\u0435 \u0432\u0430\u043B\u044E\u0442\u0443",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panSum.setBounds(10, 116, 414, 47);
		frame.getContentPane().add(panSum);
		panSum.setLayout(null);

		sum = new JTextField();
		sum.setBounds(10, 16, 339, 20);
		panSum.add(sum);
		sum.setColumns(10);

		String[] valString = { "RUB", "EUR", "USD", "GBP" };
		val = new JComboBox(valString);
		val.setBounds(359, 16, 45, 20);
		panSum.add(val);

		JPanel panRes = new JPanel();
		panRes.setBorder(new TitledBorder(null, "\u041F\u0435\u0447\u0430\u0442\u044C \u0447\u0435\u043A\u0430",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panRes.setBounds(10, 163, 414, 118);
		frame.getContentPane().add(panRes);
		panRes.setLayout(null);

		final JTextArea res = new JTextArea();
		res.setBounds(10, 21, 394, 86);
		panRes.add(res);

		JButton autorise = new JButton(
				"\u0410\u0432\u0442\u043E\u0440\u0438\u0437\u043E\u0432\u0430\u0442\u044C\u0441\u044F");
		autorise.setBounds(10, 287, 194, 23);
		frame.getContentPane().add(autorise);
		autorise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				int temp = 0;

				if (!(isEmpty(cardNum))) {
					temp++;
				}
				if (!(isEmpty(pinCode))) {
					temp++;
				}
				if (!(isEmpty(sum))) {
					temp++;
				}
				if (temp != 3)
					res.setText("Заполните обязательные поля");
				else if (temp == 3) {
					// проверка на наличие букв
					if (!(checkSymbols(cardNum)) && (!(checkSymbols(pinCode))) && (!(checkSymbols(sum)))) {
						res.setText("Идет обработка");
						try {
							if (checkNum(cardNum) && checkPin(pinCode) && validation()) {
								transaction();
								printCheck(res);
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});

		JButton clear = new JButton("\u041E\u0447\u0438\u0441\u0442\u0438\u0442\u044C");
		clear.setBounds(223, 287, 201, 23);
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				cardNum.setText("");
				pinCode.setText("");
				sum.setText("");
				res.setText("");
			}
		});

		frame.getContentPane().add(clear);
	}

	private boolean isEmpty(JTextField a) {
		if (a.getText().equals("")) {
			a.setText("Обязательное поле для заполнения");
			return true;
		} else
			return false;
	}

	private boolean checkNum(JTextField a) { // true - длина совпадает, false -
												// не совпадает
		if (a.getText().length() == 16)
			return true;
		else {
			if (a.getText().length() > 16)
				a.setText("Слишком длинный номер карты");
			if (a.getText().length() < 16)
				a.setText("Слишком короткий номер карты");
			return false;
		}
	}

	private boolean checkPin(JTextField a) {// true - длина совпадает, false -
											// не совпадает
		if (a.getText().length() == 4)
			return true;
		else {
			if (a.getText().length() > 4)
				a.setText("Слишком длинный PIN-код");
			if (a.getText().length() < 4)
				a.setText("Слишком короткий PIN-код");
			return false;
		}
	}

	private boolean correctPin(JTextField a) throws IOException {// делает Ксюша
		int counter = 0;
		boolean pinCorrect = false;
		for (int i = 0; i < 3; i++)// переделать?
			if (UserDataBase.pinChecking(getCardNum1(), getPin1())) {
				pinCorrect = true;
				break;
			}
		return pinCorrect;
	}

	private boolean validation() throws IOException {// Ksusha
		if (UserDataBase.usefulTimeOfCardChecking(getCardNum1()))
			return true;
		else
			return false;

	}

	private boolean transaction() {// Ksusha
		try {
			int row = WorkWithExcel.findRowUser(getCardNum1());
			int sum=Integer.parseInt(WorkWithExcel.getSum(row));
			if(getSum()<sum){
			//	 printCheck();??
				//финансовое подтверждение в Бэк-оффис
			}
			else{				
				//вызвать метод авторизации
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	public int getCardNum() {
		return Integer.parseInt(cardNum.getText(), 10);
	}

	public String getCardNum1() {

		return cardNum.getText();
	}

	public int getPin() {
		return Integer.parseInt(pinCode.getText(), 10);
	}

	public String getPin1() {
		return pinCode.getText();
	}

	public int getSum() {
		return Integer.parseInt(sum.getText(), 10);
	}

	private boolean checkSymbols(JTextField a) {// true - есть символы, false -
												// нет символов
		if (a.getText().matches("(?i).*[a-zа-я].*")) {
			a.setText("Ошибка! Содержатся недопустимые символы");
			return true;
		} else
			return false;
	}

	private void printCheck(JTextArea a) {
		if (transaction())
			a.setText("Ваша транзакция на сумму " + sum + " " + val.getSelectedIndex() + " выполнена успешно\n");
	}
}




//для записи в excel новых карт метод writeIntoExcel
package qwd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.RichTextString;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkWithExcel {
	@SuppressWarnings("deprecation")
	public static void writeIntoExcel(String file, String number, String type, double limit,
			int counter) throws FileNotFoundException, IOException {

		FileInputStream file1 = new FileInputStream(new File(file));
		Workbook book = new HSSFWorkbook(file1);

		book.removeSheetAt(0);
		Sheet sheet = book.createSheet();		

		FileInputStream fis = new FileInputStream(file);
		Workbook wb = new HSSFWorkbook(fis);
		
		Cell existingCell;
		Row row;
		
		int i = 0, j = 0;
		for (i = 0; i < counter; i++) {
			row = sheet.createRow(i);
			for (j = 0; j < 3; j++) {
				existingCell = row.createCell(j);		
				existingCell.setCellValue(getCellText(wb.getSheetAt(0).getRow(i).getCell(j)));
				
				System.out.println("AWR "+j+" "+existingCell);
				System.out.println("R "+book.getSheetAt(0).getRow(i).getCell(j));
			}										
		}					
		
		Row newRow = sheet.createRow(counter);
		Cell newCell = newRow.createCell(0);
		Cell newCell1 = newRow.createCell(1);
		Cell newCell2 = newRow.createCell(2);

		newCell.setCellValue(number);
		newCell1.setCellValue(type);
		newCell2.setCellValue(limit);			
		
		FileOutputStream out = new FileOutputStream(file);
		book.write(out);
		out.close();
		fis.close();
		file1.close();
	}

	public static void readFromExcel(String file, int counter) throws IOException {
		FileInputStream fis = new FileInputStream(file);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		for(int i = 0; i < counter; i++){
		System.out.println(wb.getSheetAt(0).getRow(i).getCell(0));
		System.out.println(wb.getSheetAt(0).getRow(i).getCell(1));
		System.out.println(wb.getSheetAt(0).getRow(i).getCell(2));
		}
		fis.close();	
	}
	
	public static String getCellText(Cell cell) {
		String result="";
		if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING)
		{
			result=cell.getStringCellValue();
		}
		else if(cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC)
		{
			result = Integer.toString((int) cell.getNumericCellValue());
		}
		return result;
	}
	
	public static int findRowUser(String numCard) throws IOException {
		FileInputStream fis = new FileInputStream("N:/Книга1.xls");
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
	
	public static String getSum(int rowNumber) throws IOException{
		String res="";
		FileInputStream fis;
		try {
			fis = new FileInputStream("N:/Книга1.xls");
			Workbook wb = new HSSFWorkbook(fis);		
			res=getCellText(wb.getSheetAt(0).getRow(rowNumber).getCell(2));
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return res;		
	}

	public static void main(String[] args) {
		try {
			writeIntoExcel("N:/Книга1.xls", "0994321123456", "Electron", 100, 0);
			readFromExcel("N:/Книга1.xls", 1);
			System.out.println("_----------");
			writeIntoExcel("N:/Книга1.xls", "09944547556", "Egjjon", 105, 1);
			System.out.println();
			readFromExcel("N:/Книга1.xls", 2);
			writeIntoExcel("N:/Книга1.xls", "1234944547556", "Master", 105, 2);
			System.out.println();
			readFromExcel("N:/Книга1.xls", 3);
			writeIntoExcel("N:/Книга1.xls", "1133126", "Msdfter", 113, 3);
			System.out.println();
			readFromExcel("N:/Книга1.xls", 4);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
