import java.util.Random;

public class Computer {
    public static String[][] placeShip(String[][] battleShipMap, int magicNumber){
        int[] numericalLocation = new int[2];
        Random rand = new Random();
        numericalLocation[0] = rand.nextInt(10);
        numericalLocation[1] = rand.nextInt(10);


        if (takenOrNot(battleShipMap, numericalLocation)){
            // Use random number generator to decide orientation of ship
            int orientation = rand.nextInt(1);

            // 0 will denote horizontal
            if(orientation == 0){

                if(numericalLocation[1] + magicNumber <= 10){

                    for(int number = 0; number < magicNumber; number++){
                        if (battleShipMap[numericalLocation[0]][numericalLocation[1]+number] != null){
                            placeShip(battleShipMap, magicNumber);
                        }
                    }
                    for(int number = 0; number < magicNumber; number++) {
                        battleShipMap[numericalLocation[0]][numericalLocation[1]+number] = "1";
                    }
                }
                else{
                    placeShip(battleShipMap, magicNumber);
                }
            }
            // 1 will denote vertical placement of ship
            else{
                if(numericalLocation[0] + magicNumber <= 10){
                    for(int number = 0; number < magicNumber; number++){
                        if (battleShipMap[numericalLocation[0]+number][numericalLocation[1]] != null){
                            placeShip(battleShipMap, magicNumber);
                        }
                    }
                    for(int number = 0; number < magicNumber; number++){
                        battleShipMap[numericalLocation[0]+number][numericalLocation[1]] = "1";
                    }
                }
                else{
                    placeShip(battleShipMap, magicNumber);
                }
            }
        }
        else{
            placeShip(battleShipMap, magicNumber);
        }
        return battleShipMap;
    }

    public static boolean takenOrNot(String[][] battleShipMap, int[] location) {
        if (battleShipMap[location[0]][location[1]] != null){
            return false;
        }
        else{
            return true;
        }
    }

    public static void attack(String[][] battleShipMap){
        Random rand = new Random();
        int[] numericalLocation = new int[2];
        numericalLocation[0] = rand.nextInt(10);
        numericalLocation[1] = rand.nextInt(10);

        if(battleShipMap[numericalLocation[0]][numericalLocation[1]] == "1"){
            System.out.println("The computer hit one of your ship!");
            battleShipMap[numericalLocation[0]][numericalLocation[1]] = "x";
        }
        else if(battleShipMap[numericalLocation[0]][numericalLocation[1]] == null) {
            battleShipMap[numericalLocation[0]][numericalLocation[1]] = "-";
            System.out.println("Phew, the computer missed!");
        }
    }
}
