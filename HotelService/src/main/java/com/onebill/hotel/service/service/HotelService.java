package com.onebill.hotel.service.service;

import com.onebill.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    Hotel createHotel(Hotel hotel);

    List<Hotel> getAll();

    Hotel get(String hotelId);
}
