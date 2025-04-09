package in.akntech.main.services;

import java.util.List;

import in.akntech.main.entities.Post;
import in.akntech.main.payloads.PostDto;
import in.akntech.main.payloads.PostResponse;

public interface PostService {
//	CREATE
	PostDto createPost(PostDto postDto, int userId, int categoryId);
//	UPDATE
	PostDto updatePost(PostDto postDto, int postId);
//	DELETE
	void deletePost(int postId);
//	GET - ALL
	PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);
//	GET- 
	PostDto getPostById(int postId);
//	FILTER -BY CATEGORY
	PostResponse getPostsByCategory(int categoryId, int pageNumber, int pageSize, String sortBy, String sortDir);
//	FILTER - BY USER
	PostResponse getPostsByUser(int userId, int pageNumber, int pageSize, String sortBy, String sortDir);
//	FILTER - BY SEARCH
	List<PostDto> searchPosts(String keyword);
}
