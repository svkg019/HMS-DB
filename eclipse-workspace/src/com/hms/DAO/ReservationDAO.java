package com.hms.DAO;
import com.hms.classes.*;

import java.util.List;
import java.util.ArrayList;

public class ReservationDAO {
    //Reservation List for Admin
    private List<Reservation> reservationList = new ArrayList<Reservation>();
    //Boooking History for User
    private List<Reservation> bookingHistory = new ArrayList<Reservation>();
    private int currentId = 1;

    public List<Reservation> getAllReservations() {
        return reservationList;
    }
    public List<Reservation> getAllBookingHistory() {
        return bookingHistory;
    }

    public void addReservation(Reservation reservation) {
        reservation.setReservationId(currentId++);
        reservationList.add(reservation);
        bookingHistory.add(reservation);
        System.out.println("Reservation Successful");
    }

    public void updateReservation(int reservationId, String name) {
        int i = 0;
        while (i < reservationList.size()) {
            Reservation reservation = reservationList.get(i);
            if (reservation.getReservationId() == reservationId) {
                reservation.setName(name);
                bookingHistory.set(i, reservation);
                System.out.println("User details are updated successfully");
                return;
            }
            i++;
        }
        System.out.println("Reservation not found");
    }

    public void deleteReservation(int reservationId) {
        for (Reservation reservation : reservationList) {
            if (reservation.getReservationId() == reservationId) {
                reservationList.remove(reservation);
                System.out.println("User details are deleted");
                return;
            }
        }
        System.out.println("Reservation not found");
    }
}

