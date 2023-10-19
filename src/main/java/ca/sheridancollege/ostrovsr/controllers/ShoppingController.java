package ca.sheridancollege.ostrovsr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ca.sheridancollege.ostrovsr.beans.ShoppingItem;
import ca.sheridancollege.ostrovsr.database.DatabaseAccess;

@Controller
public class ShoppingController {
	
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String loadViewPage(Model model) {
		model.addAttribute("items", da.retrieveItems());
		return "view";
	}
	
	@GetMapping("/add")
	public String loadAddPage(Model model) {
		model.addAttribute("item", new ShoppingItem());
		return "add";
	}
	
	@PostMapping("/addItem")
	public String calculate(@ModelAttribute ShoppingItem item) {
		da.addShoppingItem(item);
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String getDelete(@PathVariable int id) {
		da.deleteShoppingItem(id);
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String loadEditPage(@PathVariable int id, Model model) {
		model.addAttribute("item", da.selectItemById(id));
		return "edit";
	}
	
	@PostMapping("/editItem")
	public String doUpdate(@ModelAttribute ShoppingItem item) {
		da.editShoppingItem(item);
		return "redirect:/";
	}
	
	
	
	
}
