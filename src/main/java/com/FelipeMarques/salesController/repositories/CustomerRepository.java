package com.FelipeMarques.salesController.repositories;

import com.FelipeMarques.salesController.models.CustomerModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerModel, Integer> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO TB_CUSTOMER (name, cpf) " +
                    "VALUES (:name, :cpf)", nativeQuery = true)
    void saveCustomer(@Param("name") String name, @Param("cpf") String cpf);

    @Query(value = "SELECT * FROM TB_CUSTOMER " +
                    "WHERE cpf = :cpf", nativeQuery = true)
    Optional<CustomerModel> findByCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT * FROM TB_CUSTOMER " +
                    "WHERE id = :id", nativeQuery = true)
    Optional<CustomerModel> findById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM TB_CUSTOMER", nativeQuery = true)
    List<CustomerModel> findAllCustomers();

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM TB_CUSTOMER " +
                    "WHERE id = :id", nativeQuery = true)
    void deleteById(@Param(value = "id") Integer id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE TB_CUSTOMER " +
                    "SET name = :name, cpf = :cpf " +
                    "WHERE id = :id", nativeQuery = true)
    void updateCustomer(@Param(value = "id") Integer id, @Param(value = "name") String name, @Param(value = "cpf") String cpf);
}
