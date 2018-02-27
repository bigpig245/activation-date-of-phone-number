package assessment.utils;

import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;


public class CsvReaderUtilsTest {

    @Test
    public void should_find_real_activation_date_1() {
        String fileName = "src/test/resources/phone_data_test_1.csv";
        File file = new File(fileName);
        Map<String, List<String[]>> temp = CsvReaderUtils.readCsvFile(file);
        List<String[]> result = CsvReaderUtils.findRealActivationDates(temp);
        assertThat(result.size()).isEqualTo(3);
        assertThat(result.get(0)).isEqualTo(new String[]{"0987000001", "2016-06-01", "2016-09-01"});
        assertThat(result.get(1)).isEqualTo(new String[]{"0987000002", "2016-02-01", "2016-03-01"});
        assertThat(result.get(2)).isEqualTo(new String[]{"0987000003", "2016-01-01", "2016-01-10"});
    }

    @Test
    public void should_find_real_activation_date_2() {
        String fileName = "src/test/resources/phone_data_test_2.csv";
        File file = new File(fileName);
        Map<String, List<String[]>> temp = CsvReaderUtils.readCsvFile(file);
        List<String[]> result = CsvReaderUtils.findRealActivationDates(temp);
        assertThat(result.size()).isEqualTo(5);
        assertThat(result.get(0)).isEqualTo(new String[]{"0987000001", "2017-03-01", "2016-05-01"});
        assertThat(result.get(1)).isEqualTo(new String[]{"0987000002", "2016-02-01", "2016-03-01"});
        assertThat(result.get(2)).isEqualTo(new String[]{"0987000003", "2016-01-01", "2016-01-10"});
        assertThat(result.get(3)).isEqualTo(new String[]{"0987000004", "2017-03-01", "2018-09-01"});
        assertThat(result.get(4)).isEqualTo(new String[]{"0987000005", "2017-03-01", "2017-05-11"});
    }

    @Test
    public void should_find_real_activation_date_3() {
        String fileName = "src/test/resources/phone_data_test_3.csv";
        File file = new File(fileName);
        Map<String, List<String[]>> temp = CsvReaderUtils.readCsvFile(file);
        List<String[]> result = CsvReaderUtils.findRealActivationDates(temp);
        assertThat(result.size()).isEqualTo(6);
        assertThat(result.get(0)).isEqualTo(new String[]{"0987000001", "2017-06-01", "2017-09-01"});
        assertThat(result.get(1)).isEqualTo(new String[]{"0987000002", "2016-02-01", "2016-03-01"});
        assertThat(result.get(2)).isEqualTo(new String[]{"0987000003", "2018-01-23", ""});
        assertThat(result.get(3)).isEqualTo(new String[]{"0987000004", "2017-12-01", ""});
        assertThat(result.get(4)).isEqualTo(new String[]{"0987000005", "2017-03-01", "2017-09-01"});
        assertThat(result.get(5)).isEqualTo(new String[]{"0987000006", "2017-03-01", "2018-10-01"});
    }
}
