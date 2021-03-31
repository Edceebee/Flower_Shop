package com.flowerShop;

import com.flowerShop.api.controllers.repositories.ProductRepository;
import com.flowerShop.api.controllers.services.ProductService;
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
