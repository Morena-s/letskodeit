package com.letskodeit.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;

import java.util.concurrent.TimeUnit;

public class WebDriverFactory {
    //singleton class
    //only one instance from the class can exist at a time
    private static final WebDriverFactory instance = new WebDriverFactory();

    private WebDriverFactory(){

    }

    public static WebDriverFactory getInstance(){
        return instance;
    }

    private static ThreadLocal<WebDriver> threadedDriver = new ThreadLocal<WebDriver>();
    private static ThreadLocal<String> threadedBrowser = new ThreadLocal<String>();

    public WebDriver getDriver(String browserType){
        WebDriver driver = null;
        setDriver(browserType);
        threadedBrowser.set(browserType);
        if (threadedDriver.get()==null){
            try{
                if(browserType.equalsIgnoreCase("firefox")){
                    FirefoxOptions options = setFfOptions();
                    driver = new FirefoxDriver(options);
                    threadedDriver.set(driver);
                }
                if(browserType.equalsIgnoreCase("chrome")){
                    ChromeOptions options = setChromeOptions();
                    driver = new ChromeDriver(options);
                    threadedDriver.set(driver);
                }
                if(browserType.equalsIgnoreCase("internet")){
                    InternetExplorerOptions options = setIEOptions();
                    driver = new InternetExplorerDriver(options);
                    threadedDriver.set(driver);
                }
            }catch(Exception e){
                e.printStackTrace();
            } threadedDriver.get().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            threadedDriver.get().manage().window().maximize();
        }

        return threadedDriver.get();
    }

    public String getBrowser(){
        return threadedBrowser.get();
    }


    public void setDriver(String browser){
        String driverPath = "";
        String osName = System.getProperty("os.name").toLowerCase().substring(0,3);
        System.out.println("Operating System name from System property is :: " + osName);
        String directory = System.getProperty("user.dir")+ "//drivers//";
        String driverKey="";
        String driverValue = "";
        if(browser.equalsIgnoreCase("firefox")){
            driverKey = "webdriver.gecko.driver";
            driverValue ="geckodriver";
        }else if(browser.equalsIgnoreCase("chrome")){
            driverKey = "webdriver.chrome.driver";
            driverValue ="chromedriver-2";
        }else if(browser.equalsIgnoreCase("ie")){
            driverKey = "webdriver.ie.driver";
            driverValue ="IEDriverServer";
        }else{
            System.out.println("Browser type is not supported");
        }
        driverPath =directory + driverValue + (osName.equals("win")? ".exe":"");
        System.out.println("Driver binary :: "+ driverPath);
        System.setProperty(driverKey,driverPath);

    }

    private ChromeOptions setChromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("disable-infobars");
        return options;
    }

    private FirefoxOptions setFfOptions(){
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, false);
        return options;
    }

    private InternetExplorerOptions setIEOptions(){
        InternetExplorerOptions options = new InternetExplorerOptions();
        options.setCapability(InternetExplorerDriver.NATIVE_EVENTS,false);
        options.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING,false);
        options.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS,false);
        options.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION,true);
        options.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING,true);
        options.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
        return options;
    }

    public void quitDriver(){
        threadedDriver.get().quit();
        threadedDriver.set(null);
    }
}
