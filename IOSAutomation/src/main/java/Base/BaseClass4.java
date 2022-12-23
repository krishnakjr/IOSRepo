package Base;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseClass4{

//protected static IOSDriver driver;
protected ExtentReports extent = new ExtentReports();
ExtentSparkReporter spark= new ExtentSparkReporter("EXT.html");
protected static Properties props=new Properties();
protected ExtentTest test;


protected AppiumDriver<MobileElement>  driver;
@BeforeMethod
public void Setup() throws MalformedURLException
{

	
	DesiredCapabilities dc;
	try {
		dc = new DesiredCapabilities();
		dc.setCapability(MobileCapabilityType.PLATFORM_NAME,"iOS");
		dc.setCapability(MobileCapabilityType.PLATFORM_VERSION,"15.2");
		dc.setCapability(MobileCapabilityType.DEVICE_NAME,"iPad");
		//dc.setCapability(MobileCapabilityType.DEVICE_NAME,"iPad (9th generation)");
		dc.setCapability(MobileCapabilityType.AUTOMATION_NAME,"XCUITest");
		//dc.setCapability(MobileCapabilityType.APP,"iOS");
		//dc.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
		//dc.setCapability(MobileCapabilityType.UDID,"686CD884-68E0-44A4-BA8E-36028F21154E");
		dc.setCapability(MobileCapabilityType.UDID,"00008030-0011448236D0C02E");
		//dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"com.apple.news");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID,"com.mdsol.econsent");
		URL url= new URL("http://127.0.0.1:4723/");
		//URL url= new URL("http://127.0.0.1:4723/wd/hub");
		driver =new AppiumDriver<MobileElement>(url,dc);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		System.out.println("Cause is :"+e.getCause());
		System.out.println("Message is :"+e.getMessage());
		e.printStackTrace();
	}
	
}


/*
@BeforeMethod
public static void Steup() throws IOException
{
DesiredCapabilities caps= new DesiredCapabilities();

// real device
caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
caps.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"15.2");

caps.setCapability(MobileCapabilityType.DEVICE_NAME,"iPad");

caps.setCapability(MobileCapabilityType.UDID,"00008030-0011448236D0C02E");

caps.setCapability("xcodeOrdId","krishnakjr@icloud.com");

caps.setCapability("xcodeSigningId","iOS Developer");

caps.setCapability("usePrebuiltWDA", true);

//caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"15.6.1");
//caps.setCapability(MobileCapabilityType.DEVICE_NAME,"pravin.baban.jadhav's iPad");
//caps.setCapability(MobileCapabilityType.UDID, "00008030-000C79011E10C02E");
//caps.setCapability("xcodeOrdId","pravinjadhav17588@gmail.com");
//caps.setCapability("xcodeSigningId","iOS Developer");
//caps.setCapability("usePrebuiltWDA", true);

caps.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.mdsol.econsent");

URL url=new URL("http://127.0.0.1:4723/");
driver=new IOSDriver(url,caps);
driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);


FileInputStream ip = new FileInputStream("./DataSheet/UserData.properties");
props.load(ip);

}
*/
/*
public void Takescreenshoot(String Path, WebElement ele) throws IOException
{
String scrFile = ele.getScreenshotAs(OutputType.BASE64);
byte[] decodedBytes = Base64.getDecoder().decode(scrFile.replaceAll("\n",""));
FileUtils.writeByteArrayToFile(new File(Path), decodedBytes);
}

public void Takescreenshoot(String Path) throws IOException
{
String scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
byte[] decodedBytes = Base64.getDecoder().decode(scrFile.replaceAll("\n",""));
FileUtils.writeByteArrayToFile(new File(Path), decodedBytes);
}
*/
public void clickele(String name)
{
WebElement ele=driver.findElement(By.xpath("//*[@name='"+name+"']"));
try {

//System.out.println(ele.isDisplayed());
ele.click();
//System.out.println(name+" Clicked");
//System.out.println(name+" clicked");
//test.info(name+" clicked");
} catch (Exception e) {
// TODO: handle exception

try {
waitforelement(ele);
ele.click();
//System.out.println(name+" Clicked from catch");
} catch (Exception e2) {
// TODO: handle exception
}

test.fail("Webelement with name "+name+" Not found ");
assertEquals(false, true,"Webelement with name "+name+" Not found ");
}

}

public void waitforelement(WebElement ele)
{
//WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(10));
WebDriverWait wt=new WebDriverWait(driver, 10);
wt.until(ExpectedConditions.elementToBeClickable(ele));
}

public void scrollflow(String Name) throws InterruptedException
{
Thread.sleep(1000);
ScrollPDF(Name);
clickele("CONTINUE");
clickele("PRESS HERE TO SIGN");
Dosign("Signature Box");
clickele("doneButton");
System.out.println(Name+" PDF signed ");
clickele("CONTINUE");
}

public void FillPERSONALINFOForm(String PName,String LName,String Gender,String BirthDate)
{
Formname("First Name",PName);
Formname("Last Name", LName);
driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+Gender+"']")).click();
clickele("Year");

