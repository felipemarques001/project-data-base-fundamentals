package com.FelipeMarques.salesController.repositories;

import com.FelipeMarques.salesController.models.PurchaseModel;
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
public interface PurchaseRepository extends JpaRepository<PurchaseModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO TB_PURCHASE (weight, product_id, sale_id) " +
                    "VALUES (:weight, :productId, :saleId)", nativeQuery = true)
    void savePurchase(@Param(value = "weight") BigDecimal weight, @Param(value = "productId") Integer productId, @Param(value = "saleId") Integer saleId);

    @Query(value = "SELECT * FROM TB_PURCHASE " +
                    "WHERE id = :id", nativeQuery = true)
    Optional<PurchaseModel> findById(@Param(value = "id") Integer id);

    @Query(value = "SELECT * FROM TB_PURCHASE", nativeQuery = true)
    List<PurchaseModel> findAllPurchases();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_PURCHASE " +
                    "WHERE id = :id", nativeQuery = true)
    void deleteById(@Param(value = "id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_PURCHASE " +
                    "SET weight = :weight, product_id = :productId, sale_id = :saleId " +
                    "WHERE id = :id", nativeQuery = true)
    void update(@Param(value = "id") Integer id, @Param(value = "weight") BigDecimal weight,
                @Param(value = "productId") Integer productId, @Param(value = "saleId") Integer saleId);
}
