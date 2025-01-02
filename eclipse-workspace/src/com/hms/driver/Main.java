package com.hms.driver;
import com.hms.classes.*;
import com.hms.DAO.*;

import java.util.*;



class Main {
    private static HotelService hotelService = new HotelService();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("+++++Hotel Management System+++++");
            System.out.println("1. User Management");
            System.out.println("2. Log-in");
            switch (sc.nextInt()) {
                case 1:
                    boolean flag = true;
                    do {
                        System.out.println("1. Register new user");
                        System.out.println("2. Update user");
                        System.out.println("3. Delete user");
                        System.out.println("4. View users");
                        System.out.println("5. Go back");
                        switch (sc.nextInt()) {
                            case 1:
                                registerNewUser();
                                break;
                            case 2:
                                updateUser();
                                break;
                            case 3:
                                deleteUser();
                                break;
                            case 4:
                                viewUsers();
                                break;
                            case 5:
                                flag = false;
                                break;
                            default:
                                System.out.println("Invalid option");
                                break;
                        }
                    } while (flag);
                    break;
                case 2:
                    System.out.println("*****Log-in Menu*****");
                    String role = login();
                    if (role.equals("admin")) {
                        boolean flag1 = true;
                        do {
                            System.out.println("+++++Admin Menu+++++");
                            System.out.println("1. Booking Hotel Services");
                            System.out.println("2. View Booking History");
                            System.out.println("3. View Booking History by ID");
                            System.out.println("4. Room Status");
                            System.out.println("5. Check-out Billing Invoice");
                            System.out.println("6. View Complaints");
                            System.out.println("7. Go-Back");
                            switch (sc.nextInt()) {
                                case 1:
                                    bookingService();
                                    break;
                                case 2:
                                    hotelService.viewAllBookingHistory();
                                    break;
                                case 3:
                                    viewBookingHistoryByID();
                                    break;
                                case 4:
                                    viewRoomStatusAdmin();
                                    break;
                                case 5:
                                    viewBill();
                                    break;
                                case 6:
                                    hotelService.viewComplaints();
                                    break;
                                case 7:
                                    flag1 = false;
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }

                        } while (flag1);
                    } else if (role.equals("user")) {
                        boolean flag1 = true;
                        do {
                            System.out.println("+++++Customer Menu+++++");
                            System.out.println("1. Reservation");
                            System.out.println("2. View Booking History");
                            System.out.println("3. Room Status");
                            System.out.println("4. Check-out Billing");
                            System.out.println("5. Complaints");
                            System.out.println("6. Contact");
                            System.out.println("7. Go-Back");
                            switch (sc.nextInt()) {
                                case 1:
                                    reservationService();
                                    break;
                                case 2:
                                    viewBookingHistoryByID();
                                    break;
                                case 3:
                                    hotelService.getRoomDetails();
                                    break;
                                case 4:
                                    payBill();
                                    break;
                                case 5:
                                    addComplaint();
                                    break;
                                case 6:
                                    hotelService.contactInformation();
                                    break;
                                case 7:
                                    flag1 = false;
                                    break;
                                default:
                                    System.out.println("Invalid option");
                                    break;
                            }
                        } while (flag1);
                    } else {
                        System.out.println("Invalid credentials");
                    }
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        } while (true);
    }

    public static void registerNewUser() {
        System.out.println("Enter your preferred UserId: ");
        String userId = sc.next(); sc.nextLine();
        System.out.println("Enter your full name: ");
        String name = sc.nextLine();
        System.out.println("Enter your email: ");
        String email = sc.nextLine();
        System.out.println("Enter your phone number: ");
        String phone = sc.nextLine();
        System.out.println("Enter your preferred password: ");
        String password = sc.nextLine();

        User user = new User(userId, name, email, phone, password);
        hotelService.registerUser(user);
        System.out.println("User registered successfully");
    }

    public static void updateUser() {
        System.out.println("Enter the userId: ");
        String userId = sc.next(); sc.nextLine();
        System.out.println("Enter the new email: ");
        String email = sc.next();
        hotelService.updateUser(userId, email);
    }

    public static void deleteUser() {
        System.out.println("Enter the userId: ");
        String userId = sc.next();
        hotelService.deleteUser(userId);
    }

    public static void viewUsers() {
        hotelService.viewUsers();
    }

    public static String login() {
        System.out.println("Enter your userId: ");
        String userId = sc.next();
        System.out.println("Enter your password: ");
        String password = sc.next(); sc.nextLine();
        if (hotelService.userVerification(userId, password)) {
            System.out.println("Logged in successfully");
            if (hotelService.isAdmin(userId)) {
                return "admin";
            } else {
                return "user";
            }
        }
        return "invalid";
    }

    public static void viewBookingHistoryByID() {
        System.out.println("Enter the customerID: ");
        String userId = sc.next();
        hotelService.viewBookingHistoryByID(userId);
    }

    public static void addComplaint() {
        System.out.print("Please enter your user ID: ");
        String userId = sc.next();
        System.out.print("Please enter your contact number: ");
        String contact = sc.next();
        System.out.print("Please enter your room number: ");
        int roomNumber = sc.nextInt();
        System.out.println("Type of complaint:");
        System.out.println("1. Poor housekeeping\n"
                + "2. Noisy guests\n"
                + "3. Uncomfortable beds\n"
                + "4. Slow service\n"
                + "5. Lack of amenities\n"
                + "6. Unfriendly staff\n"
                + "7. No Complain");
        int typeOfComplaint = sc.nextInt();
        String complaintType = "";
        switch (typeOfComplaint) {
            case 1:
                complaintType = "Poor housekeeping";
                break;
            case 2:
                complaintType = "Noisy guests";
                break;
            case 3:
                complaintType = "Uncomfortable beds";
                break;
            case 4:
                complaintType = "Slow service";
                break;
            case 5:
                complaintType = "Lack of amenities";
                break;
            case 6:
                complaintType = "Unfriendly staff";
                break;
            case 7:
                complaintType = "No Complain";
                break;
            default:
                complaintType = "No Complain";
        }
        System.out.println("Rating:");
        System.out.println("1. *****\n"
                + "2. ****\n"
                + "3. ***\n"
                + "4. **\n"
                + "5. *\n"
        );
        int rating = sc.nextInt();
        String feedback = "";
        switch (rating) {
            case 1:
                feedback = "*****";
                break;
            case 2:
                feedback = "****";
                break;
            case 3:
                feedback = "***";
                break;
            case 4:
                feedback = "**";
                break;
            case 5:
                feedback = "*";
                break;
            default:
                feedback = "*****";
        }
        Complaint complain = new Complaint(userId, contact, roomNumber, complaintType, feedback);
        hotelService.registerComplaint(complain);
        System.out.println("Complain registered successfully");
    }

    public static void payBill() {
        System.out.println("Please enter your user ID: ");
        String userId = sc.next();
        List<Booking> userBookings = hotelService.bookingFinder(userId);
        if (userBookings == null) {
            System.out.println("No booking found!");
        } else {
            for (Booking booking : userBookings) {
                hotelService.billCalculation(booking);
                int exit = 0;
                do {
                    System.out.println("Do you want to pay now?");
                    System.out.println("1. Yes");
                    System.out.println("2. Later");
                    int choice = sc.nextInt(); sc.nextLine();
                    switch (choice) {
                        case 1:
                            System.out.println("Please enter your payment details:");
                            System.out.println("Card holder name: ");
                            String name = sc.nextLine();
                            System.out.print("Card number: ");
                            String cardNumber = sc.next();
                            System.out.print("CVV: ");
                            int cvv = sc.nextInt(); sc.nextLine();
                            System.out.print("Expiry Date: ");
                            String ed = sc.next();
                            PaymentDetails pd = new PaymentDetails(userId, name, cardNumber, cvv, ed);
                            hotelService.addPaymentDetails(pd);
                            System.out.println("Payment Successful");
                            exit = 1;
                            break;
                        case 2:
                            exit = 1;
                            break;
                        default:
                            System.out.println("Invalid Option! Try again.");
                    }
                } while (exit == 0);
            }
        }
    }

    public static void viewBill() {
        System.out.println("Please enter the customer ID: ");
        String userId = sc.next();
        List<Booking> userBookings = hotelService.bookingFinder(userId);
        if (userBookings == null) {
            System.out.println("No booking found!");
        } else {
            for (Booking booking : userBookings) {
                hotelService.billCalculation(booking);
                int exit = 0;
                do {
                    System.out.println("Do you want to approve the payment?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println("Payment Approved!");
                            exit = 1;
                            break;
                        case 2:
                            exit = 1;
                            break;
                        default:
                            System.out.println("Invalid Option! Try again.");
                    }
                } while (exit == 0);
            }
        }
    }

    public static void reservationService() {
        int exit = 0;
        do {
            System.out.println("1. New reservation");
            System.out.println("2. Update reservation");
            System.out.println("3. Delete reservation");
            System.out.println("4. Go-Back");
            int choice = sc.nextInt(); sc.nextLine();
            switch (choice) {
                case 1:
                    newReservation();
                    break;
                case 2:
                    //updateBookingName();
                	System.out.println("Under development!");
                    break;
                case 3:
                    deleteReservation();
                    break;
                case 4:
                    exit = 1;
                    break;
                default:
                    System.out.println("Invalid Option! Try again.");
            }
        } while (exit == 0);
    }

    public static void newReservation() {
        System.out.println("Enter desired location: ");
        String location = sc.next();
        System.out.println("Enter the check-in date: ");
        String checkInDate = sc.next();
        System.out.println("Enter the check-out date: ");
        String checkOutDate = sc.next();
        System.out.println("Enter the room preference: ");
        int roomPreferenceID = 0;
        int exit = 0;
        do {
            System.out.println("1. 1-BHK");
            System.out.println("2. 2-BHK");
            System.out.println("3. 3-BHK");
            roomPreferenceID = sc.nextInt(); sc.nextLine();
            if (roomPreferenceID >= 1 && roomPreferenceID <= 3) {
                exit = 1;
            } else {
                System.out.println("Invalid Option! Try again.");
            }
        } while (exit == 0);
        System.out.println("Enter your name: ");
        String name = sc.nextLine();
        System.out.println("Enter your contact number: ");
        String contact = sc.next();
        System.out.println("Enter your customerID: ");
        String customerID = sc.next();
        int roomNumber = hotelService.checkRoomAvailability(roomPreferenceID, location);
        if (roomNumber == -1) {
            System.out.println("No room available!");
        } else {
            Booking booking = new Booking(customerID, name, contact, "", location, roomPreferenceID, checkInDate, checkOutDate, 0, roomNumber);
            hotelService.addBooking(booking);
            hotelService.changeIsBooked(roomNumber);
            hotelService.changeDateOfAvailability(roomNumber, checkOutDate);
            System.out.println("Reservation added successfully");
            System.out.println("Your reservation ID is: " + booking.getBookingId());
        }
    }

