const startGameEle = document.getElementById('start-game');
const gameEl = document.querySelector('.container');
const endGameEl = document.getElementById('end-game-container');

const text = document.getElementById("text");
const timeEle = document.getElementById('time');
const scoreEle = document.getElementById('score');
const word = document.getElementById('word');

const words = [
    "write",
    "began",
    "he",
    "sea",
    "eye",
    "learn",
    "have",
    "love",
    "dog",
    "game",
    "element",
    "read",
    "such",
    "up",
    "them",
    "every",
    "point",
    "all",
    "form",
    "thing",
];

const btnReloadGame = document.getElementById("btn-reload-game");

const btnStartGame = document.getElementById('btn-start-game');

const selectEle = document.getElementById('level');

let randomWord;
let score = 0;
let time = 20;
let timeInterval;
let level;



function getRandomWord() {
    let index = Math.floor(Math.random() * words.length);
    return words[index];
}

function addWordToDom() {
    randomWord = getRandomWord();
    console.log(randomWord);
    word.innerText = randomWord;
}

function updateScore() {
    scoreEle.innerText = score++;
}

function updateTime() {

    timeEle.innerText = `${time--}s`;
    if(time<=5){
        timeEle.style.color ="red";
    }
    if (time == 0) {
        clearInterval(timeInterval);

        gameOver();
    }
}

function gameOver() {
    endGameEl.style.display = 'flex';
    endGameEl.querySelector('.score').innerText = `${score}`;
}


addWordToDom();
updateScore();

text.addEventListener('input', e => {
    let insertedText = e.target.value;

    if(!randomWord.startWith(insertedText)){
        word.style.background = 'red';
    }else{
        word.style.background = 'transparent';
    }
    if (insertedText == randomWord) {
        addWordToDom();
        updateScore();

        e.target.value = "";

        if (level == "hard") {
            time += 2;
        } else if (level == "medium") {
            time += 3;
        } else {
            time += 5;
        }
        updateTime();
    }
})


btnStartGame.addEventListener('click', e => {
    level = selectEle.value;
    startGameEle.classList.add('hide');
    gameEl.classList.remove('hide');
    text.focus();



    // setTimeout(() => {
    //     endGameEl.style.display = 'flex';
    // }, 3000)
    timeInterval = setInterval(updateTime, 1000);

})

btnReloadGame.addEventListener('click', e => {
    window.location.reload();
})