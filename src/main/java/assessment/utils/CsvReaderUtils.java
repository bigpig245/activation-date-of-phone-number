package assessment.utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CsvReaderUtils {

    public static int DEFAULT_SKIP_ROWS = 1;

    public static Map<String, List<String[]>> readCsvFile(File inputFile) {
        return readCsvFile(inputFile, DEFAULT_SKIP_ROWS);
    }

    public static Map<String, List<String[]>> readCsvFile(File inputFile, int skipRows) {
        Map<String, List<String[]>> realActivationDates = new TreeMap<>();
        try (CSVReader reader = new CSVReader(new FileReader(inputFile))) {

            for (int i = 0; i < skipRows; i++) {
                reader.readNext();
            }

            String[] line;
            while ((line = reader.readNext()) != null) {
                if (realActivationDates.containsKey(line[0])) {
                    realActivationDates.get(line[0]).add(line);
                } else {
                    List<String[]> arrayList = new ArrayList<>();
                    arrayList.add(line);
                    realActivationDates.put(line[0], arrayList);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return realActivationDates;
    }

    public static List<String[]> findRealActivationDates(Map<String, List<String[]>> phoneDataMap) {
        List<String[]> result = new ArrayList<>();
        phoneDataMap.values().forEach(item -> result.add(findRealActivationDate(item)));
        return result;
    }

    public static void readAndWriteCsv(File inputFile) {
        String outputFileName = "output_" + inputFile.getName();
        File outputFile = new File(inputFile.getPath().replace(inputFile.getName(), outputFileName));

        Map<String, List<String[]>> items = readCsvFile(inputFile);
        writeCsv(outputFile, findRealActivationDates(items));
    }

    private static String[] findRealActivationDate(List<String[]> activationList) {
        // sort list by activation date in descending order
        activationList.sort((i1, i2) -> i2[1].compareTo(i1[1]));

        // set real activation date is the first item
        String[] result = activationList.get(0);
        for (int i = 1; i < activationList.size(); i++) {
            String[] currentItem = activationList.get(i);
            // if the deactivation date of the current item is equal to the activation date of real activation one
            // replace the real activation one
            if (result[1].compareTo(currentItem[2]) == 0) {
                result = currentItem;
            } else { // the owner of phone number was changed, keep the current one and skip the others
                break;
            }
        }
        return result;
    }

    private static void writeCsv(File outputFile, List<String[]> items) {
        try (Writer writer = new FileWriter(outputFile)) {
            CSVWriter csvWriter = new CSVWriter(writer);
            csvWriter.writeAll(parseCsvWithHeader(items), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<String[]> parseCsvWithHeader(List<String[]> items) {
        List<String[]> records = new ArrayList<>();
        records.add(new String[]{"PHONE_NUMBER", "REAL_ACTIVATION_DATE"});
        items.forEach(i -> records.add(new String[]{i[0], i[1]}));
        return records;
    }
}
