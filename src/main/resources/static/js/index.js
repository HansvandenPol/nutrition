var oldFoodQuantity = $("#foodQuantity").val();

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
  console.log(formValue);

  var validTextRegex = new RegExp('^[A-Za-z]*$');

  if (!validTextRegex.test(formValue)) {
    alert('heejoh');
  }

  $.ajax({
    url: 'http://localhost:8080/api/product/search?product=' + formValue,

    dataType: 'json',

  }).done(function (data) {
    if($(".content").find("div.results").length > 0) {
      console.log("found");
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
      console.log(event);
      var id = jQuery(this).text().split("-")[0].trim();
      $.ajax({
        url: 'http://localhost:8080/api/product/' + id,

        dataType: 'json',

      }).done(function (data) {
        $(".details").show();
        $("#productTitle").text(data.productDescriptionNl);

        if($(".details").find("table").length > 0) {
          $(".details table").empty();
        } else {
          $(".details").append("<table>")
        }

        fillDetails(data);

        $(".details").append("</table>")

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
  $(".nutritionValue").each(function (i, object) {
    console.log(object);
    var currentValue = $(object).text().split(" ")[0].trim();
    console.log(currentValue);
    $(object).text(((currentValue/oldFoodQuantity) * quantity).toFixed(1));

  });

  oldFoodQuantity = quantity;
  // $(".nutritionValue").text()
}

function fillDetails(data) {
  console.log(data);
  $(".details table").append("<tr><td><b>Quantity</b></td><td class='nutritionValue'>" + data.quantity + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td><b>Kcal</b></td><td class='nutritionValue'>" + data.kcal + "</td></tr>");
  $(".details table").append("<tr> </tr>");
  $(".details table").append("<tr><td><b>Protein</b></td></tr>");
  $(".details table").append("<tr><td>Protein(total)</td><td class='nutritionValue'>" + data.proteinTotal + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Protein plant based</td><td class='nutritionValue'>" + data.proteinPlantBased + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Protien animal based</td><td class='nutritionValue'>" + data.proteinAnimalBased + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr> </tr>");
  $(".details table").append("<tr><td><b>Carbs</b></td></tr>");
  $(".details table").append("<tr><td>Carbs(total)</td><td class='nutritionValue'>" + data.carbsTotal + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Carbs sugar</td><td class='nutritionValue'>" + data.carbsSugar + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Carbs starchy</td><td class='nutritionValue'>" + data.carbsStarch + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Fiber</td><td class='nutritionValue'>" + data.fiber + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr> </tr>");
  $(".details table").append("<tr><td><b>Fat</b></td></tr>");
  $(".details table").append("<tr><td>Fat(total)</td><td class='nutritionValue'>" + data.fatTotal + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Fat saturated</td><td class='nutritionValue'>" + data.fatSaturated + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Fat unsaturated mono</td><td class='nutritionValue'>" + data.fatUnsaturatedMono + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Fat unsaturated poly</td><td class='nutritionValue'>" + data.fatUnsaturatedPoly + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Fat unsaturated omega 3</td><td class='nutritionValue'>" + data.fatOmega3 + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Fat unsaturated omega 6</td><td class='nutritionValue'>" + data.fatOmega6 + "</td><td>" + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td>Transfats</td><td class='nutritionValue'>" + data.transfets + "</td><td>" + data.measureUnit + "</td></tr>");
}
