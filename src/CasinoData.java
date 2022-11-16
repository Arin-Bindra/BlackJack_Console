/*
 * Arin Bindra
 * 
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;


public class CasinoData {
    
    private ArrayList<String> pastUsers = new ArrayList();
    private ArrayList<Integer> pastWins = new ArrayList();
    private ArrayList<Integer> pastLosses = new ArrayList();
    private ArrayList<Integer> pastAccounts = new ArrayList();
    private int userI;
    private final String casinoDataFile = "./resources/casino_data.txt";
    private final String userDataFile = "./resources/user_data.txt";
    private final String userWinRateFile = "./resources/user_winrate.txt";
    private String user;
    
    public CasinoData()
    {
        this.readUserData();
    }
    
    //----------------------------------------------------------------
    /**
     * 
     * the read file function takes in string file name as input, and then reads
     * the file using a buffered reader, then returns the file an arraylist of all of the lines
     * 
     **/
    private ArrayList<String> readFile(String file)
    {
        ArrayList<String> lines = new ArrayList<String>();
        
        try
        {
            BufferedReader inStream = new BufferedReader(new FileReader(file));
           
            String line = null;
        
            while((line = inStream.readLine()) != null)
            {
                lines.add(line);
            }
            inStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
        
        catch(IOException e)
        {
            System.out.println("Error reading from file");
        }
        
        
        
        return lines;
    }
    
    //----------------------------------------------------------------
     /**
     * 
     * The read casino data function outputs the amount of money the casino has
     * from the casino_data file. The function uses the read file function and then
     * sorts through all of the characters to output the money int
     * 
     **/
    
    public int readCasinoData()
    {
        ArrayList<String> lines = readFile(this.casinoDataFile);
        
        int dashCount = 0;
        String account = "";
        int accountI;
         
        for(int i = 0; i < lines.get(0).length(); i++)
        {
            int ascii =(int) lines.get(0).charAt(i);
            
            if(ascii == 124)
            {
                dashCount++;
            }
            
            if((dashCount > 0) && (ascii != 124))
            {
                account = account + lines.get(0).charAt(i);
            }
        }
        
        accountI = Integer.parseInt(account);
        
        return accountI;
    }
    
    //----------------------------------------------------------------
     /**
     * 
     * The read user data function outputs the amount of money the casino has
     * from the user_data file. The function uses the read file function and then
     * sorts through all of the lines to create two array lists filled with the usernames
     * and amount of money in each account
     * 
     **/
    private void readUserData()
    {
        ArrayList<String> lines = readFile(this.userDataFile);
     
        for(int i = 0; i < lines.size(); i++)
        {
            String username = "";
            String account = "";
            int dashCount = 0;
            
            for(int j = 0; j < lines.get(i).length(); j++)
            {
                int ascii =(int) lines.get(i).charAt(j);
            
                if(ascii == 124)
                {
                    dashCount++;
                }
            
                if((dashCount == 0) && (ascii != 124))
                {
                    username = username + lines.get(i).charAt(j);
                }
            
                if((dashCount > 0) && (ascii != 124))
                {
                    account = account + lines.get(i).charAt(j);
                }
            }
            
            this.pastUsers.add(username);
            this.pastAccounts.add(Integer.parseInt(account));
        }
    }
    
    //----------------------------------------------------------------
     /**
     * 
     * The read user win rate function uses the read file function and then
     * sorts through all of the characters to create two array lists which save
     * the amount of wins and losses a user has had
     * 
     **/
    private void readUserWinRate()
    {
        ArrayList<String> lines = readFile(this.userWinRateFile);
     
        for(int i = 0; i < lines.size(); i++)
        {
            String win = "";
            String loss = "";
            int dashCount = 0;
            
            for(int j = 0; j < lines.get(i).length(); j++)
            {
                int ascii =(int) lines.get(i).charAt(j);
            
                if(ascii == 124)
                {
                    dashCount++;
                }
            
                if((dashCount == 1) && (ascii != 124))
                {
                    win = win + lines.get(i).charAt(j);
                }
            
                if((dashCount == 2) && (ascii != 124))
                {
                    loss = loss + lines.get(i).charAt(j);
                }
            }
            
            this.pastWins.add(Integer.parseInt(win));
            this.pastLosses.add(Integer.parseInt(loss));
        }
    }
    
    //----------------------------------------------------------------
     /**
     * 
     * The read check user function takes a username string as input and returns
     * a Boolean value "same", if the inputted username is found in the array list
     * past users.
     * 
     * The win rates of the previous users are also initiated at this time.
     * 
     **/
    public boolean checkUser(String username)
    {
        boolean same = false;
        
        for(int i = 0; i < this.pastUsers.size(); i++)
        {            
            if(this.pastUsers.get(i).equalsIgnoreCase(username))
            {
                this.userI = i;
                same = true;
            }
        }
        
        this.readUserWinRate();
        
        return same;
    }
    
    //----------------------------------------------------------------
    
    public int getUserAccount()
    {
        return this.pastAccounts.get(this.userI);
    }
    
    //----------------------------------------------------------------
    
    public int getUserWins()
    {
        return this.pastWins.get(this.userI);
    }
    
    //----------------------------------------------------------------
    
    public int getUserLosses()
    {
        return this.pastLosses.get(this.userI);
    }
    
    //----------------------------------------------------------------
     /**
     * 
     * Takes a users username, account, and Boolean if the user exists as an input
     * and writes to the user_data file a users name and account value.
     * 
     * The function replaces all of the data on the file and rewrites it with the
     * new user information, using previous users stored information.
     * 
     **/    
    public void writeUserData(String username, int account, boolean existing)
    {
        if(existing == true)
        {
            this.pastUsers.remove(this.userI);
            this.pastAccounts.remove(this.userI);
        }
        
        this.pastUsers.add(username);
        this.pastAccounts.add(account);

        PrintWriter outputStream = null;
 
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(this.userDataFile));
            
            for(int i = 0; i < this.pastUsers.size(); i++)
            {
                outputStream.println(this.pastUsers.get(i)+"|"+this.pastAccounts.get(i));
            }
            
            outputStream.flush();
            outputStream.close();
        }
        
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening user data file for saving");
        }
    }
    
    //----------------------------------------------------------------
     /**
     * 
     * The write user win rate function takes the win rate and loss rate of the
     * user, as well as a Boolean if it is an existing user as an input, and 
     * writes the information to the user win rate file.
     * 
     **/
    public void writeUserWinRate(int wins, int losses, boolean existing)
    {
        if(existing == true)
        {
            this.pastWins.remove(this.userI);
            this.pastLosses.remove(this.userI);
        }
        
        this.pastWins.add(wins);
        this.pastLosses.add(losses);
        
        PrintWriter outputStream = null;
        
        try
        {
            outputStream = new PrintWriter(new FileOutputStream(this.userWinRateFile));
            
            for(int i = 0; i < this.pastUsers.size(); i++)
            {
                outputStream.println(this.pastUsers.get(i)+"|"+this.pastWins.get(i)+"|"+this.pastLosses.get(i));
            }
            
            outputStream.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening user win rate file for saving");
        }
         
    }
    
    //----------------------------------------------------------------
     /**
     * 
     * The write casino function takes the updated amount stored in the
     * casino account and writes it to the casino data file.
     * 
     **/
    
    public void writeCasinoData(int account)
    {
        PrintWriter outputStream = null;
        
        try
        {
            
            outputStream = new PrintWriter(new FileOutputStream(this.casinoDataFile));
            
            outputStream.println("casino|"+account);
            
            outputStream.flush();
            outputStream.close();
        }
        
        catch(FileNotFoundException e)
        {
            System.out.println("Error opening casino data file for saving");
        }
    }
    
}
