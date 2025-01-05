const but = document.querySelectorAll(".form-header span");
const comp = document.querySelectorAll(".change");
const comp1 = comp[0].innerHTML
but[0].addEventListener("click", () => {
    const comp = document.querySelectorAll(".change");
    const comp1 = comp[0].innerHTML
    comp[0].innerHTML = `<div class="change">
    <div style="display: flex; flex-direction: column; margin: 10px;">Complaint Type
      <select name="type" id="complaint">
        <option selected disabled>Select type</option>
        <option value="a">Hospitality</option>
        <option value="b">Behaviour</option>
        <option value="c">Room Service</option>
      </select>
    </div>
    <div class="rn" style="display: flex;flex-direction: column;">Room Number
      <input type="number">
    </div>
    <div class="description" style="display: flex; flex-direction: column; margin: 10px;">Description
      <textarea name="text" id="t"></textarea>
    </div>
    <div class="details" style="margin: 10px;">
      <span>Customer ID
        <input type="number">
      </span>
      <span>Contact Number
        <input type="number">
      </span>
    </div>
    <div class="submit">
      <button type="submit" style="background-color: red;color: white;border: none;
      padding: 10px;
      font-size: 16px;
      border-radius: 5px;
      display: block;
      margin: 10px auto;
      width: 100%;
      cursor: pointer;">Submit Complaint</button>
    </div>
  </div>
  <style>
    select {
      width: 700px;
    }

    select option {
      width: 700px;
    }

    .rn {
      margin: 10px
    }

    .rn input {
      width: 700px;
    }

    #t {
      width: 700px;
      height: 200px;
    }
  </style>`
})
but[1].addEventListener("click", () => {

    comp[0].innerHTML = comp1
    const stars = document.querySelectorAll(".rate-box i")
    // const stars = document.querySelector(".rate-box i");
    stars.forEach((star, index1) => {
        // Event Listener
        star.addEventListener("click", () => {
            console.log(index1);
            stars.forEach((star, index2) => {
                // Add active class for the stars that clicked or lower
                // Remove active class for higher stars
                if (index1 >= index2) {
                    star.classList.add("active");
                }
                else {
                    star.classList.remove("active");
                }
            })
        })
    })
})
