package com.lifen.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Member")
@Component
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberID;
    private String membername;
    private String password;
    private String email;
    private String phone;
    
    @OneToOne(mappedBy = "member")
    @JsonIgnore
    private Thread thread;

	public int getMemberID() {
		return memberID;
	}

	public void setMemberID(int memberID) {
		this.memberID = memberID;
	}

	public String getMembername() {
		return membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}



	@Override
	public String toString() {
		return "Member [memberID=" + memberID + ", membername=" + membername + ", password=" + password + ", email="
				+ email + ", phone=" + phone + ", thread="  + "]";
	}

	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}
  
	



}
