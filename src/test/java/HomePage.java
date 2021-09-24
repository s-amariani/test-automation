import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BaseTest{
    SelenideElement btnForms = $(By.xpath("//h5[text()='Forms']"));

    public void clickForms(){
        btnForms.click();
    }
}