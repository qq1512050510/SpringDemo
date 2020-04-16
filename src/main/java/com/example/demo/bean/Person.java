package com.example.demo.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.inter.MovieFactor;

@Component
public class Person {
	@Autowired
	private MovieFactor movieFactor;
	
	public void speak() {
		movieFactor.speak();
	}
}
