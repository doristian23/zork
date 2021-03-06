import java.util.*;
/**
 * Write a description of class Map here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Map
{
    private Input mapInput;
    private char map[][] = new char[21][21];
    private char mapM[][] = new char[21][21];
    private char mapC[][] = new char[21][21];
    private int numMonsters, numChests;
    private int playerX = 10, playerY = 10;
    private String pause;
    private boolean combat, chest, monsterDeath = false, nextStage;
    
    public Map(int n)
    {
        mapInput = new Input();
        monsterGenerator(n*20); 
        chestGenerator(n*20);
        for (int x = 1; x < 21; x++){
            for (int y = 1; y < 21; y++){
                map[x][y] = '█';
            }
        }
        monsterDeath = true;
    }
    
    public void monsterGenerator(int n)
    {
        Random randomGenerator = new Random();
        numMonsters = n;
        int monstersX[] = new int[numMonsters];
        int monstersY[] = new int[numMonsters];
        monstersX[0] = (randomGenerator.nextInt(20)+1);
        monstersY[0] = (randomGenerator.nextInt(20)+1);
        for (int x = 1; x < numMonsters; x++) {
            monstersX[x] = (randomGenerator.nextInt(20)+1);
            while (monstersX[x] == monstersX[x-1]) {
                monstersX[x] = (randomGenerator.nextInt(20)+1);
            }
            monstersY[x] = (randomGenerator.nextInt(20)+1);
            while (monstersY[x] == monstersY[x-1]) {
                monstersY[x] = (randomGenerator.nextInt(20)+1);
            }
            mapM[monstersX[x]][monstersY[x]] = 'M';
        }
    }
    
    public void chestGenerator(int n)
    {
        Random randomGenerator = new Random();
        numChests = n; 
        int chestsX[] = new int[numChests];
        int chestsY[] = new int[numChests];
        chestsX[0] = (randomGenerator.nextInt(20)+1);
        chestsY[0] = (randomGenerator.nextInt(20)+1);
        for (int x = 1; x < numChests; x++) {
            chestsX[x] = (randomGenerator.nextInt(20)+1);
            while (chestsX[x] == chestsX[x-1]) {
                chestsX[x] = (randomGenerator.nextInt(20)+1);
            }
            chestsY[x] = (randomGenerator.nextInt(20)+1);
            while (chestsY[x] == chestsY[x-1]) {
                chestsY[x] = (randomGenerator.nextInt(20)+1);
            }
            mapC[chestsX[x]][chestsY[x]] = 'C';
        }
    }
    
    public void grid()
    {
        for (int x = 1; x < 21; x++) {
            for (int y = 1; y < 21; y++) {
                System.out.print(map[x][y]);
            }
            System.out.println();
        }
    }
    
    public void move()
    {
        String move = mapInput.getString();
        if (move.equals("w") && playerX > 1) {
            playerX -= 1;
        } else if (move.equals("s") && playerX < 20) {
            playerX += 1;
        } else if (move.equals("a") && playerY > 1) {
            playerY -= 1;
        } else if (move.equals("d") && playerY < 20) {
            playerY += 1;
        } else {
            System.out.print("Please use only the WASD keys to move. Enter any key to continue:");
            pause = mapInput.getString();
        }
        map[playerX][playerY] = 'X';
    }
    
    public void guessCheck()
    {
        String userInput;
        if (mapM[playerX][playerY] == 'M' && mapC[playerX][playerY] != 'C'){
            map[playerX][playerY] = 'M'; 
            System.out.println("You found a monster!"); System.out.print("Enter any key to continue: "); 
            userInput = mapInput.getString();
            combat = true;
        } else if (mapC[playerX][playerY] == 'C' && mapM[playerX][playerY] != 'M') {
            map[playerX][playerY] = 'C'; 
            System.out.println("You found a chest!"); System.out.print("Enter any key to open chest: "); 
            userInput = mapInput.getString();
            chest = true;
            mapC[playerX][playerY] = ' ';
        } else if (mapM[playerX][playerY] == 'M' && mapC[playerX][playerY] == 'C') {
            map[playerX][playerY] = '*';
            System.out.println("You found a monster guarding a chest!"); System.out.print("Enter any key to continue: ");  
            userInput = mapInput.getString();
            combat = true;
            chest = true;
        } else {
            System.out.println("Nothing here.");
            map[playerX][playerY] = '░';
        }
    }
    
    public void explore()
    {
       map[playerX][playerY] = 'X'; grid(); map[playerX][playerY] = '░'; 
       checkMonster();
       while (combat == false && chest == false) {
           System.out.print("Where to? Use the WASD keys to move around.");
           move();
           System.out.print('\u000C');
           grid();
           guessCheck();
       }
    }
       
    public void checkMonster() //checks if player killed the monster or not
    {
        if (monsterDeath == false && mapC[playerX][playerY] != 'C') {
            map[playerX][playerY] = 'M'; //puts M on the map if the monster is not killed
        } else if (monsterDeath == true) {
            mapM[playerX][playerY] = ' ';
        }
    }
    
    public void setMonsterDeath(boolean mD)
    {
        monsterDeath = mD;
    }
    public void setCombat(boolean _combat)
    {
        combat = _combat;
    }
    public void setChest(boolean _chest)
    {
        chest = _chest;
    }
    public boolean getCombat()
    {
        return combat;
    }
    public boolean getChest()
    {
        return chest;
    }
}
