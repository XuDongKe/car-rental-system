submitBtn.onclick=function(e){
  e.preventDefault();
  ajax({
    url:'http://120.25.207.46:9090/user/register',
    type:"POST",
    body: {    
        "userAddress": address.value,
        "userEmail": Email.value,
        "userId": 0,
        "userName": userName.value,
        "userPassword": userPassword.value,
        "userPhone": userAccount.value
      },
    success:function(data){
    data=JSON.parse(data);
    if(data.errorCode=='000000'){
      window.location.href='login.html';
    }else{
      window.alert(data.errorMessage);
    }
    }
    }
    );
}

