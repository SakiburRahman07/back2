package com.sakib.train_service.service;

import com.sakib.train_service.dao.BookDao;
import com.sakib.train_service.dao.SeatDao;
import com.sakib.train_service.model.Booking;
import com.sakib.train_service.model.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookDao bookDao;

    @Autowired
    private SeatDao seatDao;

    public Booking addBooking(Booking booking) throws Exception {
        // Retrieve the seat information for the specific train and date
        Optional<Seat> seatOpt = seatDao.findByTrainIdAndDate(booking.getTrainid(), booking.getDate());
        System.out.println(seatOpt);
        System.out.println(seatOpt.isPresent());

        if (seatOpt.isPresent()) {
            Seat seat = seatOpt.get();
            int availableSeat = seat.getAvailableSeat();
            int bookingSeats = Integer.parseInt(booking.getBookingseat());  // Convert bookingseat to an integer

            // Check if there are enough available seats
            if (availableSeat >= bookingSeats) {
                // Update the available seats
                seat.setAvailableSeat(availableSeat - bookingSeats);
                seatDao.save(seat);  // Save the updated seat information
                System.out.println("yes");

                // Save the booking
                return bookDao.save(booking);
            } else {
                // Not enough seats available
                throw new Exception("Not enough available seats");
            }
        } else {
            // No seat information found for the specific train and date
            throw new Exception("No seat information found for the specified train and date");
        }
    }
}
