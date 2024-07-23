package com.vheekey.crud.product.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


public record Product(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id,
        String tag,
        String name,
        Double price,
        boolean isAvailable,
        Integer createdBy
) {

    public long id() {
        return id;
    }

    @Override
    public String tag() {
        return tag;
    }

    public String name() {
        return name;
    }

    @Override
    public Double price() {
        return price;
    }

    @Override
    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public Integer createdBy() {
        return createdBy;
    }
}
