const bookings = [
    {
        id: "BK789",
        roomType: "Deluxe Suite",
        checkIn: "2024-03-20",
        checkOut: "2024-03-23",
        guests: "2 Adults",
        price: 1200,
        status: "Confirmed"
    },
    {
        id: "BK790",
        roomType: "Exexutive Room",
        checkIn: "2024-04-01",
        checkOut: "2024-04-03",
        guests: "1 Adults",
        price: 800,
        status: "Pending"
    }
]
document.addEventListener("DOMContentLoaded", async () => {
    const bookingscontainer = document.getElementById("bookings-container")
    bookings.forEach((booking) => {
        const bookingcard = document.createElement("div")
        bookingcard.classList.add("booking-card");
        bookingcard.innerHTML = `
        <div class="booking-details">
          <div class="booking"><span class="booking-title">${booking.roomType}</span>
          <span class="booking-status ${booking.status.toLowerCase()}">
          ${booking.status}
            </span></div>
          <div>Booking ID: ${booking.id}</div>
          <div class="details">
            <div class="checkin"><span/><i class="ph-thin ph-calendar-blank"></i>Check-in: <br><span style="margin:15px;"/>${booking.checkIn}</div>
            <div><span><i class="ph-thin ph-clock"></i></span>Check-out: <br><span style="margin:15px;"/>${booking.checkOut}</div>
            <div class="guest"><span/><i class="ph-thin ph-users"></i>Guests: <br><span style="margin:15px;">${booking.guests}</div>
          </div>
          <hr style="margin:10px;"/>
          <div class="price"><b>$${booking.price}</b></div>
        </div>`
        bookingscontainer.appendChild(bookingcard)
    })
})