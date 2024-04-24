package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

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
       driver.get("https://www.demoblaze.com/");
       driver.manage().window().maximize();
       driver.findElement(By.linkText("Laptops")).click();
       Thread.sleep(3000);
       driver.findElement(By.linkText("MacBook air")).click();
       Thread.sleep(3000);
       driver.findElement(By.linkText("Add to cart")).click();
       Thread.sleep(3000);
       driver.switchTo().alert().accept();
       Thread.sleep(3000);
       driver.findElement(By.linkText("Cart")).click();
       Thread.sleep(3000);
       String title=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[2]")).getText();
       String price=driver.findElement(By.xpath("//*[@id='tbodyid']/tr/td[3]")).getText();
       System.out.println(title+" : $"+price);
    }
}
