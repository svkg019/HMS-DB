// Data management
const bookingsData = {
    getBookings() {
        // In a real app, this would be an API call
        return [
            {
                id: 'BK789',
                roomType: 'Deluxe Room',
                checkIn: '2024-03-20',
                checkOut: '2024-03-25',
                //guests: '2 Adults',
                price: 800,
                status: 'confirmed'
            },
            {
                id: 'BK790',
                roomType: 'Executive Suite',
                checkIn: '2024-04-01',
                checkOut: '2024-04-03',
                //guests: '1 Adult',
                price: 1200,
                status: 'pending'
            }
        ];
    }
};

// UI Components
const bookingsUI = {
    createBookingCard(booking) {
        const card = document.createElement('div');
        card.className = 'booking-card';
        
        const statusClass = booking.status === 'confirmed' ? 'status-confirmed' : 'status-pending';
        const statusText = booking.status.charAt(0).toUpperCase() + booking.status.slice(1);
        
        const actions = booking.status === 'pending' 
            ? `<div class="booking-actions">
                <div class="booking-price">$${booking.price}</div>
                <button class="btn btn-approve" data-booking-id="${booking.id}">Approve</button>
                <button class="btn btn-reject" data-booking-id="${booking.id}">Reject</button>
               </div>`
            : `<div class="booking-price">$${booking.price}</div>`;

        card.innerHTML = `
            <div class="booking-header">
                <div class="booking-title-section">
                    <h3 class="booking-title">${booking.roomType}</h3>
                    <span class="booking-id">Booking ID: ${booking.id}</span>
                </div>
                <span class="booking-status ${statusClass}">${statusText}</span>
            </div>
            <div class="booking-content">
                <div class="booking-details">
                    <div class="detail-group">
                        <i class="ph ph-calendar-dots"></i>
                        <div>
                            <div class="detail-label">Check-in</div>
                            <div class="detail-value">${this.formatDate(booking.checkIn)}</div>
                        </div>
                    </div>
                    <div class="detail-group">
                        <i class="ph ph-calendar-dots"></i>
                        <div>
                            <div class="detail-label">Check-out</div>
                            <div class="detail-value">${this.formatDate(booking.checkOut)}</div>
                        </div>
                    </div>
                    <!--<div class="detail-group">
                        <img src="icons/user.svg" alt="Guests">
                        <div>
                            <div class="detail-label">Guests</div>
                            <div class="detail-value">${booking.guests}</div>
                        </div>
                    </div>-->
                </div>
                ${actions}
            </div>
        `;
        
        return card;
    },

    formatDate(dateString) {
        const date = new Date(dateString);
        return date.toLocaleDateString('en-US', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit'
        });
    }
};

// Event Handlers
const bookingsHandlers = {
    handleApprove(bookingId) {
        console.log(`Approving booking ${bookingId}`);
        alert(`Booking ${bookingId} approved!`);
        // In a real app, this would make an API call
        location.reload(); // Refresh to show updated status
    },

    handleReject(bookingId) {
        console.log(`Rejecting booking ${bookingId}`);
        alert(`Booking ${bookingId} rejected!`);
        // In a real app, this would make an API call
        location.reload(); // Refresh to show updated status
    },

    setupEventListeners() {
        document.addEventListener('click', (e) => {
            const target = e.target;
            if (target.classList.contains('btn-approve')) {
                this.handleApprove(target.dataset.bookingId);
            } else if (target.classList.contains('btn-reject')) {
                this.handleReject(target.dataset.bookingId);
            }
        });
    }
};

// Initialize
document.addEventListener('DOMContentLoaded', () => {
    const bookingCardsContainer = document.querySelector('.booking-cards');
    if (bookingCardsContainer) {
        // Clear any existing content
        bookingCardsContainer.innerHTML = '';
        
        // Render booking cards
        const bookings = bookingsData.getBookings();
        bookings.forEach(booking => {
            bookingCardsContainer.appendChild(bookingsUI.createBookingCard(booking));
        });

        // Setup event handlers
        bookingsHandlers.setupEventListeners();
    }
});