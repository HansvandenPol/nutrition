var oldFoodQuantity = $("#foodQuantity").val();
var baseQuantity
var baseKcal
var baseTotProtein
var baseProteinPlant;
var baseProteinAnimal;
var baseTotCarbs;
var baseCarbsSugar;
var baseCarbsStarch;
var baseCarbsFiber;
var baseTotFat;
var baseFatSaturated;
var baseFatMono;
var baseFatPoly;
var baseFatOmega3;
var baseFatOmega6;
var baseFattransfets;
var latestData;

var signedIn = false;

$(document).ready(function () {
  $("#optionAddMeal").hide();
});

/**
 * shows the products after pressed on 'enter'
 */
$("#productValue").on("keypress", function (e){
 if(e.which == 13){
   e.preventDefault();
   showProducts();
   $("#productValue").blur();
 }
});

/**
 * Shows the products after a click on the button
 */
$("#searchProductBtn").click(function () {
  showProducts();
});

/**
 * Searches for products based on input from the form.
 */
$("#searchProductMealBtn").click(function () {
  var formValue = $("#productValueMeal").val().trim();

  showProductsBySearch(formValue);
});

/**
 * Shows the products bases on search filter
 */
function showProducts() {
  var formValue = $("#productValue").val().trim();
  if(formValue.length < 2 || formValue.length > 15) {
    alert("jeeh");
    return;
  }
  var validTextRegex = new RegExp('^[A-Za-z\\s]*$');

  if (!validTextRegex.test(formValue)) {
    alert('heejoh');
    return;
  }

  $.ajax({
    url: '/api/public/product/search?product=' + formValue,

    dataType: 'json',

  }).done(function (data) {
    if(data.length === 0 ) {
      $('#searchError').text("No products found");
      $('#searchError').show();
    }

    if($(".content").find("#optionProductList.results").length > 0) {
      $(".content").find("#optionProductList.results").empty();
    } else {
      $("#optionProductList").append("<div class='results'></div>").append("<ul class='list-group' id='a'>");
    }

    $.each(data, function (i, item) {
      var name = data[i].productDescriptionNl;
      var code = data[i].productCode;
      $("#optionProductList").find(".results").append("<li class='resultItem list-group-item' data-bs-target='#productDetailsModal' data-bs-toggle='modal'>"+ code + " - " + name + "</li>");
    });

    $("li.resultItem").click(function (event) {
      //document.getElementById("foodQuantity").value = "100";
      $("#foodQuantity").val(100);
      oldFoodQuantity = 100;

      var id = jQuery(this).text().split("-")[0].trim();
      $.ajax({
        url: '/api/public/product/' + id,

        dataType: 'json',

      }).done(function (data) {
        latestData = data;
        $(".details").show();
        $("#productTitle").text(data.productDescriptionNl);

        if($("#productDetails").find("table").length > 0) {
          $("#productDetails table").empty();
        } else {
          $("#productDetails").append("<table>")
        }

        fillDetails(data, 100);

        $("#productDetails").append("</table>")

      }).fail(function () {
        console.log("failed");
      });
    });

    $(".content").append("</ul>");

  }).fail(function () {
    console.error("failed");
  });
}

/**
 * Updates product details upon change event
 */
$("#foodQuantity").change(function () {
  var currentFoodQuantity = $("#foodQuantity").val();
  updateDetails(currentFoodQuantity);
});

/**
 * Changes product details when enter is pressed
 */
$("#foodQuantity").on("keypress", function (e) {
  if(e.which == 13){
    e.preventDefault();
    var currentFoodQuantity = $("#foodQuantity").val();
    updateDetails(currentFoodQuantity);
    $("#foodQuantity").blur();
  }
});

/**
 * fills the details with the latest data
 * @param quantity
 */
function updateDetails(quantity) {
  fillDetails(latestData, quantity);
}

/**
 * Fills the product details in the UI
 * @param data
 * @param quantity
 */
