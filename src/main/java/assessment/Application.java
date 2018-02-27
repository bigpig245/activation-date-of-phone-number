package assessment;

import assessment.utils.CsvReaderUtils;

import java.io.File;
import java.io.IOException;

public class Application {
    public static void main(String[] args) throws IOException {
        CsvReaderUtils.readAndWriteCsv(new File("src/test/resources/phone_data_test_1.csv"));
        CsvReaderUtils.readAndWriteCsv(new File("src/test/resources/phone_data_test_2.csv"));
        CsvReaderUtils.readAndWriteCsv(new File("src/test/resources/phone_data_test_3.csv"));
    }
}
