package mo.essam.config;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.yaml.snakeyaml.Yaml;

import mo.essam.models.User;

public class YamlConfig {

	private List<String> keys = new ArrayList<String>();
	private List<String> values = new ArrayList<String>();
	private SessionFactory factory;

	public SessionFactory getFactory() {
		return factory;
	}

	public YamlConfig() {
		getConfiguration();
		applyConfiguration();
	}

	public void applyConfiguration() {
		Configuration configuration = new Configuration();
		configuration.addAnnotatedClass(User.class);
		for (int i = 0; i < keys.size(); i++)
			configuration.setProperty(keys.get(i), values.get(i));
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		factory = configuration.buildSessionFactory(builder.build());
	}

	public Map<String, String> getSampleConfiguration(String url) {
		Map<String, String> map = null;
		Yaml yaml = new Yaml();
		InputStream inputStream;
		try {
			inputStream = this.getClass().getClassLoader().getResourceAsStream(url);
			Map<String, String> obj = yaml.load(inputStream);
		} catch (Exception e) {
			System.out.println("Error in Get configuration from yaml file");
		}
		return map;
	}

	@SuppressWarnings("unused")
	public void getConfiguration() {
		getComplexConfiguration("hibernateConfiguration.yaml");
	}

	public String getKey(Node node) {
		String line = new String();
		if (node.getParent() == null)
			return node.getName();
		line = getKey(node.getParent()) + "." + node.getName();
		return line;
	}

	public void getProperties(Node node, Object value) {
		try {
			String tempVal = (String) value;
			String property = getKey(node);
			values.add(tempVal);
			keys.add(property);
			return;
		} catch (ClassCastException e) {
			System.out.println("Error in Casting");
		}

		Map<String, Object> map = (Map<String, Object>) value;
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry element = (Map.Entry) it.next();
			Node newNode = new Node(element.getKey().toString(), node);
			getProperties(newNode, element.getValue());
		}
	}

	public void getComplexConfiguration(String url) {
		Yaml yaml = new Yaml();

		try {
			InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(url);
			Map<String, Object> obj = yaml.load(inputStream);
			System.out.println(obj);
			Set set = obj.entrySet();
			Iterator it = set.iterator();
			Object temp = null;
			while (it.hasNext()) {
				Map.Entry element = (Map.Entry) it.next();
				Node node = new Node(element.getKey().toString(), null);
				getProperties(node, element.getValue());
				System.out.println("Values ==> " + values);
				System.out.println("keys ==> " + keys);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

class Node {
	private String name;
	private Node Parent;

	public Node(String name, Node parent) {
		super();
		this.name = name;
		Parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Node getParent() {
		return Parent;
	}

	public void setParent(Node parent) {
		Parent = parent;
	}
}