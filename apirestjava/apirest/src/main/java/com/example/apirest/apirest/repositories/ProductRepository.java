package com.example.apirest.apirest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apirest.apirest.models.Product;

public interface ProductRepository extends JpaRepository <Product, Integer> {

    
} 
