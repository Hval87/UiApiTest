package form;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.ElementType;
import aquality.selenium.elements.interfaces.ILabel;
import aquality.selenium.forms.Form;
import model.TestModel;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseProjectPage extends Form {

    public static final String TEST_NAME_LOCATOR = "//*[text()='%s']";
    private static String TABLE_ROW_LOCATOR = "//*[@id='allTests']/tbody/tr";

    protected BaseProjectPage(By locator, String name) {
        super(locator, name);
    }

    public List<TestModel> getTestsList() {
        List<ILabel> rows = AqualityServices.getElementFactory().findElements(By.xpath(TABLE_ROW_LOCATOR), ElementType.LABEL);
        rows.remove(0);
        List<TestModel> list = new ArrayList<>();
        for (ILabel row : rows) {
            List<ILabel> cells = row.findChildElements(By.xpath(".//td"), ElementType.LABEL);
            TestModel TestModel = new TestModel();
            TestModel.setName(cells.get(0).getText());
            TestModel.setMethod(cells.get(1).getText());
            TestModel.setStatus(cells.get(2).getText().toUpperCase());
            TestModel.setStartTime(cells.get(3).getText());
            TestModel.setEndTime(cells.get(4).getText());
            TestModel.setDuration(cells.get(5).getText());
            list.add(TestModel);

        }
        return list;

    }

    public boolean isTestPresent(String testName) {
        return AqualityServices
                .getConditionalWait()
                .waitFor(() -> AqualityServices
                        .getElementFactory()
                        .findElements(By.xpath(String.format(TEST_NAME_LOCATOR, testName)), ElementType.LABEL).size()> 0);
    }
}
