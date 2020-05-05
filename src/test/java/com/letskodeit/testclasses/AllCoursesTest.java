package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.utilities.Constants;
import com.letskodeit.utilities.ExcelUtility;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AllCoursesTest extends BaseTest {

    @DataProvider(name = "VerifySearchCourseData")
    public Object[][] getSearchCourseData(){
        Object[][] testData = ExcelUtility.getTestData("verify_search_course");
        return testData;
    }




    @BeforeClass
    public void setUp(){

        navigationPage = login.signInWith("test@email.com","abcabc");
        ExcelUtility.setExcelFile(Constants.EXCEL_FILE, AllCoursesTest.class.getSimpleName());

    }
    @Test(dataProvider = "VerifySearchCourseData")
    public void verifySerachAllCourses(String courseName){

        navigationPage.open();
        result = search.course(courseName);

//        verify that there is a result
        boolean searchResult = result.verifySearchResult();
        Assert.assertTrue(searchResult);
    }

    @Test(enabled = false)
    public void filterByCategory(){

        navigationPage.open();
        result = category.select("Software IT");
        int count = category.findCoursesCount("Software IT");
        boolean filterResult = result.verifyFilterCourseCount(count);
        Assert.assertTrue(filterResult);
    }


    @AfterClass
    public void tearDown(){
        driver.quit();
    }
}
