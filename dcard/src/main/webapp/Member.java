package tw.team1.member.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "MEMBERS")
@Component
public class Member {

//    CREATE TABLE Members (
//            member_id INT IDENTITY(1,1) PRIMARY KEY,
//            user_name VARCHAR(50) UNIQUE NOT NULL,--登入帳號，唯一性
//            password VARCHAR(500) NOT NULL,--登入密碼
//            nick_name VARCHAR(50) NOT NULL,--會員名稱，可以重複
//            admin BIT DEFAULT 0,--是否為管理員，預設為false
//    		deleted BIT DEFAULT 0--是否標記刪除，預設為false

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MEMBERID")
	private int memberid;
	@Column(name = "USERNAME")
	private String username;
	@Column(name = "PASSWORD")
	private String password;
	@Column(name = "NICKNAME")
	private String nickname;
	@Column(name = "ADMIN")
	private boolean admin;
	@Column(name = "DELETED")
	private boolean deleted;

	// 空建構子
	public Member() {
	}

	// 帶參數的建構子

	public Member(String userName, String password, String nickName, boolean admin, boolean deleted) {
		this.username = userName;
		this.password = password;
		this.nickname = nickName;
		this.admin = admin;
		this.deleted = deleted;
	}

	// getter and setter
	
	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
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

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}