package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirstCry {
       WebDriver driver;
       WebDriverWait wait;
    ExtentReports reports;
    ExtentTest test;
    @BeforeTest
    public void before() throws Exception{
        ExtentSparkReporter exsprk = new ExtentSparkReporter("C:\\Users\\hp\\Desktop\\sem IV\\Software Testing\\Model\\firstcry\\report\\report1.html");
        reports = new ExtentReports();
        reports.attachReporter(exsprk);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Test(priority=1)
    public void test1() throws InvalidFormatException, IOException {
        test = reports.createTest("Test 1", "Toys");
        FileInputStream fs = new FileInputStream("C:\\Users\\hp\\Desktop\\sem IV\\Software Testing\\firstcry\\fcry.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(row.getCell(0).getStringCellValue());
        driver.manage().window().maximize();
        String searchTerm = row.getCell(1).getStringCellValue();
         WebElement searchInput = driver.findElement(By.xpath("//*[@id='search_box']"));
        searchInput.click();
        searchInput.sendKeys(searchTerm, Keys.ENTER);
        Assert.assertTrue(driver.getCurrentUrl().contains("kids-toys"), "URL contains 'kids-toys'. Failed!");
    }
    @Test(priority=2)
    public void test2() throws Exception
     {
        test = reports.createTest("Test 2", "Clothes");
        FileInputStream fs = new FileInputStream("C:\\Users\\hp\\Desktop\\sem IV\\Software Testing\\firstcry\\fcry.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(row.getCell(0).getStringCellValue());
        driver.manage().window().maximize();
        String searchTerm = row.getCell(2).getStringCellValue();
         WebElement searchInput = driver.findElement(By.xpath("//*[@id='search_box']"));
        searchInput.click();
        searchInput.sendKeys(searchTerm, Keys.ENTER);
        driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/ul/li[4]")).click();
        Thread.sleep(2000);
        String text=driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[1]/div[1]/h1")).getText();
        Assert.assertTrue(text.contains("Ethnic"));
    }
    @Test(priority=3)
    public void test3() throws Exception
     {
        test = reports.createTest("Test 3", "Balloons");
        FileInputStream fs = new FileInputStream("C:\\Users\\hp\\Desktop\\sem IV\\Software Testing\\firstcry\\fcry.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(row.getCell(0).getStringCellValue());
        driver.manage().window().maximize();
        String searchTerm = row.getCell(3).getStringCellValue();
         WebElement searchInput = driver.findElement(By.xpath("//*[@id='search_box']"));
        searchInput.click();
        searchInput.sendKeys(searchTerm, Keys.ENTER);
        String productname=driver.findElement(By.xpath("//*[@id='maindiv']/div[1]/div/div[1]/div[2]/a")).getText();
      driver.findElement(By.xpath("//*[@id='maindiv']/div[1]/div/div[1]/div[2]/a")).click();
      Thread.sleep(4000);
      Set<String>s=driver.getWindowHandles();
      for(String it:s)
      {
         if(!it.equals(driver.getWindowHandle()))
         {
            driver.switchTo().window(it);
            break;
         }
      }
      String pname=driver.findElement(By.xpath("//*[@id=\"prod_name\"]")).getText();
      Assert.assertEquals(productname,pname);
    }
    @AfterMethod
    public void afterTest(ITestResult result) throws Exception{
        if(result.getStatus()==ITestResult.FAILURE)
        {
            test.log(Status.FAIL, "TestCase Failed: "+result.getName());
            test.log(Status.FAIL, "Testcase Failed Reason: "+result.getThrowable());
            File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            String path = "C:\\Users\\hp\\Desktop\\sem IV\\Software Testing\\firstcry\\Screenshot\\"+result.getName()+"png";
            FileUtils.copyFile(screenshot,new File(path));
            test.addScreenCaptureFromPath(path);

        }
        else if (result.getStatus()==ITestResult.SUCCESS)
        { test.log(Status.PASS, "Test CAse Passed: "+result.getName());
        }
      else if (result.getStatus()==ITestResult.SKIP)
        { test.log(Status.SKIP, "Test CAse Skipped: "+result.getName());
        }
        reports.flush();
      //  driver.quit();
    }
    
}
