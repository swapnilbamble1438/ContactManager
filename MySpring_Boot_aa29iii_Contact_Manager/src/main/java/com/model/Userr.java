package com.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;



@Entity
@Table(name="Userr")
public class Userr {
	
	@Id
	@GeneratedValue
	(strategy = GenerationType.AUTO)
	private int uid;
	
	@NotBlank(message = "Name field is required !")
	@Size(min=2,max=20,message="min 2 and max 20 characters are allowed")
	private String uname;
	private String upassword;
	@Column(unique = true)
	private String uemail;
	private String uimage;
	@Column(length = 500)
	private String uabout;
	private String urole;
	private boolean uenabled;
	
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy="userr")
	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true,mappedBy="userr")
	private List<Contact> ucontacts = new ArrayList<>();
	
	public Userr() {

	}
	

	public Userr(int uid, String uname, String upassword, String uemail, String uimage, String uabout, String urole,
			boolean uenabled, List<Contact> ucontacts) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upassword = upassword;
		this.uemail = uemail;
		this.uimage = uimage;
		this.uabout = uabout;
		this.urole = urole;
		this.uenabled = uenabled;
		this.ucontacts = ucontacts;
	}


	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getUemail() {
		return uemail;
	}

	public void setUemail(String uemail) {
		this.uemail = uemail;
	}

	public String getUimage() {
		return uimage;
	}

	public void setUimage(String uimage) {
		this.uimage = uimage;
	}

	public String getUabout() {
		return uabout;
	}

	public void setUabout(String uabout) {
		this.uabout = uabout;
	}

	public String getUrole() {
		return urole;
	}

	public void setUrole(String urole) {
		this.urole = urole;
	}

	public boolean getUenabled() {
		return uenabled;
	}

	public void setUenabled(boolean uenabled) {
		this.uenabled = uenabled;
	}

	public List<Contact> getUcontacts() {
		return ucontacts;
	}

	public void setUcontacts(List<Contact> ucontacts) {
		this.ucontacts = ucontacts;
	}


	@Override
	public String toString() {
		return "Userr [uid=" + uid + ", uname=" + uname + ", upassword=" + upassword + ", uemail=" + uemail
				+ ", uimage=" + uimage + ", uabout=" + uabout + ", urole=" + urole + ", uenabled=" + uenabled
				+ ", ucontacts=" + ucontacts + "]";
	}

	
	
	
	
	
	

}
