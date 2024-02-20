package com.jahanfoolad.jfs.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.List;

@Entity
@Table(name="ProductAttribute")
@Getter
@Setter
public class ProductAttribute extends  AbstractEntity {

    @Column
    private Double length;

    @Column
    private Double diameter;

    @Column
    private Double width;

    @Column
    private Double weight;

    @Column
    private Double size;

    @Column
    private String unit;

    @Column
    private String type;

    @Column
    private Double thickness;

    @Column
    private String standard;

    @Column
    private String state;

    @Column
    private String alloy;

    @Column
    private String condition;

    @Column
    private String deliveryPlace;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "product_price",
            joinColumns =
            @JoinColumn(name = "price_productattribute", referencedColumnName = "productAttribute"),
            inverseJoinColumns =
            @JoinColumn(name = "productattribute_priceList", referencedColumnName = "priceList"))
    private List<Price> priceList;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> productList;
}
