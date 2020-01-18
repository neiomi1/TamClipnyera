package main.java.application.model;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class ClipContainersFromCSV
{
    static String pathstub = System.getProperty("user.dir") + "/csv_libraries/";

    static long counter;

    public static String[] getCSVFiles(String charactername)
    {
        try
        {
            System.out.println(pathstub + charactername + ".csv");
            CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(pathstub + charactername + ".csv"), StandardCharsets.UTF_8));
            return (reader.readNext());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<Long, ClipContainer> loadCSV(String filename)
    {
        System.out.println("----------Loaded Maps--------------");
        counter = 0;
        Map<Long, ClipContainer> temp = new HashMap<>();
        String[] nextLine;
        try
        {
            System.out.println(pathstub + filename + ".csv");
            CSVReader reader = new CSVReader(new FileReader(pathstub + filename + ".csv"));
            while ((nextLine = reader.readNext()) != null)
            {
                temp.put(counter, new ClipContainer(counter, nextLine[0].trim(), nextLine[1].trim(), Arrays.copyOfRange(nextLine, 2, nextLine.length)));
                counter++;
            }
            return temp;
        }
        catch (CsvValidationException | IOException file)
        {
            file.printStackTrace();
            return temp;
        }
    }

    public static Map<String, ClipContainerMap> loadFavourites(Map<String, ClipContainerMap> m, String characterName)
    {
        try
        {
            String[] nextLine;
            System.out.println("------------Favourites--------------");
            System.out.println(pathstub + characterName + ".csv");
            CSVReader reader = new CSVReader(new FileReader(pathstub + characterName + ".csv"));
            System.out.println("Reading Favourites: " + characterName + ".csv");
            System.out.println(Arrays.deepToString(reader.readNext()));
            while ((nextLine = reader.readNext()) != null && nextLine.length > 1)
            {
                System.out.println(Arrays.deepToString(nextLine));
                System.out.println(nextLine[2]);
                if (m.get(nextLine[2].trim()) != null)
                {
                    System.out.println("Putting " + nextLine[0].trim() + " at index " + m.get(nextLine[2].trim()).getNextID());
                    m.get(nextLine[2].trim()).putInMap(new ClipContainer(m.get(nextLine[2].trim()).getNextID(), nextLine[0].trim(), nextLine[1].trim(), Arrays.copyOfRange(nextLine, 3, nextLine.length)));
                }
                else
                {
                    m.put(nextLine[2].trim(), new ClipContainerMap(nextLine[2].trim()));
                    m.get(nextLine[2].trim()).putInMap(new ClipContainer(m.get(nextLine[2].trim()).getNextID(), nextLine[0].trim(), nextLine[1].trim(), Arrays.copyOfRange(nextLine, 3, nextLine.length)));
                }
            }
            return m;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return m;
    }
}
