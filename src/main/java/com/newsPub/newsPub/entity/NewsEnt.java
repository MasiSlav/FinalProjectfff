package com.newsPub.newsPub.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsEnt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String title;
    private String category;
    private String content;
    private String shortDescrption;

    public NewsEnt(String title, String category, String content) {
        this.title = title;
        this.category = category;
        this.content = content;
        if (content.length()<150){
            this.shortDescrption=content;
        }
        else{
            this.shortDescrption=content.substring(0,149)+ "...";
        }
    }
}
