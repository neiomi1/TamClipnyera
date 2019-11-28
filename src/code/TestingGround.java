package code;

import java.io.FileReader;
import java.util.Arrays;

import com.opencsv.CSVReader;

public class TestingGround
{

    static String pathstub = System.getProperty("user.dir") + "/src/csv_libraries/";

    static int counter;

    public static void main(String[] args)
    {
        CSVReader reader = null;
        try
        {
            // Get the CSVReader instance with specifying the delimiter to be
            // used
            reader = new CSVReader(new FileReader(pathstub + "Puertagoe.csv"));
            String[] nextLine;
            // Read one line at a time
            while ((nextLine = reader.readNext()) != null)
            {
                System.out.println(Arrays.deepToString(nextLine));
                System.out.println("Name: " + nextLine[0]);
                System.out.println("Description: " + nextLine[1]);
                String[] tags = Arrays.copyOfRange(nextLine, 2, nextLine.length);
                System.out.println(Arrays.deepToString(tags));
                for (String token : nextLine)
                {
                    // Print all tokens
                    System.out.println(token);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
