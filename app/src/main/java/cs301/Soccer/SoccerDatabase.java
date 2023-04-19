package cs301.Soccer;

import android.util.Log;
import cs301.Soccer.soccerPlayer.SoccerPlayer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Soccer player database -- presently, all dummied up
 *
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {


    // dummied up variable; you will need to change this
    private Hashtable<String, SoccerPlayer> database = new Hashtable<String, SoccerPlayer>();
    ;

    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName) {
        String key = firstName + " ## " + lastName;

        // if soccer player is in data base
        if (database.containsKey(key)) {
            return false;
        }

        // if soccer player isnt in data base
        else {
            SoccerPlayer playerNew = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
            database.put(key, playerNew);
            return true;
        }
    }

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String key = firstName + " ## " + lastName;

        // verify that player is in database
        if (!database.containsKey(key)) {
            return false;
        } else {
            database.remove(key);
            return true;
        }
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
    public SoccerPlayer getPlayer(String firstName, String lastName) {
        String key = firstName + " ## " + lastName;
        if (database.containsKey(key)) {
            return database.get(key);
        } else {
            return null;
        }
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String key = firstName + " ## " + lastName;
        if (database.containsKey(key)) {
            SoccerPlayer player = database.get(key);
            player.bumpGoals();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String key = firstName + " ## " + lastName;
        if (database.containsKey(key)) {
            SoccerPlayer player = database.get(key);
            player.bumpYellowCards();
            return true;
        } else {
            return false;
        }
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String key = firstName + " ## " + lastName;
        if (database.containsKey(key)) {
            SoccerPlayer player = database.get(key);
            player.bumpRedCards();
            return true;
        } else {
            return false;
        }
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
    public int numPlayers(String teamName) {
        int count = 0;
        if (teamName == null) {
            count = database.size();
        } else {
            for (String key : database.keySet()) {
                if (database.get(key).getTeamName().equals(teamName)) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerIndex(int, String)
     */
    // get the nTH player
    @Override
    public SoccerPlayer playerIndex(int idx, String teamName) {
        int numPlayers = 0;

        if (idx > database.size()) {
            return null;
        } else {
            for (String key : database.keySet()) {
                if (teamName == null) {
                    if (numPlayers == idx) {
                        return database.get(key);
                    }
                    numPlayers++;
                } else {
                    if (database.get(key).getTeamName().equals(teamName)) {
                        if (numPlayers == idx) {
                            return database.get(key);
                        }
                        numPlayers++;
                    }
                }
            }
        }
        return null;
    }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
    // read data from file
    @Override
    public boolean readData(File file) {
        Scanner scnr = null;
        try {
            scnr = new Scanner(file);
            while (scnr.hasNextLine()) {
                SoccerPlayer player = new SoccerPlayer(scnr.nextLine(), scnr.nextLine(),
                        Integer.parseInt(scnr.nextLine()), scnr.nextLine());
                int goals = Integer.parseInt(scnr.nextLine());
                int yellowCards = Integer.parseInt(scnr.nextLine());
                int redCards = Integer.parseInt(scnr.nextLine());

                for (int i = 0; i < goals; i++) {
                    bumpGoals(player.getFirstName(), player.getLastName());
                }
                for (int i = 0; i <= yellowCards; i++) {
                    bumpYellowCards(player.getFirstName(), player.getLastName());
                }
                for (int i = 0; i <= redCards; i++) {
                    bumpRedCards(player.getFirstName(), player.getLastName());
                }
                addPlayer(player.getFirstName(), player.getLastName(), player.getUniform(),
                        player.getTeamName());
            }
        }
        catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
    // write data to file
    @Override
    public boolean writeData(File file) {
        try(PrintWriter writer = new PrintWriter(file)) {
            for (String key : database.keySet()) {
                writer.println(logString("First Name: " + database.get(key).getFirstName()));
                writer.println(logString("Last Name: " + database.get(key).getLastName()));
                writer.println(logString("Team Name: " +database.get(key).getTeamName()));
                writer.println(logString("Uniform Number: " + Integer.toString(database.get(key).getUniform())));
                writer.println(logString("Goals: " + Integer.toString(database.get(key).getGoals())));
                writer.println(logString("Yellow Cards: " + Integer.toString(database.get(key).getYellowCards())));
                writer.println(logString("Red Cards: " + Integer.toString(database.get(key).getRedCards())));
                writer.println(logString(" "));
            }
            return true;
        }
        catch (FileNotFoundException e) {
            e. printStackTrace();
            return false;
        }
    }

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see cs301.Soccer.SoccerDB#getTeams()
     */
    // return list of teams
    @Override
    public HashSet<String> getTeams() {
        HashSet<String> teamNames = new HashSet<String>();
        for (String key : database.keySet()) {
            teamNames.add(database.get(key).getTeamName());
        }
        return teamNames;
    }

    /**
     * Helper method to empty the database and the list of teams in the spinner;
     * this is faster than restarting the app
     */
    public boolean clear() {
        if(database != null) {
            database.clear();
            return true;
        }
        return false;
    }
}
