package com.lao.dao;


import com.lao.pojo.Booking;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BookingMapper {
    List<Booking> getBookingList();

    List<Booking> getBookingListByState(@Param("state")int state);

    List<Booking> getBookingListBySeekerId(@Param("seekerId") String seekerId,@Param("state") int state);

    List<Booking> getBookingListByHelperId(@Param("helperId") String seekerId,@Param("state") int state);

    double getScoreByUid(@Param("uid") String uid);

    int addBooking(Booking booking);

    Booking getBookingById(@Param("bid") String bid);

    int updateBooking(Booking booking);

    int updateBookingState(@Param("bid") String bid, @Param("state") int state);

    int updateBookingHelperId(@Param("bid") String bid, @Param("helperId") String helperId);

    int updateBookingScore(@Param("bid") String bid, @Param("star") double score);

    int updateOneValue(@Param("bid") String bid, @Param("name") String name, @Param("value") String value);

    int deleteBooking(@Param("bid") String bid);
}
