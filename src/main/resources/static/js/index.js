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

$("#searchProductBtn").click(function () {
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
        console.log(data);

      }).fail(function () {
        console.log("failed");
      });
    });

    $(".content").append("</ul>");

  }).fail(function () {
    console.log("failed");
  });
});


