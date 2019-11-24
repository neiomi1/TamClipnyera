package settings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SettingsModel
{
    static String pathstub = System.getProperty("user.dir") + "/src/settings/";

    String result = "";

    InputStream inputStream;

    private String startingUser;

    private List<String> allUsers;

    private String mode;

    private List<String> allFiles;

    public SettingsModel()
    {
        this.allUsers = new ArrayList<>();
        this.allFiles = new ArrayList<>();

        try
        {
            Properties prop = new Properties();
            String propFileName = "config.properties";
            System.out.println(pathstub + propFileName);

            BufferedReader test = new BufferedReader(new FileReader(pathstub + propFileName));
            System.out.println(test.readLine());
            inputStream = new FileInputStream(pathstub + propFileName);
            if (inputStream != null)
            {
                prop.load(inputStream);
            }
            else
            {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            // get the property value and print it out
            this.startingUser = prop.getProperty("lastUser");
            String[] temp = prop.getProperty("allUsers").split(",");
            System.out.println("Users");
            for (String s : temp)
            {
                System.out.println(s);
                this.allUsers.add(s);
            }
            String[] tempFiles = prop.getProperty("generalFiles").split(",");
            System.out.println(tempFiles.length);
            for (String s : tempFiles)
            {
                System.out.println(s);
                this.allFiles.add(s);
            }
            this.mode = prop.getProperty("mode");

            result = "Last User:= " + startingUser + "\nAll Users: " + Arrays.deepToString(temp) + "\nmode: " + mode + "\nFiles: " + Arrays.deepToString(tempFiles);
            System.out.println(result + "\nProgram Ran on " + time);
        }
        catch (Exception e)
        {
            System.out.println("Exception: " + e);
            e.printStackTrace();
        }
        finally
        {
            try
            {
                inputStream.close();
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public String getStartingUser()
    {
        return startingUser;
    }

    public List<String> getAllUsers()
    {
        return allUsers;
    }

    public ObservableList<String> getAllUsersProperty()
    {
        return FXCollections.observableArrayList(allUsers);
    }

    public String getMode()
    {
        return mode;
    }

    public void updateModel(List<String> allUsers, String startingUser, String mode)
    {
        this.allUsers = allUsers;
        this.mode = mode;
        this.startingUser = startingUser;
    }

    public ObservableList<String> getAllFilesProperty()
    {
        System.out.println("/////////////////All files//////////////");
        for (String s : allFiles)
        {
            System.out.println(s);
        }
        return FXCollections.observableArrayList(allFiles);
    }

    public List<String> getAllFiles()
    {
        return allFiles;
    }

}
