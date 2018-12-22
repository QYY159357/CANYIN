package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.config.bean.RedisTool;
import com.example.demo.util.Util;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {

	@Autowired
	private RedisTool redisTool;

	@Test
	public void test() {

		System.out.println(Util.toJson(redisTool.get( "changePhone:idididid:18977568679")));

	}

}
