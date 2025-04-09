package in.akntech.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.akntech.main.entities.User;

public interface UserRepo extends JpaRepository<User, Integer> {

}
