package com.hotelmanagementapplication.controller.currentsession;

import com.hotelmanagementapplication.model.room.Room;
import com.hotelmanagementapplication.model.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSession {
    private static UserSession instance;
    private User currentUser;
    private Room currentRoom;

    private UserSession() {
    }

    /**
     * Method will get the UserSession
     *
     * @return The instance
     */
    public static UserSession getInstance() {
        if (instance == null) {
            synchronized (UserSession.class) {
                if (instance == null) {
                    instance = new UserSession();
                }
            }
        }
        return instance;
    }
}
