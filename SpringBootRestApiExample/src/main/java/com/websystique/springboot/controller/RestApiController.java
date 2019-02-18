package com.websystique.springboot.controller;

import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.websystique.springboot.model.KeyHashMap;
import com.websystique.springboot.service.FibonacciService;
import com.websystique.springboot.service.HashService;

@RestController
@RequestMapping("/api")
public class RestApiController {

	public static final Logger logger = LoggerFactory
			.getLogger(RestApiController.class);

	@Autowired
	FibonacciService fibonacciService;

	@Autowired
	HashService hashService;

	@RequestMapping(value = "/fibo/{count}", method = RequestMethod.GET)
	public List<Long> listNumbers(@PathVariable int count) {
		List<Long> users = fibonacciService.getFibonacci(count);
		if (users.isEmpty()) {
			return null;
		}
		return users;
	}

	@RequestMapping(value = "/fibosorted", method = RequestMethod.GET)
	public List<Long> getSorted() {
		List<Long> fiboNumbers = listNumbers(50);
		List<Long> users = fibonacciService.twoWaySort(fiboNumbers, 50);
		if (users.isEmpty()) {
			return null;
		}
		return users;
	}

	@RequestMapping(value = "/gethash/{key}", method = RequestMethod.GET)
	public String gethashValue(@PathVariable String key) {
		try {
			KeyHashMap keyHashMap = hashService.getKeyHashMap(key);
			return keyHashMap.toString();
		} catch (SQLException e) {
			return "";
		}
	}

	@RequestMapping(value = "/inserthash/{key}/{value}", method = RequestMethod.GET)
	public String inserthashValue(@PathVariable String key,
			@PathVariable String value) {
		KeyHashMap keyHashMap = hashService.insertHashMap(key, value);
		return keyHashMap.toString();
	}

}