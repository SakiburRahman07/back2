package com.sakib.train_service.controller;

import com.sakib.train_service.model.Booking;
import com.sakib.train_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeatBookingController {

    @Autowired
    private BookingService bookingService;




    @PostMapping("/booking")
    public ResponseEntity<?> booking(@RequestBody Booking booking) {
        try {
            Booking booking1 = bookingService.addBooking(booking);
            return ResponseEntity.ok(booking1);
        } catch (Exception e) {
            // Handle the exception, return an appropriate response
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
