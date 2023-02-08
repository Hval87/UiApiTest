package form;

import org.openqa.selenium.By;

public class MyProjectPage extends BaseProjectPage {

    public MyProjectPage(String name) {
        super(By.xpath(String.format("//li[text()='%s']", name)), "page");
    }
}
