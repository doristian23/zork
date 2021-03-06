import java.util.*;
/**
* Write a description of class Game here.
*
* @author (your name)
* @version (a version number or a date)
*/
public class Game
{
    private Input myInput;
    private Character player;
    private Monster monster;
    private Map stage1, stage2, stage3, stage4;
    private boolean alive = true, win, nextStage;
    private String pause; private int userInput, stop = 0;
    private Stones magicStone;
    private Potions potion;
    
    public Game()
    {
        myInput = new Input();
        System.out.println("Welcome to Zork!");
        System.out.println("The objective of this game is to explore your environment, fight monsters, and level up.");
        player = new Character();
        stage1 = new Map(1); stage2 = new Map(2); stage3 = new Map(3); stage4 = new Map(3); //stage 4 will spawn as many bosses as stage 3 spawns balrogs
    }
    
    public void startGame()
    {
        while (alive == true) {
            if (player.getStageNum() == 1) {
                play(stage1);
            } else if (player.getStageNum() == 2) {
                play(stage2);
            } else if (player.getStageNum() == 3) {
                play(stage3);
            } else {
                play(stage4);
            }
            player.setNextStage(1);
        }
    }
    
    public void play(Map stage)
    {
        System.out.println("Enter any key to continue: ");
        pause = myInput.getString();
        while (player.getNextStage() == 1 && alive == true) {
            stage.explore();
            if (stage.getCombat() == true) {
                combat(stage);
            }
            if (stage.getCombat() == false && stage.getChest() == true) {
                openChest(stage);
            }
        }
    }
    
    public void combat(Map stage) 
    {
        stage.setMonsterDeath(false);
        win = false;
        if (player.getStageNum() == 1) {
            monster = new Monster(1);
        } else if (player.getStageNum() == 2) {
            monster = new Monster(2);
        } else if (player.getStageNum() == 3) {
            monster = new Monster(3);
        } else {
            monster = new Monster(true);
        }//monster's attributes are shown in constructor
        System.out.print("You are currently Level "+player.getLevel()+". "); player.showAttributes();
        System.out.println("Would you like to (1) Fight (2) Use a Potion or (3) Flee?");
        userInput = myInput.getInt(); errorCheckCombat();
        if (userInput == 3) {
            System.out.println("You were hit once while fleeing.");
            player.setHealth(-20);
            player.showAttributes();
            if (player.getHealth() <= 0) {
                playerDeath();
                stop = 1;
            }
        }
        while (userInput != 3 && stop == 0) {
            if (userInput == 2 && stop == 0) {
                if (player.getInventorySize() < 1) {
                    System.out.println("You have no potions. Would you like to (1) Fight or (3) Flee?" );
                    userInput = myInput.getInt();
                    while (userInput != 1 && userInput != 3) {
                        System.out.println("Please either 1 or 3: ");
                        userInput = myInput.getInt();
                    }
                } else {
                    player.usePotions();
                    if (player.getFire() == true) {
                        monster.halveHealth();
                        player.setFire(false);
                    } else if (player.getAmbrosia() == true) {
                        player.increaseStrength();
                        player.setAmbrosia(false);
                    } else if (player.getIce() == true ) {
                        monster.halveStrength();
                        player.setIce(false);
                    }
                    player.showAttributes();
                    monster.showAttributes();
                    if (player.getHealth() <= 0) {
                        playerDeath();
                        stop = 1;
                    } else if (monster.getHealth() <= 0) {
                        win = true;
                        stop = 1;
                    } else {
                        System.out.println("Would you like to (1) Fight (2) Use a Potion (3) Flee?");
                        userInput = myInput.getInt(); errorCheckCombat();
                    }
                }
            }
            if (userInput == 1 && stop == 0) {
                Random r = new Random();
                int pStrike, mStrike;
                mStrike = r.nextInt(monster.getStrength())+1;
                pStrike = r.nextInt(player.getStrength())+1;
                if (pStrike >= mStrike) {
                    System.out.println("You hit the monster.");
                    monster.setHealth(-20);
                    player.setStrength(10);
                } else {
                    System.out.println("You were hit.");
                    player.setHealth(-20);
                    monster.setStrength(10);
                }
                player.showAttributes();
                monster.showAttributes();
                if (player.getHealth() <= 0) {
                    playerDeath();
                    stop = 1;
                } else if (monster.getHealth() <= 0) {
                    win = true;
                    stop = 1;
                } else {
                    System.out.println("Would you like to (1) Continue Fight (2) Use a Potion (3) Flee?");
                    userInput = myInput.getInt(); errorCheckCombat();
                }
            }
        }
        if (win == true) {
            System.out.println("You defeated the monster!");
            player.setPoints(monster.getLevel()*10);
            System.out.print("You gained "+monster.getLevel()*10+" points. "); player.levelUp(); player.showLevel(); 
            stage.setMonsterDeath(true);
        }
        stage.setCombat(false);
        stop = 0;
        userInput = 0;
        if (alive == true) {
            player.nextStage();
            System.out.println("Enter any key to continue: ");
            pause = myInput.getString();
            System.out.print('\u000C');
        }
    }
    public void errorCheckCombat()
    {
        while (userInput < 1 || userInput > 3) {
            System.out.println("Please either 1, 2 or 3: ");
            userInput = myInput.getInt();
        }
    }
    
    public void openChest(Map stage)
    {
        stage.setMonsterDeath(false);
        Random randomGenerator = new Random();
        int chest;
        chest = (randomGenerator.nextInt(7)+1);
        if (chest >= 6)
        {
            System.out.println("You have opened the chest and found potions!");
            potion = new Potions();
            potion.getDescription();
            player.addToInventory(potion);
        }
        else if (chest >= 3 && chest <= 5)
        {
            System.out.println("You have opened the chest and found treasure!");
            int numStone;
            numStone = (randomGenerator.nextInt(5)+1);
            System.out.println("There are "+numStone+" stones in the chest.");
            for (int x = 1; x <= numStone; x++)
            {
                magicStone = new Stones();
                magicStone.showAttributes();
                applyAttributes();
            }
            player.showAttributes();
        }
        else if (chest == 2)
        {
            System.out.println("You have opened the chest and found poison!");
            System.out.println("You lost 5 points for Health, Mana, and Strength.");
            player.setHealth(-5);
            player.setMana(-5);
            player.setStrength(-5);
            if (player.getHealth() <= 0) {
                playerDeath();
            }
        }
        else if (chest == 1)
        {
            System.out.println("You have opened the chest and found nothing.");
        }
        stage.setCombat(false);
        stage.setChest(false);
        if (alive == true) {
            player.nextStage();
            System.out.println("Enter any key to continue: ");
            pause = myInput.getString();
            System.out.print('\u000C');
        }
    }
    public void applyAttributes()
    {
        if (magicStone.getName() == "Yellow")
        {
            player.setHealth(magicStone.getValue());         
        }
        else if (magicStone.getName() == "Green")
        {
            player.setMana(magicStone.getValue());
        }
        else if (magicStone.getName() == "Purple")
        {
            player.setStrength(magicStone.getValue());
        }
    }
    
    public void playerDeath()
    {
        System.out.println("You died."); System.out.println("Game over!");
        player.showAttributes();
        alive = false;
    }
}
