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
    public static void main( String[] args )
    {
      WebDriverManager.chromedriver().setup();
      WebDriver driver=new ChromeDriver();
     driver.get("https://www.shoppersstop.com");
      
      driver.manage().window().maximize();
     driver.findElement(By.id("profileImage")).click();
      
    }
}
