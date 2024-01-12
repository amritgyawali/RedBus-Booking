package com.redbus.operator.repository;

import com.redbus.operator.entity.BusOperator;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


public interface BusOperatorRepository extends JpaRepository<BusOperator, String> {

    List<BusOperator> findByDepartureCityAndArrivalCityAndDepartureDate(String departureCity, String arrivalCity, Date departureDate);

    @Query("SELECT bo FROM BusOperator bo WHERE (:departureCity is null OR bo.departureCity = :departureCity) AND (:arrivalCity is null OR bo.arrivalCity = :arrivalCity) AND (:departureDate is null OR bo.departureDate = :departureDate)")
    List<BusOperator> searchByCitiesAndDate(@Param("departureCity") String departureCity, @Param("arrivalCity") String arrivalCity, @Param("departureDate") Date departureDate);

//    @Query("SELECT bo from BusOperator where bo.email=:email")
//    Optional<BusOperator> searchByEmail(@Param("email") String email);

}
