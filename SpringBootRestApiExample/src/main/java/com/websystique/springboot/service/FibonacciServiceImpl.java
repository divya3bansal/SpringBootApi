package com.websystique.springboot.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

@Service("fibonacciService")
public class FibonacciServiceImpl implements FibonacciService {

	@Override
	public List<Long> getFibonacci(int count) {
		if (count < 1) {
			return new ArrayList<>();
		} else if (count == 1) {
			List<Long> fibonacciList = new ArrayList<>();
			fibonacciList.add(0L);
			return fibonacciList;
		}
		Long n1 = 0L;
		Long n2 = 1L;
		Long n3 = 0L;
		List<Long> fibonacciList = new ArrayList<>();
		fibonacciList.add(n1);
		fibonacciList.add(n2);
		count = count - 2;
		while (count > 0) {
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
			fibonacciList.add(n3);
			count = count - 1;
		}
		return fibonacciList;
	}

	@Override
	public List<Long> twoWaySort(List<Long> numberList, int n) {
		int l = 0, r = n - 1;
		Long arr[] = new Long[n];
		for (int i = 0; i < numberList.size(); i++) {
			arr[i] = numberList.get(i);
		}
		int k = 0;
		while (l < r) {
			while (arr[l] % 2 != 0) {
				l++;
				k++;
			}
			while (arr[r] % 2 == 0 && l < r)
				r--;
			if (l < r) {
				Long temp = arr[l];
				arr[l] = arr[r];
				arr[r] = temp;

			}
		}
		Arrays.sort(arr, 0, k, Collections.reverseOrder());
		Arrays.sort(arr, k, n, Collections.reverseOrder());
		return Arrays.asList(arr);
	}
	
}
