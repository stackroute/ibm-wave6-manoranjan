package com.stackroute.service;

import com.stackroute.domain.Producer;
import com.stackroute.repository.ProducerRepository;
import com.stackroute.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@CacheConfig(cacheNames = "producer")
@Service
public class ProducerServiceImpl implements ProducerService {

    private ProducerRepository producerRepository;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    //to handle delay
    public void simulateDelay(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @CacheEvict(allEntries = true)
    @Override
    public Producer updateProducer(String emailId, Producer producer) {
        Producer producer1 = new Producer();
        if (producerRepository.existsById(producer.getEmailId())) {
            producer1 = producerRepository.findById(emailId).get();
            producer1.setEmailId(producer.getEmailId());
            producer1.setName(producer.getName());
            producer1.setMobileNo(producer.getMobileNo());
            producer1.setAge(producer.getAge());
            producerRepository.save(producer1);
        }
        return producerRepository.save(producer1);
    }

    @Cacheable
    @Override
    public Producer getByEmailId(String emailId) {
        Producer producer=new Producer();
        Optional optional = producerRepository.findById(emailId);
        if (optional.isPresent()) {
            producer = producerRepository.findById(emailId).get();
        }
        return producer;
    }
}
