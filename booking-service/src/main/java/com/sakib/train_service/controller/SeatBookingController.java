package com.sakib.train_service.controller;

import com.sakib.train_service.model.Booking;
import com.sakib.train_service.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SeatBookingController {

    @Autowired
    private BookingService bookingService;




//    @PostMapping("/booking")
//    public ResponseEntity<?> booking(@RequestBody Booking booking) {
//        try {
//            Booking booking1 = bookingService.addBooking(booking);
//            return ResponseEntity.ok(booking1);
//        } catch (Exception e) {
//            // Handle the exception, return an appropriate response
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

    @PostMapping("/booking")
    public ResponseEntity<?> booking(@RequestBody Booking booking) {
        try {
            Booking booking1 = bookingService.addBooking(booking);
            return ResponseEntity.ok(booking1);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/booking/{bookingId}/verify-otp")
    public ResponseEntity<?> verifyOTP(@PathVariable Long bookingId, @RequestParam int otp) {
        try {
            Booking booking = bookingService.getBookingById(bookingId);  // Implement getBookingById method
            bookingService.verifyOTP(booking, otp);
            return ResponseEntity.ok("OTP Verified Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }



    @PostMapping("/availableseat")
    public ResponseEntity<Integer> getAvailableSeat(@RequestParam Long trainId, @RequestParam String date) {
        // Fetch the available seat information from the service
        Integer availableSeats = bookingService.getAvailableSeats(trainId, date);

        // Return the number of available seats
        return ResponseEntity.ok(availableSeats);
    }


}
