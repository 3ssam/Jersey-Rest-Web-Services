package mo.essam.dao;

import java.util.List;

import mo.essam.models.User;

public interface UserDAO {
	public User getUserById(int id);

	public List<User> getUserByName(String name);

	public List<User> getUserByAddress(String address);

	public List<User> getUserByAge(int age);

	public List<User> getUsers();

	public int createUser(User user);

	public User deleteById(int id);

	public int deleteByName(String name);

	public int deleteByAddress(String address);

	public int deleteByAge(int age);

	public User updateUser(User user);
}
