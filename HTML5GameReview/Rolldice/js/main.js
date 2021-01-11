let gamePlaying, roundScore, activePlayer;
let scores;
document.querySelector('.btn-roll').addEventListener('click', e => {
    if (gamePlaying) {
        let dice1 = Math.floor(Math.random() * 6) + 1;
        let dice2 = Math.floor(Math.random() * 6) + 1;

        document.getElementById('dice-1').style.display = 'block';
        document.getElementById('dice-2').style.display = 'block';
        document.getElementById('dice-1').src = `./img/dice-${dice1}.png`;
        document.getElementById('dice-2').src = `./img/dice-${dice2}.png`;

        if (dice1 != 1 && dice2 != 1) {
            roundScore = roundScore + dice1 + dice2;
            document.querySelector(`#current-${activePlayer}`).textContent = roundScore;
        } else {
            nextPlayer();
        }
    }
})

document.querySelector('.btn-hold').addEventListener('click', function () {
    if (gamePlaying) {
        scores[activePlayer] += roundScore;

        document.querySelector(`#score-${activePlayer}`).textContent = scores[activePlayer];

        let input = document.querySelector('.final-score').nodeValue;

        let winningScore = input ? input : 100;

        if (scores[activePlayer] >= winningScore) {
            document.getElementById('dice-1').style.display = 'none';
            document.getElementById('dice-2').style.display = 'none';
            document.querySelector(`.player-${activePlayer}-panel`).classList.add('winner');
            document.querySelector(`.player-${activePlayer}-panel`).classList.remove('active');

            document.querySelector(`#name-${activePlayer}`).textContent = 'Winner!';
            gamePlaying = false;

        }
        else {
            nextPlayer();
        }
    }
})

function nextPlayer() {
    activePlayer = activePlayer == 0 ? 1 : 0;
    roundScore = 0;


    document.getElementById('current-0').textContent = "0";
    document.getElementById('current-1').textContent = "0";

    document.getElementById('dice-1').style.display = 'none';
    document.getElementById('dice-2').style.display = 'none';

    document.querySelector('.player-0-panel').classList.toggle('active');
    document.querySelector('.player-1-panel').classList.toggle('active');
}

function init() {
    gamePlaying = true;
    roundScore = 0;
    activePlayer = 0;
    scores = [0, 0];
    document.getElementById('name-0').textContent = "Player 1";
    document.getElementById('name-1').textContent = "Player 2";
    document.getElementById('score-0').textContent = "0";
    document.getElementById('score-1').textContent = "0";
    document.getElementById('current-0').textContent = "0";
    document.getElementById('current-1').textContent = "0";
    document.getElementById('dice-1').style.display = 'none';
    document.getElementById('dice-2').style.display = 'none';

    document.querySelector('.player-0-panel').classList.remove('active');
    document.querySelector('.player-1-panel').classList.remove('active');

    document.querySelector('.player-0-panel').classList.remove('winner');
    document.querySelector('.player-1-panel').classList.remove('winner');

    document.querySelector('.player-0-panel').classList.add('active');

}

document.querySelector('.btn-new').addEventListener('click',()=>{
    init();
})

window.onload = init();