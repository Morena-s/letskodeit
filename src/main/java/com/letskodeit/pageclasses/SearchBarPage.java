package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchBarPage extends BasePage {

    WebDriver driver;
    private String SEARCH_COURSE_FIELD ="id=>search-courses";
    private String SEARCH_COURSE_BUTTON ="id=>search-course-button";

    public SearchBarPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public ResultsPage course(String courseName){
        sendData(SEARCH_COURSE_FIELD,courseName,"Search Course Field");
        clickElement(SEARCH_COURSE_BUTTON,"Search Course Button");
//        WebElement searchField = driver.findElement(By.id(SEARCH_COURSE_FIELD));
//        searchField.clear();
//        searchField.sendKeys(courseName);
//
//        driver.findElement(By.id(SEARCH_COURSE_BUTTON)).click();
        return new ResultsPage(driver);
    }
}
