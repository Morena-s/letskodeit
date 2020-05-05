package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultsPage extends BasePage {

    WebDriver driver;
    private String URL = "?query=";
    private String COURSES_LIST = "xpath=>//div[@class='course-listing']";

    public ResultsPage(WebDriver driver){
        super(driver);
        this.driver = driver;
    }

    public boolean isOpen(){
        return driver.getCurrentUrl().contains(URL);
    }

    public int courseCount(){
        List<WebElement> course_list = getElementList(COURSES_LIST, "Courses List");
        return course_list.size();
    }

    public boolean verifySearchResult(){
        boolean result = true;
        if(courseCount() > 0){
            result = true;
        }
        result = isOpen() && result;
        return result;

        }

     public boolean verifyFilterCourseCount(int expectedCount){

        return courseCount() == expectedCount;
     }

    }

