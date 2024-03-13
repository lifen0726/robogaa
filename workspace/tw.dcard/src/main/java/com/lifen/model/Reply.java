package com.lifen.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Replies")
@Component
public class Reply {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyid;
    private int threadid;
    private int memberid;
    private String content;
    private Date createdate;

    @OneToOne // 声明与Member的关联
    @JoinColumn(name = "memberid", referencedColumnName = "memberid", insertable = false, updatable = false) // 添加这行指定关联列和参考列
    private Member member; // 添加一个成员变量来持有关联的Member对象
    
    // Constructor, getters, setters, and other methods...
	public int getReplyid() {
		return replyid;
	}

	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}

	public int getMemberid() {
		return memberid;
	}

	public void setMemberid(int memberid) {
		this.memberid = memberid;
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

	public void setCreatedate(Date createDate) {
		this.createdate = createDate;
	}

	public int getThreadid() {
		return threadid;
	}

	public void setThreadid(int threadid) {
		this.threadid = threadid;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Reply [replyid=" + replyid + ", threadid=" + threadid + ", memberid=" + memberid + ", content="
				+ content + ", createdate=" + createdate + "]";
	}

	public Reply() {
		super();
		// TODO Auto-generated constructor stub
	}
}

