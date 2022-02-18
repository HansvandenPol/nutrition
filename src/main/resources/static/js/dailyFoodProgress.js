var kcalGoal = 3000;
var progressValueKcal = 0;

var proteinGoal = 180;
var progressValueProtein = 0;

var carbsGoal = 500;
var progressValueCarbs = 0;

$(document).ready(function() {
  updateProgress();
  updateProgressProtein();
  updateProgressCarbs();
});

$("#kcalProgressBar").on("updateProgress", function () {
  console.log("firedd");
  updateProgress();
  updateProgressProtein();
  updateProgressCarbs();
});

function updateProgress() {
  var currentTotalKcal = parseInt($("#totalKcal").text());
  if(currentTotalKcal >= 0) {
    progressValueKcal = (currentTotalKcal / kcalGoal * 100).toFixed(0);
  } else {
    currentTotalKcal = 0;
    progressValueKcal = 0;
  }

  $("#kcalProgressBar").attr("aria-valuenow", progressValueKcal);
  $("#kcalProgressBar").css("width", progressValueKcal);

  $("#kcalProgressText").text(currentTotalKcal + "/" + kcalGoal + " kcal(g)");
}

function updateProgressProtein() {
  var currentTotalKcal = parseInt($("#totalProtein").text());
  if(currentTotalKcal >= 0) {
    progressValueProtein = (currentTotalKcal / proteinGoal * 100).toFixed(0);
  } else {
    currentTotalKcal = 0;
    progressValueProtein = 0;
  }

  $("#proteinProgressBar").attr("aria-valuenow", progressValueProtein);
  $("#proteinProgressBar").css("width", progressValueProtein);

  $("#proteinProgressText").text(currentTotalKcal + "/" + proteinGoal + " protein(g)");
}

function updateProgressCarbs() {
  var currentTotalKcal = parseInt($("#totalCarbs").text());
  if(currentTotalKcal >= 0) {
    progressValueCarbs = (currentTotalKcal / carbsGoal * 100).toFixed(0);
  } else {
    currentTotalKcal = 0;
    progressValueCarbs = 0;
  }

  $("#carbsProgressBar").attr("aria-valuenow", progressValueCarbs);
  $("#carbsProgressBar").css("width", progressValueCarbs);

  $("#carbsProgressText").text(currentTotalKcal + "/" + carbsGoal).append("<b> carbs(g)</b>");
}
