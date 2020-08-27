//Welcome screen
const gameStart = document.querySelector('.welcome');
const snakeGame = document.querySelectorAll('.level .speed');

const minesGame = document.querySelectorAll('.level .difficulty');
const snakeFrame = document.querySelector('.snake-frame');
const speeds = [200,100,50];
const minesNums = [10,15,20];

//End screen snake
const gameEndSnake = document.querySelector('#endSnake');
const returnBtnSnake = document.querySelector('#returnSnake');
const restartBtnSnake = document.querySelector('#restartSnake');
let msgSnake = document.querySelector('#snakeMsg');

//End screen minesweeper
const gameEndMine = document.querySelector('#endMine');
const returnBtnMine = document.querySelector('#returnMine');
const restartBtnMine = document.querySelector('#restartMine');
let msgMine = document.querySelector('#mineMsg');

snakeFrame.style.display = "none";
gameEndSnake.style.display = "none";
gameEndMine.style.display = "none";

//Audio 
const audioStart = document.getElementById("audioStart");
const audioMain = document.getElementById("background");
const snakeEating = document.getElementById("feeding");
const wallHit = document.getElementById("wall_hit");
const audioOver = document.getElementById("overSound");

const audioMine = document.getElementById("sweeper");
const audioBomb = document.getElementById("bomb");
const audioClick = document.getElementById("click");
//Setting the canvas
const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");
let width = canvas.width;
let height = canvas.height;

//Setting the block
let blockSize = 10;
let widthInBlocks = width / blockSize;
let heightInBlocks = height / blockSize;

//Setting the score, snakeSpeed
let score = 0;
let snakeSpeed = 0;

//Utility function
function drawBorder() {
    ctx.fillStyle = "grey";
    ctx.fillRect(0, 0, width, blockSize);
    ctx.fillRect(width - blockSize, 0, blockSize, height);
    ctx.fillRect(0, 0, blockSize, height);
    ctx.fillRect(0, height - blockSize, width, blockSize);
}

function drawScore() {
    ctx.font = "20px Arial";
    ctx.fillStyle = "white";
    ctx.textAlign = "left";
    ctx.textBaseLine = "top";
    ctx.fillText(`Scores: ${score}`, blockSize, blockSize + 15);
}

function drawCircle(x, y, radius, fillCircle) {
    ctx.beginPath();
    ctx.arc(x, y, radius, 0, Math.PI * 2, false);
    fillCircle ? ctx.fill() : ctx.stroke();
}



//Class Block
class Block {
    constructor(col, row) {
        this.col = col;
        this.row = row;
    }
    drawSquare(color) {
        let x = this.col * blockSize;
        let y = this.row * blockSize;
        ctx.fillStyle = color;
        ctx.fillRect(x, y, blockSize, blockSize);
    }
    drawCircle(color) {
        let centerX = this.col * blockSize + blockSize / 2;
        let centerY = this.row * blockSize + blockSize / 2;
        ctx.fillStyle = color;
        drawCircle(centerX, centerY, blockSize / 2, true);
    }
    equal(otherBlocks) {
        return this.col === otherBlocks.col && this.row === otherBlocks.row;
    }
}

//Class Apple
class Apple {
    constructor(color) {
        this.position = new Block(10, 10);
        this.color = color;
    }

    draw() {
        this.position.drawCircle(this.color);
    }

    move() {
        let randomCol = Math.floor(Math.random() * (widthInBlocks - 2)) + 1;
        let randomRow = Math.floor(Math.random() * (heightInBlocks - 2)) + 1;
        this.position = new Block(randomCol, randomRow);
    }
}

//Class Snake
class Snake {
    constructor(color) {
        this.segments = [new Block(7, 5), new Block(6, 5), new Block(5, 5)];
        this.direction = "right";
        this.nextDirection = "right";
        this.color = color;
    }

    draw() {
        this.segments.forEach(segment => segment.drawSquare(this.color));
        this.segments[0].drawSquare('green');
    }

    move() {
        let head = this.segments[0];
        let newHead;
        this.direction = this.nextDirection;
        if (this.direction === "right") {
            newHead = new Block(head.col + 1, head.row);
        } else if (this.direction === "down") {
            newHead = new Block(head.col, head.row + 1);
        } else if (this.direction === "left") {
            newHead = new Block(head.col - 1, head.row);
        } else if (this.direction === "up") {
            newHead = new Block(head.col, head.row - 1);
        }

        if (this.checkCollision(newHead)) {
            wallHit.play();
            gameOver();
            return;
        }
        this.segments.unshift(newHead);
        if (newHead.equal(apple.position)) {
            snakeEating.play();
            switch(snakeSpeed){
                case speeds[0]:
                    score++;
                    break;
                case speeds[1]:
                        score+=2;;
                        break;
                case speeds[2]:
                        score+=3;
                        break;
            }
            apple.move();
        } else {
            this.segments.pop();
        }

    }

