import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class PracticeFormPage extends BaseTest {
    SelenideElement firstName = $("#firstName");
    SelenideElement lastName = $("#lastName");
    SelenideElement phoneNumber = $("#userNumber");
    SelenideElement radioBtnMaleGender = $("label[for='gender-radio-1']");
    SelenideElement radioBtnFemaleGender = $("label[for='gender-radio-2']");
    SelenideElement radioBtnOtherGender = $("label[for='gender-radio-3']");
    SelenideElement btnSubmit = $("#submit");
    SelenideElement modalTitle = $("#example-modal-sizes-title-lg" );
    SelenideElement submitFormStudentName = $x("//tbody/tr[1]/td[2]");
    SelenideElement submitFormStudentPhoneNumber = $(By.xpath("//tbody/tr[4]/td[2]"));
    SelenideElement btnSubmitFormClose = $("#closeLargeModal");
    SelenideElement btnSubmitFormGender = $x("//tbody/tr[3]/td[2]");


    public void enterFirstName(String firstName){
        this.firstName.setValue(firstName);
    }
    public void enterLastname(String lastName){
        this.lastName.setValue(lastName);
    }
    public void enterPhoneNumber(String phoneNumber){
        this.phoneNumber.setValue(phoneNumber);
    }
    public void chooseMaleGender(){
        radioBtnMaleGender.click();
    }
    public void chooseFemaleGender(){
        radioBtnFemaleGender.click();
    }
    public void chooseOtherGender(){
        radioBtnOtherGender.click();
    }

    public void clickSubmit(){
        btnSubmit.click();
    }

    public void assertModalTitleVisibility(){
        modalTitle.shouldBe(Condition.visible);
    }

    public String getStudentName(){
        return this.submitFormStudentName.getText();
    }
    public String getStudentPhoneNumber(){
        return this.submitFormStudentPhoneNumber.getText();
    }

    public void assertStudentFirstAndLastName(String expectedFirstName,String expectedLastName){
        submitFormStudentName.shouldHave(Condition.text(expectedFirstName + " " + expectedLastName));
    }
    public void assertStudentPhoneNumber(String expectedPhoneNumber){
        submitFormStudentPhoneNumber.shouldHave(Condition.text(expectedPhoneNumber));
    }
    public void assertStudentGender(String expectedGender){
        btnSubmitFormGender.shouldHave(Condition.text(expectedGender));
    }

}