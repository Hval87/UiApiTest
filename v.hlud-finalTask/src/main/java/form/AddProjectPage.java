package form;

import aquality.selenium.browser.AqualityServices;
import aquality.selenium.elements.interfaces.ITextBox;
import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AddProjectPage extends Form {
    public static final ITextBox BOX_INPUT_PROJECT = AqualityServices.getElementFactory().getTextBox(By.xpath("//input[@id='projectName']"), " create project page");

    public AddProjectPage() {
        super(By.xpath("//input[@id='projectName']"), "add project page");
    }

    public void inputProject(String name) {
        BOX_INPUT_PROJECT.type(name);
        BOX_INPUT_PROJECT.submit();
    }

    public AlertSuccessForm getSuccessAlertForm(){
        return new AlertSuccessForm();
    }
}
