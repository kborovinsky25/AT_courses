import au.com.bytecode.opencsv.CSVReader;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.IOException;

public class TestData {
    @DataProvider
    public static Object[][] correctData() {
        return new Object[][] {{"kir_test_AT", "qwe123"},{"reya_test_AT", "123qwe"}};
    }
    @DataProvider
    public Object[][] incorrectData() {
        return new Object[][] {{"jsdfhgjsh", "qwe123"},{"reya_test_AT", "ajdfgasfjg"}};
    }
    @DataProvider
    public static Object[][] dataFromCSV() throws IOException {
        CSVReader reader = new CSVReader(new FileReader("C:\\Users\\Kirill_Borovinskii\\IdeaProjects\\lesson_1\\src\\test\\resources\\TestData.csv"));
        Object[][] data = reader.readAll().toArray(new Object[0][]);
        return data;
    }

}
