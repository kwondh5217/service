package com.education.webservice.domain.academy;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "class_info")
public class Academy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String gu;
    private String groupName;
    private String name;
    private String fieldName;
    private String courseName;
    private String price;
    private String isOpen;
    private String addressNumber;
    private String address;

    @Builder
    public Academy(String city, String gu, String groupName, String name, String fieldName, String courseName, String price, String isOpen, String addressNumber, String address) {
        this.city = city;
        this.gu = gu;
        this.groupName = groupName;
        this.name = name;
        this.fieldName = fieldName;
        this.courseName = courseName;
        this.price = price;
        this.isOpen = isOpen;
        this.addressNumber = addressNumber;
        this.address = address;
    }


    /**
     수정
     */
    public Academy update(String city, String gu, String groupName, String name, String fieldName, String courseName, String price, String isOpen, String addressNumber, String address){
        this.city = city;
        this.gu = gu;
        this.groupName = groupName;
        this.name = name;
        this.fieldName = fieldName;
        this.courseName = courseName;
        this.price = price;
        this.isOpen = isOpen;
        this.addressNumber = addressNumber;
        this.address = address;

        return this;
    }

}
