import java.util.*;
/**
 * Write a description of class Monster here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Monster
{
    private String typeName;
    private int type, health, strength, level;
    
    //public static final String[] TYPES = {"Zombie","Overlord","Balrog"};
    
    public Monster(int _type)
    {
        type = _type;
        setAttributes();
        System.out.println("Level "+level+" "+typeName);
        showAttributes();
    }
    
    public Monster(boolean boss)
    {
        if (boss == true) {
            typeName = "Dark Lord of Terror";
        }
        health = 1000; strength = 1000; level = 20;
        System.out.println("This is a Dark Lord of Terror.");
        showAttributes();
    }
    
    public void setAttributes()
    {
        Random r = new Random();
        if (type == 1) {
            typeName = "Zombie";
            health = 50+(r.nextInt(50)+1);
            strength = 50+(r.nextInt(50)+1);
            level = r.nextInt(5)+1;
        } else if (type == 2) {
            typeName = "Overlord";
            health = 100+(r.nextInt(50)+1);
            strength = 150+(r.nextInt(50)+1);
            level = 5+(r.nextInt(5)+1);
        } else {
            typeName = "Balrog";
            health = 200+(r.nextInt(100)+1);
            strength = 200+(r.nextInt(100)+1);
            level = 10+(r.nextInt(5)+1);
        }
    }
    public void showAttributes()
    {
        System.out.println("Monster's stats - Health: "+health+" Strength: "+strength);
    }
    public int getLevel()
    {
        return level;
    }
    public void setHealth(int h)
    {
        health += h;
    }
    public void halveHealth()
    {
        health /= 2;
    }
    public void halveStrength()
    {
        strength /= 2;
    }
    public void setStrength(int s)
    {
        strength += s;
    }
    public int getStrength()
    {
        return strength;
    }
    public int getHealth()
    {
        return health;
    }
}
