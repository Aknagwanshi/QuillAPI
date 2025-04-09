package in.akntech.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.akntech.main.payloads.ApiResponse;
import in.akntech.main.payloads.CategoryDto;
import in.akntech.main.services.CategoryService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;
	
//	CREATE
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto){
		CategoryDto createCategory = this.categoryService.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(createCategory, HttpStatus.CREATED);
	}
//	UPDATE
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable("categoryId") int cid){
		CategoryDto updatedCategory = this.categoryService.updateCategory(categoryDto,cid);
		return new ResponseEntity<CategoryDto>(updatedCategory, HttpStatus.OK);
	}
//	DELETE
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") int cid){
		this.categoryService.deleteCategory(cid);
		return new ResponseEntity<ApiResponse>(new ApiResponse("Category is deleted successfully!!",true), HttpStatus.OK);
	}
//	READ
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable("categoryId") int cid){
		CategoryDto categoryDto = this.categoryService.getCategory(cid);
		return new ResponseEntity<CategoryDto>(categoryDto, HttpStatus.OK);
	}
//	READ-ALL
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getCategories(){
		List<CategoryDto> categories = this.categoryService.getCategories();
		return ResponseEntity.ok(categories);
	}
}
