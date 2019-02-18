package com.websystique.springboot.service;

import java.util.List;



public interface FibonacciService {

	List<Long> getFibonacci(int count);

	List<Long> twoWaySort(List<Long> numberList, int n);
}
