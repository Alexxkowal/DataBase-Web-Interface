package com.databaseinterface.restaurant.service;

import com.databaseinterface.restaurant.model.Reservation;
import com.databaseinterface.restaurant.repository.ReservationRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Page<Reservation> findReservations(String clientName, Integer tableId, LocalDate date, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<Reservation> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (clientName != null && !clientName.isBlank()) {
                predicates.add(cb.like(cb.lower(root.get("client").get("name")), "%" + clientName.toLowerCase() + "%"));
            }

            if (tableId != null) {
                predicates.add(cb.equal(root.get("table").get("id"), tableId));
            }

            if (date != null) {
                predicates.add(cb.equal(root.get("reservationDate"), date));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return reservationRepository.findAll(spec, pageable);
    }
}
