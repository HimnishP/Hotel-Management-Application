package com.hotelmanagementapplication.model.room;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DoubleBed extends Room {

    public DoubleBed(double price, Status status) {
        super(price, status);
    }
}
