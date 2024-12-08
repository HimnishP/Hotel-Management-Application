package com.hotelmanagementapplication.controller.l10n_i18n;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public class LocaleSingleton {
    private static LocaleSingleton instance;
    private Locale currentLocale;

    private LocaleSingleton() {
        currentLocale = Locale.of("en", "us");
    }

    public static LocaleSingleton getInstance() {
        if (instance == null) {
            synchronized (LocaleSingleton.class) {
                if (instance == null) {
                    instance = new LocaleSingleton();
                }
            }
        }
        return instance;
    }
}


