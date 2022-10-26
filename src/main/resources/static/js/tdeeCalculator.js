var isAdvancedSet = false;

$("#flexSwitchAdvanced").change(function () {
  if(this.checked) {
    isAdvancedSet = true;
  } else {
    isAdvancedSet = false;
  }

  $("#formulaSelection").toggle();
});

$("#tdeeForm").submit((event) => {
  event.preventDefault();
  var height = $("#heightData").val();
  var weight = $("#weightData").val();
  var age = $("#ageData").val();
  var sex = getSex();
  var isMetric = true;
  var activityLevel = $("#activityLvl").find(":selected").val();
  var tdeeFormula = isAdvancedSet ? $("#tdeeMethods").find(":selected").val() : 0;

  var tdeeData = {
    sex: sex,
    weight: weight,
    height: height,
    age: age,
    isMetric: isMetric,
    activityLevel: activityLevel,
    formula: tdeeFormula
  };

  var tdeePromise = callGetTdee(tdeeData);

  tdeePromise.then((data) => {
    console.log(data);
    setTdeeText(data.score);
  }).catch((errorData) => {
    console.error(errorData);
  });
});

function setTdeeText(score) {
  var baseText = "Your TDEE is " + score + ".";

  $("#tdeeResultText").text(baseText);
}

function callGetTdee(data) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: '/api/public/calculator/tdee',
      type: 'POST',
      contentType: "application/json",
      data: JSON.stringify(data)
    }).done((responseData) => {
      setTimeout(() => {
        resolve(responseData);
      }, 500);
    }).fail((jqXHR) => {
      var errorData = jqXHR.responseJSON;
      if(!errorData) {
        errorData = JSON.parse(jqXHR.responseText);
      }
      setTimeout(() => {
        reject(errorData.message);
      }, 1000);
    });
  });
}

function getSex() {
  return  $('input[name="flexRadioSex"]:checked').val();
}
