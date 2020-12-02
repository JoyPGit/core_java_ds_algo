// 1
const obj = {name:"jo", age:26};

obj.age = 28;
console.log(obj);

// 2
var v = 1;
function f(){
    var v = 2;
    f2(v);
}

function f2(a){
    console.log(a);
}

f(); // prints 2
// var is block scoped, but the inside var is referred first inside
// the function f

//3 
sum(10, 20);
diff(10, 20);

function sum(x,y){
  return x+y;
}

let diff = function (x, y){
  return x-y;
}
// ref error : Cannot access 'diff' before initialization

// http://callbackhell.com/