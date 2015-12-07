import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class AccountingDataBase {
	InputStream fis;
	OutputStream fos;
	AccountingDataBase(){
	}
	
	public void removeCard(long number) throws IOException, EncryptedDocumentException, InvalidFormatException{
		fis = getClass().getResourceAsStream("/DataBase.xls");
		Workbook db = WorkbookFactory.create(fis);
		for(Row row: db.getSheetAt(0)) 
			if(row.getCell(0).getNumericCellValue() == number){
				int index = row.getRowNum();
				removeRow((HSSFSheet) db.getSheetAt(0), index);
				FileOutputStream fileOut = new FileOutputStream("/DataBase.xls");
			    db.write(fileOut);
			    fileOut.close();
				return;
			}
	}
	
	public double restatementOfBalances(long number, double transaction) throws IOException, EncryptedDocumentException, InvalidFormatException{
		fis = getClass().getResourceAsStream("/DataBase.xls");
		Workbook db = WorkbookFactory.create(fis);
		for(Row row: db.getSheetAt(0)) 
			if(row.getCell(0).getNumericCellValue() == number){
				double val = (row.getCell(1).getNumericCellValue() - transaction);
				Cell cell = row.getCell(0);
				cell.setCellValue(val);
				FileOutputStream fileOut = new FileOutputStream("/DataBase.xls");
			    db.write(fileOut);
			    fileOut.close();
				return val;
			}
		return -1;
	}
	
	
	public void setBalance(long number, int balance) throws IOException, Throwable, InvalidFormatException{
		fis = getClass().getResourceAsStream("/DataBase.xls");
		Workbook db = WorkbookFactory.create(fis);
		for(Row row: db.getSheetAt(0)) 
			if(row.getCell(0).getNumericCellValue() == number) row.getCell(1).setCellValue(balance);
	}
	
	private void removeRow(HSSFSheet sheet, int rowIndex) {
	    int lastRowNum = sheet.getLastRowNum();
	    if(rowIndex >= 0 && rowIndex < lastRowNum){
	       sheet.shiftRows(rowIndex+1,lastRowNum, -1);
	    }
	    if(rowIndex == lastRowNum){
	       HSSFRow removingRow = sheet.getRow(rowIndex);
	       if(removingRow != null){
	          sheet.removeRow(removingRow);
	       }
	    }
	}
	
	@Override
	protected void finalize() throws IOException{
		//fis.close();
	}
}
