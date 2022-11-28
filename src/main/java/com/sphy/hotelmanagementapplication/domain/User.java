package com.sphy.hotelmanagementapplication.domain;

import org.springframework.data.annotation.Transient;

import javax.persistence.*;

/***
 * crated by gp
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
public class User extends BaseEntity{


    private boolean emailVerify;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String hashedPassword;

    @Transient
    protected String password;

    public enum Role{
        CLIENT,ADMIN
    }
    @Enumerated(EnumType.STRING)
    protected Role role;

    public User() {
		     super();
	  }

    public User(Long id, boolean emailVerify, String username, String firstname, String lastname, String email, String password, Role role) {
        super(id);
        this.emailVerify = emailVerify;
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getId() {
		return super.getId();
	}

	public void setId(Long id) {
		super.setId(id);
	}


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isEmailVerify() {
        return emailVerify;
    }

    public void setEmailVerify(boolean emailVerify) {
        this.emailVerify = emailVerify;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

	@Override
	public boolean equals(Object o) {
		return super.equals(o);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}


    @Override
    public String toString() {
        return "User{" +
                "emailVerify=" + emailVerify +
                ", username='" + username + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", hashedPassword='" + hashedPassword + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }
}