String Yearselect2="//XCUIElementTypeCell[@name='"+BirthDate+"']";
boolean yearStatus2 = driver.findElement(By.xpath(Yearselect2)).isDisplayed();
//System.out.println(yearStatus2);

while (!yearStatus2) {
jsscrollone();
yearStatus2 = driver.findElement(By.xpath(Yearselect2)).isDisplayed();

}
//System.out.println("done with date scroll");
driver.findElement(By.xpath(Yearselect2)).click();
clickele("CONTINUE");
System.out.println("Personal information form filed ");

}

public String getPDFend(String xp)
{
String str = driver.findElement(By.xpath(xp)).getText();
//System.out.println(str);
String[] splited = str.split("\\s+");
//System.out.println(splited[2]);
return splited[2];
}


public void ScrollPDF(String Name) throws InterruptedException
{
String PDFName=props.getProperty("XPPDFName");
displayAndVerifyByXpath(PDFName, Name);
test.info(Name + " PDF name verified");
String Endpage=getPDFend(props.getProperty("XPPDFEnd"));

String xp=props.getProperty("XPPDFEnd");
String EndTest=Endpage +" of "+Endpage;
test.info("Land on PDF page");
// PDF scrolling
String d = driver.findElement(By.xpath(xp)).getText();
//System.out.println(d);

if(!d.contains("1 of"))
{
test.fail("Not Land on PDF page to scroll ");
assertEquals(true, false,"Not Land on PDF page to scroll ");
}

while (!d.equalsIgnoreCase(EndTest)) {
jsscroll();
d = driver.findElement(By.xpath(xp)).getText();
}
//System.out.println("done with scroll");
test.info("PDF scrolled at end");
Thread.sleep(1000);
}

public void EnterPin(String Pin)
{
for(int i=0; i<6;i++)
{
clickele(String.valueOf(Pin.charAt(i)));

}
}

public void Dosign(String Name) throws InterruptedException
{
Thread.sleep(2000);
WebElement p = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+Name+"']"));
int x=p.getLocation().getX();
int y=p.getLocation().getY();

//System.out.println(x +" " + y);
sign(x,y-50,x+100,y-150);
sign(x+100,y-150,x+200,y-50);
sign(x,y-100,x+100,y-100);
}

//Text display and verify
public void displayAndVerifyByXpathwithName(String Name,String Text)
{
String xp="//XCUIElementTypeStaticText[@name='"+Name+"']";
WebElement Dele = driver.findElement(By.xpath(xp));
WebDriverWait wt=new WebDriverWait(driver, 10);
wt.until(ExpectedConditions.visibilityOf(Dele));

assertEquals(Dele.isDisplayed(),true, Name +" name is not displayed");
assertEquals(Dele.getText(),Text,Text+" text is wrongly displayed ");
System.out.println(" Displayed And verifyed "+Text);
test.info(" Displayed And verifyed "+Text);

}


//Text display and verify
public void displayAndVerifyByXpath(String xp,String Text)
{
WebElement Dele = driver.findElement(By.xpath(xp+"[@name='"+Text+"']"));
WebDriverWait wt=new WebDriverWait(driver, 10);
wt.until(ExpectedConditions.visibilityOf(Dele));

assertEquals(Dele.isDisplayed(),true, xp +" xpath element is not displayed");
assertEquals(Dele.getText(),Text,Text+" text is wrongly displayed ");
System.out.println(" Displayed And verifyed "+Text);
test.info(" Displayed And verifyed "+Text);

}

//Text display and verify
public void displayAndVerify(String ID,String Text)
{
WebElement Dele = driver.findElement(By.id(ID));
//WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(10));
WebDriverWait wt=new WebDriverWait(driver, 10);
wt.until(ExpectedConditions.visibilityOf(Dele));

assertEquals(Dele.isDisplayed(),true, ID +" id is not displayed");
assertEquals(Dele.getText(),Text,Text+" text is wrongly displayed ");
System.out.println(" Displayed And verifyed "+Text);
test.info(" Displayed And verifyed "+Text);

}

