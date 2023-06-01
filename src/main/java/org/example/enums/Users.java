package org.example.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Users {

    Ivan("Иван", "Иванов", "Москва","79257327296", "22.06.2023", "Привезите после 18:00"),
    Petr("Петр", "Петров", "Москва", "79257327297", "22.06.2023", "Привезите после 18:00");


    private final String firstName;
    private final String lastName;
    private final String address;
    private final String phoneNumber;
    private final String date;
    private final String comment;
}
