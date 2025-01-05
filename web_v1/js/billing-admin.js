document.addEventListener("DOMContentLoaded", () => {
  const userSearchForm = document.getElementById("userSearchForm");
  const invoiceDetails = document.getElementById("invoiceDetails");
  const viewMode = document.getElementById("viewMode");
  const editMode = document.getElementById("editMode");
  const editBtn = document.getElementById("editBtn");
  const downloadBtn = document.getElementById("downloadBtn");
  const addServiceBtn = document.getElementById("addServiceBtn");
  const cancelEditBtn = document.getElementById("cancelEditBtn");
  const servicesContainer = document.getElementById("servicesContainer");

  // Handle user search
  userSearchForm.addEventListener("submit", (e) => {
    e.preventDefault();
    invoiceDetails.classList.remove("hidden");
  });

  // Toggle edit mode
  editBtn.addEventListener("click", () => {
    viewMode.classList.add("hidden");
    editMode.classList.remove("hidden");
  });

  // Cancel edit
  cancelEditBtn.addEventListener("click", () => {
    editMode.classList.add("hidden");
    viewMode.classList.remove("hidden");
  });

  // Add new service row
  addServiceBtn.addEventListener("click", () => {
    const serviceRow = document.createElement("div");
    serviceRow.className = "service-row";
    serviceRow.innerHTML = `
            <input type="text" placeholder="Service name">
            <input type="number" placeholder="Amount">
        `;
    servicesContainer.appendChild(serviceRow);
  });

  // Handle form submission
  editMode.addEventListener("submit", (e) => {
    e.preventDefault();
    editMode.classList.add("hidden");
    viewMode.classList.remove("hidden");
    // Here you would normally update the view mode with the new values
  });

  // Download invoice
  downloadBtn.addEventListener("click", () => {
    const invoiceContent = generateInvoiceContent();
    downloadTxtFile(invoiceContent, "invoice.txt");
  });

  function generateInvoiceContent() {
    return `
===========================================
            LUXURY HOTELS
===========================================

Invoice Details
--------------
Booking ID: ${document.getElementById("bookingId").textContent}
Date: ${new Date().toLocaleDateString()}

Room Charges: $350.00

Additional Services
-----------------
Room Service: $50.00
Spa Services: $120.00

Summary
-------
Subtotal: $520.00
Tax (10%): $52.00
Total: $572.00

===========================================
Thank you for choosing Luxury Hotels!
For any queries, please contact our support.
===========================================
        `.trim();
  }

  function downloadTxtFile(content, filename) {
    const blob = new Blob([content], { type: "text/plain" });
    const url = URL.createObjectURL(blob);
    const link = document.createElement("a");
    link.href = url;
    link.download = filename;
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
    URL.revokeObjectURL(url);
  }
});
