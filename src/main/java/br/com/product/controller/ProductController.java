package br.com.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.product.model.dto.ProductDTO;
import br.com.product.service.ProductService;

@RestController
@RequestMapping("/products")
@Validated
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public List<ProductDTO> list() {
		return productService.findByDate();

	}

	@GetMapping("/{id}")
	public ProductDTO listById(@PathVariable Long id) {
		return productService.findById(id);

	}

	@PostMapping
	public ProductDTO create(@RequestBody ProductDTO product) {
		return productService.save(product);
	}

	@PutMapping("/{productId}")
	public ProductDTO edit(@RequestBody ProductDTO product, @PathVariable Long productId) {
		product.setId(productId);
		return productService.uptade(product);

	}

	@DeleteMapping("/{id}")
	public void remove(@PathVariable Long id) {
		productService.deleteById(id);
	}

}
