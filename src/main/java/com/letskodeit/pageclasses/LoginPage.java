package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage {
    public WebDriver driver;

    private String Email_FIELD = "id=>user_email";
    private String PASS_FIELD = "id=>user_password";
    private String LOG_IN_Button = "name=>commit";

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }



    public NavigationPage signInWith(String userName, String userPass){
        sendData(Email_FIELD,userName, "Email field");
        sendData(PASS_FIELD, userPass, "User Password");
        clickElement(LOG_IN_Button, "Login Button");


//        WebElement emailField =  driver.findElement(By.id(Email_FIELD));
//        emailField.clear();
//        emailField.sendKeys(userName);
//
//        WebElement passField =  driver.findElement(By.id(PASS_FIELD));
//        passField.clear();
//        passField.sendKeys(userPass);
//
//        driver.findElement(By.name(LOG_IN_Button)).click();

        return new NavigationPage(driver);


    }
}
