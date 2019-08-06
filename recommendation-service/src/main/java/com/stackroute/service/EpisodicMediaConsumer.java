package com.stackroute.service;

import com.stackroute.domain.EpisodicMedia;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class EpisodicMediaConsumer {

    @KafkaListener(topics = "saveEpisodicMedia",groupId = "Group_EpisodicMediaObject")
    public void consumeJson(@Payload EpisodicMedia episodicMedia)
    {
        System.out.println("published Json object   "+episodicMedia.toString());
    }


}
