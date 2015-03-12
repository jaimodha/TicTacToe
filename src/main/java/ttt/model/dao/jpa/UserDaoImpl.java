package ttt.model.dao.jpa;






import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpSession;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ttt.model.GamePlayers;
import ttt.model.Games;
import ttt.model.User;
import ttt.model.dao.UserDao;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public User getUser( Integer id )
    {
        return entityManager.find( User.class, id );
    }

    @Override
    public List<User> getUsers()
    {
        return entityManager.createQuery( "from User order by id", User.class )
            .getResultList();
    }
    

  
   
    
    

	@Override
	public boolean validatelogin(String username, String password) {
		boolean check = false;
		try{
			GamePlayers player = entityManager.createQuery("from GamePlayers where username=:username and password=:password",GamePlayers.class).setParameter("username", username)
	    			.setParameter("password", password)
	    			.getSingleResult();
			if(player!=null){
				check = true;
			}
		
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
		
		return check;
	}

	
	@Override
	@Transactional
	public GamePlayers addPlayer(GamePlayers player) {
		return entityManager.merge( player );
	}

	@Override
	public void checkWin(HttpSession session) {
		Games g = (Games) session.getAttribute("g");
		int val1,val2,val3;
		String winner="";
    	
		for(int i=0;i<8;i++){
			val1 = g.winningcombo[i][0];
			val2 = g.winningcombo[i][1];
			val3 = g.winningcombo[i][2];

			if(g.pboard[val1].equalsIgnoreCase(g.pboard[val2]) && g.pboard[val2].equalsIgnoreCase(g.pboard[val3]) && !g.pboard[val1].equalsIgnoreCase("_")){
				winner = g.pboard[val1];
				g.flag = false;
				session.setAttribute("winner", winner);
			}
			
		}
		
		
		
		
		
	}
	
	@Override
	public void pcplayer(HttpSession session) {
		Games g = (Games) session.getAttribute("g");
		Random r = new Random();
    	int num;
    	if(g.pboard[0].equals("o") && g.pboard[1].equals("o") && g.pboard[2].equals("_") ){
    		g.pboard[2] = "o";
    	}else if(g.pboard[0].equals("o") && g.pboard[1].equals("_") && g.pboard[2].equals("o")){
    		g.pboard[1] = "o";
    	}else if(g.pboard[0].equals("_") && g.pboard[1].equals("o") && g.pboard[2].equals("o")){
    		g.pboard[0] = "o";
    	}else if(g.pboard[3].equals("o") && g.pboard[4].equals("o") && g.pboard[5].equals("_")){
    		g.pboard[5] = "o";
    	}else if(g.pboard[3].equals("o") && g.pboard[4].equals("_") && g.pboard[5].equals("o")){
    		g.pboard[4] = "o";
    	}else if(g.pboard[3].equals("_") && g.pboard[4].equals("o") && g.pboard[5].equals("o")){
    		g.pboard[3] = "o";
    	}else if(g.pboard[6].equals("o") && g.pboard[7].equals("o") && g.pboard[8].equals("_") ){
    		g.pboard[8] = "o";
    	}else if(g.pboard[6].equals("o") && g.pboard[7].equals("_") && g.pboard[8].equals("o")){
    		g.pboard[7] = "o";
    	}else if(g.pboard[6].equals("_") && g.pboard[7].equals("o") && g.pboard[8].equals("o")){
    		g.pboard[6] = "o";
    	}else if(g.pboard[0].equals("o") && g.pboard[3].equals("o") && g.pboard[6].equals("_") ){
    		g.pboard[6] = "o";
    	}else if(g.pboard[0].equals("o") && g.pboard[3].equals("_") && g.pboard[6].equals("o")){
    		g.pboard[3] = "o";
    	}else if(g.pboard[0].equals("_") && g.pboard[3].equals("o") && g.pboard[6].equals("o")){
    		g.pboard[0] = "o";
    	}else if(g.pboard[1].equals("o") && g.pboard[4].equals("o") && g.pboard[7].equals("_") ){
    		g.pboard[7] = "o";
    	}else if(g.pboard[1].equals("o") && g.pboard[4].equals("_") && g.pboard[7].equals("o")){
    		g.pboard[4] = "o";
    	}else if(g.pboard[1].equals("_") && g.pboard[4].equals("o") && g.pboard[7].equals("o")){
    		g.pboard[1] = "o";
    	}else if(g.pboard[2].equals("o") && g.pboard[5].equals("o") && g.pboard[8].equals("_") ){
    		g.pboard[8] = "o";
    	}else if(g.pboard[2].equals("o") && g.pboard[5].equals("_") && g.pboard[8].equals("o")){
    		g.pboard[5] = "o";
    	}else if(g.pboard[2].equals("_") && g.pboard[5].equals("o") && g.pboard[8].equals("o")){
    		g.pboard[2] = "o";
    	}else if(g.pboard[0].equals("o") && g.pboard[4].equals("o") && g.pboard[8].equals("_") ){
    		g.pboard[8] = "o";
    	}else if(g.pboard[0].equals("o") && g.pboard[4].equals("_") && g.pboard[8].equals("o")){
    		g.pboard[4] = "o";
    	}else if(g.pboard[0].equals("_") && g.pboard[4].equals("o") && g.pboard[8].equals("o")){
    		g.pboard[0] = "o";
    	}else if(g.pboard[2].equals("o") && g.pboard[4].equals("o") && g.pboard[6].equals("_") ){
    		g.pboard[6] = "o";
    	} else if(g.pboard[2].equals("o") && g.pboard[4].equals("_") && g.pboard[6].equals("o")){
    		g.pboard[4] = "o";
    	}else if(g.pboard[2].equals("_") && g.pboard[4].equals("o") && g.pboard[6].equals("o")){
    		g.pboard[2] = "o";
    	}else if(g.pboard[0].equals("x") && g.pboard[1].equals("x") && g.pboard[2].equals("_") ){
    		g.pboard[2] = "o";
    	}else if(g.pboard[0].equals("x") && g.pboard[1].equals("_") && g.pboard[2].equals("x")){
    		g.pboard[1] = "o";
    	}else if(g.pboard[0].equals("_") && g.pboard[1].equals("x") && g.pboard[2].equals("x")){
    		g.pboard[0] = "o";
    	}else if(g.pboard[3].equals("x") && g.pboard[4].equals("x") && g.pboard[5].equals("_")){
    		g.pboard[5] = "o";
    	}else if(g.pboard[3].equals("x") && g.pboard[4].equals("_") && g.pboard[5].equals("x")){
    		g.pboard[4] = "o";
    	}else if(g.pboard[3].equals("_") && g.pboard[4].equals("x") && g.pboard[5].equals("x")){
    		g.pboard[3] = "o";
    	}else if(g.pboard[6].equals("x") && g.pboard[7].equals("x") && g.pboard[8].equals("_") ){
    		g.pboard[8] = "o";
    	}else if(g.pboard[6].equals("x") && g.pboard[7].equals("_") && g.pboard[8].equals("x")){
    		g.pboard[7] = "o";
    	}else if(g.pboard[6].equals("_") && g.pboard[7].equals("x") && g.pboard[8].equals("x")){
    		g.pboard[6] = "o";
    	}else if(g.pboard[0].equals("x") && g.pboard[3].equals("x") && g.pboard[6].equals("_") ){
    		g.pboard[6] = "o";
    	}else if(g.pboard[0].equals("x") && g.pboard[3].equals("_") && g.pboard[6].equals("x")){
    		g.pboard[3] = "o";
    	}else if(g.pboard[0].equals("_") && g.pboard[3].equals("x") && g.pboard[6].equals("x")){
    		g.pboard[0] = "o";
    	}else if(g.pboard[1].equals("x") && g.pboard[4].equals("x") && g.pboard[7].equals("_") ){
    		g.pboard[7] = "o";
    	}else if(g.pboard[1].equals("x") && g.pboard[4].equals("_") && g.pboard[7].equals("x")){
    		g.pboard[4] = "o";
    	}else if(g.pboard[1].equals("_") && g.pboard[4].equals("x") && g.pboard[7].equals("x")){
    		g.pboard[1] = "o";
    	}else if(g.pboard[2].equals("x") && g.pboard[5].equals("x") && g.pboard[8].equals("_") ){
    		g.pboard[8] = "o";
    	}else if(g.pboard[2].equals("x") && g.pboard[5].equals("_") && g.pboard[8].equals("x")){
    		g.pboard[5] = "o";
    	}else if(g.pboard[2].equals("_") && g.pboard[5].equals("x") && g.pboard[8].equals("x")){
    		g.pboard[2] = "o";
    	}else if(g.pboard[0].equals("x") && g.pboard[4].equals("x") && g.pboard[8].equals("_") ){
    		g.pboard[8] = "o";
    	}else if(g.pboard[0].equals("x") && g.pboard[4].equals("_") && g.pboard[8].equals("x")){
    		g.pboard[4] = "o";
    	}else if(g.pboard[0].equals("_") && g.pboard[4].equals("x") && g.pboard[8].equals("x")){
    		g.pboard[0] = "o";
    	}else if(g.pboard[2].equals("x") && g.pboard[4].equals("x") && g.pboard[6].equals("_") ){
    		g.pboard[6] = "o";
    	} else if(g.pboard[2].equals("x") && g.pboard[4].equals("_") && g.pboard[6].equals("x")){
    		g.pboard[4] = "o";
    	}else if(g.pboard[2].equals("_") && g.pboard[4].equals("x") && g.pboard[6].equals("x")){
    		g.pboard[2] = "o";
    	}else {
    		do{
				num = r.nextInt(9-0) + 0;
			}while(!g.pboard[num].equalsIgnoreCase("_"));
    		g.pboard[num] = "o";
			
    	}

    	
	}

	@Override
	public List<GamePlayers> getPlayers(String pname) {
		return entityManager.createQuery("from GamePlayers where username = :username", GamePlayers.class).setParameter("username", pname).getResultList();
	}

	@Override
	@Transactional
	public Games SaveGame(Games game) {
		return entityManager.merge(game);
	}

	@Override
	public GamePlayers getsingleplayer(String pname) {
		return entityManager.createQuery("from GamePlayers where username = :username", GamePlayers.class).setParameter("username", pname).getSingleResult();
	}

	@Override
	public List<Games> getAllGames(GamePlayers player) {
		return entityManager.createQuery("from Games where player1=:gameplayer or Player2=:gameplay", Games.class).setParameter("gameplayer", player).setParameter("gameplay", player).getResultList();
	}

	@Override
	@Transactional
	public void addgame(List<Games> temp, int playerid) {
		GamePlayers player =new GamePlayers();
		player = entityManager.find(GamePlayers.class, playerid);
		player.setPlayedgames(temp);
		entityManager.merge(player);
		
	}

	@Override
	public List<Games> getsgames(Integer id) {
		
		return entityManager.createQuery("from Games where player1_id=:id order by id desc", Games.class).setParameter("id", id).getResultList();
	}

	@Override
	public List<Games> getGamesAgainstAI(GamePlayers uname) {
		List<Games> game = new ArrayList<Games>();
       	List<Games> g = uname.getPlayedgames();
    	int wcount = 0;
    	int lcount = 0;
    	for(int i=0; i<g.size();i++){
    		if(g.get(i).isAi()){
    			if(g.get(i).getStatus()!=null){
    				if(g.get(i).getStatus().equals("Won")){
    					wcount+=1;
    					game.add(g.get(i));
    				}
    				if(g.get(i).getStatus().equals("Lost")){
    					lcount+=1;
    					game.add(g.get(i));
    				}
    			}
    		}
    	}
    	return game;
	}

	@Override
	public List<Games> getSavedGames(GamePlayers gname) {
				List<Games> sGames = new ArrayList<Games>();
				List<Games> sEntry = gname.getSavedgames();
		    	int gameCount = 0;
		    	
		    	for(int i=0; i<sEntry.size();i++){
		    		if(sEntry.get(i).getStatus().equals("Incomplete")){
		    			gameCount+=1;
		    			sGames.add(sEntry.get(i));
		    		}
		    	}
		    	return sGames;
	}

	@Override
	@Transactional
	public void addsavedgame(List<Games> temp, int playerid) {
		GamePlayers player =new GamePlayers();
		player = entityManager.find(GamePlayers.class, playerid);
		player.setSavedgames(temp);
		entityManager.merge(player);
		
	}

	@Override
	@PreAuthorize ("principal.username == #player.username")
	public List<Games> getAllSavedGames(GamePlayers player) {
		return entityManager.createQuery("from Games where player1=:gameplayer and status=:stat", Games.class).setParameter("gameplayer", player).setParameter("stat", "Saved").getResultList();
	}

	@Override
	public Games getGame(Integer id) {
		return entityManager.createQuery("from Games where id=:id", Games.class).setParameter("id", id).getSingleResult();
	}
	

}