function fillDetails(data, quantity) {
  baseQuantity = (data.quantity / 100 * quantity).toFixed(1);
  baseKcal = (data.kcal / 100 * quantity).toFixed(1);
  baseTotProtein = (data.proteinTotal / 100 * quantity).toFixed(1);;
  baseProteinPlant = (data.proteinPlantBased / 100 * quantity).toFixed(1);
  baseProteinAnimal = (data.proteinAnimalBased / 100 * quantity).toFixed(1);
  baseTotCarbs = (data.carbsTotal / 100 * quantity).toFixed(1);
  baseCarbsSugar = (data.carbsSugar / 100 * quantity).toFixed(1);
  baseCarbsStarch =(data.carbsStarch / 100 * quantity).toFixed(1);
  baseCarbsFiber = (data.fiber / 100 * quantity).toFixed(1);
  baseTotFat = (data.fatTotal / 100 * quantity).toFixed(1);
  baseFatSaturated = (data.fatSaturated / 100 * quantity).toFixed(1);
  baseFatMono = (data.fatUnsaturatedMono / 100 * quantity).toFixed(1);
  baseFatPoly = (data.fatUnsaturatedPoly / 100 * quantity).toFixed(1);
  baseFatOmega3 = (data.fatOmega3 / 100 * quantity).toFixed(1);
  baseFatOmega6 = (data.fatOmega6 / 100 * quantity).toFixed(1);;
  baseFattransfets = (data.transfets / 100 * quantity).toFixed(1);

  if($("#productDetails table")) {
    $("#productDetails table").empty();
  }

  $("#productDetails table").append("<tr><td><b>Quantity</b></td><td class='nutritionValue'>" + baseQuantity + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td><b>Kcal</b></td><td class='nutritionValue' id='kcalValue'>" + baseKcal + "</td></tr>");
  $("#productDetails table").append("<tr> </tr>");
  $("#productDetails table").append("<tr><td><b>Protein</b></td></tr>");
  $("#productDetails table").append("<tr><td>Protein(total)</td><td class='nutritionValue' id='proteinValue'>" + baseTotProtein + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Protein plant based</td><td class='nutritionValue'>" + baseProteinPlant + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Protien animal based</td><td class='nutritionValue'>" + baseProteinAnimal + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr> </tr>");
  $("#productDetails table").append("<tr><td><b>Carbs</b></td></tr>");
  $("#productDetails table").append("<tr><td>Carbs(total)</td><td class='nutritionValue' id='carbsValue'>" + baseTotCarbs + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Carbs sugar</td><td class='nutritionValue'>" + baseCarbsSugar + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Carbs starchy</td><td class='nutritionValue'>" + baseCarbsStarch + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Fiber</td><td class='nutritionValue'>" + baseCarbsFiber + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr> </tr>");
  $("#productDetails table").append("<tr><td><b>Fat</b></td></tr>");
  $("#productDetails table").append("<tr><td>Fat(total)</td><td class='nutritionValue' id='fatValue'>" + baseTotFat + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Fat saturated</td><td class='nutritionValue'>" + baseFatSaturated + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Fat unsaturated mono</td><td class='nutritionValue'>" + baseFatMono + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Fat unsaturated poly</td><td class='nutritionValue'>" + baseFatPoly + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Fat unsaturated omega3</td><td class='nutritionValue'>" + baseFatOmega3 + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Fat unsaturated omega6</td><td class='nutritionValue'>" + baseFatOmega6 + "</td><td>" + data.measureUnit + "</td></tr>");
  $("#productDetails table").append("<tr><td>Transfats</td><td class='nutritionValue'>" + baseFattransfets + "</td><td>" + data.measureUnit + "</td></tr>");
}

/**
 * Toggles between meal and product modal content based on the selected input
 */
$('input:radio[name="btnradio"]').change(
    function() {
      var optionProductList = $("#optionProductList");
      var optionAddMeal = $("#optionAddMeal");
      if ($("#productOption").is(":checked")) {
        optionProductList.show();
        optionAddMeal.hide();
        $("#loginRequiredInfo").toggle();
      } else {
        optionProductList.hide();

        optionAddMeal.show();

        getMeals();

        // if(!signedIn) {
        //   $("#loginRequiredInfo").toggle();
        // } else {
        // }
      }
    }
);

/**
 * Shows the products based on a search result
 */
function showProductsBySearch(searchString) {
  var formValue = searchString
  if(formValue.length < 2 || formValue.length > 15) {
    alert("jeeh");
    return;
  }
  var validTextRegex = new RegExp('^[A-Za-z\\s]*$');

  if (!validTextRegex.test(formValue)) {
    alert('heejoh');
    return;
  }

  $.ajax({
    url: '/api/public/product/search?product=' + formValue,

    dataType: 'json',

  }).done(function (data) {
    console.log(data);
    if(data.length === 0 ) {
      $('#searchErrorMeal').text("No products found");
      $('#searchErrorMeal').show();
    }

    $.each(data, function (i, item) {
      var name = data[i].productDescriptionNl;
      var code = data[i].productCode;
      $(".mealProducts").append("<ul class='list-group' id='a'>").append("<li class='resultItem list-group-item' data-bs-target='#mealProductSummary' data-bs-toggle='modal'>"+ code + " - " + name + "</li>");
    });

    $("li.resultItem").click(function (event) {
      var id = jQuery(this).text().split("-")[0].trim();
      var name = jQuery(this).text().substring(jQuery(this).text().indexOf('-')+1).trim();
      console.log("id: " + id);

      var quantity = $("#mealProductNameQuantity").val();
      $("#mealProductName").text(name);
      $('input[id="mealProductId"]').val(id);
      $('input[id="mealProductQuantity"]').val(quantity);
    });

    $(".mealProducts").append("</ul>");

  }).fail(function () {
    console.log("failed");
  });
}

