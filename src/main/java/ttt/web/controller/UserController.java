package ttt.web.controller;



import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ttt.model.GamePlayers;
import ttt.model.Games;
import ttt.model.MultiplayerGame;
import ttt.model.dao.UserDao;
import ttt.service.HostList;
import ttt.service.JoinList;
import ttt.service.MultiSer;
import ttt.service.MultiplayerList;

import org.springframework.web.context.request.async.DeferredResult;

@Controller
public class UserController {

    @Autowired
    private UserDao userDao;
    
    @Autowired
    HostList hlt;
    
    @Autowired
    JoinList jlt;
    
   
    
    @Autowired
    List<MultiplayerList> ml = new ArrayList<MultiplayerList>();
    
    @Autowired
    MultiSer ms;
    
    
    @RequestMapping("/users.html")
    public String users( ModelMap models )
    {
        models.put( "users", userDao.getUsers() );
        return "users";
    }
    
    
    @RequestMapping(value="/login.html",method = RequestMethod.GET)
    public String login(ModelMap models, HttpSession session){
    	models.put( "player", new GamePlayers() );
        return "login";
    }

    
    @RequestMapping(value="/login.html",method = RequestMethod.POST)
    public String login( @ModelAttribute("player") GamePlayers player,BindingResult bindingResult, HttpSession session){
    	if( userDao.validatelogin(player.getUsername(), player.getPassword())){
    	  Games g = new Games();
    	  session.setAttribute("username", player.getUsername());
    	  GamePlayers ppp = userDao.getsingleplayer(player.getUsername());
    	  session.setAttribute("id", ppp.getId());
    	  System.out.println(player.getId());
      	  session.setAttribute("g", g);
      	  return "redirect:/GameMenu.html";
        }
        else{
      	  return "login";
        }
    }
    @RequestMapping(value="/GameMenu.html",method = RequestMethod.GET)
    public String menu(HttpSession session){
    	//if(session.getAttribute("username")!=null){
    		Games g = new Games();
    		String winner = "";
			session.removeAttribute("count");
			session.setAttribute("winner", winner);
    		session.setAttribute("g", g);
    		String pname = (String)session.getAttribute("username");
			GamePlayers gp = userDao.getsingleplayer(pname);
			List<Games> temp = new ArrayList<Games>();
			temp = userDao.getAllSavedGames(gp);
			session.setAttribute("savedlist", temp);
			session.setAttribute("fflag", false);
			for(int i=0;i<ms.getMultiplayerGame().size();i++){
    			if(ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(pname) || ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(pname)){
    				ms.remove(ms.getMultiplayerGame().get(i));
    			}
    		}
			if(hlt.getUsernames().contains(pname)){
    			hlt.remove(pname);
    		}
    		if(jlt.getUsernames().contains(pname)){
    			jlt.remove(pname);
    		}
			return "GameMenu";
    	//}else
    		//return "redirect:/login.html";
    }
    
    
    
   
    
    @RequestMapping(value="/register.html",method = RequestMethod.GET)
    public String register(ModelMap models){
    	models.put( "player", new GamePlayers() );
        return "register";
    }
    
    
    @RequestMapping(value="/register.html",method = RequestMethod.POST)
    public String register( @ModelAttribute("player") GamePlayers player,BindingResult bindingResult){
    userDao.addPlayer(player);
    return "redirect:/login1.html";
    }
    
