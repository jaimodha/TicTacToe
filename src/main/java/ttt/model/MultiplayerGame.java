package ttt.model;

import java.util.Date;

public class MultiplayerGame {
	
	private String hostname;
	private String joinname;
	private boolean hturn = true;
	public String pboard[] = {"_","_","_","_","_","_","_","_","_"};
	public boolean flag = true;
	public boolean check = false;
	private Date starttime;
	private Date endtime;
	public String winner;
	public int count = 0;
	
	public MultiplayerGame(String hostname, String joinname, boolean hturn,
			String[] pboard, boolean flag, boolean check, Date starttime, String winner, int count, Date endtime) {
		super();
		this.hostname = hostname;
		this.joinname = joinname;
		this.hturn = hturn;
		this.pboard = pboard;
		this.flag = flag;
		this.check = check;
		this.starttime = starttime;
		this.winner = winner;
		this.count = count;
		this.endtime = endtime;
	}

	public int getCount() {
		return count;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public MultiplayerGame() {
		super();
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public String getJoinname() {
		return joinname;
	}

	public void setJoinname(String joinname) {
		this.joinname = joinname;
	}

	public boolean isHturn() {
		return hturn;
	}

	public void setHturn(boolean hturn) {
		this.hturn = hturn;
	}

	public String[] getPboard() {
		return pboard;
	}

	public void setPboard(String[] pboard) {
		this.pboard = pboard;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	

}
