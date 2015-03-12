package ttt.model.dao;




import java.util.List;

import javax.servlet.http.HttpSession;


import ttt.model.GamePlayers;
import ttt.model.Games;

import ttt.model.User;

public interface UserDao {
	User getUser( Integer id );

    List<User> getUsers();

    List<Games> getGamesAgainstAI(GamePlayers uname);

	List<Games> getSavedGames( GamePlayers gname );
        
    boolean validatelogin(String username,String password);
    
    GamePlayers addPlayer(GamePlayers player);
    
    void checkWin(HttpSession session);

	void pcplayer(HttpSession session);
	
	List<GamePlayers> getPlayers(String pname);
	
	Games SaveGame(Games game);
	
	GamePlayers getsingleplayer(String pname);
	
	List<Games> getAllGames(GamePlayers player);
	
	void addgame(List<Games> temp, int playerid);
	
	List<Games> getsgames(Integer id);
	
	void addsavedgame(List<Games> temp, int playerid);
	
	List<Games> getAllSavedGames(GamePlayers player);
	
	Games getGame(Integer id);
	
	
	
}