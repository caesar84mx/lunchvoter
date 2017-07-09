package com.caesar_84;

import com.caesar_84.lunchvoter.LunchvoterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = LunchvoterApplication.class)
@WebAppConfiguration
public class LunchvoterApplicationTests {

	@Test
	public void contextLoads() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println("\nEncrypted code \"admin\":\n" + encoder.encode("admin") + "\n" +
		"Encrypted code \"password\":\n" + encoder.encode("password") + "\n");
	}

}
