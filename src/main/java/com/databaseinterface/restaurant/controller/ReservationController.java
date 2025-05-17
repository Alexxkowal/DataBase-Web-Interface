package com.databaseinterface.restaurant.controller;

import com.databaseinterface.restaurant.repository.ReservationRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/reservations")
public class ReservationController {
    private final ReservationRepository reservationRepository;

    public ReservationController (ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }
    @GetMapping
    public String listClients(Model model) {
        model.addAttribute("reservations", reservationRepository.findAll());
        return "admin/reservations";
    }
}