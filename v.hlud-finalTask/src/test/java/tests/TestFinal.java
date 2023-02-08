package tests;

import form.AddProjectPage;
import form.HomePage;
import form.MyProjectPage;
import lombok.extern.slf4j.Slf4j;
import model.TestModel;
import aquality.selenium.browser.AqualityServices;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.*;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class TestFinal extends BaseTest {

    public static final String TOKEN = "token";
    public static final String LOG_PATH_KEY="logPath";
    private HomePage homePage;
    private AddProjectPage addProjectPage;

    @Test
    public void testUI(Method method){
        log.info("Step 1:");
        String sid=new Date().toString();
        String token = APIUtil.getResponsePOST(Constants.VARIANT, 2, APIMethod.GET_TOKEN).asString();
        String url = String.format((String) DataManager.getConfigValue(Constants.URL_WEB)
                , DataManager.getCredentialsValue(Constants.LOGIN)
                , DataManager.getCredentialsValue(Constants.PASSWORD));
        browser.goTo(url);
        BrowserUtil.addCookie(TOKEN, token);
        browser.refresh();
        homePage = new HomePage();
        homePage.clickSpecificLink(DataManager.getTestValue(Constants.PROJECT_NAME));

        log.info("Step 3:");
        MyProjectPage projectPage=new MyProjectPage(DataManager.getTestValue("projectName"));
        Assert.assertTrue(AqualityServices.getConditionalWait().waitFor(() -> projectPage.state().isDisplayed()), "nextage page is not visible");

        Response response = APIUtil.getResponsePOST(Constants.PROJECT_ID, Math.toIntExact(DataManager.getTestValue(Constants.PROJECT_ID)), APIMethod.GET_TEST_LIST_JSON);
        APIUtil.checkIsJson(response, DataManager.getConfigValue(Constants.JSON_SCHEMA));
        List<TestModel> listAPI = response.jsonPath().getList(".", TestModel.class);
        List<TestModel> listUI = projectPage.getTestsList();
        Assert.assertEquals(listUI, CollectionsUtil.getSortedListByDate(listUI), " list Ui is not sorted by date");
        Assert.assertEquals(listAPI.containsAll(listUI), true, " lists are not same");

        log.info("Step 4");
        browser.goBack();
        homePage = new HomePage();
        Assert.assertTrue(AqualityServices.getConditionalWait().waitFor(() -> homePage.state().isDisplayed()), "nextage page is not visible");
        homePage.clickAddButton();
        BrowserUtil.switchToAnotherTab();
        String projectName = TextUtil.generateRandomString();
        addProjectPage = new AddProjectPage();
        addProjectPage.inputProject(projectName);
        Assert.assertEquals(addProjectPage.getSuccessAlertForm().state().isDisplayed(), true, " project is not saved");
        BrowserUtil.closeTab();
        BrowserUtil.switchToAnotherTab();
        browser.refresh();
        Assert.assertTrue(homePage.getProjectList().contains(projectName), "the project is not  presented on the page");

        log.info("Step 5 :");
        homePage.clickSpecificLink(projectName);
        MyProjectPage myProjectPage=new MyProjectPage(projectName);
        String testId=APIUtil.getResponsePOST(CollectionsUtil.getParamsForTestCreating(
                 sid
                ,projectName
                ,method.getName()
                ,this.getClass().getName()
                ,TextUtil.getIP()),APIMethod.CREATE_TEST).asString();
        APIUtil.getResponsePOST(CollectionsUtil
                .getParamsForAttachLog(testId,TextUtil.getLogFromTXT(DataManager.getConfigValue(LOG_PATH_KEY)))
                ,APIMethod.ADD_LOG);
        APIUtil.getResponsePOST(CollectionsUtil.getParamsForAttachScreen(
                testId
                ,BrowserUtil.getScreenshotAsString()
                ,Constants.CONTENT_TYPE),APIMethod.ADD_SCREENSHOOT);

        Assert.assertTrue(myProjectPage.isTestPresent(this.getClass().getName()),"test is absent on the page");

        log.info("tests are finished successfully___");
    }
}
