function viewRoomStatus() {
    const customerId = document.getElementById("customerId").value;
    const roomStatus = document.getElementById("room-status");
    const customerIdDisplay = document.getElementById("customer-id-display");
    const roomList = document.getElementById("room-list");

    customerIdDisplay.textContent = customerId;
    roomStatus.style.display = "block";

    // Mock data for room details
    const rooms = [
        {
            id: 1,
            details: "Standard Room",
            description: "Comfortable room with essential amenities",
            status: "Available",
            price: "$100.00 per night",
            occupancy: "2 Guests",
            booking: "No current booking"
        },
        {
            id: 2,
            details: "Deluxe Room",
            description: "Spacious room with premium amenities and city view",
            status: "Occupied",
            price: "$200.00 per night",
            occupancy: "3 Guests",
            booking: "Check-in: 2024-03-20<br>Check-out: 2024-03-25<br>ID: CUST123"
        },
        {
            id: 3,
            details: "Executive Suite",
            description: "Luxury suite with separate living area and panoramic views",
            status: "Available",
            price: "$350.00 per night",
            occupancy: "4 Guests",
            booking: "No current booking"
        }
    ];

    // Generate dynamic rows for the table
    roomList.innerHTML = rooms.map(room => `
      <tr data-room-id="${room.id}">
        <td>
          <strong>${room.details}</strong><br>${room.description}
        </td>
        <td class="status ${room.status.toLowerCase()}">${room.status}</td>
        <td>${room.price}</td>
        <td>${room.occupancy}</td>
        <td>${room.booking}</td>
        <td>
          <button class="action-button ${room.status === 'Available' ? 'action-occupied' : 'action-available'}"
                  onclick="toggleRoomStatus(${room.id})">
            ${room.status === 'Available' ? 'Mark Occupied' : 'Mark Available'}
          </button>
        </td>
      </tr>
    `).join('');
}

function toggleRoomStatus(roomId) {
    // Find the row associated with the room
    const row = document.querySelector(`tr[data-room-id="${roomId}"]`);
    const statusCell = row.querySelector('.status');
    const actionButton = row.querySelector('.action-button');

    // Toggle the room status and button text
    if (statusCell.textContent === 'Available') {
        statusCell.textContent = 'Occupied';
        statusCell.classList.remove('available');
        statusCell.classList.add('occupied');
        actionButton.textContent = 'Mark Available';
        actionButton.classList.remove('action-occupied');
        actionButton.classList.add('action-available');
    } else {
        statusCell.textContent = 'Available';
        statusCell.classList.remove('occupied');
        statusCell.classList.add('available');
        actionButton.textContent = 'Mark Occupied';
        actionButton.classList.remove('action-available');
        actionButton.classList.add('action-occupied');
    }
}
