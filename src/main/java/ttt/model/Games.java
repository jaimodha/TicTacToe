package ttt.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="Games")
public class Games {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(name="game_status")
	private String status;
	
	@Column(name="start_time")
	private Date starttime;
	@Column(name="end_time")
	private Date endtime;
	@Column(name="save_time")
	private Date savetime;
	
	@ManyToOne
	GamePlayers player1;
	@ManyToOne
	GamePlayers Player2;
	
	@Column(name="ai_check")
	private boolean isAi;
	
	@Column(name="is_completed")
	private boolean isCompleted;
	
	@ElementCollection
	@CollectionTable(name="bord_entries")
	@Column(name="board_entries")
	List<String> board;
	
	@Transient
	public String pboard[] = {"_","_","_","_","_","_","_","_","_"};
	@Transient
	public int[][] winningcombo = new int[][]{
			{0,1,2},
			{3,4,5},
			{6,7,8},
			{0,3,6},
			{1,4,7},
			{2,5,8},
			{0,4,8},
			{2,4,6}		
	};
	@Transient
	public int count = 0;
	@Transient
	public boolean flag = true;
	@Transient
	public boolean check = false;

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStarttime() {
		return starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Date getSavetime() {
		return savetime;
	}

	public void setSavetime(Date savetime) {
		this.savetime = savetime;
	}

	public GamePlayers getPlayer1() {
		return player1;
	}

	public void setPlayer1(GamePlayers player1) {
		this.player1 = player1;
	}

	public GamePlayers getPlayer2() {
		return Player2;
	}

	public void setPlayer2(GamePlayers player2) {
		Player2 = player2;
	}

	public boolean isAi() {
		return isAi;
	}

	public void setAi(boolean isAi) {
		this.isAi = isAi;
	}

	public List<String> getBoard() {
		return board;
	}

	public void setBoard(List<String> board) {
		this.board = board;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String[] getPboard() {
		return pboard;
	}

	public void setPboard(String[] pboard) {
		this.pboard = pboard;
	}

	public int[][] getWinningcombo() {
		return winningcombo;
	}

	public void setWinningcombo(int[][] winningcombo) {
		this.winningcombo = winningcombo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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
	
	
	
	
	

}
