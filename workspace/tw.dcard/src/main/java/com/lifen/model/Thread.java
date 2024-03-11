package com.lifen.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "threads")
@Component
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int threadid;

    private int categoryid;
    
    private int memberid;

    private String title;
    private String content;
    private Date createdate;

//    @JsonIgnore
//    @OneToOne
//    @JoinColumn(name =  "memberid")
//    private Member member;
    
	public int getThreadid() {
		return threadid;
	}

	public void setThreadid(int threadid) {
		this.threadid = threadid;
	}

	public int getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(int categoryid) {
		this.categoryid = categoryid;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
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

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}



	@Override
	public String toString() {
		return "Thread [threadid=" + threadid + ", categoryid=" + categoryid + ", memberid=" + memberid + ", title="
				+ title + ", content=" + content + ", createdate=" + createdate +  "]";
	}

	public Thread() {
		super();
		// TODO Auto-generated constructor stub
	}


    
}

