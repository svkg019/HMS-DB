document.addEventListener('DOMContentLoaded', () => {
    // Get form elements
    const form = document.querySelector('.reservation-form');
    const checkIn = document.getElementById('check-in');
    const checkOut = document.getElementById('check-out');

    // Initialize date inputs with today and tomorrow as min dates
    const today = new Date();
    const tomorrow = new Date(today);
    tomorrow.setDate(tomorrow.getDate() + 1);

    // Format date to YYYY-MM-DD
    const formatDate = (date) => {
        return date.toISOString().split('T')[0];
    };

    // Set min dates
    checkIn.min = formatDate(today);
    checkOut.min = formatDate(tomorrow);

    // Update check-out min date when check-in changes
    checkIn.addEventListener('change', (e) => {
        const newMinDate = new Date(e.target.value);
        newMinDate.setDate(newMinDate.getDate() + 1);
        checkOut.min = formatDate(newMinDate);
        
        // Clear check-out if it's before new min date
        if (checkOut.value && new Date(checkOut.value) <= new Date(e.target.value)) {
            checkOut.value = '';
        }
    });

    // Handle room selection
    const roomCards = document.querySelectorAll('.room-card');
    roomCards.forEach(card => {
        card.addEventListener('click', () => {
            // Find the radio input within the clicked card
            const radio = card.querySelector('input[type="radio"]');
            radio.checked = true;
            
            // Remove selected class from all cards
            roomCards.forEach(c => c.classList.remove('selected'));
            // Add selected class to clicked card
            card.classList.add('selected');
        });
    });

    // Handle form submission
    form.addEventListener('submit', (e) => {
        e.preventDefault();
        
        // Get form data
        const formData = {
            checkIn: checkIn.value,
            checkOut: checkOut.value,
            roomType: document.querySelector('input[name="room-type"]:checked')?.value,
            fullName: document.getElementById('full-name').value,
            contact: document.getElementById('contact').value
        };

        // Validate form data
        if (!formData.checkIn || !formData.checkOut || !formData.roomType || 
            !formData.fullName || !formData.contact) {
            alert('Please fill in all fields');
            console.log("Please fill in all fields");
            return;
        }

        // Generate a random booking ID (in real app, this would come from the server)
        const bookingId = 'BK' + Math.random().toString(36).substr(2, 8).toUpperCase();

        // Show success modal
        const modal = new ReservationModal();
        modal.show({
            bookingId,
            checkIn: formData.checkIn,
            checkOut: formData.checkOut,
            roomType: formData.roomType,
            guestName: formData.fullName,
            contact: formData.contact
        });
    });
});