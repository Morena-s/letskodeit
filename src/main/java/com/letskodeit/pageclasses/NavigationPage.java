package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.server.handler.ClickElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;
import java.util.List;


public class NavigationPage extends BasePage {
    WebDriver driver;
    private JavascriptExecutor js;
    private final String URL = "https://learn.letskodeit.com/courses";
    private String allCoursesLink = "xpath=>//a[@href='/courses']";
    private String myCourses = "xpath=>a[@href='/courses/enrolled']";
    private String ACCOUNT_ICON = "class=>gravatar";
    private String LOGOUT_LINK ="xpath=>//a[@href='/sign_out']";
    private String Login_LINK = "xpath=>//a[@href='/sign_in']";


    public NavigationPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public LoginPage login(){
        clickElement(Login_LINK,"Login Link");
//        driver.findElement(By.xpath(Login_LINK)).click();
        return new LoginPage(driver);
    }

    public void open(){
        clickElement(allCoursesLink,"All Courses Link");
//        driver.findElement(By.xpath(allCoursesLink)).click();
    }

    public boolean isOpen(){
        return URL.equalsIgnoreCase(driver.getCurrentUrl());
    }

    public boolean isUserLoggedIn(){

//        driver.findElement(By.xpath("//a[@href='/sign_in']")).click();
//        WebElement userEmail = driver.findElement(By.id("user_email"));
//        userEmail.clear();
//        userEmail.sendKeys("test@email.com");
//        WebElement userPass = driver.findElement(By.id("user_password"));
//        userPass.clear();
//        userPass.sendKeys("abcabc");
//        driver.findElement(By.name("commit")).submit();
        try {
            List<WebElement> accountImage = getElementList(ACCOUNT_ICON,"Account Icon");
            if(accountImage.size()>0)
            return true;
            else
                return false;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }

    public void logout(){
//        driver.findElement(By.className(ACCOUNT_ICON)).click();
        clickElement(ACCOUNT_ICON, "Account Icon");
        WebElement logOutLink = waitForElement(LOGOUT_LINK, 10);
        javascriptClick(logOutLink,"LogOut Link");

//        WebDriverWait wait = new WebDriverWait(driver,3);
//        WebElement logOutLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_LINK)));
//        logOutLink.click();
//        js.executeScript("arguments[0].click();",logOutLink);


    }
}
