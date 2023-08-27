package com.FelipeMarques.salesController.repositories;

import com.FelipeMarques.salesController.models.SaleModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

public interface SaleRepository extends JpaRepository<SaleModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO TB_SALE (sale_date, total_value, payment_status, customer_id) " +
                    "VALUES (:saleDate, :totalValue, :paymentStatus, :customerId)", nativeQuery = true)
    void save(@Param(value = "saleDate") LocalDate saleDate, @Param( value = "totalValue") BigDecimal totalValue,
              @Param(value = "paymentStatus") Boolean paymentStatus, @Param(value = "customerId") Integer customerId);

    @Query(value = "SELECT * FROM TB_SALE " +
                    "WHERE id = :id", nativeQuery = true)
    Optional<SaleModel> findById(@Param(value = "id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_SALE " +
                    "WHERE id = :id", nativeQuery = true)
    void delete(@Param(value = "id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_SALE " +
                    "SET sale_date = :saleDate, total_value = :totalValue, payment_status = :paymentStatus, customer_id = :customerId " +
                    "WHERE id = :id", nativeQuery = true)
    void updateSale(@Param(value = "id") Integer id, @Param(value = "saleDate") LocalDate saleDate, @Param( value = "totalValue") BigDecimal totalValue,
                @Param(value = "paymentStatus") Boolean paymentStatus, @Param(value = "customerId") Integer customerId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_SALE SET payment_status = :paymentStatus " +
                    "WHERE id = :id", nativeQuery = true)
    void updatePaymentStatus(@Param(value = "id") Integer id, @Param(value = "paymentStatus") Boolean paymentStatus);
}