    @RequestMapping(value="/TicTacToe.html",method = RequestMethod.GET)
    public String game(@RequestParam(required = false) Integer id, HttpSession session,ModelMap models){
    	//if(session.getAttribute("username")!=null){
	    	Games g = (Games)session.getAttribute("g");
	    	Games ggg = new Games();
	    	
	    	if(g.check){
	    		
		    	int id11 = (int) session.getAttribute("id");
		    	ggg = userDao.getsgames(id11).get(0);
		    	session.setAttribute("ggg", ggg);
		    	
		    	if(g.pboard[id]=="_"){
		    		g.pboard[id]="x";
		    		g.count++;
		    		userDao.checkWin(session);
		    		if(g.flag && g.count<=7){
						userDao.pcplayer(session);
						g.count++;
					}
		    		userDao.checkWin(session);
		    	}
		    		
		    		if(g.count == 9){
		    			session.setAttribute("count", g.count);
		    			ggg.setEndtime(new Date());
		    			ggg.setCompleted(true);
		    			ggg.setStatus("Tie");
		    			userDao.SaveGame(ggg);
		    			}
		    		
		    		
		    		String winner = (String) session.getAttribute("winner");
			    	if(winner!=null && winner.equalsIgnoreCase("x")){
			    		System.out.println(winner);
						ggg.setEndtime(new Date());
						ggg.setCompleted(true);
						ggg.setStatus("PLAYER1_WINS");
						userDao.SaveGame(ggg);
					}
					if(winner!=null && winner.equalsIgnoreCase("o")){
						System.out.println(winner);
						ggg.setEndtime(new Date());
						ggg.setCompleted(true);
						ggg.setStatus("AI_WINS");
						userDao.SaveGame(ggg);
					}
		    		
	    	}else
	    	{
	    		String pname = (String)session.getAttribute("username");
	    		Games game1 = new Games();
	    		List<GamePlayers> pe = userDao.getPlayers(pname);
	    		for(int i=0;i<pe.size();i++)
	    			game1.setPlayer1(pe.get(i));
	    		game1.setStarttime(new Date());
	    		game1.setAi(true);
	    		userDao.SaveGame(game1);
	    		//GamePlayers gp = userDao.getsingleplayer(pname);

	    		/*List<Games> temp = new ArrayList<Games>();
	    		temp = userDao.getAllGames(gp);
	    		userDao.addgame(temp, gp.getId());*/
	    		g.check=true;
	    	}
	    	models.put("pboard",g.pboard);
	    	models.put("flag",g.flag);
	    	
	    	return "TicTacToe";	
    	//}else
    		//return "redirect:/login.html";
    }

  @RequestMapping(value="/logout.html",method = RequestMethod.GET)
    public String logout(HttpSession session){ 
	  Games ggg = new Games();
	  	int id11 = (int) session.getAttribute("id");
    	ggg = userDao.getsgames(id11).get(0);
    	if( !ggg.isCompleted() ){
    		ggg.setEndtime(new Date());
    		ggg.setCompleted(true);
			ggg.setStatus("AI_WINS");
			userDao.SaveGame(ggg);
    	}
	  	
	  	session.invalidate();
    	return "redirect:/j_spring_security_logout";
    }
   
    @RequestMapping(value="/newgame.html",method = RequestMethod.GET)    
    public String newgame(HttpSession session){
		//if(session.getAttribute("username")!=null){
			Games ggg = new Games();
		  	int id11 = (int) session.getAttribute("id");
	    	ggg = userDao.getsgames(id11).get(0);
	    	if(!ggg.isCompleted()){
	    		ggg.setEndtime(new Date());
	    		ggg.setCompleted(true);
				ggg.setStatus("AI_WINS");
				userDao.SaveGame(ggg);
	    	}
			
			
			Games g = new Games();
			String winner = "";
			session.removeAttribute("count");
			session.setAttribute("winner", winner);
			session.setAttribute("g", g);
			return "redirect:/TicTacToe.html";
		//}else
			//return "redirect:/login.html";
    }
    
    
    @RequestMapping(value="/savegame.html",method = RequestMethod.GET) 
    public String savegame(HttpSession session){
    	//if(session.getAttribute("username")!=null){
	    	String pname = (String)session.getAttribute("username");
	    	
	    	
	    	Games g = (Games)session.getAttribute("g");
	    	Games ggg = new Games();
	    	int id11 = (int) session.getAttribute("id");
	    	ggg = userDao.getsgames(id11).get(0);
	    	session.setAttribute("ggg", ggg);
	    	List<String> bord = new ArrayList<String>();
	    	for(int i=0;i<g.pboard.length;i++){
	    		bord.add(g.pboard[i]);
	    	}
	    	ggg.setSavetime(new Date());
	    	ggg.setCompleted(false);
	    	ggg.setStatus("Saved");
	    	ggg.setBoard(bord);
	    	userDao.SaveGame(ggg);
	    	GamePlayers gp = userDao.getsingleplayer(pname);
	    	List<Games> temp = new ArrayList<Games>();
			temp = userDao.getAllSavedGames(gp);
			userDao.addsavedgame(temp, gp.getId());
	    	
	    	Games newgame = new Games();
	    	String winner = "";
			session.removeAttribute("count");
			session.setAttribute("winner", winner);
			session.setAttribute("g", newgame);
	    	return "redirect:/GameMenu.html";
    	//}else
			//return "redirect:/login.html";
    }
    
