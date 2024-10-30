import java.util.Scanner;
import java.util.Random;
public class Game {
    public static void main(String[] args) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        int counter = 0;
        String[][] accounts = new String[11][8];

        // Welcome Screen
        homePage(accounts, counter, rand, scanner); 
    }



        // HELP!! : remember to add a new method to filter out passwords that are not following valid formatting
   


    static boolean isValidName(String name) {
        if (name == null || name.isEmpty())
            return false;
        for (char ch : name.toCharArray()) {
            if (!Character.isLetter(ch) && ch != ' ')
                return false;
        }
        return true;
    }

    static boolean isValidEmail(String email) {
        if (email == null || email.isEmpty() || !email.contains("@") || !email.contains(".com"))
            return false;
        int atIndex = email.indexOf("@");
        int atIndex2 = email.indexOf(".com");
        if (atIndex == 0 || atIndex == email.length() - 1)
            return false;
        else if (atIndex2 == 0 || atIndex2 == email.length() - 1)
            return false;

        return true;
    }

    /*
    static boolean isValidInteger(String str)
    {
        if (str == null || str.isEmpty())
            return false;
        for (char ch : str.toCharArray())
        {
            if (!Character.isDigit(ch))
                return false;
        }
        return true;
    } */
    
    static void login(String[][] accounts, Scanner scanner, int counter, Random rand) {
        accounts[counter][4] = "";
        System.out.println("You have selected Login.");
        System.out.println("Please enter your username: ");
        String usernameLogin = scanner.nextLine();
        System.out.println("Please enter your password: ");
        String passwordLogin = scanner.nextLine();

        for (int i = 0; i < accounts.length; i++) {
            if (usernameLogin.equalsIgnoreCase(accounts[i][2]) && passwordLogin.equalsIgnoreCase(accounts[i][3])) {
                System.out.println("Login successful...");
                // Main Menu
                mainMenu(accounts, i, scanner);
                return;
            }
        }
        System.out.println("There is no account associated with your credentials. Do you want to create a new account?");
        System.out.println("Enter your choice: \"Yes\"");
        System.out.println("Enter your choice: \"No\"");
        String choice3 = scanner.nextLine();

        switch (choice3.toLowerCase()) {
            case "no":
                break;
            case "yes":
                signUp(accounts, counter, rand, scanner);
                break;
            default:
                System.out.println("Invalid choice, try again...");
                break;
        }
    }

     static void mainMenu(String[][] accounts, int i, Scanner scanner) {
        while (true) {
            // Perhaps add some sort of permanent totaling score in home based on your score in the play list
            System.out.print("***********Main Menu***************\n");
            System.out.println("Enter your choice: \"Home\"");
            System.out.println("Enter your choice: \"Play\"");
            System.out.println("Enter your choice: \"Exit\"");
            System.out.println("**********************************");
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "home":
                    displayAccountInfo(accounts, i);
                    break;
                case "play":
                    playGame(scanner);
                    break;
                case "exit":
                    System.out.println("Exiting to Welcome Screen...");
                    break;
                default:
                    System.out.println("Invalid choice, please try again...");
                    break;
            }
        }
    }


        

        



        

            




     static void playGame(Scanner scanner) {
        String[][] dungeon = new String[8][8];
        String[] characterInventory = new String[15];
        boolean gameIsOver = false;
        boolean keepGoing = true;
            // Character Creation
        System.out.println("Please enter your character name: ");
        String characterName = scanner.nextLine();
        System.out.println("Please select your character's race: " +
                "Barbarian (+10 STR), Wizard (+10 INT), Warrior (+10 VIT), Ranger (+10 DEX)");
        String characterRace = scanner.nextLine();
        if (!characterRace.equalsIgnoreCase("barbarian") || !characterRace.equalsIgnoreCase("wizard")
                || !characterRace.equalsIgnoreCase("warrior") || !characterRace.equalsIgnoreCase("ranger")) {
            System.out.println("Not a valid input, please try again...");
        }
        Player player = new Player(characterName, characterRace, characterInventory);

        // Shopping phase before the dungeon
        storePhase(player, scanner);

        // Code for generating the field
        for (int i = 0; i < dungeon.length; i++) {
            for (int j = 0; j < dungeon[i].length; j++) {
                dungeon[i][j] = "\u2753";
            }
        }
        // Player starting position
        int playerX = 7; // Row
        int playerY = 0; // Column
        dungeon[playerX][playerY] = "\uD83D\uDD35";

        // Game loop
        while (!gameIsOver) {
            // Display the dungeon to the user
            printDungeon(dungeon);

            // Get player input
            System.out.println("Move your character (W/A/S/D to move, Q to quit): "); // todo : make it so that you can't go to previous rooms or that you cant fight enemies as those rooms should be flagged as "cleared".
            String move = scanner.nextLine().toLowerCase();

            // Clear current position
            dungeon[playerX][playerY] = "\u2753"; // Reset to question mark

            // Update player position based on input
            switch (move) {
                case "w": // Move up
                    if (playerX > 0) playerX--;
                    break;
                case "s": // Move down
                    if (playerX < 7) playerX++;
                    break;
                case "a": // Move left
                    if (playerY > 0) playerY--;
                    break;
                case "d": // Move right
                    if (playerY < 7) playerY++;
                    break;
                case "q": // Quit the game
                    gameIsOver = true;
                    System.out.println("You have quit the game.");
                    continue;
                default:
                    System.out.println("Invalid input. Please use W/A/S/D to move or Q to quit.");
                    break;
            }

            // Randomly generate an enemy encounter
            // if (new Random().nextInt(3) == 0) { // 1 in 3 chance of an encounter
                Enemy enemy = new Enemy(generateRandomEnemy());
                battle(player, enemy, scanner);
            // }

            // Update new position
            dungeon[playerX][playerY] = "\uD83D\uDD35"; // Set new position as blue square
        }
        scanner.close();

    }

    static void storePhase(Player player, Scanner scanner) {
        // Maybe add a random quality dropper dictated with stars to indicate quality. Bosses?
        // Maybe include a score generator after each monster kill
        boolean keepGoing = true;

        do {
            System.out.println("***********Store***************\n");
            System.out.println("Gold: " + player.gold);
            System.out.println("Purchase: \"Health Potion\" (50 Gold)");
            System.out.println("Purchase: \"Mana Potion\" (50 Gold)");
            System.out.println("Purchase: \"Basic GreatSword\" (500 Gold)");
            System.out.println("Purchase: \"Basic Longsword\" (500 Gold)");
            System.out.println("Purchase: \"Basic Longbow\" (500 Gold)");
            System.out.println("Purchase: \"Basic Staff\" (500 Gold)");
            System.out.println("**********************************");
            System.out.println("Enter your purchase: ");
            String choice = scanner.nextLine().toLowerCase();

            switch (choice) {
                case "health potion":
                case "mana potion":
                    System.out.println("How many would you like to buy?");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    int totalCost = quantity * 50;
                    if (player.spendGold(totalCost)) {
                        System.out.println("You purchased " + quantity + " " + choice + "(s).");
                        System.out.println("Would you like to purchase anything else?\n\"Yes\"\n\"No \"");
                        String choice2 = scanner.nextLine();
                        switch (choice2.toLowerCase()) {
                            case "yes":
                                keepGoing = true;
                                break;
                            case "no":
                                keepGoing = false;
                                break;
                            default:
                                System.out.println("Invalid input...");
                                break;
                        }
                    }
                    break;
                case "basic greatsword":
                case "basic longsword":
                case "basic longbow":
                case "basic staff":
                    if (player.spendGold(500)) {
                        System.out.println("You have purchased a " + choice + ".");
                        System.out.println("Would you like to equip it? (yes/no)");
                        String equipChoice = scanner.nextLine().toLowerCase();
                    if (equipChoice.equals("yes")) {
                        player.equipWeapon(choice);
                        System.out.println("You equipped the " + choice + ".");
                    }
                }
                    System.out.println("Would you like to purchase anything else?\n \" Yes\" \n \" No \"");
                    String choice2 = scanner.nextLine();
                    switch (choice2.toLowerCase()) {
                        case "yes":
                            keepGoing = true;
                            break;
                        case "no":
                            keepGoing = false;
                            break;
                        default:
                            System.out.println("Invalid input...");
                    }
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        } while (keepGoing == false);
    }

        static String generateRandomEnemy() {
            String[] enemies = {"Skeleton", "Slime", "Ghoul"};
            return enemies[new Random().nextInt(enemies.length)];
        }

        static void battle(Player player, Enemy enemy, Scanner scanner) {
            boolean battleOver = false;

            while (!battleOver) {
                // Display enemy and player stats
                System.out.println("*******You have encountered an enemy!**********");
                enemy.displayStats();
                System.out.println("***************************************");
                player.displayStats();

                System.out.println("Choose action: Attack, Flee, Check Inventory");
                String action = scanner.nextLine().toLowerCase();

                switch (action) {
                    case "attack":
                        // Player attacks enemy
                        System.out.println("You attack the " + enemy.type + " for " + player.calculateDamage() + " damage!");
                        enemy.health -= player.calculateDamage();
                        if (enemy.health <= 0) {
                            System.out.println("You defeated the " + enemy.type + "!" + " It dropped 100 gold!");
                            player.addGold(100);
                            // player.addItem(); // todo : implement method
                            battleOver = true;
                        } else {
                            enemy.enemyAttacks(player);
                        }
                        break;
                    case "flee":
                        // Attempt to flee
                        if (player.Flee()) {
                            System.out.println("You successfully fled the battle!");
                            battleOver = true;
                        } else {
                            System.out.println("Failed to flee! You've been counterattacked!");
                            enemy.enemyAttacks(player);
                        }
                        break;
                    case "check inventory":
                        System.out.println("Your inventory contains: ");
                        // Display inventory (for now assume basic) does nothing for now ...
                        break;
                    default:
                        System.out.println("Invalid action. Please choose again.");
                        break;

                }

                if (player.health <= 0) {
                    player.gameOver();
                }
            }
        }
        



        


        // Method to print the dungeon grid
        static void printDungeon(String[][] dungeon) {
            for (String[] row : dungeon) {
                for (String cell : row) {
                    System.out.print(cell + " ");
                }
                System.out.println();
            }
        }

        static void recoverPassword(String[][] accounts, Scanner scanner) {
            System.out.println("In order to recover your password please enter your username and the last 4 digits of your routing number...");
            System.out.println("Enter your username: ");
            String usernameRecovery = scanner.nextLine();
            System.out.println("Enter the last 4 digits of your routing number: ");
            String routingNumRecovery = scanner.nextLine();

            boolean accountFound = false;
            for (int i = 0; i < accounts.length; i++) {
                if (accounts[i][2] != null && accounts[i][2].equals(usernameRecovery) && !routingNumRecovery.isEmpty() && accounts[i][4].endsWith(routingNumRecovery)) {
                    System.out.println("Your password is: " + accounts[i][3]);
                    accountFound = true;
                    break;
                }
            }
            if (!accountFound)
                System.out.println("You have entered incorrect information");
        }

        static void displayAccountInfo(String[][] accounts, int accountIndex) {
            System.out.println("\n **********HOME***********");
            System.out.println("Full Name: " + accounts[accountIndex][0]);
            System.out.println("Email: " + accounts[accountIndex][1]);
            System.out.println("Username: " + accounts[accountIndex][2]);
            System.out.println("Password: " + accounts[accountIndex][3]);
            System.out.println("Routing Number: " + accounts[accountIndex][4]);
        }
    
    static void homePage(String[][] accounts, int counter, Random rand, Scanner scanner) {
        while (true) {
            accounts[counter][4] = "";
            System.out.println("\n *******Welcome Page******* \n \"Sign Up\" \n \"Login\" \n \"Forgot Your Password\" \n \"Exit\" \n **************************");
            String choice = scanner.nextLine();

            switch (choice.toLowerCase()) {
                case "sign up":
                    counter = signUp(accounts, counter, rand, scanner);
                    break;
                case "login":
                    login(accounts, scanner, counter, rand);
                    break;
                case "forgot your password":
                    recoverPassword(accounts, scanner);
                    break;
                case "exit":
                    System.out.println("Exiting Program...");
                    break;
                default:
                    System.out.println("Invalid input, try again.");
                    break;
            }
        }
    }

    static int signUp(String[][] accounts, int counter, Random rand, Scanner scanner) {
        if (counter >= 10)
        // Error: If sign-ups are at capacity
        {
            System.out.println("We apologize but sign ups are temporarily at capacity.");
            return counter;
        }

        System.out.println("You have selected Sign Up.");
        System.out.println("Enter your full name:");
        String fullName = scanner.nextLine();
        // Error: if name format is invalid
        if (!isValidName(fullName)) {
            System.out.println("Invalid name format. Please try again.");
            return counter;
        }

        System.out.println("Enter your email:");
        String email = scanner.nextLine();
        // Error: if email is invalid
        if (!isValidEmail(email)) {
            System.out.println("Invalid email format. Please try again.");
            return counter;
        }
        // Error: if email matches with an already existing account
        for (int i = 0; i < accounts.length; i++) {
            if (email.equals(accounts[i][1])) {
                System.out.println("Your email matches with an already existing account, please try again");
                return counter;
            }
        }
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        // Error: if username matches with an already existing account
        for (int i = 0; i < accounts.length; i++) {
            if (username.equals(accounts[i][2])) {
                System.out.print("Your username matches with an already existing account, please try again");
                return counter;
            }
        }
        System.out.println("Enter your password: ");
        String password = scanner.nextLine();

        // Account details in 2D Array
        accounts[counter][0] = fullName;
        accounts[counter][1] = email;
        accounts[counter][2] = username;
        accounts[counter][3] = password;

        StringBuilder routingNumber = new StringBuilder();
        for (int j = 0; j < 9; j++) {
            int randomDigit = rand.nextInt(10);
            routingNumber.append(randomDigit);
        }
        accounts[counter][4] = routingNumber.toString();
        System.out.print("Account successfully created.");
        return counter + 1;
    }
}
