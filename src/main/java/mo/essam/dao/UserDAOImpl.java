package mo.essam.dao;

import java.util.List;

import org.hibernate.SessionFactory;

import mo.essam.config.YamlConfig;
import mo.essam.models.User;

public class UserDAOImpl implements UserDAO {

	private SessionFactory factory;

	public UserDAOImpl() {
		YamlConfig config = new YamlConfig();
		factory = config.getFactory();
		openTransaction();
	}

	private void openTransaction() {
		if (!factory.getCurrentSession().getTransaction().isActive())
			factory.getCurrentSession().beginTransaction();
	}

	@Override
	public User getUserById(int id) {
		return (User) factory.getCurrentSession().get(User.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUserByName(String name) {
		return factory.getCurrentSession().createQuery("from User where name = :param").setParameter("param", name)
				.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUserByAddress(String address) {
		return factory.getCurrentSession().createQuery("from User where address = :param")
				.setParameter("param", address).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUserByAge(int age) {
		return factory.getCurrentSession().createQuery("from User where age = :param").setParameter("param", age)
				.list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> getUsers() {
		return factory.getCurrentSession().createQuery("from User").list();
	}

	@Override
	public int createUser(User user) {
		factory.getCurrentSession().save(user);
		factory.getCurrentSession().getTransaction().commit();
		return user.getId();
	}

	@Override
	public User deleteById(int id) {
		User user = getUserById(id);
		if (user == null)
			return null;
		factory.getCurrentSession().delete(user);
		factory.getCurrentSession().getTransaction().commit();
		return user;
	}

	@Override
	public int deleteByName(String name) {
		int userCount = factory.getCurrentSession().createQuery("delete from User where name = :param")
				.setParameter("param", name).executeUpdate();
		factory.getCurrentSession().getTransaction().commit();
		return userCount;

	}

	@Override
	public int deleteByAddress(String address) {
		int userCount = factory.getCurrentSession().createQuery("delete from User where address = :param")
				.setParameter("param", address).executeUpdate();
		factory.getCurrentSession().getTransaction().commit();
		return userCount;
	}

	@Override
	public int deleteByAge(int age) {
		int userCount = factory.getCurrentSession().createQuery("delete from User where age = :param")
				.setParameter("param", age).executeUpdate();
		factory.getCurrentSession().getTransaction().commit();
		return userCount;
	}

	@Override
	public User updateUser(User user) {
		if (getUserById(user.getId()) == null)
			return null;
		factory.getCurrentSession()
				.createQuery("update from User set name = :param1" + ",age = :param2" + ",address = :param3"
						+ " where id = :param4")
				.setParameter("param1", user.getName()).setParameter("param2", user.getAge())
				.setParameter("param3", user.getAddress()).setParameter("param4", user.getId()).executeUpdate();
		factory.getCurrentSession().getTransaction().commit();
		return user;
	}

}
