package mo.essam.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import mo.essam.dao.UserDAO;
import mo.essam.dao.UserDAOImpl;
import mo.essam.models.User;
import mo.essam.responses.UserResponse;

@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
@Path("/users")
public class UserService {

	private UserDAO dao = new UserDAOImpl();

	@GET
	@Path("/{id}")
	public mo.essam.responses.UserResponse getUserById(@PathParam("id") String id) {
		int userId;
		try {
			userId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return new UserResponse(400, "Bad Request", "This is is incorrect");
		}
		User user = dao.getUserById(userId);
		if (user == null)
			return new UserResponse(404, "Not Found", "This id is incorrect");
		else
			return new UserResponse(200, "Successful request", "No errors are here", user);
	}

	@GET
	@Path("/name/{name}")
	public UserResponse getUserByName(@PathParam("name") String name) {
		List<User> users = dao.getUserByName(name);
		if (users.size() == 0)
			return new UserResponse(404, "Not Found", "This name is incorrect");
		else
			return new UserResponse(200, "Successful request", "No errors are here", users);
	}

	@GET
	@Path("/address/{address}")
	public UserResponse getUserByAddress(@PathParam("address") String address) {
		List<User> users = dao.getUserByAddress(address);
		if (users.size() == 0)
			return new UserResponse(404, "Not Found", "This address is incorrect");
		else
			return new UserResponse(200, "Successful request", "No errors are here", users);
	}

	@GET
	@Path("/age/{age}")
	public UserResponse getUserByAge(@PathParam("age") String age) {
		int userAge;
		try {
			userAge = Integer.parseInt(age);
		} catch (NumberFormatException e) {
			return new UserResponse(400, "Bad Request", "This age is incorrect");
		}
		List<User> users = dao.getUserByAge(userAge);
		if (users.size() == 0)
			return new UserResponse(404, "Not Found", "This age is incorrect");
		else
			return new UserResponse(200, "Successful request", "No errors are here", users);
	}

	@GET
	@Path("/")
	public UserResponse getUsers() {
		List<User> users = dao.getUsers();
		if (users.size() == 0)
			return new UserResponse(200, "Successful request", "No users in database");
		else
			return new UserResponse(200, "Successful request", "No errors are here", users);
	}

	@POST
	@Path("/")
	public UserResponse createUser(User user) {
		int id = dao.createUser(user);
		return new UserResponse(200, "Created Successfully", "you are create user its id is " + id);
	}

	@DELETE
	@Path("/{id}")
	public UserResponse deleteById(@PathParam("id") String id) {
		int userId;
		try {
			userId = Integer.parseInt(id);
		} catch (NumberFormatException e) {
			return new UserResponse(400, "Bad Request", "This age is incorrect");
		}
		User user = dao.deleteById(userId);
		if (user == null)
			return new UserResponse(404, "Not Found", "This id is incorrect");
		return new UserResponse(200, "Deleted Successfully", "you are delete user its id is " + id);
	}

	@DELETE
	@Path("/name/{name}")
	public UserResponse deleteByName(@PathParam("name") String name) {
		int countOfUser = dao.deleteByName(name);
		if (countOfUser == 0)
			return new UserResponse(404, "Not Found", "This name is incorrect");
		return new UserResponse(200, "Deleted Successfully",
				"You are delete " + countOfUser + " User with name " + name);
	}

	@DELETE
	@Path("/address/{address}")
	public UserResponse deleteByAddress(@PathParam("address") String address) {
		int countOfUser = dao.deleteByAddress(address);
		if (countOfUser == 0)
			return new UserResponse(404, "Not Found", "This address is incorrect");
		return new UserResponse(200, "Deleted Successfully",
				"You are delete " + countOfUser + " User with address " + address);
	}

	@DELETE
	@Path("/age/{age}")
	public UserResponse deleteByAge(@PathParam("age") String age) {
		int userAge;
		try {
			userAge = Integer.parseInt(age);
		} catch (NumberFormatException e) {
			return new UserResponse(400, "Bad Request", "This age is incorrect");
		}
		int countOfUser = dao.deleteByAge(userAge);
		if (countOfUser == 0)
			return new UserResponse(404, "Not Found", "This age is incorrect");
		return new UserResponse(200, "Deleted Successfully", "You are delete " + countOfUser + " User with age " + age);
	}

	@PUT
	@Path("/")
	public UserResponse updateUser(User user) {
		User newUser = dao.updateUser(user);
		System.out.println(newUser);
		if (newUser == null)
			return new UserResponse(404, "Not Found", "This user is incorrect");
		// factory.getCurrentSession().getTransaction().commit();
		return new UserResponse(200, "Successful Update", "you are update user with id " + newUser.getId(), newUser);
	}

}