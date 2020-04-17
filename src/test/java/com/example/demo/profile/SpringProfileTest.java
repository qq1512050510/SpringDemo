package com.example.demo.profile;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.SpringDemoApplication;
import com.example.demo.bean.Person;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = SpringDemoApplication.class)
@SpringBootTest(classes=SpringDemoApplication.class)
@ActiveProfiles("dev")
public class SpringProfileTest {
	@Autowired
	Person p;

	@Test
	public void testProfile() {
		System.out.println("test start");
		//p.speak();
	}

}
