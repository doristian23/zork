import java.util.*;
/**
 * Write a description of class Character here.
 *
 * @author (Doris Tian)
 * @version (2019.05.08)
 */
public class Character
{
    private String name, typeName; //warrior, barbarian, elf, magician
    private int type, health, mana, strength, skill, level = 1, points = 0, nextStage = 1, stageNum = 1, potionsIntro = 0, stageIntro = 0;
    private int userInput; private boolean use;
    private boolean fire = false, ambrosia = false, ice = false;
    private Input myInput;
    private ArrayList<Potions> inventory;
    
    public Character()
    {
        myInput = new Input();
        inventory = new ArrayList<Potions>();
        setName(); setType();
        displayAttributes();
        getWeapon();
    }
    
    public void setName()
    {
        System.out.print("Enter your name: ");
        name = myInput.getString();
    }
    public String getName()
    {
        return name;
    }
    public void setType()
    {
        System.out.println("There are 4 class types: warrior (1), barbarian (2), elf (3), magician (4).");
        System.out.print("Select your class by entering the corresponding code: ");
        type = myInput.getInt();
        while (type < 1 || type > 4) {
            System.out.println("Please enter a number from 1 to 4: ");
            type = myInput.getInt();
        }
    }
    public int getTypeCode()
    {
        return type;
    }
    public String getTypeName()
    {
        if (type == 1) {
            return "Warrior";
        } else if (type == 2) {
            return "Barbarian";
        } else if (type == 3) {
            return "Elf";
        } else {
            return "Magician";
        }
    }
    
    public void getWeapon()
    {
        Random r = new Random();
        int weapon = r.nextInt(3)+1;
        if (weapon == 1) {
            System.out.println("You are armed with a sword, with 20 HP. This is added to your strength.");
            strength += 20;
        } else if (weapon == 2) {
            System.out.println("You are armed with a knife, with 10 HP. This is added to your strength.");
            strength += 10;
        } else {
            System.out.println("You are armed with a scythe, with 30 HP. This is added to your strength.");
            strength += 30; 
        }
    }
    public void displayAttributes()
    {
        if (type == 1) {
            health = 100; mana = 100; strength = 100; //basic
            System.out.println("You are a warrior."); 
            System.out.println("Health: 100"); System.out.println("Mana: 100"); System.out.println("Strength: 100");
        } else if (type == 2) {
            health = 100; mana = 50; strength = 150; //physically strong, but bad at wielding magic
            System.out.println("You are a barbarian.");
            System.out.println("Health: 100"); System.out.println("Mana: 50"); System.out.println("Strength: 150");
        } else if (type == 3) {
            health = 150; mana = 100; strength = 50; //physically weak, but maintains good health
            System.out.println("You are an elf.");
            System.out.println("Health: 150"); System.out.println("Mana: 100"); System.out.println("Strength: 50");
        } else {
            health = 50; mana = 150; strength = 100; //frail and sickly, but wields powerful magic
            System.out.println("You are a magician."); 
            System.out.println("Health: 50"); System.out.println("Mana: 150"); System.out.println("Strength: 100");
        }
        System.out.println("Lvl: "+level);
        System.out.println("Pts: "+points);
        System.out.println("Every additional 30 points advances you to the next level. Points are gained through your adventures. Good luck!");
    }

    public void showInventory()
    {
        System.out.println("You have the following potions: ");
        Iterator<Potions> it = inventory.iterator();
        while (it.hasNext()) {
            Potions tempPotion = it.next();
            System.out.println(tempPotion.getName());
        }
    }
    public void usePotions()
    {
        potionsIntro++;
        use = false;
        if (potionsIntro == 1) {
            System.out.println("There are 3 types of potions: Fire, Ambrosia, and Ice: ");
            System.out.println("Fire decreases the monster's health by half. ");
            System.out.println("Ambrosia increases your strength by 30%.");
            System.out.println("Ice decreases the monster's strength by half. ");
            System.out.println("Using potions also depletes your mana. The specific amount will vary depending on which potion you use. ");
            System.out.print("Enter any key to continue: ");
            String u = myInput.getString();
        }
        showInventory();
        System.out.print("Enter (1) for Fire, (2) for Ambrosia, (3) for Ice: ");
        userInput = myInput.getInt();
        while (userInput < 1 || userInput > 3) {
            System.out.print("Please enter a code for a potion that you possess: ");
            userInput = myInput.getInt();
        }
        whichPotion();
        while (use == false) {
            System.out.print("Please enter a code for a potion that you possess: ");
            userInput = myInput.getInt();
            whichPotion();
        }
    }
    public void whichPotion() 
    {
        Iterator<Potions> it = inventory.iterator();
        if (userInput == 1) {
            while (it.hasNext() && use == false) {
                Potions tempPotion = it.next();
                if (tempPotion.getType() == 1) {
                    use = true;
                    System.out.println("You have chosen Fire! Fire requires 25 mana.");
                    if (tempPotion.getSpend() <= mana) {
                        fire = true;
                        mana -= tempPotion.getSpend();
                    } else {
                        System.out.println("You do not have enough mana to use this potion. "); 
                    }
                }
            }
        } else if (userInput == 2) {
            while (it.hasNext() && use == false) {
                Potions tempPotion = it.next();
                if (tempPotion.getType() == 2) {
                    use = true;
                    System.out.println("You have chosen Ambrosia! Ambrosia requires 15 mana.");
                    if (tempPotion.getSpend() <= mana) {
                        ambrosia = true;
                        mana -= tempPotion.getSpend();
                    } else {
                        System.out.println("You do not have enough mana to use this potion. "); 
                    }
                }
            }
        } else {
            while (it.hasNext() && use == false) {
                Potions tempPotion = it.next();
                if (tempPotion.getType() == 3) {
                    use = true;
                    System.out.println("You have chosen Ice! Ice requires 10 mana. ");
                    if (tempPotion.getSpend() <= mana) {
                        ice = true;
                        mana -= tempPotion.getSpend();
                    } else {
                        System.out.println("You do not have enough mana to use this potion. "); 
                    }
                }
            }
        }
    }
    public void levelUp()
    {
        if (points >= 30) {
            level = (points/30)+1;
        }
    }
    