// Enter Value in personal details form
public void Formname(String name, String value)
{
driver.findElement(By.xpath("//XCUIElementTypeCell[@name='"+name+"']/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther")).sendKeys(value);
}

public void questionclick(String ans)
{
System.out.println(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ans+"']/..//XCUIElementTypeImage[@name='circle']")).isSelected());
//System.out.println(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ans+"']/..//XCUIElementTypeImage[@name='largecircle.fill.circle']")).isDisplayed());
boolean T1 = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ans+"']/..//XCUIElementTypeImage[@name='circle']")).isSelected();
assertEquals(T1, false);
driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ans+"']/..//XCUIElementTypeImage[@name='circle']")).click();

//System.out.println(driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ans+"']/..//XCUIElementTypeImage[@name='largecircle.fill.circle']")).isSelected());
boolean T = driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ans+"']/..//XCUIElementTypeImage[@name='largecircle.fill.circle']")).isDisplayed();
//System.out.println(T);
assertEquals(T, true);

}

public void jsscrollone()
{
JavascriptExecutor js = (JavascriptExecutor) driver;
HashMap<String, String> scrollObject = new HashMap<>();
scrollObject.put("direction", "up");
js.executeScript("mobile: swipe", scrollObject);
}

public void sign(int x,int y,int x1,int y1) throws InterruptedException
{
JavascriptExecutor js = (JavascriptExecutor) driver;
Map<String, Object> params = new HashMap<>();
params.put("duration", 1.0);
params.put("fromX", x);
params.put("fromY", y);
params.put("toX", x1);
params.put("toY", y1);
//params.put("element", ((RemoteWebElement) element).getId());
js.executeScript("mobile: dragFromToForDuration", params);
//System.out.println("draged ");
//Thread.sleep(1000);

}

public void jsscroll()
{
JavascriptExecutor js = (JavascriptExecutor) driver;
HashMap<String, String> scrollObject = new HashMap<>();
scrollObject.put("direction", "up");
js.executeScript("mobile: swipe", scrollObject);
js.executeScript("mobile: swipe", scrollObject);
js.executeScript("mobile: swipe", scrollObject);
js.executeScript("mobile: swipe", scrollObject);
js.executeScript("mobile: swipe", scrollObject);
js.executeScript("mobile: swipe", scrollObject);
js.executeScript("mobile: swipe", scrollObject);
js.executeScript("mobile: swipe", scrollObject);
}


public void mouseclick(WebElement el)
{
Actions action = new Actions(driver);
action.moveToElement(el);
action.click().perform();
}


// For logo Display test
// public void isdisplayTest()
// {
// WebElement Dele = driver.findElement(By.name("logo"));
// WebDriverWait wt=new WebDriverWait(driver, Duration.ofSeconds(10));
// wt.until(ExpectedConditions.visibilityOf(Dele));
// boolean Testresult = driver.findElement(By.name("logo")).isDisplayed();
// assertEquals(Testresult,true,"Logo is not displayed");
// test.info("Logo Displayed on page");
// }


//Click by ID
public void ClickByid(String ID)
{
WebElement ele = driver.findElement(By.id(ID));
try {
ele.click();
test.info("Clicked on "+ ID +" ID element ");
} catch (Exception e) {
try {
waitforelement(ele);
ele.click();
test.info("Clicked on "+ ID +" ID element ");
} catch (Exception e2) {
// TODO: handle exception

}
// TODO: handle exception
test.fail("Webelement with ID "+ID+" Not found ");
assertEquals(false, true,"Webelement with ID "+ID+" Not found ");
}

}

public ExtentTest startreport(String Name)
{
ExtentTest test = extent.createTest(Name);
return test;
}

@BeforeTest
public void starttest()
{
//extent.attachReporter(spark);
}

@AfterTest
public void endtest()
{
extent.flush();
}


}





