package com.lao.service.Impl;

import com.lao.dao.BookingMapper;
import com.lao.pojo.Booking;
import com.lao.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    BookingMapper bookingMapper;

    @Override
    public double getUserScore(String uid) {
        return bookingMapper.getScoreByUid(uid);
    }

    @Override
    public int scoreBooking(String bid, double score) {
        return bookingMapper.updateBookingScore(bid,score);
    }

    @Override
    public List<Booking> getBookingList() {
        return bookingMapper.getBookingListByState(0);
    }

    @Override
    public List<Booking> getMySeekBookings(String uid,int state) {
        return bookingMapper.getBookingListBySeekerId(uid,state);
    }

    @Override
    @Transactional
    public int updateBooking(Booking booking) {
        return bookingMapper.updateBooking(booking);
    }

    @Override
    public int deleteBooking(String id) {
        return bookingMapper.deleteBooking(id);
    }

    @Override
    public List<Booking> getMyHelpBookings(String uid, int state) {
        return bookingMapper.getBookingListByHelperId(uid,state);
    }

    @Override
    public Booking getBookingDetail(String id) {
        return bookingMapper.getBookingById(id);
    }

    @Override
    public int addBooking(Booking booking) {
        Booking newbooking = new Booking();
        newbooking.setBid(getRandomBid());
        newbooking.setBName(booking.getBName());
        newbooking.setTimeInfo(booking.getTimeInfo());
        newbooking.setPlaceInfo(booking.getPlaceInfo());
        newbooking.setSeekerId(booking.getSeekerId());
        newbooking.setHelperId(null);
        newbooking.setDetail(booking.getDetail());
        newbooking.setReward(booking.getReward());
        newbooking.setState(0);
        newbooking.setCreate_time(new Date(System.currentTimeMillis()));
        //System.out.println(newbooking);
       return bookingMapper.addBooking(newbooking);
    }

    @Override
    public String getRandomBid() {
        Random random = new Random();
        String bid = "";
        while (true){
            bid = "";
            for (int i = 0; i < 8; i++) {
                bid = bid + (char)(random.nextInt(10)+48);
            }
            if(bookingMapper.getBookingById(bid)==null){
                break;
            }
        }
        return bid;
    }

    @Override
    @Transactional
    public int receivingOrder(String bid,String helperId) {
        bookingMapper.updateBookingState(bid,1);
        return bookingMapper.updateBookingHelperId(bid,helperId);
    }

    @Override
    public int updateBookingState(String bid, int state) {
        return bookingMapper.updateBookingState(bid,state);
    }
}
