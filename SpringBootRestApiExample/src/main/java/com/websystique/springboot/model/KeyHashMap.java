package com.websystique.springboot.model;

public class KeyHashMap {
	String key;
	String value;
	String hashValue;
	
	public KeyHashMap(String key, String value, String hashValue){
		this.key = key;
		this.value = value;
		this.hashValue = hashValue;
	}

	@Override
	public String toString() {
		return "KeyHashMap [key=" + key + ", value=" + value + ", hashValue="
				+ hashValue + "]";
	}
	
	
}
