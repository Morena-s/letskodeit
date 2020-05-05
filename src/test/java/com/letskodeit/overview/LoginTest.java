package com.letskodeit.overview;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    WebDriver driver;
    String baseURL;

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver-2");
        driver =  new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = "https://learn.letskodeit.com";
        driver.get(baseURL);
    }

    @Test
    public void testLogin(){

        WebElement accountImage = null;
            driver.findElement(By.xpath("//a[@href='/sign_in']")).click();
            WebElement userEmail = driver.findElement(By.id("user_email"));
            userEmail.clear();
            userEmail.sendKeys("test@email.com");
            WebElement userPass = driver.findElement(By.id("user_password"));
            userPass.clear();
            userPass.sendKeys("abcabc");
            driver.findElement(By.name("commit")).submit();
            try {
            accountImage = driver.findElement(By.className("gravatar"));

        }catch (Exception e){
            e.printStackTrace();
        }
        Assert.assertNotNull(accountImage);



    }
}
