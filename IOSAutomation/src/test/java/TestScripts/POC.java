package TestScripts;

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

import org.testng.annotations.*;

//import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import Base.BaseClass4;
import io.appium.java_client.MobileElement;

//import Base.ReadXLData;

import java.util.Base64;

public class POC extends BaseClass4 {

String UserName="Pravin Jadhav";

String Pin="123456";

/*
@DataProvider

public Iterator<Object[]> Datatest() throws IOException

{

ArrayList<Object[]> MyData = ReadXLData.readdata();

return MyData.iterator();

} */

//@Test
public void LaunchTest() throws IOException

{

System.out.println("app launched ");

//ExtentTest test = extent.createTest("App launch Test");

//test=startreport("App launch Test");

//test.info("info App launched");

//test.warning("warning App launched");

}

//@Test

public void UserRegistration()

{

System.out.println("app launched ");

//ExtentTest test = extent.createTest("New user test");

//test=startreport("New user test");

//test.info("Info New user flow not started");

//test.fail("Fail New user flow not started");

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

//@Test(dataProvider = "Datatest")

//public void start(String Study,String SiteID,String UserName,String UserDetails,String PDFList) throws AWTException, InterruptedException, IOException
@Test
public void start () throws InterruptedException
{

// Set Data from xl for script

//String[] d = UserDetails.toString().split(",");

//String PName=d[0];

//String LName=d[1];

//String Gender=d[2];

//String BirthDate=d[3];

//String[] PDFArray = PDFList.split(",");

//int PDFCount=PDFArray.length;

//System.out.println(Study+" "+SiteID+" "+UserName+" "+PName+" "+LName+" "+Gender+" "+BirthDate+" "+PDFList+" "+PDFCount);

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

//displayAndVerifyByXpath(props.getProperty("XPStudy"),Study);

//displayAndVerifyByXpath(props.getProperty("XPSiteID"),SiteID);

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

//FillPERSONALINFOForm(PName,LName,Gender,BirthDate);

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

List<MobileElement> wlist = driver.findElements(By.xpath("//XCUIElementTypeImage[@name='circle']"));

//System.out.println("Count "+ wlist.size());

for (WebElement ele : wlist) {

//boolean r =

//driver.findElement(By.xpath("(//XCUIElementTypeImage[@name='circle'])["+i+"]")).isDisplayed();

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

/*
for(int i=0;i<PDFArray.length;i++)

{

scrollflow(PDFArray[i].toString());

//System.out.println("Signed "+PDFArray[i]);

}
*/

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

//String Search=PName+" "+LName;

//driver.findElement(By.id("Search")).sendKeys(Search);

Thread.sleep(2000);

//WebElement Sele = driver.findElement(By.xpath("//XCUIElementTypeCell[@name='"+Search+"']"));

//assertEquals(true, Sele.isDisplayed(),"Not foud Result in search ");

//test.info(Search + " Found in search");

ClickByid("Settings");

Thread.sleep(2000);

ClickByid("signOut");

test.info(UserName + " Signed Out");

}

}


