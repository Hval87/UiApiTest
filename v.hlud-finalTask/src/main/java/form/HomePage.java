package form;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.IButton;
import aquality.selenium.elements.interfaces.ILink;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;
import util.RegMatcher;

import java.util.ArrayList;
import java.util.List;

public class HomePage extends Form {
    private static final ILink linkNexage = AqualityServices.getElementFactory().getLink(By.xpath("//a[@href='allTests?projectId=1']"), "nexage link");
    private static final IButton buttonAdd = AqualityServices.getElementFactory().getButton(By.xpath("//*[contains(@class,'btn-xs')]"), "buton 'add'");
    private static final String PROJECT_LIST_LOCATOR = "//a[contains(@class,'list-group-item')]";
    private static String SPECIFIC_LINK_LOCATOR = "//a[@href='allTests?projectId=%s']";
    private static final String SEARCH="search";

    public HomePage() {
        super(By.xpath("//div[contains(@class,'list-group')]"), "home page");
    }

    public void clickSpecificLink(String name) {
        String id=getProjectId(name);
        AqualityServices.getElementFactory().getLink(By.xpath(String.format(SPECIFIC_LINK_LOCATOR,id)),"new project link").click();
    }

    public void clickAddButton() {
        buttonAdd.click();
    }

    public List<String> getProjectList() {
        List<ILink> projectList = AqualityServices.getElementFactory().findElements(By.xpath(PROJECT_LIST_LOCATOR), ElementType.LINK);
        List<String> projectNameList = new ArrayList<>();

        for (ILink lbl : projectList) {
            projectNameList.add(lbl.getText());
        }
        return projectNameList;
    }

    public String getProjectId(String name) {
        List<ILink> projectList = AqualityServices.getElementFactory().findElements(By.xpath(PROJECT_LIST_LOCATOR), ElementType.LINK);
        List<String> projectNameList = new ArrayList<>();
        String result=null;
        for (ILink lbl : projectList) {
            if (lbl.getText().contains(name)) {
                String tmp=lbl.getAttribute(SEARCH);
                result= RegMatcher.findIdMatches(tmp);
            }
        }
        return result;
    }
}
