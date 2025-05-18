package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.model.Reservation;
import com.databaseinterface.restaurant.repository.ReservationRepository;
import com.databaseinterface.restaurant.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Controller
@RequestMapping("/admin/reservations")
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public String listReservations(
            @RequestParam(required = false) String clientName,
            @RequestParam(required = false) Integer tableId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            Model model) {

        Page<Reservation> reservations = reservationService.findReservations(clientName, tableId, date, page, size);
        model.addAttribute("reservations", reservations);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", reservations.getTotalPages());
        return "admin/reservations";
    }


    @PostMapping("/add")
    public String addReservation(Reservation reservation) {
        reservationRepository.save(reservation);
        return "redirect:/admin/reservations";
    }

    @GetMapping("/{id}/edit")
    public String editReservation(@PathVariable("id") int id, Model model) {
        Reservation res = reservationRepository.findById(id).orElse(null);
        if (res != null) {
            model.addAttribute("reservation", res);
            return "admin/reservation_edit";
        }
        return "redirect:/admin/reservations";
    }

    @PostMapping("/{id}/update")
    public String updateReservation(@PathVariable("id") int id, Reservation reservation) {
        reservation.setId(id);
        reservationRepository.save(reservation);
        return "redirect:/admin/reservations";
    }

    @GetMapping("/{id}/delete")
    public String deleteReservation(@PathVariable("id") int id) {
        reservationRepository.deleteById(id);
        return "redirect:/admin/reservations";
    }
}
