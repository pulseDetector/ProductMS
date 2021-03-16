package com.infy.product.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.infy.product.dto.ProductDTO;
import com.infy.product.dto.SubscribedProductDTO;
import com.infy.product.dto.WishlistDTO;
import com.infy.product.service.ProductService;
import com.infy.product.service.SubscribedProductService;

@RestController
@CrossOrigin
public class ProductController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	ProductService productService;
	@Autowired
	SubscribedProductService subProductService;
	@Value("${user.uri}")
	String userUri;
	
	@GetMapping(value = "/products",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getAllProducts() {
		logger.info("Fetching all products");
		return productService.getAllProducts();
	}
	
	@GetMapping(value = "/products/{productId}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDTO> getSpecificProduct(@PathVariable Integer productId) throws Exception {
		logger.info("Fetching specific product");
		ResponseEntity<ProductDTO> productRE = null;
		try {
		ProductDTO product = productService.getSpecificProduct(productId);
		productRE = new ResponseEntity<>(product, HttpStatus.OK);
		} catch(Exception e) {
			System.out.println("ProductId Invalid");
			productRE = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return productRE;
	}
	
	//new method
	@GetMapping(value = "/products/orders/{productId}",  produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductDTO getSpecificProduct1(@PathVariable Integer productId) throws Exception {
        logger.info("Fetching specific product");
        ProductDTO product = productService.getSpecificProduct(productId);
        
        return product;
    }
	
	@GetMapping(value = "/products/category/{category}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getPlansByCategory(@PathVariable String category) {
		logger.info("Fetching details of products by category", category);
		return productService.getProductsByCategory(category);
	}
	
	@GetMapping(value = "/products/name/{productName}",  produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductDTO> getPlansByProductName(@PathVariable String productName) {
		logger.info("Fetching details of products by product name", productName);
		return productService.getProductsByName(productName);
	}

	@PutMapping(value ="/products/{productId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateProduct(@PathVariable Integer productId, @RequestBody ProductDTO prodDTO) {
		productService.updateProduct(productId, prodDTO);
	}
	
	@GetMapping(value = "/subproducts", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<SubscribedProductDTO> getSubscribedProducts() {
		logger.info("Fetching all subscribed products");
		return subProductService.getAllSubProducts();
	}
	
	
	@GetMapping(value ="/subproducts/{buyerId}/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
	public SubscribedProductDTO getSpecificSubproduct(@PathVariable Integer buyerId, @PathVariable Integer productId) throws Exception{
		SubscribedProductDTO subProdDTO = subProductService.getSpecificSubProduct(buyerId, productId);
		return subProdDTO;
	}
	
	@PostMapping(value ="/subproducts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void addSubproduct(@RequestBody SubscribedProductDTO subProdDTO) {
		subProductService.addSubProduct(subProdDTO);
	}

	@PostMapping(value ="/wishlist/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String addToWislist(@RequestBody WishlistDTO wishlist) {
			try {
			new RestTemplate().postForObject(userUri+"/wishlist", wishlist, WishlistDTO.class);
			return "Product added to wishlist successfully!!!!";
			} catch(Exception e) {
				return "Product already present in wishlist.";
			}
	}
	
	@DeleteMapping(value="/wishlist/delete/{buyerId}/{proId}")
	public void deleteWishlist(@PathVariable Integer buyerId, @PathVariable Integer proId) {
		new RestTemplate().delete(userUri+"/wishlist/"+buyerId+"/"+proId, WishlistDTO.class);
	}
	
	@PostMapping(value ="/wishlist/move", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String moveToWislist(@RequestBody WishlistDTO wishlist) {
			try {
				subProductService.deleteSubProduct(wishlist.getBuyerId(), wishlist.getProId());
				new RestTemplate().postForObject(userUri+"/wishlist", wishlist, WishlistDTO.class);
				return "Product moved to wishlist!!";
			} catch(Exception e) {
				return "Product can't be moved to wishlist.";
			}
	}
	
}
