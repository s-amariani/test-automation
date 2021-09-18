
import com.codeborne.selenide.Condition;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

//Fill First Name, Last Name , Gender and mobile number dynamically using Excel and Apache POI
public class ReadExcel {

    private int studentID;
    public ReadExcel(int studentID) {
        this.studentID = studentID;
    }

    @Test
    public void testingFactory() throws IOException {
        
        File file = new File("C:\\Users\\Sergi\\Desktop\\studentsData.xlsx");   //creating a new file instance
        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file
        // creating Workbook instance that refers to .xlsx file
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);     //creating a Sheet object to retrieve object

        DataFormatter formatter = new DataFormatter();
        String firstName = formatter.formatCellValue(sheet.getRow(studentID).getCell(1));
        String lastName = formatter.formatCellValue(sheet.getRow(studentID).getCell(2));
        String phone = formatter.formatCellValue(sheet.getRow(studentID).getCell(3));
        String gender = formatter.formatCellValue(sheet.getRow(studentID).getCell(4));

        open("https://demoqa.com/automation-practice-form");

        $("#firstName").setValue(firstName);

        $("#lastName").setValue(lastName);

        switch (gender) {
            case "Male" -> $("label[for='gender-radio-1']").click();
            case "Female" -> $("label[for='gender-radio-2']").click();
            case "Other" -> $("label[for='gender-radio-3']").click();
            default -> System.out.println("Enter valid gender");
        }

        $("#userNumber").setValue(phone);

        String firstNameValue =  $("#firstName").getAttribute("value");

        //Validate the Student Name value dynamically
        $("#firstName").shouldHave(Condition.attribute("value",firstName));

    }
}

