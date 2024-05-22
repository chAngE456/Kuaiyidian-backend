package com.lao.controller;

import com.lao.pojo.Booking;
import com.lao.service.BookingService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/booking")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @GetMapping("/list")
    public ResponseEntity<?> getListData(){
        List<Booking> list = bookingService.getBookingList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/myList")
    public ResponseEntity<?> getMyListData(@RequestParam("uid") String uid){
        System.out.println(uid);
        Map<String,List<Booking>> map = new HashMap<>();
        List<Booking> seekList = new ArrayList<>();
        seekList.addAll(bookingService.getMySeekBookings(uid,0));
        seekList.addAll(bookingService.getMySeekBookings(uid,1));
        seekList.addAll(bookingService.getMySeekBookings(uid,2));
        map.put("seekList",seekList);

        List<Booking> helpList = new ArrayList<>();
        helpList.addAll(bookingService.getMyHelpBookings(uid,1));
        helpList.addAll(bookingService.getMyHelpBookings(uid,2));
        map.put("helpingList",helpList);

        List<Booking> overList = new ArrayList<>();
        overList.addAll(bookingService.getMySeekBookings(uid,3));
        overList.addAll(bookingService.getMyHelpBookings(uid,3));
        map.put("overList",overList);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("/getuseravgscore")
    public ResponseEntity<?> getUserAVGScore(@RequestParam("uid") String uid){
        double sc = bookingService.getUserScore(uid);
        return new ResponseEntity<>(sc, HttpStatus.OK);
    }

    @RequestMapping("/{bid}")
    public ResponseEntity<?> getBookingDetail(@PathVariable String bid){
        Booking booking = bookingService.getBookingDetail(bid);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBooking(@RequestBody Booking booking){
        //System.out.println(booking);
        bookingService.addBooking(booking);
        return new ResponseEntity<>("添加成功", HttpStatus.OK);
    }

    @GetMapping("/taking")
    public ResponseEntity<?> orderTaking(@RequestParam("bid") String bid,@RequestParam("helperId") String helperId){
        bookingService.receivingOrder(bid, helperId);
        return ResponseEntity.ok("接单成功");
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateBooking(@RequestBody Booking booking){
        bookingService.updateBooking(booking);
        return ResponseEntity.ok("更新成功");
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteBooking(@RequestParam("bid") String bid){
        bookingService.deleteBooking(bid);
        return ResponseEntity.ok("删除订单成功");
    }

    @GetMapping("/statechange")
    public ResponseEntity<?> changeState(@RequestParam("bid") String bid,@RequestParam("state") int state){
       bookingService.updateBookingState(bid, state);
       return ResponseEntity.ok("已确认");
    }

    @GetMapping("/score")
    public ResponseEntity<?> scoreForOrder(@RequestParam("bid") String bid,@RequestParam("star") double star){
        bookingService.scoreBooking(bid, star);
        return ResponseEntity.ok("评分成功");
    }
}