    setDirection(newDirection) {
        if (this.direction === "up" && newDirection === "down") {
            return;
        } else if (this.direction === "right" && newDirection === "left") {
            return;
        } else if (this.direction === "down" && newDirection === "up") {
            return;
        } else if (this.direction === "left" && newDirection === "right") {
            return;
        }
        this.nextDirection = newDirection;
    };

    checkCollision(head) {
        let leftCollision = (head.col === 0);
        let topCollision = (head.row === 0);
        let rightCollision = (head.col === widthInBlocks - 1);
        let bottomCollision = (head.row === heightInBlocks - 1);
        let wallCollision = leftCollision || topCollision || rightCollision || bottomCollision;
        let selfCollision = false;
        for (let i = 0; i < this.segments.length; i++) {
            if (head.equal(this.segments[i])) {
                selfCollision = true;
            }
        }
        return wallCollision || selfCollision;
    };


}

let directions = {
    37: "left",
    38: "up",
    39: "right",
    40: "down"
};
addEventListener('keydown', event => {
    let newDirection = directions[event.keyCode];
    if (newDirection)
        snake.setDirection(newDirection);
});

//Set the object snake and apple
let snake = new Snake('yellow');
let apple = new Apple('red');




//Active the game
audioStart.src = "./audio/gameStart.mp3";
window.addEventListener("keydown",e=>e.keyCode==13?audioStart.play():audioStart.pause());

    
snakeGame.forEach((speed,index)=>{
    speed.addEventListener('click',()=>{
        gameStart.style.display = "none";
        snakeFrame.style.display = "block";
        document.body.style.backgroundImage = "url('snake.jpg')";
        snakeSpeed = speeds[index];
        audioStart.pause();
        audioMain.play();
        startGame();
    })
})

//Check the game over
function gameOver() {
    clearInterval(intervalId);
    snakeFrame.style.display = "none";
    gameEndSnake.style.display = "block";
    audioMain.pause();
    audioOver.src = "./audio/gameOver.mp3";
    audioOver.play();
    document.body.style.backgroundImage = "url('gameOver.jpg')";
    msgSnake.textContent = `Congratulation!! You got: ${score} points`;
};

//Handle the end

returnBtnSnake.addEventListener('click', ()=>{
    gameEndSnake.style.display = "none";
    snakeFrame.style.display = "none";
    gameStart.style.display = "block";
    audioMain.pause();
    audioOver.pause();
    audioStart.play();
    document.body.style.backgroundImage = "url('background.jpg')"
    startGame();
    clearInterval(intervalId);
});

restartBtnSnake.addEventListener('click', e => {
    gameEndSnake.style.display = "none";
    gameStart.style.display = "none";
    snakeFrame.style.display = "block";
    audioOver.pause();
    audioMain.play();
    document.body.style.backgroundImage = "url('snake.jpg')"
    startGame();
})

//start Game function
function startGame() {
    score = 0;
    snake = new Snake('yellow');
    apple = new Apple('red');
    
    intervalId = setInterval(function () {
        ctx.clearRect(0, 0, width, height);
        drawScore();
        snake.move();
        snake.draw();
        apple.draw();
        drawBorder();
    }, snakeSpeed);
}

//start the minesweeper
let row_count = 10;
let col_count = 10;
let mine_count = 0;
let timeoutId;


minesGame.forEach((mine,index)=>{
    mine.addEventListener("click",()=>{
        gameStart.style.display = "none";
        mine_count = minesNums[index];
        document.body.style.backgroundImage = "url('minesweeper.jpg')";
        audioStart.pause();
        audioMine.play();
        createBoard();
    })
})

function createBoard() {
    const board = document.getElementById("board");
    board.style.display = "flex";
    for (let i = 0; i < row_count; i++) {
        const row = document.createElement('div');
        row.className = "row";
        board.append(row);
        for (let j = 0; j < col_count; j++) {
            const cell = document.createElement('div');
            cell.className = "cell";
            cell.id = i + "_" + j;
            row.append(cell);
            cell.addEventListener('click', function () {
                handleClick(i, j);
            });

            cell.addEventListener('contextmenu', function (e) {
                e.preventDefault();
                handleRightClick(i, j);
            })
        }
    }
    let positionsArray = createMinePosition();
    placeTheMines(positionsArray);
}

