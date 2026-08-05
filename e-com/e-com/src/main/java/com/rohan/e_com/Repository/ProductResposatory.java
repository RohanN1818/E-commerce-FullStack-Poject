package com.rohan.e_com.Repository;

import com.rohan.e_com.model.ProductTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductResposatory extends JpaRepository <ProductTable,Integer>{
    @Query("SELECT p from ProductTable p WHERE "+
            "LOWER(p.name) LIKE LOWER (CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.Description) LIKE LOWER (CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.Brand) LIKE LOWER (CONCAT('%', :keyword, '%')) OR " +
            "LOWER(p.category) LIKE LOWER (CONCAT('%', : keyword, '%'))")
    List<ProductTable> searchProducts(String keyword);
}
