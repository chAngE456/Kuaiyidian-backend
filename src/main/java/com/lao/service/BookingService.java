package com.lao.service;

import com.lao.pojo.Booking;

import java.util.List;

public interface BookingService {
    //获取订单列表
    List<Booking> getBookingList();

    List<Booking> getMySeekBookings(String uid, int state);

    List<Booking> getMyHelpBookings(String uid,int state);

    Booking getBookingDetail(String id);

    double getUserScore(String uid);

    int addBooking(Booking booking);

    public int updateBookingState(String bid, int state);

    public int updateBooking(Booking booking);

    public int scoreBooking(String bid, double score);

    public int deleteBooking(String id);

    String getRandomBid();

    int receivingOrder(String bid,String helperId);
}
