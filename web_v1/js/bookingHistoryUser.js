// user-dashboard.js

document.addEventListener("DOMContentLoaded", () => {
  // Sidebar toggle functionality
  const sidebarLinks = document.querySelectorAll(".sidebar a");
  sidebarLinks.forEach((link) => {
    link.addEventListener("click", () => {
      sidebarLinks.forEach((el) => el.classList.remove("active"));
      link.classList.add("active");
    });
  });

  // Fetch bookings data (simulating a server request)
  const bookings = [
    {
      id: "BK789",
      roomType: "Deluxe Suite",
      checkIn: "2024-03-20",
      checkOut: "2024-03-25",
      price: 1200,
      guests: 2,
      status: "Confirmed",
    },
    {
      id: "BK790",
      roomType: "Executive Room",
      checkIn: "2024-04-01",
      checkOut: "2024-04-03",
      price: 800,
      guests: 1,
      status: "Pending",
    },
  ];

  // Render bookings
  const bookingContainer = document.querySelector(".bookings-container");
  if (bookingContainer) {
    bookings.forEach((booking) => {
      const bookingCard = `
        <div class="booking-card">
          <h3>${booking.roomType}</h3>
          <p>Booking ID: ${booking.id}</p>
          <p>Check-in: ${booking.checkIn}</p>
          <p>Check-out: ${booking.checkOut}</p>
          <p>Guests: ${booking.guests} Adults</p>
          <p>Price: $${booking.price}</p>
          <span class="status ${booking.status.toLowerCase()}">${
        booking.status
      }</span>
        </div>
      `;
      bookingContainer.innerHTML += bookingCard;
    });
  }
});
