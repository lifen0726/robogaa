package com.lifen.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categories")
@Component
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryID;
    private String categoryname;
    private String description;

    // Constructor, getters, setters, and other methods...

    public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryname;
	}

	public void setCategoryName(String categoryName) {
		this.categoryname = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// 範例 constructor
    public Category(int categoryID, String categoryName, String description) {
        this.categoryID = categoryID;
        this.categoryname = categoryName;
        this.description = description;
    }

	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
}

