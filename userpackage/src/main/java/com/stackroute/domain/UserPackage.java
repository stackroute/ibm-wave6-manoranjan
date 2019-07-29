package com.stackroute.domain;

import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.lang.annotation.Documented;
import java.sql.Date;


@Document
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserPackage {
    @Id
    private String emailId;
    private String packageName;
    private Date mydate;

    public Date getMyDate() {
        return mydate;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setMydate(Date mydate) {
        this.mydate = mydate;
    }
}