    @RequestMapping(value="/resumegame.html",method = RequestMethod.GET)
    public String reusmegame(HttpSession session, @RequestParam(required = false) Integer id1,@RequestParam(required = false) Integer id, ModelMap models){
    	//if(session.getAttribute("username")!=null){
	    	String pname = (String)session.getAttribute("username");
			GamePlayers gp = userDao.getsingleplayer(pname);
			List<Games> temp = new ArrayList<Games>();
			
	    	if((boolean) session.getAttribute("fflag")){
	    		Games g = (Games)session.getAttribute("g");
	    		if(g.pboard[id].equalsIgnoreCase("_")){
		    		g.pboard[id]="x";
		    		g.count++;
		    		userDao.checkWin(session);
		    		if(g.flag && g.count<=7){
						userDao.pcplayer(session);
						g.count++;
					}
		    		userDao.checkWin(session);
		    	}
		    	
		    		if(g.count == 9){
		    			session.setAttribute("count", g.count);
		    			g.setEndtime(new Date());
		    			g.setSavetime(null);
		    			g.setCompleted(true);
		    			g.setStatus("Tie");
		    			g.getBoard().clear();
		    			userDao.SaveGame(g);
		    			temp = userDao.getAllSavedGames(gp);
		    			userDao.addsavedgame(temp, gp.getId());
		    			}
		    		
		    		
		    		
		    		String winner = (String) session.getAttribute("winner");
		    		System.out.println(winner);
			    	if(winner!=null && winner.equalsIgnoreCase("x")){
						g.setEndtime(new Date());
						g.setSavetime(null);
						g.setCompleted(true);
						g.setStatus("PLAYER1_WINS");
						g.getBoard().clear();
						userDao.SaveGame(g);
						temp = userDao.getAllSavedGames(gp);
		    			userDao.addsavedgame(temp, gp.getId());
		    			
					}
					if(winner!=null && winner.equalsIgnoreCase("o")){
						System.out.println(winner);
						g.setEndtime(new Date());
						g.setSavetime(null);
						g.setCompleted(true);
						g.setStatus("AI_WINS");
						g.getBoard().clear();
						userDao.SaveGame(g);
						temp = userDao.getAllSavedGames(gp);
		    			userDao.addsavedgame(temp, gp.getId());
		    			
					}
	    		
	    	}else{
				int c=0;
				session.setAttribute("fflag", true);
				Games gg = userDao.getGame(id1);
		    	for(int i=0;i<gg.getBoard().size();i++){
		    		gg.pboard[i] = gg.getBoard().get(i);
		    	}
		    	for(int j=0;j<gg.pboard.length;j++){
		    		if(gg.pboard[j].equalsIgnoreCase("x")||gg.pboard[j].equalsIgnoreCase("o")){
		    			c = c + 1;
		    		}
		    	}
		    	gg.setCount(c);
		    	session.setAttribute("g", gg);
			}
	    	Games g = (Games) session.getAttribute("g");
	    	models.put("pboard",g.pboard);
	    	models.put("flag",g.flag);
	    	return "resumegame";
    	//}else
			//return "redirect:/login.html";
    }
    
