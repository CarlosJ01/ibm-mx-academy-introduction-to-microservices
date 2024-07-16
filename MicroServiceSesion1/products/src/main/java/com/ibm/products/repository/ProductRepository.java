package com.ibm.products.repository;

import com.ibm.products.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    //SELECT * FROM PRODUCT WHERE NAME = ?

    /*
    @Query("SELECT * FROM Product WHERE name =:nameOfProduct")
    public List<Product> getAllProductByName(@Param("nameOfProduct") String nameOfProduct);

    @Query(value = "SELECT * FROM Product WHERE name =:nameOfProduct", nativeQuery = true)
    public List<Product> getAllProductNative(@Param("nameOfProduct") String nameOfProduct);
    */


}
