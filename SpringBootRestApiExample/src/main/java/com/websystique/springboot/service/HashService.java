package com.websystique.springboot.service;

import java.sql.SQLException;

import com.websystique.springboot.model.KeyHashMap;




public interface HashService {

	KeyHashMap getKeyHashMap(String key) throws SQLException;

	KeyHashMap insertHashMap(String key, String value);
}
