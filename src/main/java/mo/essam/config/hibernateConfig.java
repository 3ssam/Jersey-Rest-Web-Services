package mo.essam.config;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import mo.essam.models.User;

public class hibernateConfig {
	private static SessionFactory sessionFactory;

	static {

		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(User.class);

		configuration.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
		configuration.setProperty("hibernate.connection.password", "13579");
		configuration.setProperty("hibernate.connection.url", "jdbc:postgresql://127.0.0.1:8100/essamdb");
		configuration.setProperty("hibernate.connection.username", "postgres");
		configuration.setProperty("hibernate.current_session_context_class",
				"org.hibernate.context.internal.ThreadLocalSessionContext");
		configuration.setProperty("hibernate.proc.param_null_passing", "true");
		configuration.setProperty("hbm2ddl.auto", "update");
		configuration.setProperty("show_sql", "true");

		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		sessionFactory = configuration.buildSessionFactory(builder.build());
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
