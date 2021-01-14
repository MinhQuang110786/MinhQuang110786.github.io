let card = document.getElementsByClassName('card');
let deck = document.getElementById('card-deck');

let cards = [...card];
let openedCards = [];
let matchedCard = document.getElementsByClassName("match");
let moves = 0;
let counter = document.querySelector(".moves"); // Nơi hiển thị số lần di chuyển trên giao diện

function randomPostion(arr) {
    return arr.sort(() => Math.random() - 0.5);
}

function startGame() {
    cards = randomPostion(cards);
    deck.innerHTML = "";
    for (let i = 0; i < cards.length; i++) {
        deck.appendChild(cards[i]);
        cards[i].classList.remove('open', 'match', 'disable');
    }
}

for (let i = 0; i < cards.length; i++) {
    cards[i].addEventListener("click", displayCard);
    cards[i].addEventListener("click", cardOpen);
}


function displayCard() {
    this.classList.toggle("open");
    this.classList.toggle("disable");
}

function cardOpen() {
    openedCards.push(this);
    let len = openedCards.length;
    if (len === 2) {
        // TODO: update số lần di chuyển
        moveCounter();
        if (openedCards[0].type === openedCards[1].type) {
            // TODO: Xử lý khi 2 card giống nhau
            matched();

        } else {
            // TODO: Xử lý khi 2 card khác nhau
            unmatched();
        }
    }
}

function matched() {
    openedCards[0].classList.add("match", "disabled");
    openedCards[1].classList.add("match", "disabled");
    openedCards[0].classList.remove("open");
    openedCards[1].classList.remove("open");
    openedCards = [];
}

function unmatched() {
    openedCards[0].classList.add("unmatched");
    openedCards[1].classList.add("unmatched");
    disable();
    setTimeout(function () {
        openedCards[0].classList.remove("open", "unmatched");
        openedCards[1].classList.remove("open", "unmatched");
        enable();
        openedCards = [];
    }, 1100);
}

function disable() {
    cards.map(c => c.classList.add("disabled"));
}

function enable() {
    cards.map(c => { c.classList.remove("disabled") })
    Array.from(matchedCard).map(c => c.classList.add("disabled"))
}


function moveCounter() {
    moves++;
    counter.innerHTML = moves;
    if (moves == 1) {
        second = 0;
        minute = 0;
        startTimer();
    }
}

let second = 0;
let minute = 0;
let interval;
let timer = document.querySelector(".timer"); // Nơi hiển thị thời gian trên giao diện

function startTimer() {
    interval = setInterval(function () {
        timer.innerHTML = `${minute} mins ${second} secs`;
        second++;
        if (second == 60) {
            minute++;
            second = 0;
        }
    }, 1000);
}
startGame();


