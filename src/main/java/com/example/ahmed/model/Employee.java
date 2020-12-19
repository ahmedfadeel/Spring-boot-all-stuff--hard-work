package com.example.ahmed.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/*   {thorben-janssen good name in jpa and hibernate }       {many to many association bitween employees and  my dtos }
 *     Employee-ID= id ,  SimpleDto-ID= id  
 *     make  third relation  Employee_Dto  which has (int payment)
 *   
 *   mdel associations as  util.set  
 *     use lazy fetch type 
 *     Apply query specific fetching 
 *   
 *    @ManyToMany(mappedBy = "authors")
    private Set<Book> books = new HashSet<Book>();
 
    ...
     
    public void addBook(Book book) {
        this.books.add(book);
        book.getAuthors().add(this);
    }
 
    public void removeBook(Book book) {
        this.books.remove(book);
        book.getAuthors().remove(this);
    }
    
 * */
@Entity
@Table(name="employees")
public class Employee implements Serializable{
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
@Column(name="first_name")
private String firstName;
@Column(name="last_name")
private String lastName;
@Column (name="email_id")
private String email;

public Employee() {
	 
}
public Employee(long id, String firstName, String lastName, String email) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	email = email;
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getFirstName() {
	return firstName;
}
public void setFirstName(String firstName) {
	this.firstName = firstName;
}
public String getLastName() {
	return lastName;
}
public void setLastName(String lastName) {
	this.lastName = lastName;
}
public String getEmail() {
	return this.email;
}
public void setEmail(String email) {
	this.email = email;
}

}