//    public static void updateBookingName() {
//        System.out.println("Enter the booking id: ");
//        int bookingId = sc.nextInt(); sc.nextLine();
//        System.out.println("Enter the new name: ");
//        String name = sc.nextLine();
//        hotelService.updateNameInBooking(bookingId, name);
//    }

    public static void deleteReservation() {
        System.out.println("Enter the booking id: ");
        int bookingId = sc.nextInt();
        hotelService.deleteBooking(bookingId);
    }

    public static void bookingService() {
        int exit = 0;
        do {
            System.out.println("1. New reservation");
            System.out.println("2. Update reservation");
            System.out.println("3. Delete reservation");
            System.out.println("4. Go-Back");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    newBookingAdmin();
                    break;
                case 2:
                    //updateRoomTypeInBooking();
                	System.out.println("Under development!");
                    break;
                case 3:
                    deleteReservation();
                    break;
                case 4:
                    exit = 1;
                    break;
                default:
                    System.out.println("Invalid Option! Try again.");
            }
        } while (exit == 0);
    }

//    public static void updateRoomTypeInBooking() {
//        System.out.println("Enter the booking id: ");
//        int bookingId = sc.nextInt();
//        System.out.println("Enter the new room type: ");
//        int roomPreferenceID = 0;
//        int exit = 0;
//        do {
//            System.out.println("1. 1-BHK");
//            System.out.println("2. 2-BHK");
//            System.out.println("3. 3-BHK");
//            roomPreferenceID = sc.nextInt();
//            if (roomPreferenceID >= 1 && roomPreferenceID <= 3) {
//                exit = 1;
//            } else {
//                System.out.println("Invalid Option! Try again.");
//            }
//        } while (exit == 0);
//        hotelService.updateRoomTypeInBooking(bookingId, roomPreferenceID);
//        System.out.println("Booking updated successfully");
//    }

    public static void newBookingAdmin() {
        System.out.println("Enter desired location: ");
        String location = sc.next();
        System.out.println("Enter the check-in date: ");
        String checkInDate = sc.next();
        System.out.println("Enter the check-out date: ");
        String checkOutDate = sc.next();
        System.out.println("Enter the room preference: ");
        int roomPreferenceID = 0;
        int exit = 0;
        do {
            System.out.println("1. 1-BHK");
            System.out.println("2. 2-BHK");
            System.out.println("3. 3-BHK");
            roomPreferenceID = sc.nextInt();
            if (roomPreferenceID >= 1 && roomPreferenceID <= 3) {
                exit = 1;
            } else {
                System.out.println("Invalid Option! Try again.");
            }
        } while (exit == 0);
        System.out.println("Enter your name: ");
        String name = sc.next();
        System.out.println("Enter your contact number: ");
        String contact = sc.next();
        System.out.println("Enter your customerID: ");
        String customerID = sc.next();
        int roomNumber = hotelService.checkRoomAvailability(roomPreferenceID, location);
        if (roomNumber == -1) {
            System.out.println("No room available!");
        } else {
            Booking booking = new Booking(customerID, name, contact, "", location, roomPreferenceID, checkInDate, checkOutDate, 0, roomNumber);
            hotelService.addBooking(booking);
            hotelService.changeIsBooked(roomNumber);
            hotelService.changeDateOfAvailability(roomNumber, checkOutDate);
            hotelService.billCalculation(booking);
            System.out.println("---------------------");
            System.out.println("Your reservation ID is: " + booking.getBookingId());
            System.out.println("Reservation added successfully");
        }
    }

    public static void viewRoomStatusAdmin() {
        hotelService.getRoomDetails();
        int exit = 0;
        do {
            System.out.println("1. Update room status");
            System.out.println("2. Update date of availability");
            System.out.println("3. Go-Back");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter the room number: ");
                    int roomNumber = sc.nextInt();
                    hotelService.changeIsBooked(roomNumber);
                    break;
                case 2:
                    System.out.println("Enter the room number: ");
                    int roomNumber1 = sc.nextInt();
                    System.out.println("Enter the new date of availability: ");
                    String date = sc.next();
                    hotelService.changeDateOfAvailability(roomNumber1, date);
                    break;
                case 3:
                    exit = 1;
                    break;
                default:
                    System.out.println("Invalid Option! Try again.");
            }
        } while (exit == 0);
    }
}