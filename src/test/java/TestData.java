import org.testng.annotations.DataProvider;

public class TestData {
    @DataProvider
    public Object[][] correctData() {
        return new Object[][] {{"kir_test_AT", "qwe123"},{"reya_test_AT", "123qwe"}};
    }

}
