package br.com.product.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.product.model.dto.ProductDTO;
import br.com.product.model.entity.Product;
import br.com.product.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	// método1
	public List<Product> find() {
		return productRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));

	}

	// método2

	public List<ProductDTO> findByDate() {
		List<Product> products = productRepository.findAll();
		List<Product> orderedProducts = products.stream().sorted(Comparator.comparing(p -> p.getDate())).collect(Collectors.toList());
		
// Forma 1 - utilizando o modelMapper para converter a lista original em uma lista generica (aqui o java compreende que a lista generica será do tipo Product)
		return modelMapper.map(orderedProducts, List.class);
		
		
//	Forma 2 - convertendo List<Product> para List<ProductDTO> percorrendo a lista original e montando objetos do tipo desejado
//		List<ProductDTO> dtos = new ArrayList<ProductDTO>();
//		for(Product p : orderedProducts) {
//			ProductDTO dto = modelMapper.map(p, ProductDTO.class);
//			dtos.add(dto);
//		}
		
//	Forma 3 - utilizando o proprio lambda, adicionando o ".map()" para converter um tipo em outro
//		List<ProductDTO> teste = products.stream().sorted(Comparator.comparing(p -> p.getDate())).map(p -> modelMapper.map(p, ProductDTO.class)).collect(Collectors.toList());
	}

	public ProductDTO findById(Long id) {
		Product product = productRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));

		return modelMapper.map(product, ProductDTO.class);
	}

	public ProductDTO save(ProductDTO dto) {
		Product productDB = modelMapper.map(dto, Product.class);
		productDB = productRepository.save(productDB);
		dto.setId(productDB.getId());

		return dto;

	}

	public ProductDTO uptade(ProductDTO dto) {
		Product product = productRepository.findById(dto.getId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
		Product productToSave = modelMapper.map(dto, Product.class);

		product = productRepository.save(productToSave);

		return modelMapper.map(product, ProductDTO.class);
	}

	public void deleteById(Long id) {
		productRepository.deleteById(id);

	}

//		ProductDTO dto = new ProductDTO();
//		dto.setName(product.getName());
//		dto.setDescription(product.getDescription());
//		dto.setPrice(product.getPrice());
//		dto.setId(product.getId());
//		dto.setDate(product.getDate().toString());
//		return dto;

//	

}
