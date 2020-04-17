package com.example.demo.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.example.demo.SpringDemoApplication;
import com.example.demo.bean.Person;

@RunWith(SpringJUnit4ClassRunner.class)
//Spring整合JUnit4测试时，使用注解引入多个配置文件
//@ContextConfiguration(Locations="../application.yml")
//@ContextConfiguration(locations = { "classpath*:/application.yml", "classpath*:/spring2.xml" }) 
//@ContextConfiguration(classes = SpringDemoApplication.class)
@SpringBootTest(classes=SpringDemoApplication.class)
@ActiveProfiles("prod")
public class SpringProfileTest {
	@Autowired
	Person p;

	@Test
	public void testProfile() {
		System.out.println("test start");
		p.speak();
	}

}
