package com.redbus.operator.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket_cost")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketCost {
    @Id
    @Column(name = "ticket_id", unique = true)
    private String ticketId;

    @OneToOne(mappedBy = "ticketCost")
    @JoinColumn(name = "bus_id")
    private BusOperator busOperator;

    private double cost;

    private String code;

    @Column(name = "discount_amount", unique = true)
    private double discountAmount;
}
