package com.letskodeit.base;

import com.letskodeit.pageclasses.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class BaseTest {
    protected WebDriver driver;
    protected String baseURL;
    protected LoginPage login ;

    protected SearchBarPage search ;

    protected NavigationPage navigationPage;
    protected  ResultsPage result;
    protected CategoryFilterPage category;

    @BeforeClass
    @Parameters({"browser"})
    public void commonSetUp(String browser){
//        System.setProperty("webdriver.chrome.driver","drivers/chromedriver-2");
        driver = WebDriverFactory.getInstance().getDriver(browser);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        baseURL = "https://learn.letskodeit.com";
        driver.get(baseURL);

        login = new LoginPage(driver);
        search = new SearchBarPage(driver);
        navigationPage = new NavigationPage(driver);
        category = new CategoryFilterPage(driver);
//        Open url

//        login using a valid user name and password
        login = navigationPage.login();
//        navigationPage = login.signInWith("test@email.com","abcabc");
//        enter the name of the course to search for

    }

    @BeforeMethod
    public void methodSetup(){
        CheckPoint.clearHashMap();
    }

    @AfterClass
    public void commonTearDown(){
        WebDriverFactory.getInstance().quitDriver();
    }
}
