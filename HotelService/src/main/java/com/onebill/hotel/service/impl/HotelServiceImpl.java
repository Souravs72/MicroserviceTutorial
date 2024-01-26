package com.onebill.hotel.service.impl;

import com.onebill.hotel.service.entities.Hotel;
import com.onebill.hotel.service.exceptions.ResourceNotFoundException;
import com.onebill.hotel.service.repositories.HotelRepository;
import com.onebill.hotel.service.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;

    @Override
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Override
    public List<Hotel> getAll() {
        return hotelRepository.findAll();
    }

    @Override
    public Hotel get(String hotelId) {
        return hotelRepository.findById(hotelId).orElseThrow(() -> new ResourceNotFoundException("Hotel with given id "+ hotelId +" not found"));
    }
}
