package form;

import aquality.selenium.forms.Form;
import org.openqa.selenium.By;

public class AlertSuccessForm extends Form {

    public AlertSuccessForm() {
        super(By.xpath("//*[contains(@class,'alert-success')]"), " succes save allert");
    }
}
