package com.qb.dynamic;

import org.springframework.stereotype.Service;

@Service("testDao")
public class TestDaoImpl implements TestDao{

	public void readData() {
		System.out.println("read");
	}

	public void writeData() {
		System.out.println("write");
	}

}
