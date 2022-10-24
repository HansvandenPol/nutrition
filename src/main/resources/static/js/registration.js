$(document).ready(() => {
  $("#onRegisterFailure").hide();
  $("#onRegisterSuccess").hide();
  $("#spinnerRegister").hide();

  var button = $("#registrationBtn");
  button.attr('value', 'Create');
});

$("#registrationForm").submit(async function (e) {
  console.log("huh");
  e.preventDefault();
  $("#spinnerRegister").show();
  var button = $("#registrationBtn");
  document.getElementById("registrationBtn").lastChild.data = "Loading...";
 // button.text('Loading...');
  button.attr("disabled", true);
  var username = $("#usernameData").val().trim();
  var firstname = $("#firstNameData").val().trim();
  var lastname = $("#lastNameData").val().trim();
  var email = $("#emailAddressData").val().trim();
  var password = $("#passwordData").val().trim();
  var passwordConfirmation = $("#passwordConfirmData").val().trim();

  var userData = {firstName: firstname, lastName: lastname, email: email, username: username, password:password, passwordConfirm:passwordConfirmation};

  if (!await isPasswordCorrect(password, passwordConfirmation)) {
    return;
  }

  callPostRegister(userData);
});

async function isUsernameUniqueness(username) {
  var isUnique = await new Promise(resolve => {
    setTimeout(() => {
      resolve(true);
    }, 2000);
  });

  $("#invalidUsername").hide();

  if(!isUnique) {
    $("#invalidUsername").show();
    return false;
  }


  return true;
}

function isPasswordCorrect(password, passwordConfirmation) {
  $("#invalidPasswordConfirm").hide();
  $("#invalidPassword").hide();

  if(password !== passwordConfirmation) {
    $("#invalidPasswordConfirm").show();
    resetButton();
    return;
  }

  var formatSpecial = /[ `!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~]/;
  var formatDigit = /[\d]/;
  var formatCapital = /[A-Z]/;

  if(!formatSpecial.test(password) || !formatDigit.test(password) || !formatCapital.test(password)) {
    $("#invalidPassword").show();
    return false;
  }
  return true;
}

function callPostRegister(userData) {
  console.log(userData);
  var accountPromise = new Promise((resolve, reject) => {
    $.ajax({
      url: '/register',
      type: 'POST',
      contentType: "application/json",
      data: JSON.stringify(userData)
    }).done((data) => {
      setTimeout(() => {
        resolve(data);
      }, 2000);
    }).fail((jqXHR) => {
      var errorData = jqXHR.responseJSON;
      if(!errorData) {
        console.log("huh");
        errorData = JSON.parse(jqXHR.responseText);
      }

      setTimeout(() => {
        reject(errorData.message);
      }, 2000);
    });
  });

  accountPromise.then((successData) => {
    $("#onRegisterSuccess").show();
    setTimeout(() => {
      window.location.href = "/login";
      },2000);
    }).catch((errorData) => {
    console.log("failed");
    console.log(errorData);
    var errorMessageDomElem = $("#onRegisterFailure");
    console.log(errorMessageDomElem.text());
    errorMessageDomElem.text(errorMessageDomElem.text() + " " + errorData);
    errorMessageDomElem.show();
    var button = $("#registrationBtn");
    document.getElementById("registrationBtn").lastChild.data = "Create";
    button.attr("disabled", false);
  }).finally(() => {
    $("#spinnerRegister").hide();
  });
}

function resetButton() {
  $("#spinnerRegister").hide();
  document.getElementById("registrationBtn").lastChild.data = "Create";
  var button = $("#registrationBtn");
  button.attr("disabled", false);
}
