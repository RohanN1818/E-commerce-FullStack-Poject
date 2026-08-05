package com.rohan.e_com.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
@Data
@Entity
public class ProductTable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private String name;
    @JsonProperty("description")
    private String Description;
    @JsonProperty("brand")
    private String Brand;
    private int price;
    @JsonProperty("releaseDate")
    private Date ReleaseDate;
    private boolean Available;
    private int stockQuantity;
    private String imageName;
    private String imageType;
    @Lob
    private byte[] imageData;
    private String category;


}
