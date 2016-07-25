package com.utilsforappian.xsdgenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.utilsforappian.xsdgenerator.utils.ReadExcelFileUtils;
import com.utilsforappian.xsdgenerator.utils.XSDWriter;

public class Main {
	
	public static void main(String[] args) {
		ArrayList<XSDComplexType> complexTypes;
		/*It is assumed that the CDT structure will be defined in different sheets in the same Excel */
		/*Inputfilepathname will be passed through command line args */
		/*Namespace will also be passed via command line */
		String filePath = "D:\\Samba_POC\\Samba_CDT.xlsx";
		String nameSpace = "urn:com:psl:rcos" ;
		 
	/*	if(filePath == null) {
			throw new NullPointerException("You did not specify the path to Excel file. Error. Stopping") ;
		}
		
		if(nameSpace == null) {
			throw new NullPointerException("Error. You did not specify a namespace. Stopping") ;
		}*/
		try {
			complexTypes = ReadExcelFileUtils.readFromExcelFile(filePath);
			
			File excelFile = new File(filePath) ;
			File parentDir = excelFile.getParentFile() ;
			String parentDirPath = parentDir.getCanonicalPath() ;
			
			for(XSDComplexType complexType: complexTypes){
				/*The XSD files shud. be generated in the same folder as the excel file containing CDT structure */
				/*We should ideally using Threading here -- instead of going sequqntially */
				XSDWriter writer = new XSDWriter(parentDirPath + complexType.getName() + ".xsd" ) ;
				writer.writeComplexType(complexType, nameSpace);	
			}
			
		} catch (IOException e) {
			System.out.println("An error occurred while writing the XSD files");
			e.printStackTrace();
		}
	}
	
	

}
