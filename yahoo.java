package datadriven1;


	



	import java.io.File;
	import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
	import org.apache.poi.hssf.usermodel.HSSFRow;
	import org.apache.poi.hssf.usermodel.HSSFSheet;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;
import pageobject.nonyahoo;

public class yahoo {






public static class facebook {
		
	WebDriver wd;
	String firstname;
	String lastname;
	String username;
	String password;
	String phonenumber1;
	String month;
	String day;
	String year;
	String gender;
	String expecteddata;
	int myRows,myCols;
	String my_input_data;
	String expectedresult;
		
		
		@BeforeClass
		public void setup()throws Exception{
		
		
		
		
		wd=new FirefoxDriver();
		wd.navigate().to("https://login.yahoo.com/account/create?specId=yidReg");
		
		
		}
		
		@Test
		public void mymaintest()throws Exception{
			
		String SheetPath="C:/Users/MuneefAhsan/Desktop/yahoo.xls";

		String [][] myXLSheet= readXLSheet(SheetPath,"data");
		for (int k=1;k<myRows;k++){
			
			
		firstname=myXLSheet[k][0];
		lastname=myXLSheet[k][1];
		username=myXLSheet[k][2];
		password=myXLSheet[k][3];
		phonenumber1=myXLSheet[k][4];
		month=myXLSheet[k][5];
		day=myXLSheet[k][6];
		year=myXLSheet[k][7];
		gender=myXLSheet[k][8];
		expecteddata=myXLSheet[k][9];
		
		
		wd.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
		
		wd.navigate().to("https://login.yahoo.com/account/create?specId=yidReg");	
		
		nonyahoo yahoo=new nonyahoo(wd);
		yahoo.firstname().sendKeys(firstname);
		yahoo.lastname().sendKeys(lastname);
		yahoo.username().sendKeys(username);
		yahoo.password().sendKeys(password);
		yahoo.phonenumber().sendKeys(phonenumber1);
		Select month1=new Select(wd.findElement(By.xpath(".//*[@id='usernamereg-month']")));
		month1.selectByVisibleText(month);
		yahoo.day().sendKeys(day);
		yahoo.year().sendKeys(year);
		wd.findElement(By.xpath(".//*[@id='usernamereg-freeformGender']")).sendKeys(gender);
		
		
		String title=wd.getTitle();
		Assert.assertEquals(expecteddata,title);
		System.out.println(title);
		
		
		
		
			
		
		}
		
		}
		
		
		
		// Method/Function for reading data from Excel Sheet
			@SuppressWarnings("resource")
			public String[][] readXLSheet(String SheetPath, String SheetName) throws Exception{

				String[][] xData;                                                                
				
				File myXLSheet = new File(SheetPath);                                
				FileInputStream myStream = new FileInputStream(myXLSheet);                                
				HSSFWorkbook myWB = new HSSFWorkbook(myStream);                                
				HSSFSheet mySheet = myWB.getSheet(SheetName);                                 
				myRows = mySheet.getLastRowNum()+1;                                
				myCols = mySheet.getRow(0).getLastCellNum();                                
				xData = new String[myRows][myCols];        
				for (int i = 0; i < myRows; i++) {                           
						HSSFRow row = mySheet.getRow(i);
						for (int j = 0; j < myCols; j++) {                               
							HSSFCell cell = row.getCell(j);                               
							String value = cellToString(cell);                               
							xData[i][j] = value;                               
							}        
						}                                      
				return xData;
			}

		

		
		

		//Method/Function to Change cell type
			@SuppressWarnings("deprecation")
			public static String cellToString(HSSFCell cell) { 
							int type = cell.getCellType();                        
				Object result;                        
				switch (type) {                            
					case HSSFCell.CELL_TYPE_NUMERIC: //0                                
						result = cell.getNumericCellValue();                                
						break;                            
					case HSSFCell.CELL_TYPE_STRING: //1                                
						result = cell.getStringCellValue();                                
						break;                            
					case HSSFCell.CELL_TYPE_FORMULA: //2                                
						throw new RuntimeException("We can't evaluate formulas in Java");  
						case HSSFCell.CELL_TYPE_BLANK: //3                                
						result = "-";                                
						break;                            
					case HSSFCell.CELL_TYPE_BOOLEAN: //4     
						result = cell.getBooleanCellValue();       
						break;                            
					case HSSFCell.CELL_TYPE_ERROR: //5       
						throw new RuntimeException ("This cell has an error");    
					default:                  
						throw new RuntimeException("We don't support this cell type: " + type); 
						}                        
				return result.toString();      
				}


		@AfterClass
		public void teardown()throws Exception{
		
	}
	}
		



}
