package com.example;

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
         driver.get("https://www.google.com");

        //  WebDriverManager.edgedriver().setup();
        // WebDriver driver=new EdgeDriver();
        // driver.get("https://www.google.com");

        // WebDriverManager.firefoxdriver().setup();
        // WebDriver driver=new FirefoxDriver();
        // driver.get("https://www.google.com");
        
        driver.manage().window().maximize();

    }
}
