$(document).ready(function (){
  loadProductsFromLocalStorage();
  setDay();
});

$("#addToDailyBtn").click(function () {
  var productName = $("#productTitle").text();
  var kcalValue = $("#kcalValue").text()
  var proteinValue = $("#proteinValue").text();
  var carbsValue = $("#carbsValue").text();
  var fatValue = $("#fatValue").text();

  setLocalStorageProduct(productName, kcalValue, proteinValue, carbsValue, fatValue);

  if($("#totalValues").length === 0) {
    $("#dailyProductsTable").append("<tr id='totalValues'><th>Total</th><th id='totalKcal'></th><th id='totalProtein'></th><th id='totalCarbs'></th><th id='totalFat'></th></tr>");
  }

  $("<tr><td>" + productName + "</td><td class='kcalValue'>" + kcalValue + "</td><td class='proteinValue'>" + proteinValue + "</td><td class='carbsValue'>" + carbsValue + "</td><td class='fatValue'>" + fatValue + "</td></tr>").insertBefore('#totalValues');

  var totalKcal = calculateTotalOfClass('kcalValue');
  var totalProtein = calculateTotalOfClass('proteinValue');
  var totalCarbs = calculateTotalOfClass('carbsValue');
  var totalFat = calculateTotalOfClass('fatValue');

  $("#totalKcal").text(totalKcal);
  $("#totalProtein").text(totalProtein);
  $("#totalCarbs").text(totalCarbs);
  $("#totalFat").text(totalFat);
});

$("#clearBtn").click(function (){
  localStorage.clear();
  if($("#dailyProductsTable tbody tr")) {
    $("#dailyProductsTable tbody").empty();
  };
});

function calculateTotalOfClass(classNmae) {
  var total = 0;
  $("."+classNmae).each(function (i, object) {
    total += parseInt($(object).text(), 10);
  });
  return total;
}

function loadProductsFromLocalStorage() {
  for (i = 0; i < window.localStorage.length; i++) {
    key = window.localStorage.key(i);
    if (key.includes("product")) {
      loadProduct(key);
    }
  }
}

function loadProduct(key) {
  var product = JSON.parse(localStorage.getItem(key));
  if($("#totalValues").length === 0) {
    $("#dailyProductsTable").append("<tr id='totalValues'><th>Total</th><th id='totalKcal'></th><th id='totalProtein'></th><th id='totalCarbs'></th><th id='totalFat'></th></tr>");
  }

  $("<tr><td>" + product.productName + "</td><td class='kcalValue'>" + product.kcal + "</td><td class='proteinValue'>" + product.protein + "</td><td class='carbsValue'>" + product.carbs + "</td><td class='fatValue'>" + product.fat + "</td></tr>").insertBefore('#totalValues');

  var totalKcal = calculateTotalOfClass('kcalValue');
  var totalProtein = calculateTotalOfClass('proteinValue');
  var totalCarbs = calculateTotalOfClass('carbsValue');
  var totalFat = calculateTotalOfClass('fatValue');

  $("#totalKcal").text(totalKcal);
  $("#totalProtein").text(totalProtein);
  $("#totalCarbs").text(totalCarbs);
  $("#totalFat").text(totalFat);
}

function setLocalStorageProduct(productName, kcalValue, protValue, carbsValue, fatValue) {
  var uid = ((Math.random() + 1) * 10000).toFixed(0);
  var key = "product-"+productName + "-" + uid;
  var productData = {
    productName: productName,
    kcal: kcalValue,
    protein: protValue,
    carbs: carbsValue,
    fat: fatValue
  };

  localStorage.setItem(key, JSON.stringify(productData));
}

function setDay() {
  var day = new Date().getDay();
  console.log()
  var dayText;
  switch (day) {
    case 1:
      dayText = "monday";
      break;
    case 2:
      dayText = "tuesday";
      break;
    case 3:
      dayText  ="wednesday";
      break;
    case 4:
      dayText = "thursday";
      break;
    case 5:
      dayText = "friday";
      break;
    case 6:
      dayText = "saturday";
      break;
    case 7:
      dayText  ="sunday";
      break;
  }

  $("#productListTitle").text($("#productListTitle").text() + " - " + dayText);
}


