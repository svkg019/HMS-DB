function logout() {
  alert("Logged out successfully!");
  window.location.reload();
}

function goToHome() {
  alert("Redirecting to Home Page");
}

function goToPaymentPage() {
  document.querySelector(".payment-container").style.display = "none";
  document.getElementById("cardDetails").style.display = "block";
}

function goToBillPage() {
  document.getElementById("cardDetails").style.display = "none";
  document.querySelector(".payment-container").style.display = "block";
}

document.getElementById("cardForm").addEventListener("submit", function (e) {
  e.preventDefault();
  const transactionID = Math.floor(Math.random() * 1000000000);
  alert(`Payment Successful! Transaction ID: ${transactionID}`);
});
