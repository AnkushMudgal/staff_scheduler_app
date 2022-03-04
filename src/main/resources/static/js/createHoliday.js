$(document).ready(function(){
    $("#holiday-form").submit(function(e) {
    e.preventDefault();

    var formData = new FormData(document.querySelector('#holiday-form'));

        $.ajax({
            contentType: 'application/json',
            dataType: 'json',
            data: JSON.stringify(formData),
            type: 'POST',
            url: '/admin/create/holiday',
            passwordType: false,
            success: function(data, response){
                console.log(response);
                if(data.status === 'SUCCESS' && data.created === true){

                    $("#informDialog").modal('show');
                    $("#close").click(function(){
                        $("#informDialog").modal('hide');
                    });
                }
            },error: function(response) {
                console.log("Error status", response.status, "Error text", response.statusText);
            }
        });
    });
});