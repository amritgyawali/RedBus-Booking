package com.redbus.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "bookings")
public class Booking {
    @Id
    @Column(name = "booking_id")
    private  String bookingId;

    @Column(name = "bus_id")
    private  String busId;

    @Column(name = "ticket_id")
    private  String ticketId;

    @Column(name = "bus_company")
    private  String busCompany;

    @Column(name = "`to`")
    private  String to;

    @Column(name = "`from`")
    private  String from;

    @Column(name = "first_name")
    private  String firstName;

    @Column(name = "last_name")
    private  String lastName;

    private  String email;

    private  String mobile;

    private  double price;

}
