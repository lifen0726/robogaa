package com.lifen.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Thread")
@Component
public class Thread {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int threadID;
    private int categoryID;
    private int userID;
    private String title;
    private String content;
    private Date createDate;

    // Constructor, getters, setters, and other methods...

    // 範例 constructor
    public Thread(int threadID, int categoryID, int userID, String title, String content, Date createDate) {
        this.threadID = threadID;
        this.categoryID = categoryID;
        this.userID = userID;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
    }

	public int getThreadID() {
		return threadID;
	}

	public void setThreadID(int threadID) {
		this.threadID = threadID;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
}
