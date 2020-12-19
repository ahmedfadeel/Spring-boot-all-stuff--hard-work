package com.example.ahmed.model;

import java.io.Serializable;

import javax.persistence.	Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
/*   {java bean   validation   [https://www.baeldung.com/javax-validation] }
 *  
 * 
 * List<@NotBlank String> preferences;
   ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
   Validator validator = factory.getValidator();
 * Set<ConstraintViolation<User>> violations = validator.validate(user);
   for (ConstraintViolation<User> violation : violations) {
     log.error(violation.getMessage()); 
     
}  @Size(min = 10, max = 200, message 
      = "About Me must be between 10 and 200 characters")
 * 
 * @Lob
 * */
@Entity
@Table(name="dtos")
public class SimpleDto  implements Serializable {
public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

@Id	
@Min (value = 1, message = "Id can't be less than 1 or bigger than 999999")
@Max(999999)
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id ;

@NotEmpty
@Size(max=100)
private String name;

@NotNull
private boolean active;

@Email(message = "Email should be valid")
private String email;


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
