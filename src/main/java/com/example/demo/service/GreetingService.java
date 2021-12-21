package com.example.demo.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.bytebuddy.asm.Advice.AllArguments;

import com.example.demo.model.Greeting;
import com.example.demo.model.User;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.example.demo.Repository.GreatingRepository;
/**
 * 
 * @Service : to Create GreetingService Class
 * @Autowired : to crete new object of class 
 * @Override : overriding method
 *
 */
@Service
public class GreetingService implements IGreetingService {
	public static final String template = "Hello  %s";
	private final AtomicLong counter = new AtomicLong();
	
	@Autowired
	private GreatingRepository greetingRepository;
	
	@Override
	public Greeting addGreeting(User user) {
		String message = String.format(template, (user.toString().isEmpty()) ? " World " : user.toString());
		return greetingRepository.save(new Greeting(counter.incrementAndGet(),message));
	}

	@Override
	public Greeting getGreetingById(long Id) {
		return greetingRepository.findById(Id).get();
	}

	@Override
	public List<Greeting> getGreetingALL() {
		return greetingRepository.findAll();
	}

	

	@Override
	public List<Greeting> deletebyID(Long Id) {
		greetingRepository.deleteById(Id);
		return greetingRepository.findAll();
	}
		
}
