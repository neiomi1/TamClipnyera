package main.java.application.settings;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.text.Font;

public class SettingsModel
{
    static String pathstub = System.getProperty("user.dir") + "/src/main/resources/properties/";

    String result = "";

    InputStream inputStream;

    private String startingUser;

    private ObservableList<String> allUsers;

    private String mode;

    private int textSize;

    private String resolution;

    private String font;

    private String theme;

    private ObservableList<String> fontList;

    private ObservableList<String> resolutionList;

    private ObservableList<String> themeList;

    private ObservableList<String> allFiles;

    public SettingsModel()
    {
        this.allUsers = FXCollections.observableArrayList();
        this.allFiles = FXCollections.observableArrayList();
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

            fontList = FXCollections.observableList(Font.getFontNames());
            resolutionList = FXCollections.observableArrayList();
            themeList = FXCollections.observableArrayList();

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

    public ObservableList<String> getAllUsersProperty()
    {
        return this.allUsers;
    }

    public String getMode()
    {
        return mode;
    }

    public void updateModel(List<String> allUsers, String startingUser, String mode)
    {
        // this.allUsers = allUsers;
        this.mode = mode;
        this.startingUser = startingUser;
    }

    public ObservableList<String> getAllFilesProperty()
    {
        return this.allFiles;
    }

    public ObservableList<String> getAllThemes()
    {
        return this.themeList;
    }

    public ObservableList<String> getAllFonts()
    {
        return this.fontList;
    }

    public ObservableList<String> getAllResolutions()
    {
        return this.resolutionList;
    }

}