    @RequestMapping(value="/stats.html",method = RequestMethod.GET)
    public String history(HttpSession session, ModelMap models){
    	//if(session.getAttribute("username")!=null){
	    	String pname = (String)session.getAttribute("username");
	    	int completedcount = 0;
	    	int aicompletedcount = 0;
	    	int twopcount = 0;
	    	int aicount = 0;
	    	int vscount = 0;
	    	int aiwin = 0;
	    	int twopwin = 0;
	    	float rateai;
	    	float ratetwop;
	    	Calendar cal = Calendar.getInstance();
	    	int year = cal.get(Calendar.YEAR);
	    	int month = cal.get(Calendar.MONTH);
	    	List<String> duration = new ArrayList<String>();
	    	GamePlayers gp = userDao.getsingleplayer(pname);
			List<Games> temp = new ArrayList<Games>();
			List<Games> history = new ArrayList<Games>();
			temp = userDao.getAllGames(gp);
			
	
			for(int i=0;i<temp.size();i++){
				if(temp.get(i).isCompleted()){
	
				Calendar starttime = Calendar.getInstance();
	    		Calendar endtime = Calendar.getInstance();
				starttime.setTime(temp.get(i).getStarttime());
				endtime.setTime(temp.get(i).getEndtime());
				
				if(endtime.get(Calendar.YEAR)==year && endtime.get(Calendar.MONTH)==month) {
					history.add(temp.get(i));
				}
				long tempdur = endtime.getTimeInMillis() - starttime.getTimeInMillis();
				
				long day = tempdur / (24*60*60*1000);
				tempdur = tempdur - (day*24*60*60*1000);
				long hour = tempdur / (60*60*1000);
				tempdur = tempdur - (hour*60*60*1000);
				long minute = tempdur / (60*1000);
				tempdur = tempdur - (minute*60*1000);
				long sec = tempdur / 1000;
		
				String result = day+" Day, "+ hour +" Hours," + minute + " Minutes, " + sec + " Seconds";
				
				duration.add(result);
				}
				if(temp.get(i).isAi() && temp.get(i).isCompleted()){
					aicount = aicount + 1;
				}
				if(!temp.get(i).isAi() && temp.get(i).isCompleted()){
					vscount = vscount + 1;
				}
				
				if(temp.get(i).isCompleted()){
					completedcount = completedcount + 1;
				}
				if(temp.get(i).isAi() && temp.get(i).isCompleted()){
					aicompletedcount = aicompletedcount + 1;
				}
				
				if(temp.get(i).isAi() && temp.get(i).isCompleted() && temp.get(i).getStatus().equalsIgnoreCase("PLAYER1_WINS")){
					aiwin= aiwin + 1;
				}
			
				if(!temp.get(i).isAi() && temp.get(i).isCompleted()){
					twopcount = twopcount + 1;
				}
				
				if(!temp.get(i).isAi() && temp.get(i).isCompleted() && temp.get(i).getStatus().equalsIgnoreCase(pname)){
					twopwin  = twopwin + 1;
				}
				
			}
			rateai = (float) (aiwin * 100) / aicount;
			ratetwop = (float) (twopwin * 100) / 	vscount;
			models.put("ans1",completedcount);
			models.put("ans2",aicompletedcount);
			models.put("ans3",twopcount);
			if(aicount ==0){
			models.put("ans4",aicount);
			}else
			{
				models.put("ans4",rateai);
			}
			if(vscount == 0){
				models.put("ans5",vscount);
			}else{
			models.put("ans5",ratetwop);
			}
			models.put("ans6", history);
			models.put("duration", duration);
	    	return "stats";
    	//}else
			//return "redirect:/login.html";
    }
    
