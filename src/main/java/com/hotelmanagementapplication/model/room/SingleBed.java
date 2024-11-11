package com.hotelmanagementapplication.model.room;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SingleBed extends Room {

    public SingleBed(double price, Status status) {
        super(price, status);
    }
}
