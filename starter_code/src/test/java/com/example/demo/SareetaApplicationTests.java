package com.example.demo;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SareetaApplicationTests {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Test
	public void contextLoads() {
	}

	@Test
	public void beanForBCryptPasswordEncoderLoads() {
		Assert.assertEquals(BCryptPasswordEncoder.class, bCryptPasswordEncoder.getClass());
	}

}
