package com.example.ahmed.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Entity
@Table(name="dtos")
public class SimpleDto {
@Id	
@Min (value = 1, message = "Id can't be less than 1 or bigger than 999999")
@Max(999999)
private long id ;	
@NotEmpty
@Size(max=100)
private String name;

@NotNull
private boolean active;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public boolean isActive() {
	return active;
}

public void setActive(boolean active) {
	this.active = active;
}


}
