package in.akntech.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.akntech.main.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