    public void nextStage()
    {
        stageIntro++;
        String pause;
        if (level >= 2) {
            System.out.println();
            if (stageIntro == 1) {
                System.out.println("Would you like to advance to the next stage? Greener pastures (and fiercer monsters) await.");
                System.out.print("Your choices are: (1) Remain in current stage (2) Advance to next stage ");
                nextStage = myInput.getInt();
                while (nextStage != 1 && nextStage != 2) {
                    System.out.println("Please enter only 1 or 2.");
                    System.out.print("(0) Go back to previous stage (1) Remain in current stage (2) Advance to next stage ");
                    nextStage = myInput.getInt();
                }
            } else {
                System.out.println("Leave stage? (0) Go back to previous stage (1) Remain in current stage (2) Advance to next stage ");
                nextStage = myInput.getInt();
                while (nextStage < 0 || nextStage > 2) {
                    System.out.println("Please enter only 0, 1, or 2.");
                    System.out.print("(0) Go back to previous stage (1) Remain in current stage (2) Advance to next stage ");
                    nextStage = myInput.getInt();
                }
            }
            if (nextStage == 0) {
                if (stageNum <= 1) {
                    System.out.println("Stage 1 is the first stage. There are no previous stages. ");
                    System.out.print("Enter any key to continue: ");
                    pause = myInput.getString();
                } else {
                    stageNum--;
                    System.out.println("Welcome to Stage "+stageNum+"!");
                }
            } else if (nextStage == 2) {
                if (stageNum >= 4) {
                    System.out.println("Stage 4 is the last stage. This is the final frontier. ");
                    System.out.print("Enter any key to continue: ");
                    pause = myInput.getString();
                } else {
                    stageNum++;
                    System.out.println("Welcome to Stage "+stageNum+"!");
                }
            }
        }
    }
    
    public void showAttributes()
    {
        System.out.println("Your stats - Health: "+health+" Mana: "+mana+" Strength: "+strength);
    }
    public void showLevel()
    {
        System.out.println("You are currently Level "+level+" with "+points+ " points.");
    }

    public void setNextStage(int _nextStage)
    {
        nextStage = _nextStage;
    }
    public void setPoints(int _points)
    {
        points += _points;
    }
    public void setMana(int m)
    {
        mana += m;
    }
    public void setHealth(int h)
    {
        health += h;
    }
    public void setStrength(int s)
    {
        strength += s;
    }
    public void setFire(boolean f)
    {
        fire = f;
    }
    public void setAmbrosia(boolean a)
    {
        ambrosia = a;
    }
    public void setIce(boolean i)
    {
        ice = i;
    }
    public void increaseStrength()
    {
        strength *= 1.3;
    }
    public void addToInventory(Potions potion)
    {
        inventory.add(potion);
    }
    
    public int getHealth()
    {
        return health;
    }
    public int getMana()
    {
        return mana;
    }
    public int getStrength()
    {
        return strength;
    }
    public int getLevel()
    {
        return level;
    }
    public int getPoints()
    {
        return points;
    }
    public int getNextStage()
    {
        return nextStage;
    }
    public int getStageNum()
    {
        return stageNum;
    }
    public int getSkill()
    {
        return skill;
    }
    public int getInventorySize()
    {
        return inventory.size();
    }
    public boolean getFire()
    {
        return fire;
    }
    public boolean getAmbrosia()
    {
        return ambrosia;
    }
    public boolean getIce()
    {
        return ice;
    }
    
    public void setAttributes()
    {
        System.out.print("Enter your health (1-300): ");
        health = myInput.getInt();
        while (health > 300 || health < 1) {
            System.out.print("Please enter a value no less than 1 and no more than 300: ");
            health = myInput.getInt();
        }
        System.out.print("Enter your mana (1-300): ");
        mana = myInput.getInt();
        while (mana > 300 || mana < 1) {
            System.out.print("Please enter a value no less than 1 and no more than 300: ");
            mana = myInput.getInt();
        }
        System.out.print("Enter your strength (1-300): ");
        strength = myInput.getInt(); 
        while (strength > 300 || strength < 1) {
            System.out.print("Please enter a value no less than 1 and no more than 300: ");
            strength = myInput.getInt();
        }
    }
}
