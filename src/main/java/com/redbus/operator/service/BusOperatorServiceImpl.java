package com.redbus.operator.service;

import com.redbus.operator.entity.BusOperator;
import com.redbus.operator.entity.TicketCost;
import com.redbus.operator.payload.BusOperatorDto;
import com.redbus.operator.repository.BusOperatorRepository;
import com.redbus.operator.repository.TicketCostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.UUID;



@Service
public class BusOperatorServiceImpl implements BusOperatorService {
    private BusOperatorRepository busOperatorRepository;
    private TicketCostRepository ticketCostRepository;
    private ModelMapper modelMapper;

    public BusOperatorServiceImpl(BusOperatorRepository busOperatorRepository,
                                  TicketCostRepository ticketCostRepository,
                                  ModelMapper modelMapper) {
        this.busOperatorRepository = busOperatorRepository;
        this.ticketCostRepository = ticketCostRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public BusOperatorDto scheduleBus( BusOperatorDto busOperatorDto) {
        BusOperator busOperator = mapToEntity(busOperatorDto);

        // Set the ticketCost for BusOperator
        TicketCost ticketCost = new TicketCost();
        ticketCost.setTicketId(busOperatorDto.getTicketCost().getTicketId());
        ticketCost.setCost(busOperatorDto.getTicketCost().getCost());
        ticketCost.setCode(busOperatorDto.getTicketCost().getCode());
        ticketCost.setDiscountAmount(busOperatorDto.getTicketCost().getDiscountAmount());

        //set ticket-cost entity in the operator entity
        busOperator.setTicketCost(ticketCost);

        String busId = UUID.randomUUID().toString();
        busOperator.setBusId(busId);

        //save both entitys
        BusOperator savedBusSchedule = busOperatorRepository.save(busOperator);

        return mapToDto(savedBusSchedule);
    }


    BusOperator mapToEntity(BusOperatorDto busOperatorDto) {
        BusOperator busOperator = modelMapper.map(busOperatorDto, BusOperator.class);
        return busOperator;
    }

    BusOperatorDto mapToDto(BusOperator busOperator) {
        BusOperatorDto busOperatorDto = modelMapper.map(busOperator, BusOperatorDto.class);
        return busOperatorDto;
    }



    private BusOperatorDto convertToDto(BusOperator busOperator) {
        return modelMapper.map(busOperator, BusOperatorDto.class);
    }

    private BusOperator convertToEntity(BusOperatorDto busOperatorDto) {
        return modelMapper.map(busOperatorDto, BusOperator.class);
    }
}
