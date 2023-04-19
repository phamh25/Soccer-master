package cs301.Soccer;

import cs301.Soccer.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.util.HashSet;

/**
 * A database of soccer players
 *
 * @author Vegdahl
 * @author Nuxoll
 *
 */
public interface SoccerDB {

    /**
     * Creates a player object and adds it to the database. Sets its
     * internal message state if the creation was not successful due
     * to the name already being there.
     *
     * @param firstName the player's first name
     * @param lastName the player's last name
     * @param uniformNumber the player's uniform number
     * @param teamName the name of the player's team
     */
    public boolean addPlayer(String firstName, String lastName,
                             int uniformNumber, String teamName);
    /**
     * Removes a player from the database. Sets its internal message
     * state if the creation was not successful due to the name not being
     * there.
     *
     * @param firstName the player's first name
     * @param lastName the player's last name
     */
    public boolean removePlayer(String firstName, String lastName);

    /**
     * Looks up a player in the database.
     *
     * @param firstName the player's first name
     * @param lastName the player's last name
     * @return the SoccerPlayer object with the given first and last name.  If
     * a player by that name is not there, it sets its internal message state
     * and returns null.
     */
    public SoccerPlayer getPlayer(String firstName, String lastName);

    /**
     * Increments the number of goals for a given player by one.
     *
     * @param firstName the player's first name
     * @param lastName the player's last name
     * @return true iff the operation was successful
     */
    public boolean bumpGoals(String firstName, String lastName);

    /**
     * Increments the number of yellow cards for a given player by one.
     *
     * @param firstName the player's first name
     * @param lastName the player's last name
     * @return true iff the operation was successful
     */
    public boolean bumpYellowCards(String firstName, String lastName);

    /**
     * Increments the number of red cards for a given player by one.
     *
     * @param firstName the player's first name
     * @param lastName the player's last name
     * @return true iff the operation was successful
     */
    public boolean bumpRedCards(String firstName, String lastName);

    /**
     * Returns the number of players on a given team.
     *
     * @param teamName the name of the team, or null if players on all
     *    teams are to be counted
     * @return the number of players on that team, or -1 if the team was
     *    not known to exist
     */
    public int numPlayers(String teamName);

    /**
     * Returns the idxTH player on the given team (according to some arbitrary
     * ordering).  The purpose is to be able to list all the team members by
     * giving successively larger numbers starting at 0.  If the team name
     * is null, returns the idxTH player in the entire database.
     *
     * @param idx the index
     * @param teamName the team name, or null if the entire database should be
     * used
     * @return the Soccer player object for that player, or null if that
     * player does not exist
     */
    public SoccerPlayer playerIndex(int idx, String teamName);

    /**
     * Reads data from a file that writeData has written.
     * @param file the object that denotes the file to read from
     * @return true iff the operation was successful
     */
    public boolean readData(File file);

    /**
     * Writes data to a file in a format that readData can read in order to
     * reconstruct the database.
     *
     * @param file the object that denotes the file to write to
     * @return true iff the operation was successful
     */
    public boolean writeData(File file);

    /**
     * Returns the set of teams for all the players in the database.
     *
     * @return the collective set of teams from players in the database.
     *
     */
    public HashSet<String>getTeams();

    /**
     * Removes all players from the database
     */
    public boolean clear();
}
