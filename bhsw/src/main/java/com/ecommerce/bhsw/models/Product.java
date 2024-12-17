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


}
