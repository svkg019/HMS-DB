const form = document.getElementById("form");
const name = document.getElementById("name");
const username = document.getElementById("username");
const password = document.getElementById("password");
const confirm = document.getElementById("confirm");
const reset1 = document.getElementById("reset");
const modal = document.getElementById("myModal");
const modalButton = document.getElementById("modalButton");

//reset the form

reset1.addEventListener("click", function () {
  console.log("hello");
  // location.reload();
  document.getElementById("name").value = "";
  document.getElementById("email").value = "";
  document.getElementById("mobile-number").value = "";
  document.getElementById("address").value = "";
  document.getElementById("username").value = "";
  document.getElementById("password").value = "";
  document.getElementById("confirm").value = "";
  alert("Form has been reset.");
});

form.addEventListener("submit", (e) => {
  console.log("before");
  e.preventDefault();
  console.log("after");

  let isValid = true;
  if (name.value.trim() === "") {
    alert("Please fill name");
    isValid = false;
    return;
  }

  if (name.value.length > 30) {
    alert("name is not valid");
    isValid = false;
    return;
  }

  if (username.value.trim() === "") {
    alert("Please fill username");
    isValid = false;
    return;
  }

  if (username.value.length < 5 || username.value.length > 20) {
    alert("Username is not valid");
    isValid = false;
    return;
  }

  if (password.value.trim() === "") {
    alert("Please fill password");
    isValid = false;
    return;
  }

  const hasUppercase = /[A-Z]/.test(password.value);
  const hasLowercase = /[a-z]/.test(password.value);
  const hasSpecialChar = /[^\w\s]/.test(password.value);
  // const hasSpecialChar=/[!@#$%^&*()_+-=[]{}|;':",.<>/?]/.test(password.value);
  if (
    password.value.length > 30 ||
    !hasUppercase ||
    !hasLowercase ||
    !hasSpecialChar
  ) {
    alert("password is not valid...");
    isValid = false;
    return;
  }

  if (confirm.value.trim() === "") {
    alert("Please fill confirm password");
    isValid = false;
    return;
  }

  const hasUppercase1 = /[A-Z]/.test(confirm.value);
  const hasLowercase1 = /[a-z]/.test(confirm.value);
  const hasSpecialChar1 = /[^\w\s]/.test(confirm.value);
  // const hasSpecialChar=/[!@#$%^&*()_+-=[]{}|;':",.<>/?]/.test(password.value);
  if (
    password.value != confirm.value ||
    confirm.value.length > 30 ||
    !hasUppercase1 ||
    !hasLowercase1 ||
    !hasSpecialChar1
  ) {
    alert("Please enter the same password...");
    isValid = false;
    return;
  }

  if (isValid) {
    modalButton.click();
    console.log("form submitted", username.value, password.value);
  }
});

// let username=declaration1("username"),
// email=declaration1("email"),
// password=declaration1("password"),
// form=declaration1("form"),
// error=declaration2("error"),
// failure=declaration2("failure-icon"),
// success=declaration2("success-icon");

// let input=document.getElementsByTagName("input");
// console.log(input)

// function declaration1(name1)
// {
//     return document.getElementById(name1);

// }
// function declaration2(name2)
// {
//     return document.getElementsByClassName(name2);

// }

// function engine(elm,serial,message)
// {
//     if(elm.value.trim() === "")
//     {
//         error[serial].innerText=message;
//         failure[serial].style.opacity=1;
//         success[serial].style.opacity=0;
//         elm.style.borderColor="red";
//     }
//     else
//     {
//         error[serial].innerText="";
//         failure[serial].style.opacity=0;
//         success[serial].style.opacity=1;
//         elm.style.borderColor="green";
//     }
// }
// form.addEventListener("submit",(e)=>
// {
//     e.preventDefault();
//     engine(username,0,"Username cannot be blank");
//     engine(email,1,"Email cannot be blank");
//     engine(password,2,"Password cannot be blank");

// })
