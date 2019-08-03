package com.stackroute.seed;

import com.stackroute.domain.Producer;
import com.stackroute.domain.User;
import com.stackroute.repository.ProducerRepository;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.core.env.Environment;

@Component
@PropertySource("classpath:config.properties")
public class SeedData implements ApplicationListener<ContextRefreshedEvent> {

    Producer producer= new Producer();
    Producer producer1 = new Producer();
    Producer producer2 = new Producer();
    @Autowired
    ProducerRepository producerRepository;
    @Autowired
    private Environment environment;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        producer.setEmailId(environment.getProperty("producer.emailId"));
        producer.setName(environment.getProperty("producer.name"));
        producer.setAge(Integer.parseInt(environment.getProperty("producer.age")));
        producer.setMobileNo(environment.getProperty("producer.mobileNo"));
        producer.setPassword(environment.getProperty("producer.password"));
        producer.setRole(environment.getProperty("producer.role"));
        producerRepository.save(producer);

        producer1.setEmailId(environment.getProperty("producer1.emailId"));
        producer1.setName(environment.getProperty("producer1.name"));
        producer1.setAge(Integer.parseInt(environment.getProperty("producer1.age")));
        producer1.setMobileNo(environment.getProperty("producer1.mobileNo"));
        producer1.setPassword(environment.getProperty("producer1.password"));
        producer1.setRole(environment.getProperty("producer1.role"));
        producerRepository.save(producer1);

        producer2.setEmailId(environment.getProperty("producer2.emailId"));
        producer2.setName(environment.getProperty("producer2.name"));
        producer2.setAge(Integer.parseInt(environment.getProperty("producer2.age")));
        producer2.setMobileNo(environment.getProperty("producer2.mobileNo"));
        producer2.setPassword(environment.getProperty("producer2.password"));
        producer2.setRole(environment.getProperty("producer2.role"));
        producerRepository.save(producer2);
    }
}
