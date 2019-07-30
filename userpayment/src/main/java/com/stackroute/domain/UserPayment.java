package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.sql.Date;


@Document
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class UserPayment {
    @Id
    private String emailId;
    private String packageName;
    private Date mydate;
}

