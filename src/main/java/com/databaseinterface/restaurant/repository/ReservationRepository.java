package com.databaseinterface.restaurant.repository;

import com.databaseinterface.restaurant.model.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r FROM Reservation r " +
       "WHERE (:clientName IS NULL OR r.client.name LIKE %:clientName%) " +
       "AND (:tableId IS NULL OR r.table.id = :tableId) " +
       "AND (:date IS NULL OR r.reservationDate = :date)")
Page<Reservation> findReservationsJPQL(
        @Param("clientName") String clientName,
        @Param("tableId") Integer tableId,
        @Param("date") LocalDate date,
        Pageable pageable);


}