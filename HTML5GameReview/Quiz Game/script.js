let questions = [
    {
        question: "What is the full form of RAM ?",
        answer: "Random Access Memory",
        options: [
            "Random Access Memory",
            "Randomely Access Memory",
            "Run Aceapt Memory",
            "None of these",
        ],
    },
    {
        question: "What is the full form of CPU?",
        answer: "Central Processing Unit",
        options: [
            "Central Program Unit",
            "Central Processing Unit",
            "Central Preload Unit",
            "None of these",
        ],
    },
    {
        question: "What is the full form of E-mail",
        answer: "Electronic Mail",
        options: [
            "Electronic Mail",
            "Electric Mail",
            "Engine Mail",
            "None of these",
        ],
    },
];

let current_question = 0;
let answers = [];
let score = 0;
function showQuestion(count) {
    let quiz_question = document.querySelector(".quiz-question p");
    let quiz_answer = document.querySelector(".quiz-answer");

    let question = questions[count];

    quiz_question.innerText = `Câu ${count + 1} : ${question.question}`;

    quiz_answer.innerHTML = '';
    for (let i = 0; i < question.options.length; i++) {
        let q = question.options[i];
        quiz_answer.innerHTML += `
            <div class="quiz-item">
                <input 
                    type="radio" 
                    id="answer-${i + 1}" 
                    name="question-${count + 1}"

                    // Hiển thị lại các đáp án người dùng đã chọn
                    ${q === answers[count] ? 'checked' : null}
                />
                <label for="answer-${i + 1}">
                    <span></span>
                    <p>${q}</p>
                </label>
            </div>
        `
    }

    updateProgressBar();

    // Nếu người chơi chưa trả lời câu hỏi thì disable nút next
    if (!answers[count] || count == questions.length - 1) {
        document.querySelector('.next-btn').classList.add('disable')
    } else {
        document.querySelector('.next-btn').classList.remove('disable')
    }

    if (count == 0) {
        document.querySelector('.prev-btn').classList.add('disable')
    } else {
        document.querySelector('.prev-btn').classList.remove('disable')
    }

    // Nếu là câu hỏi cuối cùng thì hiển thị nút submit
    if (count == questions.length - 1) {
        document.querySelector('.finish-btn').style.display = 'inline-block';
        document.querySelector('.next-btn').style.display = 'none';
    } else {
        document.querySelector('.finish-btn').style.display = 'none';
        document.querySelector('.next-btn').style.display = 'inline-block';
    }

    // Lắng nghe sự kiện khi người dùng chọn đáp án
    let inputs = document.querySelectorAll('.quiz-item input');
    Array.from(inputs).forEach(input => {
        input.addEventListener('change', choseAnswer)
    })
}


function updateProgressBar() {
    document.querySelector('.quiz-progress-bar').style.width = `${(current_question + 1) * 100 / questions.length}%`;
}


window.onload = function () {
    showQuestion(current_question);
}


document.querySelector('.next-btn').addEventListener('click', nextQuestion);

function nextQuestion() {
    current_question++
    if (current_question > questions.length - 1) {
        current_question = questions.length - 1;
    }
    showQuestion(current_question);
}

document.querySelector('.prev-btn').addEventListener('click', prevQuestion);

function prevQuestion() {
    current_question--
    if (current_question < 0) {
        current_question = 0;
    }
    showQuestion(current_question);
}

function choseAnswer() {
    // Lưu trữ nội dung của câu trả lời, tương ứng với current_question
    answers[current_question] = this.nextElementSibling.innerText.trim();

    // Nếu không phải câu hỏi cuối thì remove class "disable" của nút "Next Question"
    // Ngược lại remove class "disable" của nút "Submit"
    if (current_question != questions.length - 1) {
        document.querySelector('.next-btn').classList.remove('disable');
    } else {
        document.querySelector('.finish-btn').classList.remove('disable');
    }
}

document.querySelector('.finish-btn').addEventListener('click', showResult);

function showResult() {
    for (let i = 0; i < questions.length; i++) {
        for (let j = 0; j < answers.length; j++) {
            // Nếu cùng vị trí và 2 đáp án giống nhau thì score tăng thêm 1
            if (i == j && questions[i].answer == answers[j]) {
                score++
            }
        }
    }
    document.querySelector('.quiz-bottom').style.display = 'none';
    document.querySelector('.quiz-progress-bar').style.display = 'none';
    document.querySelector('.quiz-results').style.display = 'block';
    document.querySelector('.quiz-results-score').innerText = `Bạn đã trả lời đúng ${score}/${questions.length} câu hỏi`;
}

document.querySelector('.back-btn').addEventListener('click', function () {
    // Reload lại trang
    window.location.reload();
})




