package tech.kaomidev.springboot.helloworld.api

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import tech.kaomidev.springboot.helloworld.model.Product
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping

@RestController
@RequestMapping("/products")
class ProductController {
	
	val products = mutableListOf<Product>()
	
	@PostMapping
	fun createProduct(@RequestBody productDto: Product): ResponseEntity<Int> {		
		val productId = products.size + 1
		
		products.add(productDto.apply {
			id = productId
		})
		
		return ResponseEntity(productId, HttpStatus.CREATED)
	}
	
	@GetMapping
	fun getAllProducts(): List<Product> {
		return products
	}
	
	@PutMapping("{id}")
	fun updateProduct(@PathVariable id: Int, @RequestBody productDto: Product) {		
		val product = products.find { it.id == id } ?: throw NoSuchElementException("product not found")
			
		product.apply {
			name = productDto.name
			price = productDto.price
		}
	}
	
	@DeleteMapping("{id}")
	fun deleteProduct(@PathVariable id: Int): ResponseEntity<Void> {
		products.removeIf { it.id == id }
		
		return ResponseEntity.noContent().build()
	}
}