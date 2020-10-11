import java.util.*;
/**
 * Write a description of class Potions here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Potions
{
    private String name;
    private int spend, type;

    public Potions()
    {
        Random r = new Random();
        type = (r.nextInt(3)+1);
        setAbility();
    }
    
    public void setAbility()
    {
        Random r = new Random();
        if (type == 1)
        {
            name = "Fire";//burn monster and damage half its life
            spend = 25;
        }
        else if (type == 2)
        {
            name = "Ambrosia"; //add 20 strength to yourself
            spend = 15;
        }
        else if (type == 3)
        {
            name = "Ice"; //when chose to fight a monster, avoid being hit once
            spend = 10;
        }
    }
    
    public void getDescription()
    {
        System.out.println("The potion you found is the "+name+" potion! (Requires "+spend+" mana to use)");
        if (type == 1)
        {
            System.out.println("This potion will burn the monster and decrease its health by half.");
        }
        else if (type == 2)
        {
            System.out.println("This potion will increase your strength by 30%.");
        }
        else if (type == 3)
        {
            System.out.println("This potion will decrease the monster's strength by half.");
        }
        System.out.println("All potions are added to your inventory.");
    }
    
    public String getName()
    {
        return name;
    }
    public int getType()
    {
        return type;
    }
    public int getSpend()
    {
        return spend;
    }
}