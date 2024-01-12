package com.redbus.user.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.entity.TicketCost;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.operator.repository.TicketCostRepository;
import com.redbus.user.entity.Booking;
import com.redbus.user.payload.BookingDetailsDto;
import com.redbus.user.payload.PassengerDetails;
import com.redbus.user.repository.BookingRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class BookingService {
    @Value("${stripe.api.Key}")
    private String stripeApiKey;

    private BusOperatorRepository busOperatorRepository;
    private TicketCostRepository ticketCostRepository;
    private BookingRepository bookingRepository;

    public BookingService(BusOperatorRepository busOperatorRepository, TicketCostRepository ticketCostRepository, BookingRepository bookingRepository) {
        this.busOperatorRepository = busOperatorRepository;
        this.ticketCostRepository = ticketCostRepository;
        this.bookingRepository = bookingRepository;
    }

    public BookingDetailsDto createBooking(String busId, String ticketId, PassengerDetails passengerDetails) {
        BusOperator bus = busOperatorRepository.findById(busId).get();
        TicketCost ticketCost = ticketCostRepository.findById(ticketId).get();

        String paymentIntent = createPaymentIntent((int) ticketCost.getCost());

        if (paymentIntent != null) {

            Booking booking = new Booking();
            String bookingId = UUID.randomUUID().toString();
            booking.setBookingId(bookingId);
            booking.setBusId(busId);
            booking.setTicketId(ticketId );
            booking.setTo(bus.getArrivalCity());
            booking.setFrom(bus.getDepartureCity());
            booking.setBusCompany(bus.getBusOperatorCompanyName());
            booking.setPrice(ticketCost.getCost());
            booking.setFirstName(passengerDetails.getFirstName());
            booking.setLastName(passengerDetails.getLastName());
            booking.setMobile(passengerDetails.getMobile());
            booking.setEmail(passengerDetails.getEmail());
            Booking ticketCreatedDetails = bookingRepository.save(booking);

            BookingDetailsDto dto = new BookingDetailsDto();

            dto.setFrom(ticketCreatedDetails.getFrom());
            dto.setTo(ticketCreatedDetails.getTo());
            dto.setBookingId(ticketCreatedDetails.getBookingId());
            dto.setFirstName(ticketCreatedDetails.getFirstName());
            dto.setLastName(ticketCreatedDetails.getLastName());
            dto.setPrice(ticketCreatedDetails.getPrice());
            dto.setEmail(ticketCreatedDetails.getEmail());
            dto.setMobile(ticketCreatedDetails.getMobile());
            dto.setBusCompany(ticketCreatedDetails.getBusCompany());
            dto.setMessage("Booking Confirmed");
            return dto;
        } else {
            System.out.println("error");
        }
        return null;
    }
    public String createPaymentIntent(@RequestParam Integer amount) {
        Stripe.apiKey = stripeApiKey;

        try {
            PaymentIntent intent = PaymentIntent.create(new PaymentIntentCreateParams.Builder().setCurrency("usd").setAmount((long) amount * 100).build());
            return generateResponse(intent.getClientSecret());
        } catch (StripeException e) {
            return generateResponse("Error creating PaymentIntent: " + e.getMessage());
        }
    }
    private String generateResponse(String clientSecret) {
        return "{\"clientSecret\":\"" + clientSecret + "\"}";
    }
}
