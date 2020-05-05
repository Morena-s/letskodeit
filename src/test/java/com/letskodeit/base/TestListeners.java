package com.letskodeit.base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.letskodeit.utilities.ExtentManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import java.io.IOException;
import java.util.Arrays;

public class TestListeners extends BaseTest implements ITestListener {

    private static ExtentReports extentReports = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();
    private static Logger log = LogManager.getLogger(TestListeners.class.getName());

    /**
     * Invoked before running all the test methods belonging to the classes inside the &lt;test&gt; tag
     * and calling all their Configuration methods.
     *
     * @param context
     */
    @Override
    public void onStart(ITestContext context) {

        log.info("onStart -> Test Tag Name:" + context.getName());
        ITestNGMethod methods[] = context.getAllTestMethods();
        log.info(("There methods will be executed in this <test> tag"));
        for(ITestNGMethod method : methods){
            log.info(method.getMethodName());
        }

    }

    /**
     * Invoked after all the test methods belonging to the classes inside the &lt;test&gt; tag have run
     * and all their Configuration methods have been called.
     *
     * @param context
     */
    @Override
    public void onFinish(ITestContext context) {
        log.info("onFinish -> Test Tag Name:" + context.getName());
        extentReports.flush();

    }


    /**
     * Invoked each time a test succeeds.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("onTestSuccess method name ->" + result.getName());
        String methodName = result.getMethod().getMethodName();
        String logTest = "<b>" + "Test Method "  + methodName + " Successful" + "</b>";
        Markup markUp = MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS,markUp);

    }

    /**
     * Invoked each time a test fails.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#FAILURE
     */
    @Override
    public void onTestFailure(ITestResult result) {
        log.info("onTestFailure method name ->" + result.getName());
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details>" + "<summary>" +"<b>" +"<font color=red>" +"Exception occurred Click to see details ->"
                + "</font>" + "</b>" + "</summary>"
                + exceptionMessage.replaceAll(",", "<b>") +"</details>" + "\n");
        String browser = WebDriverFactory.getInstance().getBrowser();
        WebDriver driver = WebDriverFactory.getInstance().getDriver(browser);
        CustomDriver cd = new CustomDriver(driver);
        String path = cd.takeScreenShot(result.getName(),browser);
        try {
            extentTest.get().fail("<b>" + "<font color=red>" +
                            "Screenshot of failure" + "</font>" + "</b>",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
        } catch (IOException e) {
            extentTest.get().fail("Test Method Failed, cannot attach screenshot");
        }



        String logTest = "<b>" + "Test Method "  + methodName + " Failed" + "</b>";
        Markup markUp = MarkupHelper.createLabel(logTest, ExtentColor.RED);
        extentTest.get().log(Status.FAIL,markUp);


    }

    /**
     * Invoked each time a test is skipped.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SKIP
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        log.info("onTestSkipped method name ->" + result.getName());
        String methodName = result.getMethod().getMethodName();
        String logTest = "<b>" + "Test Method "  + methodName + " Skipped" + "</b>";
        Markup markUp = MarkupHelper.createLabel(logTest, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP,markUp);

    }

    /**
     * Invoked each time a method fails but has been annotated with successPercentage and this failure
     * still keeps it within the success percentage requested.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     * @see ITestResult#SUCCESS_PERCENTAGE_FAILURE
     */
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    /**
     * Invoked each time a test fails due to a timeout.
     *
     * @param result <code>ITestResult</code> containing information about the run test
     */
    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }



    /**
     * Invoked each time before a test will be invoked. The <code>ITestResult</code> is only partially
     * filled with the references to class, method, start millis and status.
     *
     * @param result the partially filled <code>ITestResult</code>
     * @see ITestResult#STARTED
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest test = extentReports.createTest(result.getInstanceName() + "::"
                + result.getMethod().getMethodName());
        extentTest.set(test);

    }
}
