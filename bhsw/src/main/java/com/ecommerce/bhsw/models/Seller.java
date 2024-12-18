package com.ecommerce.bhsw.models;

import jakarta.persistence.*;

@Entity
public class Seller {
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
@Column(nullable=false,unique=true)
private String phonenumber;
public String getPhonenumber() {
	return phonenumber;
}
public void setPhonenumber(String phonenumber) {
	this.phonenumber = phonenumber;
}
@Column(nullable=false,unique=true)
private String email;
public String getemail() {
	return email;
}
public void setemail(String email) {
	this.email = email;
}
private String password;
public String getpassword() {
	return password;
}
public void setpassword(String password) {
	this.password = password;
}
private String storename;
public String getstorename() {
	return storename;
}
public void setstorename(String storename) {
	this.storename = storename;
}

private String Address;
public String getAddress(){
    return Address;
}
public void setAddress(String Address){
    this.Address=Address;
}

}
