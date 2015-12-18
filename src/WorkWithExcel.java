import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class WorkWithExcel {
	@SuppressWarnings("deprecation")
	public static void writeIntoExcel(String number, int Cardtype, int CurrType, double limit,
			int counter) throws FileNotFoundException, IOException {

		FileInputStream file1 = new FileInputStream(new File("TerminalDB.xls"));
		Workbook book = new HSSFWorkbook(file1);

		book.removeSheetAt(0);
		Sheet sheet = book.createSheet();		

		FileInputStream fis = new FileInputStream("TerminalDB.xls");
		Workbook wb = new HSSFWorkbook(fis);
		
		Cell existingCell;
		Row row;
		
		int i = 0, j = 0;
		for (i = 0; i < counter; i++) {
			row = sheet.createRow(i);
			for (j = 0; j < 4; j++) {
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
		Cell newCell3 = newRow.createCell(3);

		newCell.setCellValue(number);
		newCell1.setCellValue(Cardtype);
		newCell2.setCellValue(limit);			
		newCell3.setCellValue(CurrType);
		
		FileOutputStream out = new FileOutputStream("TerminalDB.xls");
		book.write(out);
		out.close();
		fis.close();
		file1.close();
	}

public static void writeIntoExcel1(String number, int cvv, String pin, double residue, Date valid, int blt, int blb, int counter) throws FileNotFoundException, IOException {

		FileInputStream file1 = new FileInputStream(new File("N:/DB.xls"));
		Workbook book = new HSSFWorkbook(file1);

		book.removeSheetAt(0);
		Sheet sheet = book.createSheet();

		FileInputStream fis = new FileInputStream("N:/DB.xls");
		Workbook wb = new HSSFWorkbook(fis);
		
		Cell existingCell;
		Row row;
		
		int i = 0, j = 0;
		for (i = 0; i < counter; i++) {
			row = sheet.createRow(i);
			for (j = 0; j < 7; j++) {
				existingCell = row.createCell(j);		
				existingCell.setCellValue(getCellText(wb.getSheetAt(0).getRow(i).getCell(j)));				
			}										
		}					
		
		Row newRow = sheet.createRow(counter);
		Cell newCell = newRow.createCell(0);
		Cell newCell1 = newRow.createCell(1);
		Cell newCell2 = newRow.createCell(2);
		Cell newCell3 = newRow.createCell(3);
		Cell newCell4 = newRow.createCell(4);
		Cell newCell5 = newRow.createCell(5);
		Cell newCell6 = newRow.createCell(6);

		newCell.setCellValue(number);
		newCell1.setCellValue(cvv);
		newCell2.setCellValue(pin);			
		newCell3.setCellValue(residue);
		
		
		CreationHelper createHelper = book.getCreationHelper();
		CellStyle cellStyle = book.createCellStyle();
		cellStyle.setDataFormat(createHelper.createDataFormat().getFormat("MMMM dd, yyyy")); 
		newCell4.setCellValue(valid);
		
		DataFormat format = book.createDataFormat();
        CellStyle dateStyle = book.createCellStyle();
        dateStyle.setDataFormat(format.getFormat("yyyy/mm"));
        newCell4.setCellStyle(dateStyle); 	
		
		
		newCell5.setCellValue(blt);
		newCell6.setCellValue(blb);
		
		FileOutputStream out = new FileOutputStream("N:/DB.xls");
		book.write(out);
		out.close();
		fis.close();
		file1.close();
	}


	public static void readFromExcel(int counter) throws IOException {
		FileInputStream fis = new FileInputStream("TerminalDB.xls");
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		for(int i = 0; i < counter; i++){
		System.out.println("QWE " + wb.getSheetAt(0).getRow(i).getCell(0));
		System.out.println("QWE " + wb.getSheetAt(0).getRow(i).getCell(1));
		System.out.println("QWE " + wb.getSheetAt(0).getRow(i).getCell(2));
		System.out.println("QWE " + wb.getSheetAt(0).getRow(i).getCell(3));
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
		FileInputStream fis = new FileInputStream("TerminalDB.xls");
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
			fis = new FileInputStream("TerminalDB.xls");
			Workbook wb = new HSSFWorkbook(fis);		
			res=getCellText(wb.getSheetAt(0).getRow(rowNumber).getCell(2));
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return res;		
	}

public static String getCur(int rowNumber) throws IOException{
		String res="";
		FileInputStream fis;
		try {
			fis = new FileInputStream("TerminalDB.xls");
			Workbook wb = new HSSFWorkbook(fis);		
			res=getCellText(wb.getSheetAt(0).getRow(rowNumber).getCell(3));
			fis.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return res;		
	}

/*	public static void main(String[] args) {
		try {
			writeIntoExcel("N:/Книга1.xls", "1234567890123456", 1, 1, 100, 0);
			

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}
