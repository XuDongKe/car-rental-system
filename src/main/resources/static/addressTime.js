var data={};
submitBtn.onclick=function(e){
 e.preventDefault();
window.location.href='main.html';
data={
  'beginRentalTime':startDay.value,
  'returnTime':EndDay.value,
  'rentalAddress':address.value
}
console.log(data);
window.localStorage.setItem('storage',JSON.stringify(data));
}