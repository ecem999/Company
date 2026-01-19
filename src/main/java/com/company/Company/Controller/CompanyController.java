package com.company.Company.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.Company.Entity.Customer;
import com.company.Company.Entity.Enrollment;
import com.company.Company.Entity.Order;
import com.company.Company.Entity.Product;
import com.company.Company.Repository.CustomerRepository;
import com.company.Company.Repository.EnrollmentRepository;
import com.company.Company.Repository.OrderRepository;
import com.company.Company.Repository.ProductRepository;

@RestController
@RequestMapping("company")
public class CompanyController {

	@Autowired
	CustomerRepository cusRep;
	@Autowired
	EnrollmentRepository enRep;
	@Autowired
	OrderRepository ordRep;
	@Autowired
	ProductRepository proRep;
	
	String output = "";
	
	@GetMapping("customers")
	public String getCustomers() {
		output = "";
		for (Customer c : cusRep.findAll()) {
			output += c.getName()+" "+c.getSurname()+"<br>";
			for (Order o : c.getOrders()) {
				output += o.getId()+" "+o.getDate()+"<br>";
				for (Enrollment en : o.getEnrollments()) {
					output += en.getProduct().getTitle()+" "+en.getQuantity()+"<br>";
				}
			}
			output += "<br>";
		}
		return output;
	}
	
	@GetMapping("orders/{customerId}")
	public List<Order> getOrders(@PathVariable Long customerId) {
		return ordRep.findByCustomerId(customerId);
	}
	
	@GetMapping("products/{orderId}")
	public List<Product> getProducts(@PathVariable Long orderId){
		List<Product> temp_products = new ArrayList<>();
		for (Enrollment en : ordRep.findById(orderId).get().getEnrollments())  {
			temp_products.add( en.getProduct() );
		}
		return temp_products;
	}
	
	@PostMapping("newOrder/{customerId}")
	public String newOrder(@PathVariable Long customerId, @RequestBody Order o) {
		o.setCustomer(cusRep.findById(customerId).get());
		ordRep.save(o);
		return "Order Saved!";
		
	}
	
	@GetMapping("addProduct/{orderId}/{productId}/{quantity}")
	public String addProduct(@PathVariable Long orderId,@PathVariable Long productId,
									@PathVariable int quantity) {
		Enrollment en = new Enrollment();
		en.setOrder(ordRep.findById(orderId).get());
		en.setProduct(proRep.findById(productId).get());
		en.setQuantity(quantity);
		enRep.save(en);
		return "Added!";
	}
	
	@GetMapping("removeProduct/{orderId}/{productId}")
	public String removeProduct(@PathVariable Long orderId,@PathVariable Long productId) {
		enRep.deleteByOrderIdAndProductId(orderId, productId);
		return "Removed!";
		}
	
}


