package com.redbus.operator.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bus_operators")
public class BusOperator {
    @Id
    @Column(name = "bus_id")
    private String busId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bus_id")
    private TicketCost ticketCost;


    @Column(name = "bus_number")
    private String busNumber;

    @Column(name = "bus_operator_company_name")
    private String busOperatorCompanyName;

    @Column(name = "bus_driver_name")
    private String driverName;

    @Column(name = "support_staff")
    private String supportStaff;

    @Column(name = "number_of_seats")
    private String numberSeats;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_date")
    @Temporal(TemporalType.DATE)
    private Date departureDate;

    @Column(name = "arrival_date")
    @Temporal(TemporalType.DATE)
    private Date arrivalDate;

    @Column(name = "total_travel_time")
    private double totalTravelTime;

    @Column(name = "bus_type")
    private String busType;

    @Column(name = "amenities")
    private String amenities;

    public String getNumericField() {
        return  getNumericField();
    }
}
