package in.akntech.main.payloads;

import java.util.Date;

import in.akntech.main.entities.Category;
import in.akntech.main.entities.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {
	@NotBlank
	@Size(min = 4, message = "Title muste have 4 letters")
	private String title;
	@NotBlank
	@Size(min = 20, message = "Title muste have 20 letters")
	private String content;
	
	@NotNull
	private String imageName;
	
	private Date addedDate;
	
	private CategoryDto category;
	
	private UserDto user;
	
	
	
}