/*
 
 package Base;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.IOException;

import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ReadXLData {

public static ArrayList<Object[]> readdata() throws IOException

{

System.out.println("in readdata");

String path="./DataSheet/DataFile.xls";

FileInputStream file=new FileInputStream(path);

HSSFWorkbook wb = new HSSFWorkbook(file);

ArrayList<Object[]> myobj1 = new ArrayList<Object[]>();

HSSFSheet sheet = wb.getSheet("Sheet2");

int rowcnt=sheet.getLastRowNum();

System.out.println(rowcnt);

for(int i=1;i<=1;i++)

{

String Study=sheet.getRow(i).getCell(0).toString();

String SiteID=sheet.getRow(i).getCell(1).toString();

String UserNAme=sheet.getRow(i).getCell(2).toString();

String FName=sheet.getRow(i).getCell(3).toString();

//String LName=sheet.getRow(i).getCell(4).toString();

String PDFList=sheet.getRow(i).getCell(4).toString();

Object obj[]= {Study,SiteID,UserNAme,FName,PDFList};

myobj1.add(obj);

}

return myobj1;

}

}

package POC;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;

import java.awt.image.BufferedImage;

import java.io.ByteArrayInputStream;

import java.io.File;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Iterator;

import java.util.List;

import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;

import org.openqa.selenium.OutputType;

import org.openqa.selenium.TakesScreenshot;

import org.openqa.selenium.WebElement;

import org.testng.annotations.DataProvider;

import org.testng.annotations.Test;

import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import Base.BaseClass;

import Base.ReadXLData;

import java.util.Base64;

public class EconsentFlowTest extends BaseClass {

String UserName="";

String Pin="";

@DataProvider

public Iterator<Object[]> Datatest() throws IOException

{

ArrayList<Object[]> MyData = ReadXLData.readdata();

return MyData.iterator();

}

//@Test

public void LaunchTest() throws IOException

{

System.out.println("app launched ");

//ExtentTest test = extent.createTest("App launch Test");

test=startreport("App launch Test");

test.info("info App launched");

test.warning("warning App launched");

}

//@Test

public void UserRegistration()

{

System.out.println("app launched ");

//ExtentTest test = extent.createTest("New user test");

test=startreport("New user test");

test.info("Info New user flow not started");

test.fail("Fail New user flow not started");

}

// @Test(dataProvider = "Datatest")

public void startone(String Study,String SiteID,String UserName,String UserDetails,String PDFList) throws AWTException, InterruptedException, IOException

{

String[] d = UserDetails.toString().split(",");

String PName=d[0];

String LName=d[1];

String Gender=d[2];

String BirthDate=d[3];

String[] PDFArray = PDFList.split(",");

int PDFCount=PDFArray.length;

System.out.println(Study+" "+SiteID+" "+UserName+" "+PName+" "+LName+" "+Gender+" "+BirthDate+" "+PDFList+" "+PDFCount);

// System.out.println(Study+" "+SiteID+" "+UserName+" "+UserDetails+" "+PDFList);

}

//@Test

public void Studyselect() throws IOException

{

//WebElement eml = driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='forgotPassword'])[1]"));

driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='forgotPassword'])[1]")).click();

driver.findElement(By.id("Username")).sendKeys("pravinjadhav");

WebElement ele = driver.findElement(By.id("Username"));

// File scr = driver.findElement(By.id("Username")).getScreenshotAs(OutputType.FILE);

// FileUtils.copyFile(scr, new File("./target/ELEimage.jpg"));

//Takescreenshoot("./target/eleimage.jpg",ele);

//Takescreenshoot("./target/image.jpg");

// String scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);

// System.out.println(scrFile.replaceAll("¥n",""));

// byte[] decodedBytes = Base64.getDecoder().decode(scrFile.replaceAll("¥n",""));

// FileUtils.writeByteArrayToFile(new File("./target/image.jpg"), decodedBytes);

// driver.findElement(By.id("Next")).click();

// Pin=props.getProperty("Pin");

// EnterPin(Pin);

// //test.info("6 Digit PIN Entered");

// //displayAndVerifyByXpath(props.getProperty("XPStudy"),Study);

// displayAndVerifyByXpath(props.getProperty("XPSiteID"),"0001");

// //ClickByid("goButton");

}

@Test(dataProvider = "Datatest")

public void start(String Study,String SiteID,String UserName,String UserDetails,String PDFList) throws AWTException, InterruptedException, IOException

{

// Set Data from xl for script

String[] d = UserDetails.toString().split(",");

String PName=d[0];

String LName=d[1];

String Gender=d[2];

String BirthDate=d[3];

String[] PDFArray = PDFList.split(",");

int PDFCount=PDFArray.length;

System.out.println(Study+" "+SiteID+" "+UserName+" "+PName+" "+LName+" "+Gender+" "+BirthDate+" "+PDFList+" "+PDFCount);

//UserName=props.getProperty("UserName");

//ExtentTest test = extent.createTest("End to End Flow ");

test=startreport("End to End Flow ");

//Old User Flow

//Select User to login

//clickele(UserName);

//test.info("selected User from list");

//test.log(Status.INFO, "this in infor from log");

// End Old user flow

// New User Flow

driver.findElement(By.xpath("(//XCUIElementTypeButton[@name='forgotPassword'])[1]")).click();

driver.findElement(By.id("Username")).sendKeys("pravinjadhav");

driver.findElement(By.id("Next")).click();

test.info("New User login ");

// End New User Flow

// Enter Password

Pin=props.getProperty("Pin");

EnterPin(Pin);

//System.out.println("6 Digit PIN Entered");

test.info("6 Digit PIN Entered");

displayAndVerifyByXpath(props.getProperty("XPStudy"),Study);

displayAndVerifyByXpath(props.getProperty("XPSiteID"),SiteID);

ClickByid("goButton");

displayAndVerify(UserName,UserName);

displayAndVerify("Site Name","Desert Regional Medical Center");

test.info("Land on Participent list and Verified User Loged In and its site name");

// Add Participant

clickele("Add");

// Language selection

displayAndVerify("headerLabel","Select Participant Language");

// next button clicked

WebElement el1 = driver.findElement(By.xpath("//XCUIElementTypeButton[@name='confirmButton']"));

mouseclick(el1);

//Popup Title verify

displayAndVerify("headerLabel","Start Participant Informed Consent");

clickele("startButton");

//// Land on What to expect page and verify title

displayAndVerify("whatToExpectHeader","What to Expect");

displayAndVerify("videoHeadline","Video Overview");

displayAndVerify("documentHeadline","Informed Consent Document(s)");

displayAndVerify("knowledgeReviewHeadline","Knowledge Review");

displayAndVerify("consentHeadline","Consent");

clickele("CONTINUE");

// 1st PDF with one page

displayAndVerify("endTutorial","CLOSE");

clickele("endTutorial");

clickele("CONTINUE");

// Play video

clickele("PlayButton");

clickele("CONTINUE");

test.info("Video played and clicked on next button");

//Verify Reading the Informed Consent p-age with title

displayAndVerify("viewHeader","Reading the Informed Consent");

Thread.sleep(1000);

clickele("CONTINUE");

//displayAndVerifyByXpath(props.getProperty("XPPDFName"), "");

ScrollPDF("Informed Consent for Main Study");

clickele("CONTINUE");

//Knowledge Review Page

displayAndVerify("viewTitle","Knowledge Review");

displayAndVerify("CONTINUE","BEGIN KNOWLEDGE REVIEW");

clickele("CONTINUE");

test.info("Land on question page");

//Question page 1

displayAndVerify("questionNumber","Question 1 of 2");

displayAndVerify("questionText", "The main reason this study is being conducted is to...");

String ans1="determine the safety and effectiveness of an investigational drug called BMS123";

displayAndVerify(ans1,ans1);

//questionclick(ans1);

// Inspect Ans to clcik

driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='determine the safety and effectiveness of an investigational drug called BMS123']/..//XCUIElementTypeImage[@name='circle']")).click();

clickele("CONTINUE");

// //Question page 2

Thread.sleep(1000);

String c = driver.findElement(By.id("questionNumber")).getText();

//displayAndVerify("questionNumber","Question 2 of 2");

// displayAndVerify("questionText", "Once I decide to participate in the study, I can stop ...");

String ans2="whenever I choose";

displayAndVerify(ans2,ans2);

questionclick(ans2);

//

// // Inspect Ans to clcik

//driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ans2+"']/..//XCUIElementTypeImage[@name='circle']")).click();

clickele("CONTINUE");

displayAndVerify("viewTitle", "Congratulations!");

clickele("CONTINUE");

// s

FillPERSONALINFOForm(PName,LName,Gender,BirthDate);

String ans3="I will sign the informed consent.";

//driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='"+ans3+"']/..//XCUIElementTypeImage[@name='circle']")).click();

driver.findElement(By.xpath("//XCUIElementTypeButton[@name='"+ans3+"']")).click();

//XCUIElementTypeButton[@name="I will sign the informed consent."]//XCUIElementTypeImage[@name="circle"]

clickele("CONTINUE");

driver.findElement(By.xpath("//XCUIElementTypeStaticText[@name='YES __________ My study doctor can tell my personal doctor that I am taking part in this study']")).click();

Dosign("Sign");

clickele("doneButton");

clickele("CONTINUE");

assertEquals(driver.findElement(By.xpath("//XCUIElementTypeCell[@name='Patient Phone Number:']")).isDisplayed(),true,"Patient Phone Number: Text is not displayed");

driver.findElement(By.xpath("//XCUIElementTypeCell[@name='Please enter a response.']")).sendKeys("1234567891");

//XCUIElementTypeCell[@name="Please enter a response."]/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther

clickele("CONTINUE");

displayAndVerify("signatureTitle", "By signing below, I show that:");

List<WebElement> wlist = driver.findElements(By.xpath("//XCUIElementTypeImage[@name='circle']"));

//System.out.println("Count "+ wlist.size());

for (WebElement ele : wlist) {

//boolean r =

driver.findElement(By.xpath("(//XCUIElementTypeImage[@name='circle'])["+i+"]")).isDisplayed();

//assertEquals(r, true);

ele.click();

//boolean r1 = driver.findElement(By.xpath("//XCUIElementTypeImage[@name='largecircle.fill.circle'])["+i+"]")).isDisplayed();

//assertEquals(r1, true);

//System.out.println(i+" "+ r );

}

//displayAndVerifyByXpathwithName(Username, Username);

clickele("PRESS HERE TO SIGN");

Dosign("Signature Box");

clickele("doneButton");

clickele("CONTINUE");

clickele("PRESS HERE TO SIGN");

Dosign("Signature Box");

clickele("doneButton");

clickele("CONTINUE");

Thread.sleep(2000);

scrollflow("HIPAA");

clickele("CONTINUE");

displayAndVerifyByXpathwithName("viewTitle","Additional Consent(s) (Optional)");

clickele("CONTINUE");

//PDF signing

for(int i=0;i<PDFArray.length;i++)

{

scrollflow(PDFArray[i].toString());

//System.out.println("Signed "+PDFArray[i]);

}

displayAndVerifyByXpathwithName("viewHeader","Thank you for your time.");

displayAndVerifyByXpathwithName("signatureStatus","Consent signed.");

// Final Verification

//displayAndVerifyByXpathwithName("admin","Admin");

clickele("Menu");

clickele("admin");

Thread.sleep(2000);

// cross check flow

clickele(UserName);

EnterPin(Pin);

Thread.sleep(2000);

//ClickByid("goButton");

displayAndVerify(UserName,UserName);

String Search=PName+" "+LName;

driver.findElement(By.id("Search")).sendKeys(Search);

Thread.sleep(2000);

WebElement Sele = driver.findElement(By.xpath("//XCUIElementTypeCell[@name='"+Search+"']"));

assertEquals(true, Sele.isDisplayed(),"Not foud Result in search ");

test.info(Search + " Found in search");

ClickByid("Settings");

Thread.sleep(2000);

ClickByid("signOut");

test.info(UserName + " Signed Out");

}

}

<dependencies>

<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->

<dependency>

<groupId>org.apache.poi</groupId>

<artifactId>poi</artifactId>

<version>5.2.2</version>

</dependency>

<!-- https://mvnrepository.com/artifact/com.aventstack/extentreports -->

<!-- https://mvnrepository.com/artifact/com.aventstack/extentreports -->

<dependency>

<groupId>com.aventstack</groupId>

<artifactId>extentreports</artifactId>

<version>5.0.9</version>

</dependency>

<!-- https://mvnrepository.com/artifact/commons-io/commons-io -->

<dependency>

<groupId>commons-io</groupId>

<artifactId>commons-io</artifactId>

<version>2.11.0</version>

</dependency>

<dependency>

<groupId>io.appium</groupId>

<artifactId>java-client</artifactId>

<version>8.1.1</version>

</dependency>

<!-- https://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-java -->

<dependency>

<groupId>org.seleniumhq.selenium</groupId>

<artifactId>selenium-java</artifactId>

<version>4.4.0</version>

</dependency>

<!-- https://mvnrepository.com/artifact/org.testng/testng -->

<dependency>

<groupId>org.testng</groupId>

<artifactId>testng</artifactId>

<version>7.6.1</version>

<scope>test</scope>

</dependency>

</dependencies>

Create DAtaFile.xls file

Study SiteID UserName Fname PDFList

EC001-001

(FUNCTEST) 0001 Pravin Jadhav Soman,Pande,Male,1950 Informed Consent For Optional Sample Collection (s) During the Study,Optional Future Research,Additional ICF (Opt.)
 
 
 */

