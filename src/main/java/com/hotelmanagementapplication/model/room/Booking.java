package com.hotelmanagementapplication.model.room;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
