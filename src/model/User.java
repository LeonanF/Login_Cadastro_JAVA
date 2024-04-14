package model;

public class User {
	
	private String name;
	private String address;
	private String phone;
	private String cpf;
	private String bloodtype;
	private String rhFactor;
	private String course;
	private String emergencyPhone;
	private String username;
	private String password;
	
	public User(String name,
	String address,
	String phone,
	String cpf,
	String bloodtype,
	String rhFactor,
	String course,
	String emergencyPhone,
	String username,
	String password
	) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.cpf = cpf;
		this.bloodtype = bloodtype;
		this.rhFactor = rhFactor;
		this.course = course;
		this.emergencyPhone = emergencyPhone;
		this.username = username;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	public String getCpf() {
		return cpf;
	}

	public String getBloodtype() {
		return bloodtype;
	}

	public String getRhFactor() {
		return rhFactor;
	}

	public String getCourse() {
		return course;
	}

	public String getEmergencyPhone() {
		return emergencyPhone;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setBloodtype(String bloodtype) {
		this.bloodtype = bloodtype;
	}

	public void setRhFactor(String rhFactor) {
		this.rhFactor = rhFactor;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}
	
	
	
}
