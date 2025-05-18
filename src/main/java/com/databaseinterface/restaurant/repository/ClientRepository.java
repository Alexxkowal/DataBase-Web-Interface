package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(value = "SELECT * FROM clients c " +
            "WHERE (:name IS NULL OR c.name ILIKE '%' || :name || '%') " +
            "AND (:email IS NULL OR c.mail ILIKE '%' || :email || '%') " +
            "AND (:minDiscount IS NULL OR c.discount >= :minDiscount) " +
            "AND (:maxDiscount IS NULL OR c.discount <= :maxDiscount)",
            countQuery = "SELECT count(*) FROM clients c " +
                    "WHERE (:name IS NULL OR c.name ILIKE '%' || :name || '%') " +
                    "AND (:email IS NULL OR c.mail ILIKE '%' || :email || '%') " +
                    "AND (:minDiscount IS NULL OR c.discount >= :minDiscount) " +
                    "AND (:maxDiscount IS NULL OR c.discount <= :maxDiscount)",
            nativeQuery = true)
    Page<Client> searchClients(
            @Param("name") String name,
            @Param("email") String email,
            @Param("minDiscount") Double minDiscount,
            @Param("maxDiscount") Double maxDiscount,
            Pageable pageable);
}