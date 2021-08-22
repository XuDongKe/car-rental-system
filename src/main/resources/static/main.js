var storageData=JSON.parse(window.localStorage.getItem('storage'));
console.log(storageData);
var tbody=document.getElementsByTagName('tbody')[0];
var selectData={};
renderList();
function renderList(){
ajax({
    url:'http://120.25.207.46:9091/car/car-list',
    type:'get',
    success:function(data){
    data=JSON.parse(data);
    renderItem(data.data);
    console.log(data);
    } 
})
};
var renderItem=function(data){
 var str='';
 data.forEach(function(item){
str+='<tr>'+
'<td>'+
   '<span>'+
       item.carId
  + '</span>'+
'</td>'+
'<td>'+
    '<span>'+
       item.carModel+
    '</span>'+
'</td>'+
'<td>'+
   '<span>'+
       item.carColour+
   '</span>'+
'</td>'+
'<td>'+
  '<span>'+
    item.carPricePerDay+ 
  '</span>'+
'</td>'+
'<td>'+
  '<span>'+
  (item.carRentStatus===0?'Rented':'No rented')+
  '</span>'+
'</td>'+
'<td>'+
    '<button class="choice" onclick="btn('+item.carId+')">rentaling</button>'+
'</td>'+
'</tr>'
 })
 tbody.innerHTML=str;
}
function btn(item){
ajax({
    url:'http://120.25.207.46:9092/order/rental-car',
    type:'post',
    body:{
        "beginRentalTime":storageData.beginRentalTime ,
        "carId": item,
        "carModel": "",
        "createTime": "",
        "orderAmount": 0,
        "orderId": 0,
        "orderStatus": "",
        "rentalAddress":storageData.rentalAddress,
        "returnTime": storageData.returnTime,
        "updateTime": "",
        "userId": 0,
        "userName":""
      },
    success:function(data){
       data=JSON.parse(data);
       window.alert(data.errorMessage);
    }
}); 
}
