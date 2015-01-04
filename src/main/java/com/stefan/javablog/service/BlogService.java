package com.stefan.javablog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.method.P;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.stefan.javablog.entity.Blog;
import com.stefan.javablog.entity.Item;
import com.stefan.javablog.entity.User;
import com.stefan.javablog.exception.RssException;
import com.stefan.javablog.repository.BlogRepository;
import com.stefan.javablog.repository.ItemRepository;
import com.stefan.javablog.repository.UserRepository;

@Service
public class BlogService {

	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RssService rssService;
	@Autowired
	private ItemRepository itemRepository;

	public void saveItems(Blog blog) {
		try {
			List<Item> items = rssService.getItems(blog.getUrl());
			for (Item item : items) {
				Item savedItem = itemRepository.findByBlogAndLink(blog, item.getLink());
				if(savedItem == null){
					item.setBlog(blog);
					itemRepository.save(item);
				}
				
			}
		} catch (RssException e) {
			e.printStackTrace();
		}
	}
	//1 hour = 60 seconds * 60 minutes * 1000
	@Scheduled(fixedDelay=3600000)
	public void reloadBlogs(){
		List<Blog> blogs = blogRepository.findAll();
		for(Blog blog: blogs){
			saveItems(blog);
		}
	}

	public void save(Blog blog, String name) {
		User user = userRepository.findByName(name);
		blog.setUser(user);
		blogRepository.save(blog);
		saveItems(blog);
	}

	@PreAuthorize("#blog.user.name == authentication.name or hasRole('ROLE_ADMIN')")
	public void delete(@P("blog") Blog blog) {
		blogRepository.delete(blog);
	}

	public Blog findOne(int id) {
		return blogRepository.findOne(id);
	}

}
