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

    @Column(name = "memberid") // 添加这行以映射到数据库中的正确列名
    private int memberid;

    private String title;
    private String content;
    private Date createdate;

    @OneToOne // 声明与Member的关联
    @JoinColumn(name = "memberid", referencedColumnName = "memberid", insertable = false, updatable = false) // 添加这行指定关联列和参考列
    private Member member; // 添加一个成员变量来持有关联的Member对象

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Thread [threadid=" + threadid + ", categoryid=" + categoryid + ", memberid=" + memberid + ", title="
                + title + ", content=" + content + ", createdate=" + createdate + "]";
    }

    public Thread() {
        super();
    }
}

