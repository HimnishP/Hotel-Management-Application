package com.hotelmanagementapplication.controller.currentsession;

import com.hotelmanagementapplication.model.user.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSession {
    private static UserSession instance;
    private User currentUser;

    private UserSession() {
    }

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
