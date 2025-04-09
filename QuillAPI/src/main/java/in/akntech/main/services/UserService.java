package in.akntech.main.services;

import java.util.List;
import java.util.Optional;
import in.akntech.main.payloads.UserDto;

public interface UserService {
	UserDto createUser(UserDto userDto);	//Create
	
	List<UserDto> getUser();	//ReadAll
	
	Optional<UserDto> getUserById(int userId);	//Read
	
	UserDto updateUser(UserDto userDto, int userId);	//Update
	
	void deleterUserById(int userId);	//Delete
	
}
