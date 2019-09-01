import java.util.Scanner;

public class Helper {

    public static String[][] placeShip(String[][] battleShipMap, int magicNumber) {
        Scanner input = new Scanner(System.in);
        String magic = Integer.toString(magicNumber);
        System.out.println("Where do you want to place your sized " + magic + " ship? Enter a location (eg. A0, E6): ");

        String location = input.next();
        // Check that user has input a correct value
        if (correctValue(location)){
            int[] numericalLocation = convertLocation(location);

            if (takenOrNot(battleShipMap, numericalLocation)){
                // Not taken, ask user whether they want to place ship horizontally or vertically
                char orientation = orientationOfShip();

                // Once we know the orientation, we can check whether its a valid location to place it
                // For a horizontal orientation, we check the rows []
                if(orientation == 'h'){
                    // Need to check whether got space for the ship to be placed or not
                    if(numericalLocation[1] + magicNumber <= 10){
                        // since its horizontal, we stay in same row and iterate through columns
                        for(int number = 0; number < magicNumber; number++){
                            if (battleShipMap[numericalLocation[0]][numericalLocation[1]+number] != null){
                                System.out.println("Invalid location. Please choose again.");
                                placeShip(battleShipMap, magicNumber);
                            }
                        }
                        for(int number = 0; number < magicNumber; number++) {
                            battleShipMap[numericalLocation[0]][numericalLocation[1]+number] = "1";
                        }
                    }
                    else{
                        System.out.println("Invalid location. Please choose again.");
                        placeShip(battleShipMap, magicNumber);
                    }
                }
                // For a vertical orientation, we check the columns [][]
                // Stay in same column, iterate though rows
                else{
                    if(numericalLocation[0] + magicNumber <= 10){
                        for(int number = 0; number < magicNumber; number++){
                            if (battleShipMap[numericalLocation[0]+number][numericalLocation[1]] != null){
                                System.out.println("Invalid location. Please choose again.");
                                placeShip(battleShipMap, magicNumber);
                            }
                        }
                        for(int number = 0; number < magicNumber; number++){
                            battleShipMap[numericalLocation[0]+number][numericalLocation[1]] = "1";
                        }
                    }
                    else{
                        System.out.println("Invalid location. Please choose again.");
                        placeShip(battleShipMap, magicNumber);
                    }
                }
            }
            else{
                placeShip(battleShipMap, magicNumber);
            }
        }
        else{
            placeShip(battleShipMap, magicNumber);
        }
        return battleShipMap;
    }

    // This function returns true if the location is empty, false if taken
    public static boolean takenOrNot(String[][] battleShipMap, int[] location) {
        if (battleShipMap[location[0]][location[1]] != null){
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean correctValue(String location) {
        int length = location.length();
        if (length == 2){
            char alpha = location.charAt(0);
            char num = location.charAt(1);
            if (alpha >= 'A' && alpha <= 'J'){
                int number = Character.getNumericValue(num);
                if (number >= 0 && number <= 10){
                    return true;
                }
            }
        }
        return false;
    }

    public static char orientationOfShip(){
        Scanner input = new Scanner(System.in);
        System.out.println("Would you like the ship to be placed horizontally or vertically? (h/v): ");
        String orientation = input.next();

        char firstChar = orientation.charAt(0);
        // while it is not any of these, we will need to reprompt for input
        while (firstChar != 'h' && firstChar != 'v'){
            System.out.print("Please enter a valid input (h/v): ");
            orientation = input.next();
            firstChar = orientation.charAt(0);
        }
        if (firstChar == 'h' || firstChar == 'H'){
            return 'h';
        }
        else{
            return 'v';
        }
    }

    public static int[] convertLocation(String location){
        char alpha = location.charAt(0);
        char num = location.charAt(1);
        int[] returnValue = new int[2];
        returnValue[0] = Math.abs('A' - alpha);
        returnValue[1] = Character.getNumericValue(num);
        return returnValue;
    }

    public static void attack(String[][] battleShipMap){
        System.out.println("Please enter a location to shoot at the computer's ship (E.g: A0, E9): ");
        Scanner input = new Scanner(System.in);
        String location = input.next();
        if (correctValue(location)) {
            int[] numericalLocation = convertLocation(location);
            if(battleShipMap[numericalLocation[0]][numericalLocation[1]] == "1"){
                System.out.println("Congrats, you hit a ship!");
                battleShipMap[numericalLocation[0]][numericalLocation[1]] = "x";
            }
            else if(battleShipMap[numericalLocation[0]][numericalLocation[1]] == null){
                battleShipMap[numericalLocation[0]][numericalLocation[1]] = "-";
                System.out.println("Aw man, you didn't hit anything!");
            }
        }
        else{
            attack(battleShipMap);
        }
    }
}
