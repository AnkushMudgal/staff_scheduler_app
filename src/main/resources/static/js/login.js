$(document).ready(function(){
<<<<<<< HEAD
    $("#login-form").submit(function(e) {
        e.preventDefault();
        let loginId = $("#loginId").val();
        let loginPwd = $("#loginPwd").val();
        if(loginId !== "" && loginPwd !== "" ) {
              $.ajax({
                    headers: { "Accept": "application/json", "id": loginId, "pswd" : loginPwd},
                    type: 'POST',
                    url: 'http://localhost:8086/login/request',
                    success: function(data, textStatus, request){
                        console.log(data);
                        if(data.userType == "ADMIN") {
                            $(location).attr('href',"/views/admin.html");
                        }else if (data.userType == "SUPERVISOR") {
                            $(location).attr('href',"/views/supervisor.html");
                        }else {
                            $(location).attr('href',"/views/staff.html");
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
