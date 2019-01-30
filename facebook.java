package datadriven1;



	import java.io.File;
	import java.io.FileInputStream;


	import org.apache.poi.hssf.usermodel.HSSFCell;
	import org.apache.poi.hssf.usermodel.HSSFRow;
	import org.apache.poi.hssf.usermodel.HSSFSheet;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import junit.framework.Assert;






public class facebook {
		
	WebDriver wd;
	String firstname;
	String lastname;
	String email;
	String password1;
	int myRows,myCols;
	String my_input_data;
	String expectedresult;
		
		
		@BeforeClass
		public void setup()throws Exception{
		
		
		
		
		wd=new FirefoxDriver();
		wd.navigate().to("https://www.facebook.com/");
		
		
		}
		
		@Test
		public void mymaintest()throws Exception{
			
		String SheetPath="C:/Users/MuneefAhsan/Desktop/facebook.xls";
		String [][] myXLSheet= readXLSheet(SheetPath,"data");
		for (int k=1;k<myRows;k++){
			
			
		firstname=myXLSheet[k][0];
		lastname=myXLSheet[k][1];
		email=myXLSheet[k][2];
		password1=myXLSheet[k][3];
		expectedresult=myXLSheet[k][4];
		Thread.sleep(3000);
		
		wd.navigate().to("https://www.facebook.com/");	
		
		wd.findElement(By.xpath(".//*[@id='u_0_c']")).sendKeys(firstname);
		wd.findElement(By.xpath(".//*[@id='u_0_e']")).sendKeys(lastname);
		wd.findElement(By.xpath(".//*[@id='u_0_h']")).sendKeys(email);
		wd.findElement(By.xpath(".//*[@id='u_0_k']")).sendKeys(email);
		wd.findElement(By.xpath(".//*[@id='u_0_o']")).sendKeys(password1);
		String title=wd.getTitle();
		Assert.assertEquals(title,expectedresult);
		System.out.println(title);
		if(title==expectedresult) {
			System.out.println("pass");
		}
		
		
			
		
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
		


