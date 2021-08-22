loginBtn.onclick=function(e){
    e.preventDefault();
    ajax({
        url:'http://120.25.207.46:9090/user/login',
        type:"POST",
        params:{
            "userPhone": userName.value,
            'password':userPassword.value
          },
        success:function(data){
            data=JSON.parse(data);
            if(data.errorCode=='000002'){
                window.alert(data.errorMessage);
            }else{
                window.location.href='addressTime.html';
            }
            console.log(data);
        }
    })
}

