package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClipContainerModel
{

    private String[] loadedCSV;

    private Map<String, ClipContainerMap> storedMaps;

    public ClipContainerModel(String characterName)
    {
        this.storedMaps = new HashMap<String, ClipContainerMap>();
        loadedCSV = ClipContainersFromCSV.getCSVFiles(characterName);
        // load all relevant csv Files from user.properties
        for (String csvName : loadedCSV)
        {
            if (!csvName.isEmpty())
            {
                this.storedMaps.put(csvName, new ClipContainerMap(csvName));
                // load values from csv
            }
        }
        storedMaps = ClipContainersFromCSV.loadFavourites(storedMaps, characterName);
        for (ClipContainerMap map : storedMaps.values())
        {
            System.out.println(map.getCsvName());
            for (ClipContainer elem : map.getStoredMap().values())
            {
                System.out.println(elem.getId());
                System.out.println(elem.getName());
                System.out.println(elem.getDescription());
            }
        }
    }

    public List<ClipContainerMap> searchClipContainers(String[] keywords)
    {
        System.out.println("-------------------------ALL VALUES----------------------");
        for (ClipContainerMap m : storedMaps.values())
        {
            for (ClipContainer con : m.getStoredMap().values())
            {
                System.out.println(con.getName() + " in " + m.getCsvName());
            }
        }
        System.out.println("-----------------------------------------------------------");
        List<ClipContainerMap> matches = new ArrayList<>();
        if (keywords == null || keywords.length == 0)
        {
            matches.addAll(this.storedMaps.values());
            return matches;
        }
        for (ClipContainerMap m : storedMaps.values())
        {
            Map<Long, ClipContainer> temp = new HashMap<>();
            for (ClipContainer container : m.getStoredMap().values())
            {
                for (String keyword : keywords)
                {
                    keyword = keyword.toLowerCase();
                    if (container.getName().toLowerCase().contains(keyword) || container.getDescription().toLowerCase().contains(keyword))
                    {
                        temp.put(container.getId(), container);
                    }
                    // TODO Tags
                }
            }
            matches.add(new ClipContainerMap(m.getCsvName(), temp));
        }
        return matches;
    }

    public void updateClipContainerMap(ClipContainer model, String key)
    {
        this.storedMaps.get(key).getStoredMap().put(model.getId(), model);
    }

    public static void main(String[] args)
    {
        ClipContainerModel m = new ClipContainerModel("Daos");
        for (ClipContainerMap map : m.storedMaps.values())
        {
            System.out.println(map.getCsvName());
            for (ClipContainer elem : map.getStoredMap().values())
            {
                System.out.println(elem.getId());
                System.out.println(elem.getName());
                System.out.println(elem.getDescription());
            }
        }
    }

    public long getNextID(String map)
    {
        for (String name : storedMaps.keySet())
        {
            if (name.contentEquals(map))
            {
                return storedMaps.get(name).getNextID();
            }
        }
        return 0;
    }

    public void putContainerInMap(ClipContainer container, String map)
    {
        storedMaps.get(map).putInMap(container);
    }
}
