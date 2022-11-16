import java.util.Scanner;

/*
 *
 * Arin Bindra
 * 
 */

public class Table {
    
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcome to the Black Jack Casino, where you can bet big and win big in black jack!\n");
        System.out.println("To start please enter your username.");
        
        System.out.println("Username: ");
        String username = sc.nextLine();
        
        Guest guest = new Guest(username);
        Casino casino = new Casino();
        
        Transaction transaction = new Transaction(guest, casino);
        
        CardDeck cardDeck = new CardDeck();
        boolean blackJackE = false;
        boolean blackJackN = false;
        
        boolean validGame = false;
        
        while(validGame == false) // Inside this while loop the user is asked which game mode they would like to play
        {
            System.out.println("\nWhich game would you like to play today?");
            System.out.println("Press 'N' for normal Black Jack");
            System.out.println("Press 'E' for easy Black Jack\n");
            String gameSelect = sc.nextLine();
            
            try{

                if(gameSelect.trim().equalsIgnoreCase("n"))
                {
                    System.out.println("Black Jack Normal Selected");
                    blackJackN = true;
                    validGame = true;
                }
                if(gameSelect.trim().equalsIgnoreCase("e"))
                {
                    System.out.println("Black Jack Easy Selected");

                    blackJackE = true; 
                    validGame = true;
                }
                
                if(gameSelect.trim().equalsIgnoreCase("x"))
                {
                    System.out.println("You cannot exit at this time\n");
                }
                if ((!gameSelect.trim().equalsIgnoreCase("e")) || (!gameSelect.trim().equalsIgnoreCase("n")))
                {
                    System.out.println("");
                    System.out.println("Please select either 'E' or 'N' for your game mode\n");
                }
            } 
            catch(Exception e)
            {
               System.out.println("Please enter a valid input");
            }
        }
        
