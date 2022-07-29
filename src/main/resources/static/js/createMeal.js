var mealCollection = [];
var mealData = [];

$("#addProductToMeal").click(function () {
  var productName = $("#mealProductName").text();
  var productId = $('input[id="mealProductId"]').val();
  var productQuantity = $("#mealProductNameQuantity").val();

  console.log('test data: ' + productName + ' ' + productId);
  $("#selectedMealProducts").append("<p>" + productName + "(" + productQuantity + " g)</p>");

  var mealData = {productName: productName, productId: productId, productQuantity: productQuantity};
  mealCollection.push(mealData);
});

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
    console.log("done");
    getMeals();
    mealCollection = [];
    clearMealForm();
  });
});

function getMeals() {
  $("#optionAddMeal").find("#a").empty();

  // get from backend;
  getMealData().then(function (data) {
    mealData = data;
    showMeals();
  });
}


function showMeals() {
  $.each(mealData, function (i, item) {
    console.log(mealData[i]);
    var name = mealData[i].mealName;
    var listItem = $("<li class='resultItem list-group-item'>" + name + "</li>");
    listItem.data('id', mealData[i].id);
    $("#optionAddMeal").find("#a").append(listItem);
  });


  $("li.resultItem").click(function (event){
    console.log('click');
    var id = jQuery(this).data('id');
    console.log('MealId: ' + id);

    var meal = mealData.find(obj => obj.id === id);

    console.log("found mealdata: " + meal.id);
    addProductToList(meal.mealName, meal.totalKcal, meal.totalProtein, meal.totalCarbs, meal.totalFat);

    $('#staticBackdrop').modal('hide');
  });
}

function clearMealForm() {
  $("#mealName").val('');
  $("#selectedMealProducts").empty();
}

function callAddMeal(payload) {
  return new Promise(function (resolve, reject) {
    $.ajax({
      url: '/api/meal',
      type: 'POST',
      contentType: "application/json",
      data: JSON.stringify(payload)
    }).done(function () {
      resolve();
    });
  });
}

function getMealData() {
  return new Promise(function (resolve, reject) {
    $.ajax({
      url: '/api/meal',
      type: 'GET',
    }).done(function (data) {
      resolve(data);
    });
  });

}

