package com.lifen.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Reply")
@Component
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyID;
    private int threadID;
    private int userID;
    private String content;
    private Date createDate;

    // Constructor, getters, setters, and other methods...

    public int getReplyID() {
		return replyID;
	}

	public void setReplyID(int replyID) {
		this.replyID = replyID;
	}

	public int getThreadID() {
		return threadID;
	}

	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	// 範例 constructor
    public Reply(int replyID, int threadID, int userID, String content, Date createDate) {
        this.replyID = replyID;
        this.threadID = threadID;
        this.userID = userID;
        this.content = content;
        this.createDate = createDate;
    }

	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
}

