package main.java.application.model;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

public class ClipContainerToCSV
{
    static String pathstub = System.getProperty("user.dir") + "/src/main/resources/csv/";

    public static void writeToCSV(String[] data, String filename)
    {
        StringWriter sw = new StringWriter();
        CSVWriter writer = new CSVWriter(sw);
        try
        {
            OutputStreamWriter fileWriter = new OutputStreamWriter(new FileOutputStream(pathstub + "test.csv", true), StandardCharsets.UTF_8);
            // FileWriter fileWriter = new FileWriter(pathstub + "test.csv",
            // true);
            writer.writeNext(data);

            try
            {
                Files.write(Paths.get(pathstub + filename + ".csv"), (sw.toString()).getBytes(), StandardOpenOption.APPEND);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException
    {
        String[] tag = new String[]
        { "test" };
        ClipContainer test = new ClipContainer(1, "a", "b", tag);
        StringWriter sw = new StringWriter();
        CSVWriter writer = new CSVWriter(sw);
        FileWriter fileWriter = new FileWriter(pathstub + "test.csv", true);
        writer.writeNext(test.toStringArray());

        try
        {
            Files.write(Paths.get(pathstub + "test.csv"), ("\n" + sw.toString()).getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e)
        {
            // exception handling left as an exercise for the reader
        }
    }
}
