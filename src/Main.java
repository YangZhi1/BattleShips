public class Main {
    public static void main(String[] args) {
        // Making our new map which is empty :)
        String[][] playerBattleShipMap = new String[10][10];
        String[][] computerBattleShipMap = new String[10][10];

        System.out.println("Welcome to this game of battleships!");

        System.out.println("You will be given 4 ships to be placed, which are sized 2, 3, 4, 5 units long.");
        printMap(playerBattleShipMap, computerBattleShipMap);
        // Ask player to place their ships
        //battleShipMap = Helper.placeShip(battleShipMap);
        for (int i = 5; i > 1; i--){
            Helper.placeShip(playerBattleShipMap, i);
            printMap(playerBattleShipMap, computerBattleShipMap);
        }
        System.out.println("The computer will now place their ships");

        for (int i = 5; i > 1; i--){
            Computer.placeShip(computerBattleShipMap, i);
        }
        System.out.println("The computer is done. The battle will now commence.");

        // while both players still have 1 surviving ship, continue the game
        while(stillAlive(playerBattleShipMap) && stillAlive(computerBattleShipMap)){
            Helper.attack(computerBattleShipMap);
            Computer.attack(playerBattleShipMap);
            printMap(playerBattleShipMap, computerBattleShipMap);
        }

        System.out.println("Game over");
        if(stillAlive(playerBattleShipMap)){
            System.out.println("Congratulations, you won!");
        }
        else{
            System.out.println("Oh well, better luck next time");
        }
    }



    public static void printMap(String[][] playerBattleShipMap, String[][] computerBattleShipMap){
        System.out.println("     Y O U          C O M P U T E R  ");
        System.out.println("   0123456789          0123456789");
        for(int i = 0; i < playerBattleShipMap.length; i++){
            char c = (char) ('A' + i);
            System.out.print(c + " |");
            for(int j = 0; j < playerBattleShipMap[i].length; j++){
                if(playerBattleShipMap[i][j] == "1"){
                    System.out.print("@");
                }
                else if(playerBattleShipMap[i][j] == "x"){
                    System.out.print("x");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.print("| " + c + "   " + c + " | ");
            for(int j = 0; j < computerBattleShipMap[i].length; j++){
                if(computerBattleShipMap[i][j] == "x"){
                    System.out.print("x");
                }
                else{
                    System.out.print(" ");
                }
            }
            System.out.println("| " + c);
        }
    }

    public static boolean stillAlive(String[][] battleShipMap){
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(battleShipMap[i][j] == "1"){
                    return true;
                }
            }
        }
        return false;
    }
}