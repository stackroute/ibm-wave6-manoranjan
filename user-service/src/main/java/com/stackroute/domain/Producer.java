package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Transient;
import java.util.List;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producer {
    @Id
    private String emailId;
    private String name;
    private int age;
    private String gender;
    private String mobileNo;
    @Transient
    private String password;
    private String role = "producer";
    private List<String> uploadedVideo;
}
