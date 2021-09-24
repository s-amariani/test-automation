import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    public void initializeConfiguration(){
        Configuration.startMaximized=true;
    }
    public void openPage(String pageURL){
        open(pageURL);
    }
}