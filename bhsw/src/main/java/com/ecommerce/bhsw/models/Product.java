package com.ecommerce.bhsw.models;


import jakarta.persistence.*;


@Entity
public class Product {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Long id;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
@Column(nullable=false)
private String name;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
private int price;
public int getPrice(){
	return price;
}
public void setPrice(int price){
	this.price=price;
}
private String category;
public String getCategory(){
	return category;
}
public void setCategory(String category){
	this.category=category;
}
private int size;
public int getSize(){
	return size;
}
public void setSize(int size){
	this.size=size;
}
private String description;
public String getDescription(){
	return description;
}
public void setDescription(String description){
	this.description=description;
}
private String color;
public String getColor(){
	return color;
}
public void setColor(String color){
	this.color=color;
}

@Column(nullable = false)
    private String imageUrl; // Field to store image URL

    // Getters and setters for all fields
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    @Column(nullable = false)
    private Long sellerId; // Reference to the seller
    
    public Long getSellerId() {
        return sellerId;
    }
    
    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
