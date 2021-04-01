package com.flowerShop;

import com.flowerShop.api.repositories.product.ProductRepository;
import com.flowerShop.api.services.Product.ProductService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlowerShopApplicationTests {
	@Mock
	private ProductRepository productRepository;

	@Mock
	private ProductService productService;
	@Test
	void contextLoads() {
	}

}