        if(blackJackN == true)// if the user has selected the black jack normal game mode they can deposit more money into their account
        {
            System.out.println("\nWe offer 3 different tables for you to play on. $5, $25, $50");
            System.out.println("You must have avaliable funds in your account to play on any of the tables\n");
            
            if(guest.getDollars() < 5)
            {
                System.out.println("You do not currently have enough money in your account to play a hand");
            }
            
            if((guest.getDollars() < 25) && (guest.getDollars() >= 5))
            {
                System.out.println("You currently have enough money in your account to play on the $5 table");
            }
            
            if((guest.getDollars() < 50) && (guest.getDollars() >= 25))
            {
                System.out.println("You currently have enough money in your account to play on the $5 & $25 table");
            }
            
            if(guest.getDollars() >= 50)
            {
                System.out.println("You currently have enough money in your account to play on the $5, $25 & $50 table");
            }
            
            boolean depositYN = false;
            
            while(depositYN != true) // if the user would like to deposit into their account they can choose the int amount
            {
                if(guest.getDollars() < 5)
                {
                    System.out.println("Would you like to depsit some money into your account?");
                    
                    boolean depositInputUFiveN = false;
                    
                    while(depositInputUFiveN != true)
                    {
                        System.out.println("Press 'Y' for yes, to 'N' if you would not like to and exit the game");
                        String depositUnderFiveInputN = sc.nextLine();
                        
                        try
                        {
                            if(depositUnderFiveInputN.trim().equalsIgnoreCase("y"))
                            {
                                depositInputUFiveN = true;
                            }
                            
                            if(depositUnderFiveInputN.trim().equalsIgnoreCase("n"))
                            {
                                System.exit(0);
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please enter a vaid letter");
                        }
                    } 
                }
                
                if(guest.getDollars() >= 5) 
                {
                    System.out.println("Would you like to deposit some more money into your account?");
                    
                    boolean depositInputOFiveN = false;
                    
                    while(depositInputOFiveN != true)
                    {
                        System.out.println("Press 'Y' for yes, to 'N' if you would like to move forward.");
                        String depositOverFiveInputN = sc.nextLine();
                        
                        try
                        {
                            if(depositOverFiveInputN.trim().equalsIgnoreCase("y"))
                            {
                                depositInputOFiveN = true;
                            }
                            
                            if(depositOverFiveInputN.trim().equalsIgnoreCase("n"))
                            {
                                depositYN = true;
                                depositInputOFiveN = true;
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please enter a vaid letter");
                        }
                    } 
                }
                
                boolean inputDepositN = false;
                
                if(depositYN == true)
                {
                    inputDepositN = true;
                }
                
                while(inputDepositN != true)
                {
                    System.out.println("How much would you like to deposit into your account?");
                    String initalDepositN = sc.nextLine();

                    try
                    {
                        int initialDepositNint = Integer.parseInt(initalDepositN);

                        if((initialDepositNint > 0) && ((guest.getDollars() + initialDepositNint) >=5 ))
                        {
                            transaction.deposit(initialDepositNint);
                            System.out.println("$" + initialDepositNint + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                            inputDepositN = true;
                        }
                        
                        if((initialDepositNint > 0) && ((guest.getDollars() + initialDepositNint) <=5 )) // Because the smallest table is $5 the user must at least have 5 in their account
                        {
                            transaction.deposit(initialDepositNint);
                            System.out.println("$" + initialDepositNint + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                            System.out.println("You still need to deposit another $" + (5 - guest.getDollars()) + " in order to play, and continue");
                        }
                        
                        if(initialDepositNint < 0)
                        {
                            System.out.println("Please try again, we only accept whole positve amounts");
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Please try again, we only accept whole positve amounts");
                    }
                }
            }
        }
        
        if(blackJackE == true)
        {
            System.out.println("The only avalible table in EASY MODE is the $5 table\n");
            
            if(guest.getDollars() < 5)
            {
                System.out.println("You do not currently have enough money in your account to play a hand");
            }
            
            if(guest.getDollars() >= 5)
            {
                System.out.println("You currently have enough money in your account to play on the $5 table");
            }
            
            boolean depositYE = false;
            
            while(depositYE != true)
            {        
                if(guest.getDollars() < 5)
                {
                    System.out.println("\nWould you like to deposit some money into your account?");
                    
                    boolean depositInputUFiveE = false;
                    
                    while(depositInputUFiveE != true)
                    {
                        System.out.println("Press 'Y' for yes, to 'N' if you would not like to and exit the game");
                        String depositUnderFiveInputE = sc.nextLine();
                        
                        try
                        {
                            if(depositUnderFiveInputE.trim().equalsIgnoreCase("y"))
                            {
                                depositInputUFiveE = true;
                            }
                            
                            if(depositUnderFiveInputE.trim().equalsIgnoreCase("n"))
                            {
                                System.exit(0);
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please enter a vaid letter");
                        }
                    } 
                }
                
                if(guest.getDollars() >= 5)
                {
                    System.out.println("Would you like to deposit some more money into your account?");
                    
                    boolean depositInputOFiveE = false;
                    
                    while(depositInputOFiveE != true)
                    {
                        System.out.println("Press 'Y' for yes, press 'N' to move forward.");
                        String depositOverFiveInputE = sc.nextLine();
                        
                        try
                        {
                            if(depositOverFiveInputE.trim().equalsIgnoreCase("y"))
                            {
                                depositInputOFiveE = true;
                            }
                            
                            if(depositOverFiveInputE.trim().equalsIgnoreCase("n"))
                            {
                                depositYE = true;
                                depositInputOFiveE = true;
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please enter a vaid letter");
                        }
                    } 
                }
                
                boolean inputDepositE = false;
                
                if(depositYE == true)
                {
                    inputDepositE = true;
                }
                
                while(inputDepositE != true)
                {
                    System.out.println("How much would you like to deposit into your account?");
                    String initalDepositE = sc.nextLine();

                    try
                    {
                        int initialDepositEint = Integer.parseInt(initalDepositE);

                        if((initialDepositEint > 0) && ((guest.getDollars() + initialDepositEint) >=5 ))
                        {
                            transaction.deposit(initialDepositEint);
                            System.out.println("$" + initialDepositEint + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                            inputDepositE = true;
                        }
                        
                        if((initialDepositEint > 0) && ((guest.getDollars() + initialDepositEint) <=5 ))
                        {
                            transaction.deposit(initialDepositEint);
                            System.out.println("$" + initialDepositEint + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                            System.out.println("You still need to deposit another $" + (5 - guest.getDollars()) + " in order to play, and continue");
                        }
                        
                        if(initialDepositEint < 0)
                        {
                            System.out.println("Please try again, we only accept whole positve amounts");
                        }
                    }
                    catch(NumberFormatException e)
                    {
                        System.out.println("Please try again, we only accept whole positve amounts");
                    }
                }
            }
        }
        
        int table = 0;
        
        if(blackJackN == true) // Asks the user which table they would like to play on based on their account value
        {
            System.out.println("\nWhich table would you like to play at today?");
                
            if((guest.getDollars() >= 5) && (guest.getDollars() < 25))
            {
                boolean under25N = false;
                
                while(under25N != true)
                {
                    System.out.println("The only avaliable table today is the $5 table");
                    System.out.println("Press 'Y' to start game, Press 'N' to exit");
                    String continueUnder25N = sc.nextLine();
                    
                    try
                    {
                        if(continueUnder25N.trim().equalsIgnoreCase("y"))
                        {
                            under25N = true;
                            table = 5;
                        }
                        
                        if(continueUnder25N.trim().equalsIgnoreCase("n"))
                        {
                            guest.saveUser();
                            System.exit(0);
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Please enter a valid letter");
                    }
                }
            }
            
            if((guest.getDollars() >= 25) && (guest.getDollars() < 50))
            {
                boolean under50N = false;
                
                while(under50N != true)
                {
                    System.out.println("\nPlease select one of the avalible tables,");
                    System.out.println("Press 'A' to start a game on the $5 table | Press 'B' to start a game on the $25 table");
                    System.out.println("Press 'X' to exit the game");
                    String continueUnder50N = sc.nextLine();
                    
                    try
                    {
                        if(continueUnder50N.trim().equalsIgnoreCase("a"))
                        {
                            table = 5;
                            System.out.println("\n$5 table selected\n");
                            under50N = true;
                        }
                        
                        if(continueUnder50N.trim().equalsIgnoreCase("b"))
                        {
                            table = 25;
                            System.out.println("\n$25 table selected\n");
                            under50N = true;
                        }
                        
                        if(continueUnder50N.trim().equalsIgnoreCase("x"))
                        {
                            guest.saveUser();
                            System.exit(0);
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Please enter a valid letter");
                    }
                }
            }
            
            if(guest.getDollars() >= 50)
            {
                boolean over50N = false;
                
                while(over50N != true)
                {
                    System.out.println("\nPlease select one of the avalible tables,");
                    System.out.println("Press 'A' to start a game on the $5 table | Press 'B' to start a game on the $25 table");
                    System.out.println("Press 'C' to start a game on the $50 table | Press 'X' to exit the game");
                    String continueOver50N = sc.nextLine();
                    
                    try
                    {
                        if(continueOver50N.trim().equalsIgnoreCase("a"))
                        {
                            table = 5;
                            System.out.println("\n$5 table selected\n");
                            over50N = true;
                        }
                        
                        if(continueOver50N.trim().equalsIgnoreCase("b"))
                        {
                            table = 25;
                            System.out.println("\n$25 table selected\n");
                            over50N = true;
                        }
                        
                        if(continueOver50N.trim().equalsIgnoreCase("c"))
                        {
                            table = 50;
                            System.out.println("\n$50 table selected\n");
                            over50N = true;
                        }
                        
                        if(continueOver50N.trim().equalsIgnoreCase("x"))
                        {
                            casino.saveCasino();
                            guest.saveUser();
                            System.exit(0);
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Please enter a valid letter");
                    }
                }
            }
        }
        
        if(blackJackE == true)
        {
            table = 5;
        }
        
        //Prints the rules for black jack and additional information to help the user
        System.out.println("\nWelcome to Black Jack.\n");
        System.out.println("The rules are as follows:");
        System.out.println("There are two ways you can win the game.");
        System.out.println("1. The value of your cards are higher than the dealers, and under 21");
        System.out.println("2. The value of the dealers cards go above 21, whilst yours do not\n");
        System.out.println("The sequence of events in the game are as follows:");
        System.out.println("The dealer will deal one card to himselve and two cards to you.");
        System.out.println("Based on the value of your cards and the dealers, you can choose to take('Hit') another card or stay('Stand') with the cards you have.");
        System.out.println("After this, the dealer will deal himself more cards, until he feels the value of his cards are more then yours.");
        System.out.println("The value of whoevers cards are greater at the end of the game, whilst being under 21\n");
        System.out.println("Card Values:");
        System.out.println("[A] = either 1 or 11 | [2] = 2 | [3] = 3 | [4] = 4 | [5] = 5 | [6] = 6 | [7] = 7 |");
        System.out.println("[8] = 8 | [9] = 9 | [10] = 10 | [J] = 10 | [Q] = 10 | [K] = 10\n");

        if(blackJackN == true) // starts the game if the user selected normal mode
        {
            boolean newHandN = false;
            
            while(newHandN != true)
            {
                boolean gameCompletedN = false;
                
                while(gameCompletedN != true)
                {
                    BlackJackGame gameN = new BlackJackGame(cardDeck);
                    
                    boolean uOverN = false;

                    gameN.printCasinoCards();
                    gameN.printUserCards();

                    boolean standHitN = false;
                    
                    while(standHitN != true)
                    {
                        boolean standHitInputN = false;
                        boolean standN = false;
                        
                        while(standHitInputN != true)
                        {
                            System.out.println("Press 'S' to stand, Press 'H' to hit\n");
                            String choice = sc.nextLine();

                            try
                            {
                                if(choice.trim().equalsIgnoreCase("h"))
                                {
                                    gameN.Userhit();
                                    gameN.printCasinoCards();
                                    gameN.printUserCards();

                                    if(gameN.getUserCardValue() > 21)
                                    {
                                        uOverN = true;
                                        standN = true;
                                        standHitInputN = true;
                                    }

                                    standHitInputN = true;
                                }

                                if(choice.trim().equalsIgnoreCase("s"))
                                {
                                    standN = true;
                                    standHitInputN = true;
                                }

                                if(choice.trim().equalsIgnoreCase("x"))
                                {
                                    System.out.println("Sorry, you cannot quit at this time\n");
                                }
                            } 
                            catch(Exception e)
                            {
                                System.out.println("Please enter a valid letter");
                            }
                        }
                        
                        if(standN == true)
                        {
                            standHitN = true;
                        }
                    }
                    
                    if(uOverN != true)
                    {
                        while(gameN.getCasinoCardValue() <= 17)
                        {
                            gameN.Casinohit();                          
                        }
                    } 
                    else
                    {
                        gameN.Casinohit();
                    }
                    
                    System.out.println("\n----------------------------------------------------"); // the following code checks if the user has won, lost , or drawn the current game
                    gameN.printCasinoCards();
                    gameN.printUserCards();
                    System.out.println("");
                    
                    if(gameN.checkUserOver() && gameN.checkCasinoOver())
                    {
                        System.out.println("Both you and the Dealer are over, the game is a draw\n");
                        gameCompletedN = true;
                    }
                    
                    if(gameN.checkWin() == true)
                    {
                        System.out.println("Congratulations you have won!\n");
                        
                        transaction.win(table);
                        guest.win();
                        
                        System.out.println("$" + table + " has been added to your account. your new balance is $" + guest.getDollars());
                        System.out.println("Your win rate is now " + guest.getWinRate() + "\n");
                        
                        gameCompletedN = true;   
                    }
                    
                    if(gameN.checkUserCasinoDraw() == true)
                    {
                        System.out.println("Both you and the Dealer have drawn on this hand\n");
                        
                        gameCompletedN = true;
                    }
                    
                    if((gameN.checkLoss() == true) && (gameN.checkWin() == false) && (gameN.checkUserCasinoDraw() == false))
                    {
                        System.out.println("You have LOST, the dealer has won this hand\n");
                        
                        transaction.loss(table);
                        guest.loss();
                        
                        System.out.println("$" + table + " has been deducted from your account. Your new balance is $" + guest.getDollars());
                        System.out.println("Your win rate is now " + guest.getWinRate() + "\n");
                        
                        gameCompletedN = true;
                    }
                }
                
                if(guest.getDollars() < table) // checks if the user has enough money in their account to carry on, and allows them to deposit the required amount
                {
                    boolean invalidFundsN = false;
                    
                    while(invalidFundsN != true)
                    {
                        System.out.println("\nYou do not currently have enough funds to get a new hand");
                        System.out.println("If you would like to deposit at least $" + (table - guest.getDollars()) + " to continue playing on the $" + table + " table");
                        System.out.println("press 'D', otherwise press 'X' to exit today.");
                        String continueFundsD = sc.nextLine();
                        
                        try
                        {
                            if(continueFundsD.trim().equalsIgnoreCase("d"))
                            {
                                boolean insertingFundsN = false;
                                
                                while(insertingFundsN != true)
                                {
                                    System.out.println("How much money would you like to deposit, to continue playing");
                                    String newFundsN = sc.nextLine();
                                    
                                    try
                                    {
                                        int newFundsNint = Integer.parseInt(newFundsN);
                                        
                                        if((newFundsNint > 0) && ((guest.getDollars() + newFundsNint) <= table ))
                                        {
                                            transaction.deposit(newFundsNint);
                                            System.out.println("$" + newFundsNint + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                                            System.out.println("You still need to deposit another $" + (table - guest.getDollars()) + " in order to play, and continue");
                                        }
                                        
                                        if((newFundsNint > 0) && ((guest.getDollars() + newFundsNint) >= table ))
                                        {
                                            transaction.deposit(newFundsNint);
                                            System.out.println("$" + newFundsNint + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                                            invalidFundsN = true;
                                            insertingFundsN = true;
                                        }

                                        if(newFundsNint < 0)
                                        {
                                            System.out.println("Please try again, we only accept whole positive amounts");
                                        }
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Please try again, we only accept whole positive amounts");
                                    }
                                }
                            }
                            
                            if(continueFundsD.trim().equalsIgnoreCase("x"))
                            {
                                guest.saveUser();
                                casino.saveCasino();
                                System.exit(0);
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please enter a valid letter");
                        }
                    }
                }
                
                if(guest.getDollars() >= table) // Produces a menu allowing the user to play agin, read the rules, exit, or withdraw and exit
                {
                    boolean playAgainInputN = false;

                    while (playAgainInputN != true)
                    {
                        System.out.println("To play again press 'Y' | to exit today press 'X' | to read the rules again press 'R'");
                        System.out.println("To deposit more money into your account press 'D' | to withdraw all of your winnings and exit press 'W'");
                        String playAgainN = sc.nextLine();

                        try
                        {
                            if(playAgainN.trim().equalsIgnoreCase("y"))
                            {
                                playAgainInputN = true;
                            }

                            if(playAgainN.trim().equalsIgnoreCase("x"))
                            {
                                guest.saveUser();
                                casino.saveCasino();
                                System.exit(0);
                                newHandN = true;
                                playAgainInputN = true;
                            }

                            if(playAgainN.trim().equalsIgnoreCase("r"))
                            {
                                System.out.println("\nThe rules are as follows:");
                                System.out.println("There are two ways you can win the game.");
                                System.out.println("1. The value of your cards are higher than the dealers, and under 21");
                                System.out.println("2. The value of the dealers cards go above 21, whilst yours do not\n");
                                System.out.println("The sequence of events in the game are as follows:");
                                System.out.println("The dealer will deal one card to himselve and two cards to you.");
                                System.out.println("Based on the value of your cards and the dealers, you can choose to take('Hit') another card or stay('Stand') with the cards you have.");
                                System.out.println("After this, the dealer will deal himself more cards, until he feels the value of his cards are more then yours.");
                                System.out.println("The value of whoevers cards are greater at the end of the game, whilst being under 21\n");
                                System.out.println("Card Values:");
                                System.out.println("[A] = either 1 or 11 | [2] = 2 | [3] = 3 | [4] = 4 | [5] = 5 | [6] = 6 | [7] = 7 |");
                                System.out.println("[8] = 8 | [9] = 9 | [10] = 10 | [J] = 10 | [Q] = 10 | [K] = 10\n");
                            }

                            if(playAgainN.trim().equalsIgnoreCase("d"))
                            {
                                boolean depositD = false;

                                while(depositD != true)
                                {
                                    System.out.println("How much would you like to deposit into your account?");
                                    String depositInputD = sc.nextLine();

                                    try
                                    {
                                        int depositInputDint = Integer.parseInt(depositInputD);

                                        if(depositInputDint > 0)
                                        {
                                            transaction.deposit(depositInputDint);
                                            System.out.println("$" + depositInputD + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                                            depositD = true;
                                        }
                                        else
                                        {
                                            System.out.println("Please try again, we only accept whole positive amounts");
                                        }
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Please try again, we only accept whole positive amounts");
                                    }
                                }
                            }

                            if(playAgainN.trim().equalsIgnoreCase("w"))
                            {
                                System.out.println("$" + guest.getDollars() + " has been withdrawn from your account, Your account balance is now $0");

                                transaction.withdraw();
                                
                                guest.saveUser();
                                casino.saveCasino();

                                newHandN = true;
                                playAgainInputN = true;
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please enter a valid letter");
                        }
                    }
                }
            }
        }
        
        if(blackJackE == true)
        {
            boolean newHandE = false;
            
            while(newHandE != true)
            {
                boolean gameCompletedE = false;
                
                while(gameCompletedE != true)
                {
                    BlackJackGame gameE = new BlackJackGameEasy(cardDeck);
                    
                    boolean uOverE = false;

                    gameE.printCasinoCards();
                    gameE.printUserCards();

                    boolean standHitE = false;
                    
                    while(standHitE != true)
                    {
                        boolean standHitInputE = false;
                        boolean standE = false;
                        
                        while(standHitInputE != true)
                        {
                            if(gameE.getUserCardValue() != 21)
                            {
                                gameE.hitChance();
                            }
                            
                            System.out.println("Press 'S' to stand, Press 'H' to hit\n");
                            String choice = sc.nextLine();

                            try
                            {
                                if(choice.trim().equalsIgnoreCase("h"))
                                {
                                    if(gameE.getUserCardValue() != 21)
                                    {
                                        gameE.Userhit();
                                        gameE.printCasinoCards();
                                        gameE.printUserCards();
                                    }
                                    
                                    if(gameE.getUserCardValue() > 21)
                                    {
                                        uOverE = true;
                                        standE = true;
                                        standHitInputE = true;
                                    }
                                    
                                    standHitInputE = true;
                                }

                                if(choice.trim().equalsIgnoreCase("s"))
                                {
                                    standE = true;
                                    standHitInputE = true;
                                }

                                if(choice.trim().equalsIgnoreCase("x"))
                                {
                                    System.out.println("Sorry, you cannot quit at this time\n");
                                }
                            } 
                            catch(Exception e)
                            {
                                System.out.println("Please enter a valid letter");
                            }
                        }
                        
                        if(standE == true)
                        {
                            standHitE = true;
                        }
                    }
                    
                    if(uOverE != true)
                    {
                        while(gameE.getCasinoCardValue() <= 17)
                        {
                            gameE.Casinohit();                          
                        }
                    } 
                    else
                    {
                        gameE.Casinohit();
                    }
                    
                    System.out.println("\n----------------------------------------------------");
                    gameE.printCasinoCards();
                    gameE.printUserCards();
                    System.out.println("");
                    
                    if(gameE.checkUserOver() && gameE.checkCasinoOver())
                    {
                        System.out.println("Both you and the Dealer are over, the game is a draw\n");
                        gameCompletedE = true;
                    }
                    
                    if(gameE.checkWin() == true)
                    {
                        System.out.println("Congratulations you have won!\n");
                        
                        transaction.win(table);
                        guest.win();
                        
                        System.out.println("$" + table + " has been added to your account. your new balance is $" + guest.getDollars());
                        System.out.println("Your win rate is now " + guest.getWinRate() + "\n");
                        
                        gameCompletedE = true;   
                    }
                    
                    if(gameE.checkUserCasinoDraw() == true)
                    {
                        System.out.println("Both you and the Dealer have drawn on this hand\n");
                        
                        gameCompletedE = true;
                    }
                    
                    if((gameE.checkLoss() == true) && (gameE.checkWin() == false) && (gameE.checkUserCasinoDraw() == false))
                    {
                        System.out.println("You have LOST, the dealer has won this hand\n");
                        
                        transaction.loss(table);
                        guest.loss();
                        
                        System.out.println("$" + table + " has been deducted from your account. Your new balance is $" + guest.getDollars());
                        System.out.println("Your win rate is now " + guest.getWinRate() + "\n");
                        
                        gameCompletedE = true;
                    }
                }
                
                if(guest.getDollars() < table)
                {
                    boolean invalidFundsE = false;
                    
                    while(invalidFundsE != true)
                    {
                        System.out.println("\nYou do not currently have enough funds to get a new hand");
                        System.out.println("If you would like to deposit at least $" + (table - guest.getDollars()) + " to continue playing on the $" + table + " table");
                        System.out.println("press 'D', otherwise press 'X' to exit today.");
                        String continueFundsE = sc.nextLine();
                        
                        try
                        {
                            if(continueFundsE.trim().equalsIgnoreCase("d"))
                            {
                                boolean insertingFundsE = false;
                                
                                while(insertingFundsE != true)
                                {
                                    System.out.println("How much money would you like to deposit, to continue playing");
                                    String newFundsE = sc.nextLine();
                                    
                                    try
                                    {
                                        int newFundsEint = Integer.parseInt(newFundsE);
                                        
                                        if((newFundsEint > 0) && ((guest.getDollars() + newFundsEint) <= table ))
                                        {
                                            transaction.deposit(newFundsEint);
                                            System.out.println("$" + newFundsEint + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                                            System.out.println("You still need to deposit another $" + (table - guest.getDollars()) + " in order to play, and continue");
                                        }
                                        
                                        if((newFundsEint > 0) && ((guest.getDollars() + newFundsEint) >= table ))
                                        {
                                            transaction.deposit(newFundsEint);
                                            System.out.println("$" + newFundsEint + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                                            invalidFundsE = true;
                                            insertingFundsE = true;
                                        }

                                        if(newFundsEint < 0)
                                        {
                                            System.out.println("Please try again, we only accept whole positive amounts");
                                        }
                                    }
                                    catch(NumberFormatException e)
                                    {
                                        System.out.println("Please try again, we only accept whole positive amounts");
                                    }
                                }
                            }
                            
                            if(continueFundsE.trim().equalsIgnoreCase("x"))
                            {
                                guest.saveUser();
                                casino.saveCasino();
                                System.exit(0);
                            }
                        }
                        catch(Exception e)
                        {
                            System.out.println("Please enter a valid letter");
                        }
                    }
                }
                
                boolean playAgainInputE = false;
                
                while (playAgainInputE != true)
                {
                    System.out.println("To play again press 'Y' | to exit today press 'X' | to read the rules again press 'R'");
                    System.out.println("To deposit more money into your account press 'D' | to withdraw all of your winnings and exit press 'W'");
                    String playAgainE = sc.nextLine();
                    
                    try
                    {
                        if(playAgainE.trim().equalsIgnoreCase("y"))
                        {
                            playAgainInputE = true;
                        }
                        
                        if(playAgainE.trim().equalsIgnoreCase("x"))
                        {
                            guest.saveUser();
                            casino.saveCasino();
                            newHandE = true;
                            playAgainInputE = true;
                        }
                        
                        if(playAgainE.trim().equalsIgnoreCase("r"))
                        {
                            System.out.println("\nThe rules are as follows:");
                            System.out.println("There are two ways you can win the game.");
                            System.out.println("1. The value of your cards are higher than the dealers, and under 21");
                            System.out.println("2. The value of the dealers cards go above 21, whilst yours do not\n");
                            System.out.println("The sequence of events in the game are as follows:");
                            System.out.println("The dealer will deal one card to himselve and two cards to you.");
                            System.out.println("Based on the value of your cards and the dealers, you can choose to take('Hit') another card or stay('Stand') with the cards you have.");
                            System.out.println("After this, the dealer will deal himself more cards, until he feels the value of his cards are more then yours.");
                            System.out.println("The value of whoevers cards are greater at the end of the game, whilst being under 21\n");
                            System.out.println("Card Values:");
                            System.out.println("[A] = either 1 or 11 | [2] = 2 | [3] = 3 | [4] = 4 | [5] = 5 | [6] = 6 | [7] = 7 |");
                            System.out.println("[8] = 8 | [9] = 9 | [10] = 10 | [J] = 10 | [Q] = 10 | [K] = 10\n");
                        }
                        
                        if(playAgainE.trim().equalsIgnoreCase("d"))
                        {
                            boolean depositD = false;
                            
                            while(depositD != true)
                            {
                                System.out.println("How much would you like to deposit into your account?");
                                String depositInputD = sc.nextLine();
                                
                                try
                                {
                                    int depositInputDint = Integer.parseInt(depositInputD);
                                    
                                    if(depositInputDint > 0)
                                    {
                                        transaction.deposit(depositInputDint);
                                        System.out.println("$" + depositInputD + " has successfully been deposited into your account. Your current balance is $" + guest.getDollars() +"\n");
                                        depositD = true;
                                    }
                                    else
                                    {
                                        System.out.println("Please try again, we only accept whole positve amounts");
                                    }
                                }
                                catch(NumberFormatException e)
                                {
                                    System.out.println("Please try again, we only accept whole positve amounts");
                                }
                            }
                        }
                        
                        if(playAgainE.trim().equalsIgnoreCase("w"))
                        {
                            System.out.println("$" + guest.getDollars() + " has been withdrawn from your account, Your account balance is now $0");
                            
                            transaction.withdraw();
                            
                            guest.saveUser();
                            casino.saveCasino();
                            
                            newHandE = true;
                            playAgainInputE = true;
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println("Please enter a valid letter");
                    }
                }
            }
        }
    }
}
