import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class FormsPage extends BaseTest{
    SelenideElement btnPracticeForm = $(By.xpath("//span[text()='Practice Form']"));

    public void clickPracticeForm(){
        btnPracticeForm.click();
    }
}