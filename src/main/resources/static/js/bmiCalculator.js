$("#bmiForm").submit((event) => {
  event.preventDefault();
  var height = $("#heightData").val();
  var weight = $("#weightData").val();
  var age = $("#ageData").val();
  var sex = getSex();
  var isMetric = true;
  console.log(height + " " + weight + " " + age);

  var bmiData = {
    sex: sex,
    weight: weight,
    height: height,
    age: age,
    isMetric: isMetric
  };

  var bmiScorePromise = callGetBmiScore(bmiData);

  bmiScorePromise.then((data) => {
    setBmiText(data.score, age);
  }).catch((errorData) => {
    console.error(errorData);
  });

});

function callGetBmiScore(data) {
  return new Promise((resolve, reject) => {
    $.ajax({
      url: '/api/public/calculator/bmi',
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

function setBmiText(score, age) {
  var baseText = "Your BMI score is " + score + ".";
  var conclusionText;

  console.log(score);

  if(age > 69) {
    if(score < 18.5) {
      conclusionText = "According to your score you're underweight.";
    } else if(score >= 18.5 && score < 25) {
      conclusionText = "You've a healthy BMI!.";
    } else if(score >= 25 && score < 30) {
      conclusionText = "According to your score you're overweight";
    } else if(score >= 30 && score < 35) {
      conclusionText = "According to your score you're obese";
    } else {
      conclusionText = "According to your score you're extreme obese";
    }
  } else {
    if(score < 18.5) {
      conclusionText = "According to your score you're underweight.";
    } else if(score >= 18.5 && score < 25) {
      conclusionText = "You've a healthy BMI!.";
    } else if(score >= 25 && score < 30) {
      conclusionText = "According to your score you're overweight";
    } else if(score >= 30 && score < 35) {
      conclusionText = "According to your score you're obese";
    } else {
      conclusionText = "According to your score you're extreme obese";
    }
  }

  $("#bmiResultText").text(baseText + " " + conclusionText);
  $("#helpText").text("Click the button below for more information about BMI scores");
}

function getSex() {
  var sex = $('input[name="flexRadioDefault"]:checked').val();
  console.log(sex);
  return sex;
}
