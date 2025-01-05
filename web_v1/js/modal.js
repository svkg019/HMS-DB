class ReservationModal {
    constructor() {
        this.createModal();
        this.bindEvents();
    }

    createModal() {
        const modal = document.createElement('div');
        modal.className = 'modal-overlay';
        modal.innerHTML = `
            <div class="modal">
                <button class="modal-close">
                   <i class="ph ph-x"></i>
                </button>
                <div class="success-icon">
                   <i class="ph ph-check-fat"></i>
                </div>
                <h2 class="modal-title">Reservation Successful!</h2>
                <p class="modal-subtitle">Your booking has been confirmed. Please save your booking ID for future reference.</p>
                <div class="booking-details">
                    <div class="booking-detail">
                        <i class="ph ph-ticket"></i>
                        <span class="detail-label">Booking ID:</span>
                        <span class="detail-value booking-id"></span>
                    </div>
                    <div class="booking-detail">
                        <i class="ph ph-calendar-dots"></i>
                        <span class="detail-label">Check-in:</span>
                        <span class="detail-value check-in-date"></span>
                    </div>
                    <div class="booking-detail">
                        <i class="ph ph-calendar-dots"></i>
                        <span class="detail-label">Check-out:</span>
                        <span class="detail-value check-out-date"></span>
                    </div>
                    <div class="booking-detail">
                        <i class="ph ph-bed"></i>
                        <span class="detail-label">Room Type:</span>
                        <span class="detail-value room-type"></span>
                    </div>
                    <div class="booking-detail">
                        <i class="ph ph-user"></i>
                        <span class="detail-label">Guest Name:</span>
                        <span class="detail-value guest-name"></span>
                    </div>
                    <div class="booking-detail">
                        <i class="ph ph-address-book"></i>
                        <span class="detail-label">Contact:</span>
                        <span class="detail-value contact-number"></span>
                    </div>
                </div>
                <button class="modal-button">Continue</button>
            </div>
        `;
        document.body.appendChild(modal);
        this.modal = modal;
    }

    bindEvents() {
        const closeButton = this.modal.querySelector('.modal-close');
        const continueButton = this.modal.querySelector('.modal-button');
        
        closeButton.addEventListener('click', () => this.hide());
        continueButton.addEventListener('click', () => this.hide());
        
        this.modal.addEventListener('click', (e) => {
            if (e.target === this.modal) {
                this.hide();
            }
        });
    }

    show(reservationData) {
        this.updateDetails(reservationData);
        this.modal.classList.add('active');
        document.body.style.overflow = 'hidden';
    }

    hide() {
        this.modal.classList.remove('active');
        document.body.style.overflow = '';
    }

    updateDetails(data) {
        const {
            bookingId,
            checkIn,
            checkOut,
            roomType,
            guestName,
            contact
        } = data;

        this.modal.querySelector('.booking-id').textContent = bookingId;
        this.modal.querySelector('.check-in-date').textContent = this.formatDate(checkIn);
        this.modal.querySelector('.check-out-date').textContent = this.formatDate(checkOut);
        this.modal.querySelector('.room-type').textContent = roomType;
        this.modal.querySelector('.guest-name').textContent = guestName;
        this.modal.querySelector('.contact-number').textContent = contact;
    }

    formatDate(dateString) {
        return new Date(dateString).toLocaleDateString('en-US', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit'
        });
    }
}