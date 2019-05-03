function loadTwoRandomCats() {
    $.get("match",
        function( data ) {
          var cat1 = data[0]
          $("#cat1").attr("name",cat1.id);
          $("#cat1").attr("src",cat1.url);
          var cat2 = data[1]
          $("#cat2").attr("name",cat2.id);
          $("#cat2").attr("src",cat2.url);
        }
    )
}

function vote(winner, looser) {
    var data = JSON.stringify({winner: winner, looser: looser});
        $.ajax({
          type: "POST",
          url: "vote",
          contentType: "application/json;charset=utf-8",
          data: data,
          success: function() {console.log("vote sent")},
          dataType: "json"
        });
}

function catsRanking() {
     $.get("ranking",
        function( data ) {
            var html = "";
            $.each(data, function(i, cat){
                html += '<img src="'+cat.url+'" />';
                if ((i+1) %5 == 0)
                    html +="<br />"
            });
            $("#cats").html(html);
        })
}

