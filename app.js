//signups
const signUpForm = document.getElementById("signup-form");
const signUpButton = document.getElementById("signup-form-submit");

//login button in signup page
const signUpAlready = document.getElementById("login-already");

//.
const test = document.getElementById("testing");

//logins
const loginForm = document.getElementById("login-form");
const loginButton = document.getElementById("login-form-submit");

//WİLL USE ONCLİCK STİLL WORKİN ON THE WEBSİTE

loginButton.addEventListener('click' , (e) => {
    e.preventDefault();
    const usrnme = loginForm.username.value;
    const pswrd = loginForm.password.value;

    if (usrnme === '8561' && pswrd === 'dor') {
        // Display a message on the same page
        document.getElementById('testing').textContent = 'Niceee';
    } else {
        // Display an error message
        document.getElementById('testing').textContent = 'Incorrect username or password';
   }
   resetPage(document.getElementById('testing').textContent)

});

function resetPage(message) {
    const mainHolder = document.getElementById("main-holder");
    mainHolder.innerHTML = ''; // Clear the existing content

    // Create a new element for displaying the message
    const messageElement = document.createElement('div');
    messageElement.textContent = message;
    mainHolder.appendChild(messageElement)
}

