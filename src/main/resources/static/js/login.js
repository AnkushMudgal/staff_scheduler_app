$(document).ready(function(){
<<<<<<< HEAD
    $("#login-form").submit(function(e) {
        e.preventDefault();
        let loginId = $("#loginId").val();
        let loginPwd = $("#loginPwd").val();
        if(loginId !== "" && loginPwd !== "" ) {
<<<<<<< HEAD

            $.ajax({
                contentType: 'application/json',
                dataType: 'json',
                data: JSON.stringify({"userID": loginId, "password" : loginPwd}),
                type: 'POST',
                url: '/login/request',
                passwordType: true,
                success: function(data, response){
                    console.log(response)
                    if (data.userType === "ADMIN" && data.status === 'SUCCESS') {
                        $(location).attr('href',"/views/admin.html");
                    } else if (data.userType === "SUPERVISOR" && data.status === 'SUCCESS') {
                        $(location).attr('href',"/views/supervisor.html");
                    } else if (data.userType === 'STAFF' && data.status === 'SUCCESS'){
                        $(location).attr('href',"/views/staff.html");
                    } else if (data.status === 'INCORRECT_PASSWORD'){
                        //Respond with Error
                        alert("Incorrect Password for ID: " + loginId);
                    } else {
                        alert("Invalid Credentials");
=======
              $.ajax({
                    headers: { "Accept": "application/json", "id": loginId, "pswd" : loginPwd},
                    type: 'POST',
                    url: '/login/request',
                    success: function(data, textStatus, request){
                        console.log(data);
                        if(data.userType == "ADMIN") {
                            $(location).attr('href',"/views/admin.html");
                        }else if (data.userType == "SUPERVISOR") {
                            $(location).attr('href',"/views/supervisor.html");
                        }else {
                            $(location).attr('href',"/views/staff.html");
                        }
>>>>>>> 0cfa66b (Added supervisor initial form)
                    }
                }
            });
        }
    });

});
=======
    $("#loginSubmit").click(function(){
      console.log("button clicked");
    });
  });
>>>>>>> 8dec12e (Sprint 1 | ASSGS-6 Added the initial structures and files for frontend)
