package com.hotelmanagementapplication.model;

import java.time.LocalDate;

public class Booking {
    private int bookingNo;
    private int roomId;
    private LocalDate bookingDate;

    public Booking(int bookingNo, int roomId, LocalDate bookingDate) {
        this.bookingNo = bookingNo;
        this.roomId = roomId;
        this.bookingDate = bookingDate;
    }
}
