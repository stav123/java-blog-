package com.stefan.javablog.service;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.stefan.javablog.entity.Item;
import com.stefan.javablog.exception.RssException;

public class RssServiceTest {

	private RssService rssService;
	
	@Before
	public void setUp() throws Exception {
		rssService = new RssService();
	}

	@Test
	public void testGetItemsFile() throws RssException{
		List<Item> items = rssService.getItems(new File("test-rss/javavids.xml"));
		assertEquals(10, items.size());
		Item item = items.get(0);
		assertEquals("How to solve Source not found error during debug in Eclipse", item.getTitle());
	}

}
