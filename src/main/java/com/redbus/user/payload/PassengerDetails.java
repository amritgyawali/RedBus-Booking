package com.redbus.user.payload;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class PassengerDetails {
    private  String firstName;
    private  String lastName;
    private  String email;
    private  String mobile;
}
