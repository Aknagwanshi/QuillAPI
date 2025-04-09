package in.akntech.main.services;

import java.util.List;

import in.akntech.main.payloads.CategoryDto;

public interface CategoryService {
//	CREATE
	public CategoryDto createCategory(CategoryDto categoryDto);
//	UPDATE
	public CategoryDto updateCategory(CategoryDto categoryDto, int categoryId);
//	DELETE
	public void deleteCategory(int categoryId);
//	GET
	public CategoryDto getCategory(int categoryId);
//	GET-ALL
	public List<CategoryDto> getCategories();
	
}
