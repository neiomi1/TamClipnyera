package main.java.application.model;

import org.apache.commons.lang3.ArrayUtils;

public class ClipContainer
{
    private String name;

    private String description;

    private String[] tags;

    private long id;

    public ClipContainer(long id, String name, String description, String[] tags)
    {
        this.name = name;
        this.id = id;
        this.description = description;
        this.tags = tags;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public String[] getTags()
    {
        return tags;
    }

    public long getId()
    {
        return id;
    }

    public String toString()
    {
        return this.name;
    }

    public String[] toStringArray()
    {
        String[] temp = new String[tags.length + 2];
        temp[0] = this.name;
        temp[1] = this.description;
        int i = 0;
        for (String s : tags)
        {
            temp[i + 2] = s;
            i++;
        }
        return temp;
    }
}
