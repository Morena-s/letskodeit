package com.letskodeit.base;

import com.letskodeit.utilities.Util;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class CustomDriver {
    public WebDriver driver;
    private JavascriptExecutor js;

    public CustomDriver(WebDriver driver){
        this.driver = driver;
        js = (JavascriptExecutor)driver;
    }

    /**
     * Refresh the current browser session
     */

    public  void refresh(){
        driver.navigate().refresh();
        System.out.println("The Current browser location was refreshed");

    }

    /**
     *
     * @return the Current page title
     */

    public String getTitle(){
        String title = driver.getTitle();
        System.out.println("Title of the page is " + title);
        return title;
    }

    /**
     *
     * @return the Current page URL
     */
    public String getCurrentUrl(){
        String currentUrl = driver.getCurrentUrl();
        System.out.println("The Current Url is " + currentUrl);
        return currentUrl;
    }

    /**
     * Navigate back
     */

    public void browserBack(){
        driver.navigate().back();
        System.out.println("Navigate back");
    }

    /**
     * Navigate forward
     */
    public void browserForward(){
        driver.navigate().forward();
        System.out.println("Navigate forward");
    }

    /**
     *
     * @param locator
     * @return
     */

    public By getByType(String locator){
        By by = null;
        String locatorType = locator.split("=>")[0];
        locator = locator.split("=>")[1];
        try{
            if (locatorType.contains("id")){
                by = By.id(locator);
            }else if (locatorType.contains("name")){
                by = By.name(locator);
            }else if (locatorType.contains("xpath")){
                by = By.xpath(locator);
            }else if (locatorType.contains("class")){
                by = By.className(locator);
            }else if (locatorType.contains("css")){
                by = By.cssSelector(locator);
            }else if (locatorType.contains("link")){
                by = By.linkText(locator);
            }else if (locatorType.contains("tag")){
                by = By.tagName(locator);
            }else if (locatorType.contains("partiallink")){
                by = By.partialLinkText(locator);
            }else{
                System.out.println("Locator " + locatorType + "is not supported");
            }

        }catch(Exception e){
            System.out.println("Element could not be found by the locator type" + locatorType);

        }
        return by;



    }

    /***
     * Build the WebElement by given locator strategy
     * @param locator - locator strategy, id=>example, xpath=>example etc
     * @param info -> information about element usually text on element
     * @return WebElement
     */

    public WebElement getElement(String locator, String info){

        WebElement element = null;
        By byType = getByType(locator);
        try{
            element =  driver.findElement(byType);
            System.out.println("Element " + info +  "found by locator " + locator);
        }catch(Exception e){
            System.out.println("Element not found by " + locator);
            e.printStackTrace();
        }
        return element;
    }

    public List<WebElement> getElementList(String locator, String info){
        List<WebElement> elementList = new ArrayList<>();
        By byType = getByType(locator);
        try{
            elementList = driver.findElements(byType);
            System.out.println("Element List " + info +  "found by locator " + locator);


        }catch(Exception e){
            System.out.println("Element List not found by " + locator);
            e.printStackTrace();
        }

        return elementList;

    }

    public boolean isElementPresent(String locator, String info){
        List<WebElement> elementList = getElementList(locator, info);
        if(elementList.size()>0){
            return true;
        }else{
            return false;
        }
    }

    public void clickElement(WebElement element, String info, long timeToWait){
        try{
            element.click();
            if(timeToWait == 0){
                System.out.println("Clicked on :: " + info) ;
            }else{
                Util.sleep(timeToWait, "Clicked on :: " + info);
            }

        }catch(Exception e){

            System.out.println("Cannot click on :: " + info);
//            takeScreenShot("Click Error", "");

        }

    }

    public void clickElement(WebElement element, String info){
        clickElement(element, info,0);
    }

    public void clickElement(String locator, String info, long timeToWait){
        WebElement element = this.getElement(locator,info);
        clickElement(element, info,timeToWait);
    }

    public void clickElement(String locator, String info){
        WebElement element = this.getElement(locator,info);
        clickElement(element, info,0);
    }

    public void javascriptClick(WebElement element, String info){

        try{
            js.executeScript("arguments[0].click();", element);
            System.out.println("Clicked on :: " + info);
        }catch (Exception e){
            System.out.println("Cannot click on :: " + info);
        }

    }

    public void javascriptClick(String locator, String info){
        WebElement element = getElement(locator, info);
        try{
            js.executeScript("arguments[0].click();", element);
            System.out.println("Clicked on :: " + info);
        }catch (Exception e){
            System.out.println("Cannot click on :: " + info);
        }

    }

    public void clickWhenReady(By locator, int timeout){
        try{
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            WebElement element = null;
            System.out.println("Waiting for max: " + timeout + "Seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver,15);
            element = wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
            System.out.println("Element is clicked on the web page");
            driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);

        }catch (Exception e){
            System.out.println("Element did not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
        }

    }

    public void sendData(WebElement element, String data, String info, Boolean clear){

        try{
            if (clear){
                element.clear();
            }
            element.sendKeys(data);
            System.out.println("Send keys on element "+ info + " with data:: " + data);
        }catch (Exception e){
            System.out.println("Cannot send keys on element "+ info + " with data:: " + data);
        }
    }

    public void sendData(String locator, String data, String info, Boolean clear){

        WebElement element = getElement(locator, info);
        sendData(element, data, info, clear);
    }

    public void sendData(WebElement element, String data, String info){
        sendData(element, data, info,true);
    }
    public void sendData(String locator, String data, String info) {

        WebElement element = getElement(locator, info);
        sendData(element, data, info, true);

    }
    public String getText(WebElement element, String info){
        System.out.println("Getting text from element :: "+ info);
        String text = null;
        text = element.getText();
        if (text.length() == 0){
            text = element.getAttribute("InnerText");
        }
        if(!text.isEmpty()){
            System.out.println("The text is " + text);

        }else{
            text = "Not Found!!";
        }

        return text.trim();

    }

    public String getText(String locator, String info){
        WebElement element = getElement(locator, info);
        return getText(element, info);
    }

    public Boolean isEnabled(WebElement element, String info){
        Boolean enabled = false;
        if(element != null){
            enabled = element.isEnabled();
            if(enabled){
                System.out.println("Element " + info +" is enabled");
            }else{
                System.out.println("Element " + info +" is not enabled");
            }
        }
        return enabled;
    }

    public Boolean isEnabled(String locator, String info){
        WebElement element = getElement(locator,info);
        return isEnabled(element, info);
    }

    public Boolean isDisplayed(WebElement element, String info){
        Boolean displayed = false;
        if(element != null){
            displayed = element.isDisplayed();
        }
        if (displayed)
            System.out.println("Element ::" + info + "is displayed");
        else
            System.out.println("Element ::" + info + "is Not displayed");

        return displayed;
    }

    public Boolean isDisplayed(String locator, String info){
        WebElement element = getElement(locator, info);
        return isDisplayed(element, info);
    }

    public Boolean isSelected(WebElement element, String info){
        Boolean selected = false;
        if(element != null){
            selected = element.isSelected();
        }
        if (selected)
            System.out.println("Element ::" + info + "is selected");
        else
            System.out.println("Element ::" + info + "is already selected");

        return selected;
    }
    public Boolean isSelected(String locator, String info){
        WebElement element = getElement(locator, info);
        return isSelected(element, info);
    }

    public void Check(WebElement element, String info){
        if(!isSelected(element, info)){
            clickElement(element,info);
            System.out.println("Element:: " + info + " is checked");
        }else{
            System.out.println("Element:: " + info + " is already checked");
        }

    }
    public void Check(String locator, String info){
        WebElement element = getElement(locator, info);
        Check(element, info);

    }

    public void unCheck(WebElement element, String info){
        if(isSelected(element, info)){
            clickElement(element,info);
            System.out.println("Element:: " + info + " is unchecked");
        }else{
            System.out.println("Element:: " + info + " is already unchecked");
        }

    }

    public void unCheck(String locator, String info){
        WebElement element = getElement(locator, info);
        unCheck(element, info);

    }

    public Boolean submit(WebElement element, String info){
        if(element != null){
            element.submit();
            System.out.println("Element :: " + info + " is submitted");
            return true;
        }else{
            System.out.println("Element :: " + info + " cannot be submitted");
            return false;
        }
    }

    public String getElementAttribute(WebElement element, String attribute){
        return element.getAttribute(attribute);

    }
    public String getElementAttribute(String locator, String attribute){
        WebElement element = getElement(locator, "info");
        return element.getAttribute(attribute);

    }

    public WebElement waitForElement(String locator, int timeout){
        By byType = getByType(locator);
        WebElement element = null;
        try{
            System.out.println("Waiting for max :: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(byType));
            System.out.println("Element appeared on web page");

        }catch (Exception e){
            System.out.println("Element did not appear on web page");
        }
        return element;
    }
    public Boolean waitForLoading(String locator, int timeout){
        By byType = getByType(locator);
        Boolean visible = false;
        try{
            System.out.println("Waiting for max :: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            visible = wait.until(ExpectedConditions.invisibilityOfElementLocated(byType));
            System.out.println("Element appeared on web page");

        }catch (Exception e){
            System.out.println("Element did not appear on web page");
        }
        return visible;
    }

    public void mouseHover(String locator, String info){
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
    }

    public String  takeScreenShot(String methodName, String browserName){
        String filename = Util.getScreenShotName(methodName, browserName);
        String screenShotDir = System.getProperty("user.dir") + "//" + "test-outputs/screenshots";
        new File(screenShotDir).mkdirs();
        String path = screenShotDir +"//"+ filename;

        try{
            File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenShot,new File(path));
            System.out.println("Screenshot is stored at " + path);


        }catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }









//    public WebElement getElem(By locatorType){
//        WebElement element = null;
//        try{
//            element = driver.findElement(locatorType);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return element;
//    }




}
