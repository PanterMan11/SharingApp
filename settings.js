document.addEventListener("DOMContentLoaded", function () {
    // Fetch the JSON data using Fetch API or XMLHttpRequest
    fetch("accounts.json")
        .then(response => response.json())
        .then(data => {
            // Select the HTML div where you want to display the JSON data
            const dataContainer = document.getElementById("data-container");

            // Create HTML elements to display the data
            const nameElement = document.createElement("p");
            nameElement.textContent = "Name: " + data.username;

            const ageElement = document.createElement("p");
            ageElement.textContent = "ID: " + data.id;

            const emailElement = document.createElement("p");
            emailElement.textContent = "Email: " + data.email;

            // Append the elements to the div
            dataContainer.appendChild(nameElement);
            dataContainer.appendChild(ageElement);
            dataContainer.appendChild(emailElement);
        })
        .catch(error => {
            console.error("Error fetching JSON data: ", error);
        });
});
