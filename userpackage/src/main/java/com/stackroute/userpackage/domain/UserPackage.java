package com.stackroute.userpackage.domain;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.lang.annotation.Documented;


@Document
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPackage {
    @Id
    private String emailId;
    private String packageName;
    private String mydate;

    public String getMyDate() {
        return mydate;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setMydate(String mydate) {
        this.mydate = mydate;
    }
}

