package ttt.service;

import java.util.ArrayList;

import java.util.List;


import org.springframework.stereotype.Service;




@Service
public class MultiplayerList {
	
	
	List<String> pbord = new ArrayList<String>();
	
	public String hostname;
	public String joinname;
	
	public MultiplayerList(){
		hostname = "";
		joinname = "";
		for(int i=0;i<9;i++){
			pbord.add("_");
		}
	    //results = new LinkedList<DeferredResult<String>>();
	}
	
	public MultiplayerList(String hname, String jname){
		hostname = hname;
		joinname = jname;
		for(int i=0;i<9;i++){
			pbord.add("_");
		}
	}
	
	

}
