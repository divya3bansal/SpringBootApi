package com.websystique.springboot.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.websystique.springboot.model.KeyHashMap;

@Service("hashService")
public class HashServiceImpl implements HashService {

	@PostConstruct
	private void tableCreate() {
		Connection connection = null;
		Statement statement = null;
		String createCreateStatement = createCreateStatement();
		executeH2Query(createCreateStatement, false, connection,
				statement);
		closeConnection(connection, statement);
	}

	@Override
	public KeyHashMap getKeyHashMap(String key)
			throws SQLException {
		Connection connection = null;
		Statement statement = null;
		KeyHashMap hashMap = null;
		String select = createSelectStatement(key);
		ResultSet executeH2Query = executeH2Query(select, true, connection,
				statement);
		while (executeH2Query.next()) {
			hashMap = new KeyHashMap(key, executeH2Query.getString("value"),
					executeH2Query.getString("hashValue"));
		}
		closeConnection(connection, statement);
		return hashMap;
	}
	
	@Override
	public KeyHashMap insertHashMap(String key, String value){
		Connection connection = null;
		Statement statement = null;
		KeyHashMap hashMap = null;
		String generateHash = generateHash(key, value);
		executeH2Query(insertIntokey(key, value, generateHash), false, connection,
				statement);
		closeConnection(connection, statement);
		hashMap = new KeyHashMap(key, value, generateHash);
		return hashMap;
	}

	private String generateHash(String key, String value) {
		String hash = key + value;
		return hash;
	}

	private Connection createConnection() {
		Connection connection = null;
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			connection = DriverManager
					.getConnection("jdbc:h2:~/test", "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	private String createCreateStatement() {
		StringBuilder builder = new StringBuilder();
		builder.append("CREATE TABLE IF NOT EXISTS " + "keystorage" + " (");
		builder.append("key VARCHAR(255) not NULL, ");
		builder.append("value VARCHAR(255) not NULL, ");
		builder.append("hashValue VARCHAR(255) not NULL, ");
		builder.append("PRIMARY KEY (key))");
		return builder.toString();
	}

	private String createSelectStatement(String key) {
		StringBuilder builder = new StringBuilder();
		builder.append("Select value, hashValue from " + "keystorage WHERE key = "
				+ "'" + key + "'");
		return builder.toString();
	}

	private String insertIntokey(String key, String value, String hash) {
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO keystorage (key, value, hashValue) values(");
		builder.append("'" + key + "',");
		builder.append("'" + value + "',");
		builder.append("'" + hash + "'");
		builder.append(")");
		return builder.toString();
	}

	private ResultSet executeH2Query(String sqlQuery, boolean fetchResultSet,
			Connection connection, Statement statement) {
		connection = null;
		statement = null;
		try {
			connection = createConnection();
			statement = connection.createStatement();
			if (fetchResultSet) {
				return statement.executeQuery(sqlQuery);
			} else {
				statement.execute(sqlQuery);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void closeConnection(Connection connection, Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
