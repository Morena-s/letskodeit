package com.letskodeit.pageclasses;

import com.letskodeit.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CategoryFilterPage extends BasePage {

    WebDriver driver;
    private JavascriptExecutor js;
    private String CATEGORY_DROPDOWN = "xpath=>//div[contains(@class,'course-filter')][1]//button";
    private String CATEGORY_OPTION = "xpath=>//a[@href='/courses/category/%s']";

    public CategoryFilterPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        js = (JavascriptExecutor) driver;

    }

    public WebElement findCategory(String categoryName){
        clickOnCategoryFilter();
//        WebDriverWait wait = new WebDriverWait(driver,3);
//        WebElement categoryOption = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(String.format(CATEGORY_OPTION,categoryName)))));
        WebElement categoryOption = waitForElement(String.format(CATEGORY_OPTION, categoryName), 15);

        return categoryOption;
    }

    public ResultsPage select(String categoryName){

        WebElement categoryOption = findCategory(categoryName);
        javascriptClick(categoryOption, "Category Option");
//        js.executeScript("arguments[0].click();", findCategory(categoryName));
        return new ResultsPage(driver);
    }

    public void clickOnCategoryFilter(){
        clickElement(CATEGORY_DROPDOWN, "Category Dropdown");
//        driver.findElement(By.xpath(CATEGORY_DROPDOWN)).click();
    }

    public int findCoursesCount(String categoryName){

        WebElement categoryOption = findCategory(categoryName);
        String categoryText = getText(categoryOption," Category Option");

//        String categoryText = findCategory(categoryName).getText();
        String[] arrayTemp = categoryText.split("\\(");
        String courseCountString = arrayTemp[1].split("\\)")[0];
        int courseCount = Integer.parseInt(courseCountString);
        clickOnCategoryFilter();
        return courseCount;


    }

}
