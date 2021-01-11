// Truy cập vào các nút chứa ký tự số, và ký tự biểu thức
let nums = document.querySelectorAll('.num-input');
// Truy cập vào ô hiển thị biểu thức
let expEle = document.querySelector('.c-ex');;
// Truy cập vào ô hiển thị kết quả
let resultEle = document.querySelector('.c-result');
// Truy cập vào nút tính toán kết quả
let enter = document.querySelector('.enter');

let clear = document.querySelector(".clear");
let del = document.querySelector(".del");


let sqrtNum = document.querySelector('.sqrt');
let switchNum = document.querySelector('.switch');

let exp = "";
let result = 0;

// Khi ấn các nút số + biểu thức
Array.from(nums).forEach(num => {
    num.addEventListener('click', function () {
        let char = num.getAttribute('char');
        console.log(char);
        exp += char;
        updateExpEle();
    })
})

enter.addEventListener('click', function () {
    try {
        if (exp == "") {
            resultEle.innerText = "0";
        } else {
            result = eval(exp);
            updateResultEle();
        }
    } catch {
        expEle.innerText = "Error";
        exp = "";
        resetResultEle();
    }
})

function updateExpEle() {
    let expCopy = exp;
    expCopy = expCopy.replace(/\*/g, "×");
    expCopy = expCopy.replace(/\//g, "÷");
    expCopy = expCopy.replace(/\-/g, "−");
    expCopy = expCopy.replace(/\+/g, "+");
    expEle.innerText = expCopy;
}

function updateResultEle() {
    resultEle.innerText = result;
}

function resetResultEle() {
    resultEle.innerText = "";
}

del.addEventListener('click', function () {
    if (exp.length > 0) {
        exp = exp.slice(0, exp.length - 1);
        updateExpEle();
    } else {
        resetExpEle();
        return;
    }
})

clear.addEventListener('click', function () {
    resetExpEle();
    resetResultEle();
})

function resetExpEle() {
    exp = "";
    expEle.innerText = exp;
}

sqrtNum.addEventListener('click', function () {
    if (result == 0) {
        return
    } else {
        result = Math.sqrt(result);
        updateResultEle();
        resetExpEle();
    }
})

switchNum.addEventListener('click', function () {
    if (result == 0) {
        return
    } else {
        result *= -1;
        updateResultEle();
        resetExpEle();
    }
})

let whiteBg = {
    '--primary-color': '#2980B9',
    '--bold-color': '#2255A4',
    '--bolder-color': '#1B308F',
    '--boldest-color': '#241062',
    '--light-color': '#44A9C4',
    '--lighter-color': '#7AD9CA',
    '--bg-color': '#fff',
    '--text-color': '#414141',
    '--bg-sub-color': '#464646'
}

let darkBg = {
    '--primary-color': '#333333',
    '--bold-color': '#262626',
    '--bolder-color': '#202020',
    '--boldest-color': '#1A1A1A',
    '--light-color': '#4E4E4E',
    '--lighter-color': '#858585',
    '--bg-color': '#223239',
    '--text-color': '#fff',
    '--bg-sub-color': '#bbd7e4'
}


document.querySelector('.toggle-wrapper')
    .addEventListener('click', function () {
        if (document.querySelector('#toggle-handle').checked) {
            for (let color in whiteBg) {
                document.documentElement.style.setProperty(color, whiteBg[color]);
            }
        } else {
            for (let color in darkBg) {
                document.documentElement.style.setProperty(color, darkBg[color]);
            }
        }
    })