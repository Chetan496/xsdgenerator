package com.utilsforappian.xsdgenerator.utils;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.utilsforappian.xsdgenerator.XSDComplexType;
import com.utilsforappian.xsdgenerator.XSDElement;
import com.utilsforappian.xsdgenerator.XSDSequence;



public class ReadExcelFileUtils {

	public ReadExcelFileUtils() {
		// TODO Auto-generated constructor stub
	}

	public static Workbook getWorkbook(FileInputStream inputStream,
			String excelFilePath) throws IOException {
		Workbook workbook = null;

		if (excelFilePath.endsWith("xlsx")) {
			workbook = new XSSFWorkbook(inputStream);
		} else if (excelFilePath.endsWith("xls")) {
			workbook = new HSSFWorkbook(inputStream);
		} else {
			throw new IllegalArgumentException(
					"The specified file is not Excel file");
		}

		return workbook;
	}

	public static Object getCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return cell.getBooleanCellValue();
		case Cell.CELL_TYPE_NUMERIC:
			return cell.getNumericCellValue();
		}

		return null;
	}

	public static FileInputStream getInputStreamFromExcelFile(
			String excelFilePath) {
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(excelFilePath));
		} catch (FileNotFoundException e) {
			System.out
					.println("\n Error while getting a Input Stream from the Excel file");
			e.printStackTrace();
		}
		return inputStream;
	}
	
	
	public static ArrayList<XSDComplexType> readFromExcelFile(final String excelFilePath) throws IOException{
		if(excelFilePath == null){
			throw new NullPointerException("No path specified for the excel file") ;
		}
		FileInputStream inputStream = getInputStreamFromExcelFile(excelFilePath);
		Workbook workbook;
		XSDComplexType complexType = null ;
		XSDSequence sequence = new XSDSequence();
		
		workbook = getWorkbook(inputStream, excelFilePath);
		
		ArrayList<XSDComplexType> complexTypes = new ArrayList<XSDComplexType>() ; 
		for(int i=0; i < workbook.getNumberOfSheets(); i++){
			complexTypes.add(readFromExcelFile(excelFilePath, workbook.getSheetAt(i)) ) ;
		}

		workbook.close();
		inputStream.close();
		return complexTypes ;
	}
	
	public static XSDComplexType readFromExcelFile(final String excelFilePath, Sheet sheet) throws IOException{
		
		XSDComplexType complexType = null ;
		XSDSequence sequence = new XSDSequence();
		int i = 0;
		
		
		Iterator<Row> iterator = sheet.iterator();
		
		while (iterator.hasNext()) {

			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
							
			if(i == 0){ //the row with CDT names
				Cell nextCell = cellIterator.next() ;
				complexType = new XSDComplexType((String)getCellValue(nextCell)) ; // just read the CDT name from first line
			}else{
				if(i == 1){
						//the header row
				}else{
					//element rows
					XSDElement element = new XSDElement() ;  
					
				    while(cellIterator.hasNext()) {
				    	Cell nextCell = cellIterator.next() ;
				    	int columnIndex = nextCell.getColumnIndex();
				    	Object value = getCellValue(nextCell) ;
				    	
				    	switch (columnIndex){
				    	case 0:
				    		element.setName((String)value);
				    		break;
				    	case 1:
				    		element.setType((String)value);
				    		break;
				    	case 2:
				    		element.setNillable(value.toString());
				    		break;
				    	}
				    }
				    
				    sequence.addElement(element);
				}
				
			}
				
			i = i + 1;
		}
		
		complexType.setSequence(sequence);
			
		return complexType ;

	}

	
	
}
