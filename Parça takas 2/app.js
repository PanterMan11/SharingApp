

/* function checkInputs() {
  const usrnme = usernameInput.value;
  const pswrd = passwordInput.value;

  if (usrnme === "yes" && pswrd === "no") {
      console.log("Nicee");
  }else{
    console.log("Thanks")
  }
  // Add more validation or processing logic as needed
}
        
usernameInput.addEventListener('keyup', function() {
  checkInputs();
});

passwordInput.addEventListener('keyup', function() {
  checkInputs();
});
    */
//
//
//
// Simulate the FileManagement functionality with localStorage



const usernameInput = document.getElementById("Ur");
const passwordInput = document.getElementById("Pr");
const loginButton = document.getElementById("loginButton");


const jsonData = document.getElementById("jsonData")
const jsonDataText = jsonData.textContent;


const id = jsonData.id;
const password = jsonData.password;

const m_jsonData = JSON.parse(jsonDataText);

function readJsonArray(file) {
  const jsonString = localStorage.getItem(file);
  return JSON.parse(jsonString) || [];
}

function quickSaveJson(file, data) {
  localStorage.setItem(file, JSON.stringify(data));
}

const error = {
  SUCCESS: 'SUCCESS',
  ID_ALREADY_EXISTS: 'ID_ALREADY_EXISTS',
  NAME_ALREADY_EXISTS: 'NAME_ALREADY_EXISTS',
  PASSWORD_OR_ID_WRONG: 'PASSWORD_OR_ID_WRONG',
  TOKEN_ERROR: 'TOKEN_ERROR',
};

function checkInputs(){
  const usrnme = usernameInput.value;
  const pswrd = passwordInput.value;


  const loginRes = login(usrnme, pswrd);

  if (loginRes === error.SUCCESS) {
    console.log("Logged in!");
  } else if (loginRes === error.PASSWORD_OR_ID_WRONG) {
      console.log("Not logged in");
  }

};
const loginForm = document.getElementById("FormElement");

loginForm.addEventListener('submit', function(event) {
  event.preventDefault(); // Prevent the default form submission behavior
  checkInputs();
});



console.log("Yes")

function login(user, pass) {
  for (const userData of m_jsonData.data) {
    if (userData.id === user && userData.password === pass) {
      return error.SUCCESS;
    }
  }
  return error.PASSWORD_OR_ID_WRONG;
}
