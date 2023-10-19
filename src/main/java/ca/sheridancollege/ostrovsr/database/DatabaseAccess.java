package ca.sheridancollege.ostrovsr.database;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ca.sheridancollege.ostrovsr.beans.ShoppingItem;

@Repository
public class DatabaseAccess {
	@Autowired
	protected NamedParameterJdbcTemplate jdbc;

	public ArrayList<ShoppingItem> retrieveItems() {
		String q = "SELECT id, name, description, price, link from my_purchases";
		ArrayList<ShoppingItem> calculations = (ArrayList<ShoppingItem>) jdbc.query(q,
				new BeanPropertyRowMapper<ShoppingItem>(ShoppingItem.class));
		return calculations;
	}

	public void addShoppingItem(ShoppingItem item) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("name", item.getName());
		namedParameters.addValue("description", item.getDescription());
		namedParameters.addValue("price", item.getPrice());
		namedParameters.addValue("link", item.getLink());
		String query = "INSERT INTO my_purchases (name, description, price, link) VALUES (:name, :description, :price, :link)";
		jdbc.update(query, namedParameters);
	}
	
	public ShoppingItem selectItemById(int id) {
		String q = "SELECT * FROM my_purchases WHERE id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		ArrayList<ShoppingItem> students = (ArrayList<ShoppingItem>) jdbc.query(q, namedParameters,
				new BeanPropertyRowMapper<ShoppingItem>(ShoppingItem.class));
		return students.get(0);
	}
	
	public int deleteShoppingItem(int id) {
		String q = "DELETE FROM my_purchases WHERE id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", id);
		return jdbc.update(q, namedParameters);
	}
	
	public int editShoppingItem(ShoppingItem item) {
		String q = "UPDATE my_purchases SET name= :name, description = :description, price = :price, link = :link WHERE id = :id";
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		namedParameters.addValue("id", item.getId());
		namedParameters.addValue("name", item.getName());
		namedParameters.addValue("description", item.getDescription());
		namedParameters.addValue("price", item.getPrice());
		namedParameters.addValue("link", item.getLink());
		return jdbc.update(q, namedParameters);
	}
	
	
}
