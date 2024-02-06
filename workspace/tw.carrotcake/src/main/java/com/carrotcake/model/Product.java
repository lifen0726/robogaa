package com.carrotcake.model;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Product")
@Component
public class Product {
    // 私有属性
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private String Pid; // 商品ID
    private String name; // 商品名称
    private int price; // 商品价格
    private String quantity; // 商品數量
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT +8")
    private Date date;
    private String note;

    // 无参构造函数
    public Product() {
    }

    // 带所有参数的构造函数
    public Product(String Pid, String name, int price, String quantity, Date date, String note) {
		super();
		this.Pid = Pid;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.date = date;
		this.note = note;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	// getter和setter方法
    public String getPId() {
        return Pid;
    }

    public void setPId(String Pid) {
        this.Pid = Pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    // toString方法，用于返回商品的字符串表示，方便打印
    @Override
    public String toString() {
        return "Product{" +
                "Pid='" + Pid + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}

