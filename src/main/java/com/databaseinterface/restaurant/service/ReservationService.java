package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.Reservation;
import com.databaseinterface.restaurant.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Page<Reservation> findReservations(String clientName, Integer tableId, LocalDate date, int page, int size) {
    Pageable pageable = PageRequest.of(page, size);
    System.out.println("Search params - clientName: " + clientName +
                      ", tableId: " + tableId +
                      ", date: " + date);
    return reservationRepository.findReservationsJPQL(clientName, tableId, date, pageable);
}

}
