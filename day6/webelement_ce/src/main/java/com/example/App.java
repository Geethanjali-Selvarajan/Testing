package com.example;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
       WebDriverManager.chromedriver().setup();
       WebDriver driver=new ChromeDriver();
       driver.get("https://economictimes.indiatimes.com/et-now/results");
       driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
     

      driver.findElement(By.linkText("Mutual Funds")).click();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

      JavascriptExecutor js=(JavascriptExecutor)driver;
      js.executeScript("window.scrollBy(0,500)"," ");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

      WebElement drop=driver.findElement(By.xpath("//*[@id='amcSelection']"));
      Select select1=new Select(drop);
      select1.selectByVisibleText("Canara Robeco");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

      WebElement drop1=driver.findElement(By.xpath("//*[@id='schemenm']"));
      Select select2=new Select(drop1);
      select2.selectByVisibleText("Canara Robeco Bluechip Equity Direct-G");
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

      driver.findElement(By.xpath("//*[@id='getDetails']")).click();
      driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

      ArrayList<String> windowhandles = new ArrayList<>(driver.getWindowHandles());
      driver.switchTo().window(windowhandles.get(1));

      driver.findElement(By.xpath("//*[@id='installment_type']/li/span")).click();
      driver.findElement(By.xpath("//*[@id='installment_type']/li/ul/li[1]")).click();

      driver.findElement(By.xpath("//*[@id='installment_amt']/li")).click();
      driver.findElement(By.xpath("//*[@id='installment_amt']/li/ul/li[3]/span")).click();

      driver.findElement(By.xpath("//*[@id='installment_period']/li")).click();
      driver.findElement(By.xpath("//*[@id='installment_period']/li/ul/li[4]/span")).click();

      driver.findElement(By.xpath("//*[@id='mfNav']/div/ul/li[2]")).click();
      
      String text=driver.findElement(By.xpath("//*[@id='mfReturns']/div[2]/div[2]/ul/li[1]/table/tbody/tr[1]")).getText();
      System.out.println(text);

    }
}
