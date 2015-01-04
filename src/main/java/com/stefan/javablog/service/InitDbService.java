package com.stefan.javablog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.stefan.javablog.entity.Blog;
import com.stefan.javablog.entity.Item;
import com.stefan.javablog.entity.Role;
import com.stefan.javablog.entity.User;
import com.stefan.javablog.repository.BlogRepository;
import com.stefan.javablog.repository.ItemRepository;
import com.stefan.javablog.repository.RoleRepository;
import com.stefan.javablog.repository.UserRepository;

@Transactional
@Service
public class InitDbService {
	@Autowired
	private BlogRepository blogRepository;
	@Autowired
	private ItemRepository itemRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;

	@PostConstruct
	public void init() {
		Role roleUser = new Role();
		roleUser.setName("ROLE_USER");
		roleRepository.save(roleUser);

		Role roleAdmin = new Role();
		roleAdmin.setName("ROLE_ADMIN");
		roleRepository.save(roleAdmin);

		User userAdmin = new User();
		userAdmin.setEnabled(true);
		userAdmin.setName("admin");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		userAdmin.setPassword(encoder.encode("admin"));
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleUser);
		roles.add(roleAdmin);
		userAdmin.setRoles(roles);
		userRepository.save(userAdmin);
		
		Blog javaBlog = new Blog();
		javaBlog.setName("Java Blog");
		javaBlog.setUrl("http://feeds.feedburner.com/javavids?format=xml");
		javaBlog.setUser(userAdmin);
		blogRepository.save(javaBlog);
//		TEST ITEMS 
//		Item item1 = new Item();
//		item1.setBlog(javaBlog);
//		item1.setTitle("first");
//		item1.setLink("http://google.com");
//		item1.setPublishedDate(new Date());
//		itemRepository.save(item1);
//		
//		Item item2 = new Item();
//		item2.setBlog(javaBlog);
//		item2.setTitle("second");
//		item2.setLink("http://google.com");
//		item2.setPublishedDate(new Date());
//		itemRepository.save(item2);
		
		

	}

}
