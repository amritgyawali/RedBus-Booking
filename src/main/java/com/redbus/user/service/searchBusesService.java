package com.redbus.user.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.user.payload.BusListDto;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class searchBusesService {
    private BusOperatorRepository busOperatorRepository;
    public searchBusesService(BusOperatorRepository busOperatorRepository) {
        this.busOperatorRepository = busOperatorRepository;
    }


        public List<BusListDto> searchBusBy(String departureCity, String arrivalCity, Date departureDate) {
            List<BusOperator> busesAvailable = busOperatorRepository
                    .findByDepartureCityAndArrivalCityAndDepartureDate(
                            departureCity, arrivalCity, departureDate);

            List<BusListDto> dtos = busesAvailable.stream()
                    .filter(bus -> isValidNumeric(bus.getNumericField()))
                    .map(bus -> mapToDto(bus))
                    .collect(Collectors.toList());

            return dtos;
        }

        private boolean isValidNumeric(String numericValue) {
            try {
                Double.parseDouble(numericValue);
                return true;
            } catch (NumberFormatException | NullPointerException e) {
                return false;
            }
        }


        BusListDto mapToDto(BusOperator busOperator){
        BusListDto busListDto=new BusListDto();
        busListDto.setBusId(busOperator.getBusId());
        busListDto.setBusNumber(busOperator.getBusNumber());
        busListDto.setBusType(busOperator.getBusType());
        busListDto.setAmenities(busOperator.getAmenities());
        busListDto.setArrivalDate(busOperator.getArrivalDate());
        busListDto.setBusOperatorCompanyName(busOperator.getBusOperatorCompanyName());
        busListDto.setArrivalTime(busOperator.getArrivalTime());
        busListDto.setDepartureTime(busOperator.getDepartureTime());
        busListDto.setDepartureDate(busOperator.getDepartureDate());
        busListDto.setTotalTravelTime(busOperator.getTotalTravelTime());
        busListDto.setNumberSeats(busOperator.getNumberSeats());
        busListDto.setArrivalCity(busOperator.getArrivalCity());
        busListDto.setDepartureCity(busOperator.getDepartureCity());
        busListDto.setSupportStaff(busOperator.getSupportStaff());
        busListDto.setDriverName(busOperator.getDriverName());
        return busListDto;
    }
}
