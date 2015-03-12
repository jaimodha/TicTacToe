package ttt.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="GamePlayers")
public class GamePlayers {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Column(nullable=false,unique=true)
	private String username;
	@Column(nullable=false)
	private String password;
	@Column(name="email")
	private String email;
	
	@OneToMany
	@JoinTable(name = "played_games")
	List<Games> playedgames;
	
	@OneToMany
	@JoinTable(name = "saved_games")
	List<Games> savedgames;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Games> getPlayedgames() {
		return playedgames;
	}

	public void setPlayedgames(List<Games> playedgames) {
		this.playedgames = playedgames;
	}

	public List<Games> getSavedgames() {
		return savedgames;
	}

	public void setSavedgames(List<Games> savedgames) {
		this.savedgames = savedgames;
	}
	
}
