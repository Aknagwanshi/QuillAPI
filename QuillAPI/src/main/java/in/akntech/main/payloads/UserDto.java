package in.akntech.main.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserDto {
	
	private int id;
	@NotEmpty
	@Size(min = 4, message= "Username must be min of 4 characters!!")
	private String name;
	@Email
	@NotEmpty
	private String email;
	@NotEmpty()
	@Size(min = 4, max = 12, message = "Email is not valid!!")
	private String password;
	@NotEmpty
	private String about;
	
}
	