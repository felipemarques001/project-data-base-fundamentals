package com.FelipeMarques.salesController.repositories;

import com.FelipeMarques.salesController.models.ProductModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO TB_PRODUCT (name, price_per_kg) " +
                    "VALUES (:name, :pricePerKg)", nativeQuery = true)
    void saveProduct(@Param(value = "name") String name, @Param(value = "pricePerKg") BigDecimal pricePerKg);

    @Query(value = "SELECT * FROM TB_PRODUCT " +
                    "WHERE name = :name", nativeQuery = true)
    Optional<ProductModel> findByName(@Param(value = "name") String name);

    @Query(value = "SELECT * FROM TB_PRODUCT " +
                    "WHERE id = :id", nativeQuery = true)
    Optional<ProductModel> findById(@Param(value = "id") Integer id);

    @Query(value = "SELECT * FROM TB_PRODUCT", nativeQuery = true)
    List<ProductModel> findAllProducts();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_PRODUCT " +
                    "WHERE id = :id", nativeQuery = true)
    void deleteById(@Param(value = "id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_PRODUCT " +
                    "SET name = :name, price_per_kg = :pricePerKg " +
                    "WHERE id = :id", nativeQuery = true)
    void update(@Param(value = "id") Integer id, @Param(value = "name") String name, @Param(value = "pricePerKg") BigDecimal pricePerKg);
}