    @RequestMapping(value="/resavegame.html",method = RequestMethod.GET) 
    public String resave(HttpSession session){
    	//if(session.getAttribute("username")!=null){
	    	Games g = (Games)session.getAttribute("g");
	    	List<String> bord = new ArrayList<String>();
	    	for(int i=0;i<g.pboard.length;i++){
	    		bord.add(g.pboard[i]);
	    	}
	    	g.setSavetime(new Date());
	    	g.setCompleted(false);
	    	g.setStatus("Saved");
	    	g.setBoard(bord);
	    	userDao.SaveGame(g);
	    	
	    	Games newgame = new Games();
	    	String winner = "";
			session.removeAttribute("count");
			session.setAttribute("winner", winner);
			session.setAttribute("g", newgame);
	    	return "redirect:/GameMenu.html";
    	//}else
			//return "redirect:/login.html";
    }
    
    @RequestMapping(value="/relogout.html",method = RequestMethod.GET)
    public String relogout(HttpSession session){
    	Games ggg = (Games) session.getAttribute("g");
	  	
    	if( !ggg.isCompleted() ){
    		ggg.setEndtime(new Date());
    		ggg.setSavetime(null);
    		ggg.setCompleted(true);
			ggg.setStatus("AI_WINS");
			userDao.SaveGame(ggg);
    	}
	  	
	  	session.invalidate();
    	return "redirect:/j_spring_security_logout";
    }
    
    @RequestMapping(value="/menulogout.html",method = RequestMethod.GET)
    public String menulogout(HttpSession session){
    	session.invalidate();
    	return "redirect:/j_spring_security_logout";
    }
    
    @RequestMapping(value="/renewgame.html",method = RequestMethod.GET) 
    public String renew(HttpSession session){
    	//if(session.getAttribute("username")!=null){
			Games ggg = (Games) session.getAttribute("g");
		  	
	    	if(!ggg.isCompleted()){
	    		ggg.setEndtime(new Date());
	    		ggg.setSavetime(null);
	    		ggg.setCompleted(true);
				ggg.setStatus("AI_WINS");
				userDao.SaveGame(ggg);
	    	}
			
			
			Games g = new Games();
			String winner = "";
			session.removeAttribute("count");
			session.setAttribute("winner", winner);
			session.setAttribute("g", g);
			return "redirect:/TicTacToe.html";
		//}else
			//return "redirect:/login.html";
    }
    
    
    @RequestMapping("/hostlist.json")
    public String hlJson( ModelMap models )
    {
        models.put( "hostnames", hlt.getUsernames() );
        return "jsonView";
    }

    @RequestMapping("/hostlist.deferred.json")
    @ResponseBody
    public DeferredResult<String> hlDeferred()
    {
        DeferredResult<String> result = new DeferredResult<String>();
        hlt.addResult( result );
        return result;
    }
    
    
    @RequestMapping("/hostlist.html")
    public String hl(HttpSession session, ModelMap models)
    {
    	//if(session.getAttribute("username")!=null){
    		String pname = (String)session.getAttribute("username");
    		if(jlt.getUsernames().isEmpty()){
    			if(!hlt.getUsernames().contains(pname))
    			hlt.add(pname);
    			models.put("message", "Wating for another player to join the game");
    		}else{
    			String jlttemp = jlt.getUsernames().get(0);
    			//ml.add(new MultiplayerList(pname, jlttemp));
    			//hlt.remove(pname);
    			//jlt.remove(jlttemp);
    			MultiplayerGame g = new MultiplayerGame();
    			g.setHostname(pname);
    			g.setJoinname(jlttemp);
    			g.setStarttime(new Date());
    			ms.add(g);
    			hlt.remove(pname);
    			jlt.remove(jlttemp);
    		}
    		
    		return "TwoPlayer";
    	//}
    	//return "redirect:/login.html";
    }

    
    
    
    @RequestMapping("/joinlist.json")
    public String jlJson( ModelMap models )
    {
        models.put( "joinnames", jlt.getUsernames() );
        return "jsonView";
    }

