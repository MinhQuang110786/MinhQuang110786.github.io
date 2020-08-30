
var gamePlaying, activePlayer;

class Player {
    constructor(name, active, score) {
        this.name = name;
        this.active = active;
        this.score = score;
        this.roundScore = 0;
    }
    roll() {
        this.dice1 = Math.floor(Math.random() * 6) + 1;
        this.dice2 = Math.floor(Math.random() * 6) + 1;
        if (this.dice1 !== 1 && this.dice2 !== 1) {
            this.roundScore += this.dice1 + this.dice2;
        }
        else {
            this.active = false;
            this.roundScore = 0;
        }
    }
}
var player1, player2;
init();

document.querySelector('.btn-roll').addEventListener('click', function () {
    if (gamePlaying) {
        // 1. Random number
        // var dice1 = Math.floor(Math.random() * 6) + 1;
        // var dice2 = Math.floor(Math.random() * 6) + 1;

        activePlayer = player1.active == true ? player1 : player2;
        console.log(activePlayer);
        activePlayer.roll();
        document.getElementById('dice-1').style.display = 'block';
        document.getElementById('dice-2').style.display = 'block';
        document.getElementById('dice-1').src = 'dice-' + activePlayer.dice1 + '.png';
        document.getElementById('dice-2').src = 'dice-' + activePlayer.dice2 + '.png';
        // //2. Display the result
        // document.getElementById('dice-1').style.display = 'block';
        // document.getElementById('dice-2').style.display = 'block';
        // document.getElementById('dice-1').src = 'dice-' + activePlayer.dice1 + '.png';
        // document.getElementById('dice-2').src = 'dice-' + activePlayer.dice2 + '.png';

        // //3. Update the round score IF the rolled number was NOT a 1
        // if (dice1 !== 1 && dice2 !== 1) {
        //     //Add score
        //     roundScore += dice1 + dice2;
        //     document.querySelector('#current-' + activePlayer).textContent = roundScore;
        // } else {
        //     //Next player
        //     nextPlayer();
        // }
        console.log(activePlayer);
        if (activePlayer.active == true) {
            var number = (activePlayer.name == "Player1") ? 0 : 1;
            document.querySelector('#current-' + number).textContent = activePlayer.roundScore;
        }
        else {

            activePlayer = activePlayer == player1 ? player2 : player1;

            activePlayer.active = true;
            console.log(activePlayer);
            document.getElementById('current-0').textContent = '0';
            document.getElementById('current-1').textContent = '0';

            document.querySelector('.player-0-panel').classList.toggle('active');
            document.querySelector('.player-1-panel').classList.toggle('active');

            document.getElementById('dice-1').style.display = 'none';
            document.getElementById('dice-2').style.display = 'none';
        }




    }
});


document.querySelector('.btn-hold').addEventListener('click', function () {
    if (gamePlaying) {
        // Add CURRENT score to GLOBAL score
        // scores[activePlayer] += roundScore;
        activePlayer.score += activePlayer.roundScore;
        var number = (activePlayer.name == "Player1") ? 0 : 1;
        // Update the UI
        document.querySelector('#score-' + number).textContent = activePlayer.score;

        var input = document.querySelector('.final-score').value;
        var winningScore;

        // Undefined, 0, null or "" are COERCED to false
        // Anything else is COERCED to true
        if (input) {
            winningScore = input;
        } else {
            winningScore = 100;
        }

        // Check if player won the game
        if (activePlayer.score >= winningScore) {
            document.querySelector('#name-' + number).textContent = 'Winner!';
            document.getElementById('dice-1').style.display = 'none';
            document.getElementById('dice-2').style.display = 'none';
            document.querySelector('.player-' + number + '-panel').classList.add('winner');
            document.querySelector('.player-' + number + '-panel').classList.remove('active');
            gamePlaying = false;
        } else {
            activePlayer.active = false;
            activePlayer = (activePlayer.name == "Player1") ? player2 : player1;
            activePlayer.active = true;
            document.getElementById('current-0').textContent = '0';
            document.getElementById('current-1').textContent = '0';

            document.querySelector('.player-0-panel').classList.toggle('active');
            document.querySelector('.player-1-panel').classList.toggle('active');

            document.getElementById('dice-1').style.display = 'none';
            document.getElementById('dice-2').style.display = 'none';
        }
    }
});

document.querySelector('.btn-new').addEventListener('click', init);



function init() {
    player1 = new Player('Player1', true, 0);
    player2 = new Player('Player2', false, 0);
    // gamePlaying = true;
    // scores = [0, 0];
    // activePlayer = 0;
    // roundScore = 0;
    gamePlaying = true;

    document.getElementById('dice-1').style.display = 'none';
    document.getElementById('dice-2').style.display = 'none';

    document.getElementById('score-0').textContent = player1.score;
    document.getElementById('score-1').textContent = player2.score;
    document.getElementById('current-0').textContent = player1.roundScore;
    document.getElementById('current-1').textContent = player2.roundScore;
    document.getElementById('name-0').textContent = player1.name;
    document.getElementById('name-1').textContent = player2.name;
    document.querySelector('.player-0-panel').classList.remove('winner');
    document.querySelector('.player-1-panel').classList.remove('winner');
    document.querySelector('.player-0-panel').classList.remove('active');
    document.querySelector('.player-1-panel').classList.remove('active');
    document.querySelector('.player-0-panel').classList.add('active');
}