function createMinePosition() {
    
    let arrMinePositions = [];
    for (let i = 0; i < mine_count;) {
        let x = Math.floor(Math.random() * row_count);
        let y = Math.floor(Math.random() * col_count);
        let position = [x, y];
        let isUnique = true;
        arrMinePositions.forEach(function(eachExistingPosition){
            if(eachExistingPosition[0] == x && eachExistingPosition[1] ==y){
                isUnique = false;
            }
        });

        if(isUnique){
            arrMinePositions[i] = position;
            i++;
        }
    }
    return arrMinePositions;
}

function placeTheMines(minePositions) {
    for (let i = 0; i < minePositions.length; i++) {
        let eachPosition = minePositions[i];
        let x = eachPosition[0];
        let y = eachPosition[1];
        let cellAtThisPosition = document.getElementById(x + "_" + y);
        cellAtThisPosition.classList.add("mine");
    }
}

function handleClick(x, y) {
    let cell = document.getElementById(x + "_" + y);
    if (cell.classList.contains('mine')) {
        //Reveal all mine
        for (let i = 0; i < row_count; i++) {
            for (let j = 0; j < col_count; j++) {
                let cell = document.getElementById(i + "_" + j);
                if (cell.classList.contains('mine')){
                    cell.classList.add("revealed");
                    audioBomb.play();
                }
               
            }
        }
    } else {
        //Reveals the cells without a mine
        audioClick.play();
        reveal(x, y);
    }
    timeoutId = setTimeout(checkIfWon,5000);
    
}

function reveal(x, y) {
    let cell = document.getElementById(x + "_" + y);
    if (cell.classList.contains('revealed')) {
        //already revealed

    }
    else {
        cell.classList.add('revealed');
        let mine_count_adjacent = 0;
        for (let m = Math.max(x - 1, 0); m <= Math.min(x + 1, row_count - 1); m++) {
            for (let n = Math.max(y - 1, 0); n <= Math.min(y + 1, col_count - 1); n++) {
                let adjacentCell = document.getElementById(m + "_" + n);
                if (adjacentCell.classList.contains('mine')) {
                    mine_count_adjacent++;
                }
            }
        }

        if (mine_count_adjacent > 0) {
            cell.innerHTML = mine_count_adjacent;
        }
        else {
            for (let m = Math.max(x - 1, 0); m <= Math.min(x + 1, row_count - 1); m++) {
                for (let n = Math.max(y - 1, 0); n < Math.min(y + 1, col_count - 1); n++) {
                    reveal(m, n);
                }
            }
        }
    }
}

function handleRightClick(x, y) {
    let cell = document.getElementById(x + "_" + y);
    if (!cell.classList.contains("revealed")) {
        if (cell.classList.contains("flagged")) {
            cell.classList.remove("flagged");
        } else {
            cell.classList.add("flagged");
        }
    } 
}


function checkIfWon(){
    let minesRevealed = document.getElementsByClassName("mine revealed").length;
    let cellsStillHidden = row_count * col_count - document.getElementsByClassName("cell revealed").length;
    
    if(minesRevealed>0){
        showGameOver(false);
    }else if(cellsStillHidden == mine_count){
        showGameOver(true);
    }
   
}

function showGameOver(won){
    board.style.display = "none";
    clearTimeout(timeoutId);
    audioMine.pause();
    if(won){
        gameEndMine.style.display = "block";
        audioOver.src = "./audio/Victory.mp3";
        document.body.style.backgroundImage = "url('victory.jpg')";
        msgMine.textContent = "Congratulation! You won!";
    }else{
        gameEndMine.style.display = "block";
        audioOver.src = "./audio/gameOver.mp3";
        msgMine.textContent = "You lose !!";
        document.body.style.backgroundImage = "url('gameOver.jpg')";
    }
    audioOver.play();
}

returnBtnMine.addEventListener('click',(e)=>{
    gameEndMine.style.display = "none";
    board.style.display = "none";
    document.body.style.backgroundImage = "url('background.jpg')";
    gameStart.style.display = "block";
    audioOver.pause();
    audioStart.play();
    clearBoard();
});

restartBtnMine.addEventListener('click',()=>{
    gameEndMine.style.display = "none";
    gameStart.style.display = "none";
    board.style.display = "flex";
    document.body.style.backgroundImage = "url('minesweeper.jpg')";
    audioOver.pause();
    audioMine.play();
    clearBoard();
    createBoard();
})

function clearBoard(){
   const board = document.getElementById("board");
   board.innerHTML = "";
}







