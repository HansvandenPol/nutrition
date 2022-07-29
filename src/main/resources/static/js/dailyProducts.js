$(document).ready(function (){
  loadProductsFromLocalStorage();
  setDay();
});

/**
 * Adds a product to the list of consumed products
 */
$("#addToDailyBtn").click(function () {
  var productName = $("#productTitle").text();
  var kcalValue = $("#kcalValue").text()
  var proteinValue = $("#proteinValue").text();
  var carbsValue = $("#carbsValue").text();
  var fatValue = $("#fatValue").text();

  addProductToList(productName, kcalValue, proteinValue, carbsValue, fatValue);
});

/**
 * Shows the added product in the UI and adds the product to the localstorage.
 * @param productName
 * @param kcalValue
 * @param proteinValue
 * @param carbsValue
 * @param fatValue
 */
function addProductToList(productName, kcalValue, proteinValue, carbsValue, fatValue) {
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

  $("#kcalProgressBar").trigger("updateProgress");
}

/**
 * Clears the list of consumed products
 */
$("#clearBtn").click(function (){
  localStorage.clear();
  if($("#dailyProductsTable tbody tr")) {
    $("#dailyProductsTable tbody").empty();
    $("#kcalProgressBar").trigger("updateProgress");
  };
});

/**
 * Calculates the total of the integral text value based on the 'product' class
 * @param classNmae
 * @returns {string}
 */
function calculateTotalOfClass(classNmae) {
  var total = 0;
  $("."+classNmae).each(function (i, object) {
    total += parseInt($(object).text(), 10);
  });
  return total.toFixed(1);
}

/**
 * Loads the products from localstorage.
 */
function loadProductsFromLocalStorage() {
  for (i = 0; i < window.localStorage.length; i++) {
    key = window.localStorage.key(i);
    if (key.includes("product")) {
      loadProduct(key);
    }
  }
}

/**
 * Loads a product from the localstorage
 * @param key
 */
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

/**
 * Sets a product in the localstorage.
 * @param productName
 * @param kcalValue
 * @param protValue
 * @param carbsValue
 * @param fatValue
 */
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

/**
 * Sets the name of the current day in the UI
 */
function setDay() {
  var day = new Date().getDay();
  var dayText;

  switch (day) {
    case 0:
      dayText  ="sunday";
      break;
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
  }

  $("#productListTitle").text($("#productListTitle").text() + " - " + dayText);
}
