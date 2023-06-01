import org.example.MainPageObject;
import org.example.enums.FaqList;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class DropDownList extends BaseTest {

    @ParameterizedTest
    @EnumSource(FaqList.class)
    public void checkFaq(FaqList faqList) {
        new MainPageObject(faqList, driver)
                .clickDropdownButton()
                .checkDropDownElementText();
    }
}

