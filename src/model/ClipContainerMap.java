package model;

import java.util.HashMap;
import java.util.Map;

public class ClipContainerMap
{
    private String csvName;

    private Map<Long, ClipContainer> storedMap;

    private long counter;

    public ClipContainerMap(String csvName)
    {
        if (csvName.contentEquals("Favourites"))
        {
            this.csvName = csvName;
            this.storedMap = new HashMap<Long, ClipContainer>();
            counter = 0;
        }
        else
        {
            this.csvName = csvName;

            this.storedMap = ClipContainersFromCSV.loadCSV(csvName);

            while (storedMap.get(counter) != null)
            {
                counter++;
            }
        }
    }

    public ClipContainerMap(String csvName, Map<Long, ClipContainer> storedMap)
    {
        this.csvName = csvName;
        this.storedMap = storedMap;
    }

    public String getCsvName()
    {
        return csvName;
    }

    public Map<Long, ClipContainer> getStoredMap()
    {
        return storedMap;
    }

    public void putInMap(ClipContainer container)
    {
        System.out.println("*****************PUTTING*************");
        System.out.println(container.getName() + "  in " + csvName);
        counter++;
        this.storedMap.put(counter, container);
    }

    public long getNextID()
    {
        return counter;
    }
}
