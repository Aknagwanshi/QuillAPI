package in.akntech.main.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import in.akntech.main.entities.Category;
import in.akntech.main.entities.Post;
import in.akntech.main.entities.User;
import in.akntech.main.exceptions.ResourceNotFoundException;
import in.akntech.main.payloads.PostDto;
import in.akntech.main.payloads.PostResponse;
import in.akntech.main.repositories.CategoryRepo;
import in.akntech.main.repositories.PostRepo;
import in.akntech.main.repositories.UserRepo;
import in.akntech.main.services.PostService;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public PostDto createPost(PostDto postDto, int userId, int categoryId) {
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "CategoryId", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("default.png");
		post.setAddedDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> 
				new ResourceNotFoundException("Post", "post id", postId));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setImageName(postDto.getImageName());
		
		Post updatedPost = this.postRepo.save(post);
		return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePost(int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> 
				new ResourceNotFoundException("Post", "post id", postId));
		this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable p = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findAll(p);
		List<Post> allposts = pagePost.getContent();
	
		List<PostDto> postDtos = allposts.stream().map((post)-> 
				this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages((int) Math.ceil((double) pagePost.getTotalElements() / pageSize));
		postResponse.setLastPages(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getPostsByCategory(int categoryId, int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Category category = this.categoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		Page<Post> pagePost = this.postRepo.findByCategory(category, pageable);
		
		List<PostDto> postDtos = pagePost.getContent().stream()
				.map((post)-> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages((int) Math.ceil((double) pagePost.getTotalElements() / pageSize));
		postResponse.setLastPages(pagePost.isLast());
		return postResponse;
	}

	@Override
	public PostResponse getPostsByUser(int userId, int pageNumber, int pageSize, String sortBy, String sortDir) {
		
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		User user = this.userRepo.findById(userId)
				.orElseThrow(()-> new ResourceNotFoundException("User", "user id", userId));
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
	
		Page<Post> pagePost = this.postRepo.findByUser(user, pageable);
		
		List<PostDto> postDtos = pagePost.getContent().stream()
				.map((post)->this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElements(pagePost.getTotalElements());
		postResponse.setTotalPages((int) Math.ceil((double) pagePost.getTotalElements() / pageSize));
		postResponse.setLastPages(pagePost.isLast());
		return postResponse;
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitleContaining(keyword);
		List<PostDto> postDto = posts.stream().map((post)-> 
					this.modelMapper.map(post, PostDto.class))
					.collect(Collectors.toList());
		return postDto;
	}

}
