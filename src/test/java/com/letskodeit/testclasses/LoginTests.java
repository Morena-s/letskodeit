package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.base.CheckPoint;
import com.letskodeit.pageclasses.LoginPage;
import com.letskodeit.pageclasses.NavigationPage;
import com.letskodeit.utilities.Constants;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;

public class LoginTests extends BaseTest {






    @BeforeClass
    public void setUp(){
//        System.setProperty("webdriver.chrome.driver","drivers/chromedriver-2");
//        driver = new ChromeDriver();
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = Constants.BASE_URL;
        driver.get(baseURL);

        login = new LoginPage(driver);

        navigationPage = new NavigationPage(driver);

//        Open url

//        login using a valid user name and password
        login = navigationPage.login();

//        enter the name of the course to search for

    }
    @Test
    public void testLogin(){

        navigationPage = login.signInWith("test@email.com","abcabc");
//        boolean headerResult = navigationPage.
        boolean result = navigationPage.isUserLoggedIn();
//        Assert.assertTrue(result);
        CheckPoint.markFinal("test-01",result,"Login verification");


    }

    @Test
    public void testInvalidLogin(){

        navigationPage = login.signInWith("testrrr@email","abcabc");
        boolean result = navigationPage.isUserLoggedIn();
        Assert.assertFalse(result);
    }

    @AfterMethod
    public void afterMethod(){

        if(navigationPage.isUserLoggedIn()){
            navigationPage.logout();
            navigationPage.login();
        }
    }

}
