// $(document).ready(function () {
//   $.ajax('http://localhost:8080/api/meal/test', {
//     dataType: 'text',
//     success: function (data, status, xhr) {
//       console.log(data);
//     },
//     error: function (jqXhr, textStatus, errorMessage) {
//       console.log(textStatus);
//     }
//   })
// });

$("#productValue").on("keypress", function (e){
 if(e.which == 13){
   e.preventDefault();
   showProducts();
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

function fillDetails(data) {
  console.log(data);
  $(".details table").append("<tr><td><b>Quantity</b></td><td>" + data.quantity + " " + data.measureUnit + "</td></tr>");
  $(".details table").append("<tr><td><b>Kcal</b></td><td>" + data.kcal + "</td></tr>");
  $(".details table").append("<tr> </tr>");
  $(".details table").append("<tr><td><b>Protein</b></td></tr>");
  $(".details table").append("<tr><td>Protein(total)</td><td>" + data.proteinTotal + " g</td></tr>");
  $(".details table").append("<tr><td>Protein plant based</td><td>" + data.proteinPlantBased + " g</td></tr>");
  $(".details table").append("<tr><td>Protien animal based</td><td>" + data.proteinAnimalBased + " g</td></tr>");
  $(".details table").append("<tr> </tr>");
  $(".details table").append("<tr><td><b>Carbs</b></td></tr>");
  $(".details table").append("<tr><td>Carbs(total)</td><td>" + data.carbsTotal + " g</td></tr>");
  $(".details table").append("<tr><td>Carbs sugar</td><td>" + data.carbsSugar + " g</td></tr>");
  $(".details table").append("<tr><td>Carbs starchy</td><td>" + data.carbsStarch + " g</td></tr>");
  $(".details table").append("<tr><td>Fiber</td><td>" + data.fiber + " g</td></tr>");
  $(".details table").append("<tr> </tr>");
  $(".details table").append("<tr><td><b>Fat</b></td></tr>");
  $(".details table").append("<tr><td>Fat(total)</td><td>" + data.fatTotal + " g</td></tr>");
  $(".details table").append("<tr><td>Fat saturated</td><td>" + data.fatSaturated + " g</td></tr>");
  $(".details table").append("<tr><td>Fat unsaturated mono</td><td>" + data.fatUnsaturatedMono + " g</td></tr>");
  $(".details table").append("<tr><td>Fat unsaturated poly</td><td>" + data.fatUnsaturatedPoly + " g</td></tr>");
  $(".details table").append("<tr><td>Fat unsaturated omega 3</td><td>" + data.fatOmega3 + " g</td></tr>");
  $(".details table").append("<tr><td>Fat unsaturated omega 6</td><td>" + data.fatOmega6 + " g</td></tr>");
  $(".details table").append("<tr><td>Transfats</td><td>" + data.transfets + " g</td></tr>");
}