    @RequestMapping("/joinlist.deferred.json")
    @ResponseBody
    public DeferredResult<String> jlDeferred()
    {
        DeferredResult<String> result = new DeferredResult<String>();
        jlt.addResult( result );
        return result;
    }
    
    
    @RequestMapping("/joinlist.html")
    public String jl(HttpSession session, ModelMap models)
    {
    	//if(session.getAttribute("username")!=null){
    		String pname = (String)session.getAttribute("username");
    		
    		if(hlt.getUsernames().isEmpty()){
    			if(!jlt.getUsernames().contains(pname))
    			jlt.add(pname);
    			models.put("message", "Wating for another player to host the game");
    		}else{
    			String hlttemp = hlt.getUsernames().get(0);
    			//ml.add(new MultiplayerList(hlttemp, pname));
    			//hlt.remove(hlttemp);
    			//jlt.remove(pname);
    			MultiplayerGame g = new MultiplayerGame();
    			g.setHostname(hlttemp);
    			g.setJoinname(pname);
    			g.setStarttime(new Date());
    			ms.add(g);
    			hlt.remove(hlttemp);
    			jlt.remove(pname);
    		}
    		return "TwoPlayer";
    	//}
    	//return "redirect:/login.html";
    }
    
    @RequestMapping("/remultimenu.html")
    public String remultimenu(HttpSession session)
    {
    	//if(session.getAttribute("username")!=null){
    		String pname = (String)session.getAttribute("username");
    		for(int i=0;i<ms.getMultiplayerGame().size();i++){
    			if(ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(pname) || ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(pname)){
    				ms.remove(ms.getMultiplayerGame().get(i));
    			}
    		}
    		if(hlt.getUsernames().contains(pname)){
    			hlt.remove(pname);
    		}
    		if(jlt.getUsernames().contains(pname)){
    			jlt.remove(pname);
    		}
    		return "redirect:/GameMenu.html";
    		//}
    		//return "redirect:/login.html";   
    }
    
    @RequestMapping("/mplogout.html")
    public String mplogout(HttpSession session)
    {
    	//if(session.getAttribute("username")!=null){
    		String pname = (String)session.getAttribute("username");
    		for(int i=0;i<ms.getMultiplayerGame().size();i++){
    			if(ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(pname) || ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(pname)){
    				ms.remove(ms.getMultiplayerGame().get(i));
    			}
    		}
    		if(hlt.getUsernames().contains(pname)){
    			hlt.remove(pname);
    		}
    		if(jlt.getUsernames().contains(pname)){
    			jlt.remove(pname);
    		}
    		session.invalidate();
    		return "redirect:/j_spring_security_logout";
    		//}
    		//return "redirect:/login.html";   
    }
    
    
    
    
    
    @RequestMapping("/TwoPlayer.json")
    public String twoplayerJson( ModelMap models )
    {
        models.put( "twopgame", ms.getMultiplayerGame() );
        return "jsonView";
    }

    @RequestMapping("/TwoPlayer.deferred.json")
    @ResponseBody
    public DeferredResult<String> twoplayerDeferred()
    {
        DeferredResult<String> result = new DeferredResult<String>();
        ms.addResult( result );
        return result;
    }
    
