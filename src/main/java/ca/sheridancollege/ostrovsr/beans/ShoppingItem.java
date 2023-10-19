package ca.sheridancollege.ostrovsr.beans;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String description;
	private Double price;
	private String link;
}
