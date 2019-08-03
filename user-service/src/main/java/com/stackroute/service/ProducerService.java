package com.stackroute.service;


import com.stackroute.domain.Producer;


public interface ProducerService {

    //update particular producer
    public Producer updateProducer(String emailId, Producer producer);

    //get producer by their emailId
    public Producer getByEmailId(String emailId);
}

