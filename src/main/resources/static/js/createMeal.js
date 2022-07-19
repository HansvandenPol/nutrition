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
  payload.mealName = formValue;
  payload.products = [];

  $.each(mealCollection, function (i, item) {
    var data = mealCollection[i];
    payload.products.push({id: data.productId, quantity: data.productQuantity});
  });

  getMeals();
  mealCollection = [];
  clearMealForm();
});

function getMeals() {
  $("#optionAddMeal").find("#a").empty();

  // get from backend;
  mealData = mockMealData();

  $.each(mealData, function (i, item) {
    var name = mealData[i].mealName;
    var listItem = $("<li class='resultItem list-group-item'>" + name + "</li>");
    listItem.data('id', mealData[i].mealId);
    $("#optionAddMeal").find("#a").append(listItem);
  });


  $("li.resultItem").click(function (event){
    console.log('click');
    var id = jQuery(this).data('id');
    console.log('MealId: ' + id);

    var meal = mealData.find(obj => obj.mealId === id);

    console.log("found mealdata: " + meal.mealId);
    addProductToList(meal.mealName, meal.totalKcal, meal.totalProtein, meal.totalCarbs, meal.totalFat);

    $('#staticBackdrop').modal('hide');
  });
}

function mockMealData() {
  var data = [];
  var names = ["ontbijt met havermout", "lasagne", "kwark en muesli", "pizza", "rijsttafel"]
  for (let i = 0; i < 5; i++) {
    data.push({mealId:i, mealName: names[i], totalKcal: 600, totalProtein: 80, totalCarbs: 120, totalFat: 20});
  }
  return data;
}

function clearMealForm() {
  $("#mealName").val('');
  $("#selectedMealProducts").empty();
}

