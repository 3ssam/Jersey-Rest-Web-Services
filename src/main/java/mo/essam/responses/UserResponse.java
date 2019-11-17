package mo.essam.responses;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import mo.essam.models.User;

@XmlRootElement
public class UserResponse {

	private int statuscode;
	private String message;
	private String descripation;
	private List<User> users;
	private User user;

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public int getStatuscode() {
		return statuscode;
	}

	public void setStatuscode(int statuscode) {
		this.statuscode = statuscode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescripation() {
		return descripation;
	}

	public void setDescripation(String descripation) {
		this.descripation = descripation;
	}

	public UserResponse(int statuscode, String message, String descripation) {
		super();
		this.statuscode = statuscode;
		this.message = message;
		this.descripation = descripation;
	}

	public UserResponse(int statuscode, String message, String descripation, List<User> users) {
		super();
		this.statuscode = statuscode;
		this.message = message;
		this.descripation = descripation;
		this.users = users;
	}

	public UserResponse(int statuscode, String message, String descripation, User user) {
		super();
		this.statuscode = statuscode;
		this.message = message;
		this.descripation = descripation;
		this.user = user;
	}

	public UserResponse() {
		// TODO Auto-generated constructor stub
	}

}