    @RequestMapping(value="/multicontrol.html", method = RequestMethod.GET)
    public String twopcontrol(HttpSession session, @RequestParam Integer id ){
    	//if(session.getAttribute("username")!=null){
    		int[][] winningcombo = new int[][]{
    				{0,1,2},
    				{3,4,5},
    				{6,7,8},
    				{0,3,6},
    				{1,4,7},
    				{2,5,8},
    				{0,4,8},
    				{2,4,6}		
    		};
    		String win = "";
    		String pname = (String)session.getAttribute("username");
    		for(int i=0;i<ms.getMultiplayerGame().size();i++){
    			if(ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(pname)){
    				if(ms.getMultiplayerGame().get(i).pboard[id].equalsIgnoreCase("_")){
    					ms.getMultiplayerGame().get(i).pboard[id] = "x";
    					ms.getMultiplayerGame().get(i).count = ms.getMultiplayerGame().get(i).count +1;
    					for(int i1=0;i1<8;i1++){
    						int val1 = winningcombo[i1][0];
    						int val2 = winningcombo[i1][1];
    						int val3 = winningcombo[i1][2];
    						if(ms.getMultiplayerGame().get(i).pboard[val1].equalsIgnoreCase(ms.getMultiplayerGame().get(i).pboard[val2]) && ms.getMultiplayerGame().get(i).pboard[val2].equalsIgnoreCase(ms.getMultiplayerGame().get(i).pboard[val3]) && !ms.getMultiplayerGame().get(i).pboard[val1].equalsIgnoreCase("_")){
    							win = ms.getMultiplayerGame().get(i).pboard[val1];
    						}
    					}
    					System.out.println(ms.getMultiplayerGame().get(i).winner);
    					ms.getMultiplayerGame().get(i).setHturn(false);
    				}
    			}
    			if(ms.getMultiplayerGame().get(i).getJoinname().equalsIgnoreCase(pname)){
    				if(ms.getMultiplayerGame().get(i).pboard[id].equalsIgnoreCase("_")){
    					ms.getMultiplayerGame().get(i).pboard[id] = "o";
    					ms.getMultiplayerGame().get(i).count = ms.getMultiplayerGame().get(i).count +1;
    					for(int i1=0;i1<8;i1++){
    						int val1 = winningcombo[i1][0];
    						int val2 = winningcombo[i1][1];
    						int val3 = winningcombo[i1][2];
    						if(ms.getMultiplayerGame().get(i).pboard[val1].equalsIgnoreCase(ms.getMultiplayerGame().get(i).pboard[val2]) && ms.getMultiplayerGame().get(i).pboard[val2].equalsIgnoreCase(ms.getMultiplayerGame().get(i).pboard[val3]) && !ms.getMultiplayerGame().get(i).pboard[val1].equalsIgnoreCase("_")){
    							win = ms.getMultiplayerGame().get(i).pboard[val1];
    							break;
    						}
    					}
    					System.out.println(ms.getMultiplayerGame().get(i).winner);
    					ms.getMultiplayerGame().get(i).setHturn(true);
    				}
    			}
    			
    			if(ms.getMultiplayerGame().get(i).getJoinname().equalsIgnoreCase(pname) || ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(pname)){
    			if(win.equalsIgnoreCase("x")){
    				ms.getMultiplayerGame().get(i).winner = ms.getMultiplayerGame().get(i).getHostname();
    				ms.getMultiplayerGame().get(i).setEndtime(new Date());
    			}
    			if(win.equalsIgnoreCase("o")){
    				ms.getMultiplayerGame().get(i).winner = ms.getMultiplayerGame().get(i).getJoinname();
    				ms.getMultiplayerGame().get(i).setEndtime(new Date());
    			}
    			
    			if(ms.getMultiplayerGame().get(i).count == 9){
    				ms.getMultiplayerGame().get(i).winner = "Tie";
    				ms.getMultiplayerGame().get(i).setEndtime(new Date());
    			}
    			}
    			if(ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(pname)){
    				if(ms.getMultiplayerGame().get(i).winner!=null){
    				Games g = new Games();
    				String temp = "";
    				GamePlayers p1 = userDao.getsingleplayer(ms.getMultiplayerGame().get(i).getHostname());
    				GamePlayers p2 = userDao.getsingleplayer(ms.getMultiplayerGame().get(i).getJoinname());
    				g.setPlayer1(p1);
    				g.setPlayer2(p2);
    				g.setAi(false);
    				g.setStarttime(ms.getMultiplayerGame().get(i).getStarttime());
    				g.setEndtime(ms.getMultiplayerGame().get(i).getEndtime());
    				if(ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(ms.getMultiplayerGame().get(i).winner)){
    					temp = ms.getMultiplayerGame().get(i).winner;
    				}
    				if(ms.getMultiplayerGame().get(i).getJoinname().equalsIgnoreCase(ms.getMultiplayerGame().get(i).winner)){
    					temp = ms.getMultiplayerGame().get(i).winner;
    				}
    				if(ms.getMultiplayerGame().get(i).winner.equalsIgnoreCase("tie")){
    					temp = "Tie";
    				}
    				g.setStatus(temp);
    				g.setCompleted(true);
    				userDao.SaveGame(g);
    				
    				}
    			}else if(ms.getMultiplayerGame().get(i).getJoinname().equalsIgnoreCase(pname)){
    				if(ms.getMultiplayerGame().get(i).winner!=null){
    				Games g = new Games();
    				String temp = "";
    				GamePlayers p1 = userDao.getsingleplayer(ms.getMultiplayerGame().get(i).getHostname());
    				GamePlayers p2 = userDao.getsingleplayer(ms.getMultiplayerGame().get(i).getJoinname());
    				g.setPlayer1(p1);
    				g.setPlayer2(p2);
    				g.setAi(false);
    				g.setStarttime(ms.getMultiplayerGame().get(i).getStarttime());
    				g.setEndtime(ms.getMultiplayerGame().get(i).getEndtime());
    				if(ms.getMultiplayerGame().get(i).getHostname().equalsIgnoreCase(ms.getMultiplayerGame().get(i).winner)){
    					temp = ms.getMultiplayerGame().get(i).winner;
    				}
    				if(ms.getMultiplayerGame().get(i).getJoinname().equalsIgnoreCase(ms.getMultiplayerGame().get(i).winner)){
    					temp = ms.getMultiplayerGame().get(i).winner;
    				}
    				if(ms.getMultiplayerGame().get(i).winner.equalsIgnoreCase("tie")){
    					temp = "Tie";
    				}
    				g.setStatus(temp);
    				g.setCompleted(true);
    				userDao.SaveGame(g);
    				
    				}
    			}
    		}
    		
    		ms.processDeferredResults();
    		return "TwoPlayer";
    	//}
    	//return "redirect:/login.html";
    }

