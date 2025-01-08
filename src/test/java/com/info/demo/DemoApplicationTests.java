package com.info.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
		String expected = "App is running successfully!";
		String actual = "App is running successfully!";
		assertEquals(expected, actual);
	}

}
