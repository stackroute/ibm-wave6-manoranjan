package com.stackroute.seed;

import com.stackroute.domain.User;
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

    User user=new User();
    User user1=new User();
    User user2=new User();
    @Autowired
    UserRepository userRepository;
    @Autowired
    private Environment environment;
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        user.setEmailId(environment.getProperty("user.emailId"));
        user.setName(environment.getProperty("user.name"));
        user.setGender(environment.getProperty("user.gender"));
        user.setAge(Integer.parseInt(environment.getProperty("user.age")));
        user.setMobileNo(environment.getProperty("user.mobileNo"));
        user.setPassword(environment.getProperty("user.password"));
        user.setRole(environment.getProperty("user.role"));
        userRepository.save(user);

        user1.setEmailId(environment.getProperty("user1.emailId"));
        user1.setName(environment.getProperty("user1.name"));
        user1.setGender(environment.getProperty("user1.gender"));
        user1.setAge(Integer.parseInt(environment.getProperty("user1.age")));
        user1.setMobileNo(environment.getProperty("user1.mobileNo"));
        user1.setPassword(environment.getProperty("user1.password"));
        user1.setRole(environment.getProperty("user1.role"));
        userRepository.save(user1);

        user2.setEmailId(environment.getProperty("user2.emailId"));
        user2.setName(environment.getProperty("user2.name"));
        user2.setGender(environment.getProperty("user2.gender"));
        user2.setAge(Integer.parseInt(environment.getProperty("user2.age")));
        user2.setMobileNo(environment.getProperty("user2.mobileNo"));
        user2.setPassword(environment.getProperty("user2.password"));
        user2.setRole(environment.getProperty("user2.role"));
        userRepository.save(user2);
    }
}
