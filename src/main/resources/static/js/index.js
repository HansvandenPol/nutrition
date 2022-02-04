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

$("#productValue").on("keypress", function (e){
 if(e.which == 13){
   e.preventDefault();
   showProducts();
   $("#productValue").blur();
 }
});

$("#searchProductBtn").click(function () {
  showProducts();
});


function showProducts() {
  var formValue = $("#productValue").val().trim();
  if(formValue.length < 2 || formValue.length > 15) {
    alert("jeeh");
    return;
  }
  var validTextRegex = new RegExp('^[A-Za-z]*$');

  if (!validTextRegex.test(formValue)) {
    alert('heejoh');
    return;
  }

  $.ajax({
    url: 'http://localhost:8080/api/product/search?product=' + formValue,

    dataType: 'json',

  }).done(function (data) {
    if(data.length === 0 ) {
      $('#searchError').text("No products found");
      $('#searchError').show();
    }

    if($(".content").find("div.results").length > 0) {
      $(".content").find("div.results").empty();
    } else {
      $(".content").append("<div class='results'></div>").append("<ul id='a'>");
    }

    $.each(data, function (i, item) {
      // console.log(data);
      var name = data[i].productDescriptionNl;
      var code = data[i].productCode;
      $(".results").append("<li class='resultItem'>"+ code + " - " + name + "</li>");
    });

    $("li.resultItem").click(function (event) {
      //document.getElementById("foodQuantity").value = "100";
      $("#foodQuantity").val(100);
      oldFoodQuantity = 100;

      var id = jQuery(this).text().split("-")[0].trim();
      $.ajax({
        url: 'http://localhost:8080/api/product/' + id,

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
    console.log("failed");
  });
}

$("#foodQuantity").change(function () {
  var currentFoodQuantity = $("#foodQuantity").val();
  console.log(currentFoodQuantity);
  updateDetails(currentFoodQuantity);
});

$("#foodQuantity").on("keypress", function (e) {
  if(e.which == 13){
    e.preventDefault();
    var currentFoodQuantity = $("#foodQuantity").val();
    updateDetails(currentFoodQuantity);
    $("#foodQuantity").blur();
  }
});

function updateDetails(quantity) {
  // $(".nutritionValue").each(function (i, object) {
  //   var currentValue = $(object).text().split(" ")[0].trim();
  //   $(object).text(((currentValue/oldFoodQuantity) * quantity).toFixed(1));
  // });

  fillDetails(latestData, quantity);
}

function fillDetails(data, quantity) {
  console.log(data);
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