    @RequestMapping(value="/result.html", method = RequestMethod.GET)
    public String result(){
    	return "result";
    }
    
    
    @RequestMapping(value="/welcome", method = RequestMethod.GET)
	public String printWelcome(ModelMap model,HttpSession session,Principal principal) {
 
		//UserEntry user = (UserEntry)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String name = principal.getName();
	
		//model.addAttribute("username", name);
		session.setAttribute("username", name);
		 Games g = new Games();
		GamePlayers ppp = userDao.getsingleplayer(name);
  	  session.setAttribute("id", ppp.getId());
  	  //System.out.println(player.getId());
    	  session.setAttribute("g", g);
    	  
		return "redirect:/GameMenu.html";
 
	}
 
	@RequestMapping(value="/login1", method = RequestMethod.GET)
	public String login(ModelMap model) {
 
		return "login1";
 
	}
	
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		model.addAttribute("error", "true");
		return "login1";
 
	}
	
	/*@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 
		return "login";
 
	}*/
	@RequestMapping(value="/ungame.html", method = RequestMethod.GET)
	public String gameun(HttpSession session){
		Games g = new Games();
		session.setAttribute("g", g);
		session.removeAttribute("count");
		session.removeAttribute("winner");
		return "redirect:/allgame.html";
	}
	
	@RequestMapping(value="/allgame.html", method = RequestMethod.GET)
	public String allgame(@RequestParam(required = false) Integer id, HttpSession session,ModelMap models){
		Games g = (Games)session.getAttribute("g");
    	if(g.check){
	    	if(g.pboard[id]=="_"){
	    		g.pboard[id]="x";
	    		g.count++;
	    		userDao.checkWin(session);
	    		if(g.flag && g.count<=7){
					userDao.pcplayer(session);
					g.count++;
				}
	    		userDao.checkWin(session);
	    		}
	    		
	    		if(g.count == 9){
	    			session.setAttribute("count", g.count);
	    		}
    	}else
    	{
    		
    		g.check=true;
    	}
    	models.put("pboard",g.pboard);
    	models.put("flag",g.flag);
    	
    	return "allgame";	

	}
    
	@RequestMapping(value="/back.html", method = RequestMethod.GET)
	public String back(HttpSession session){
		session.removeAttribute("g");
		session.removeAttribute("count");
		session.removeAttribute("winner");
		return "redirect:/login1.html";
	}
	

}
