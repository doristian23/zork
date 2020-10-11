import java.util.*;
/**
 * Write a description of class Stones here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stones
{
    private String name, levelName, typeName;
    private int value, type, level;

    public Stones()
    {
        Random r = new Random();
        type = (r.nextInt(5)+1);
        setAttributes();
        setLevel();
    }

    public void setAttributes()
    {
        Random r = new Random();
        if (type >= 1 && type <= 3)
        {
            name = "Yellow";
            level = r.nextInt(3)+1;
            typeName = "health";
        }
        else if (type == 4)
        {
            name = "Green";
            level = r.nextInt(3)+1;
            typeName = "mana";
        }
        else if (type == 5)
        {
            name = "Purple";
            level = r.nextInt(3)+1;
            typeName = "strength";
        }
    }
    
    public void setLevel()
    {
        if (level == 1)
        {
            levelName = "Bronze";
            value = 5;
        }
        else if (level == 2)
        {
            levelName = "Silver";
            value = 10;
        }
        else if (level == 3)
        {
            levelName = "Gold";
            value = 15;
        }
    }
    
    public void showAttributes()
    {
        System.out.println(name+" stone of "+levelName+" level (+"+value+" to "+typeName+")");
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getLevel()
    {
        return level;
    }
    
    public int getValue()
    {
        return value;
    }
}
