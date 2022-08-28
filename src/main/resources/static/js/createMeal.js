var mealCollection = [];
var mealData = [];

/**
 * Adds a product to the new meal
 */
$("#addProductToMeal").click(function () {
  var productName = $("#mealProductName").text();
  var productId = $('input[id="mealProductId"]').val();
  var productQuantity = $("#mealProductNameQuantity").val();

  $("#selectedMealProducts").append("<p>" + productName + "(" + productQuantity + " g)</p>");

  var mealData = {productName: productName, productId: productId, productQuantity: productQuantity};
  mealCollection.push(mealData);
});

/**
 * Creates a new meal and calls the backend to store it.
 */
$("#createMealBtn").click(function () {
  var validTextRegex = new RegExp('^[A-Za-z1-9\\s]+$');
  var formValue = $("#mealName").val().trim();

  if (!validTextRegex.test(formValue)) {
    alert('Please enter a valid meal name');
    return;
  }

  if(mealCollection.length === 0) {
    alert('Please add at least 1 product');
    return;
  }

  // Add meal to the DB
  var payload = {};
  payload.name = formValue;
  payload.mealProductRequests = [];

  $.each(mealCollection, function (i, item) {
    var data = mealCollection[i];
    payload.mealProductRequests.push({productId: data.productId, productQuantity: data.productQuantity});
  });

  callAddMeal(payload).then(function () {
    getMeals();
    mealCollection = [];
    clearMealForm();
  });
});

/**
 * Retrieves the meals from the backend
 */
function getMeals() {
  $("#optionAddMeal").find("#a").empty();

  // get from backend;
  getMealData().then(function (data) {
    mealData = data;
    showMeals();
  });
}

/**
 * Makes sure the meals are being displayed in the UI, and creates a 'click' handler for each meal.
 */
function showMeals() {
  if(mealData.length > 0) {
    console.log(mealData);
    $.each(mealData, function (i, item) {
      var name = mealData[i].mealName;
      var listItem = $("<li class='resultItem list-group-item'>" + name + "</li>");
      listItem.data('id', mealData[i].id);
      $("#optionAddMeal").find("#a").append(listItem);
    });


    $("li.resultItem").click(function (event){
      var id = jQuery(this).data('id');

      var meal = mealData.find(obj => obj.id === id);

      addProductToList(meal.mealName, meal.totalKcal, meal.totalProtein, meal.totalCarbs, meal.totalFat);

      $('#staticBackdrop').modal('hide');
    });
  }
}

/**
 * Clears the form
 */
function clearMealForm() {
  $("#mealName").val('');
  $("#selectedMealProducts").empty();
}

/**
 * Calls the backend to add a meal
 * @param payload
 * @returns {Promise<unknown>}
 */
function callAddMeal(payload) {
  return new Promise(function (resolve, reject) {
    $.ajax({
      url: '/api/private/meal',
      type: 'POST',
      contentType: "application/json",
      data: JSON.stringify(payload)
    }).done(function () {
      resolve();
    });
  });
}

/**
 * Calls the backend to retrieve meals.
 * @returns {Promise<unknown>}
 */
function getMealData() {
  return new Promise(function (resolve, reject) {
    $.ajax({
      url: '/api/private/meal',
      type: 'GET',
    }).done(function (data) {
      resolve(data);
    });
  });
}

$("#addMealProductBtn").click(function () {
  console.log('clicked');
  $(".mealProducts").empty();
});

