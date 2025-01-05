document.addEventListener("DOMContentLoaded", () => {
  // Accept and Reject Buttons
  document.querySelectorAll(".accept-btn").forEach((btn) => {
    btn.addEventListener("click", (e) => {
      const bookingId = e.target.dataset.id;
      updateStatus(bookingId, "Confirmed");
    });
  });

  document.querySelectorAll(".reject-btn").forEach((btn) => {
    btn.addEventListener("click", (e) => {
      const bookingId = e.target.dataset.id;
      updateStatus(bookingId, "Rejected");
    });
  });

  // Update Status Function
  function updateStatus(id, status) {
    const row = document.querySelector(`button[data-id="${id}"]`).closest("tr");
    const statusCell = row.querySelector(".status");

    statusCell.textContent = status;
    statusCell.className = `status ${status.toLowerCase()}`;
    alert(`Booking ${id} has been updated to ${status}`);
  }
});
