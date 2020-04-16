package com.example.demo.inter.impl;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.example.demo.inter.MovieFactor;

import lombok.extern.slf4j.Slf4j;
@Configuration
@Profile(value="prod")
@Slf4j
public class Chinese implements MovieFactor {

	@Override
	public void speak() {
		log.info("Chinese");
	}

}
