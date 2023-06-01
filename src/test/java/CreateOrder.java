import org.example.OrderPageObject;
import org.example.enums.Users;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

public class CreateOrder extends BaseTest {

    @ParameterizedTest
    @EnumSource(Users.class)
    public void createOrder(Users users) {
        new OrderPageObject(driver, users)
                .clickOrderButton(1)
                .fillingUserForm()
                .fillingRentForm()
                .clickCreateOrderButton()
                .confirmationOrder()
                .createdOrderLabel();
    }
}

