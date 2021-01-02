const todo_list = document.querySelector('.todo-list');
const todo_input = document.querySelector('#todo-input');
const btn_add = document.querySelector('#btn-add');
let isUpdate = false;
let idUpdate = null;

const all = document.querySelector("#all");
const unactive = document.querySelector('#unactive');
const active = document.querySelector('#active');

all.addEventListener('change',filterTodo);
unactive.addEventListener('change',filterTodo);
active.addEventListener('change',filterTodo);
//id, title, status
function createId() {
    let id = Math.floor(Math.random() * 100000);
    return id;
}

let todos = [
    {
        id: createId(),
        title: "Đi đá bóng",
        status: true
    },
    {
        id: createId(),
        title: "Làm bài tập",
        status: false
    },
    {
        id: createId(),
        title: "Học lập trình javascript",
        status: true
    }
];

btn_add.addEventListener('click', () => {
    let todoTitle = todo_input.value;
    if (todoTitle == "") {
        alert("Nội dung không để trống");
        return;
    }

    if (isUpdate) {
        for (let i = 0; i < todos.length; i++) {
            if (todos[i].id == idUpdate) {
                todos[i].title = todoTitle;
            }
        }

        btn_add.innerText = "Thêm";
        isUpdate = false;
        idUpdate = null;
    } else {
        let newTodo = {
            id: createId(),
            title: todoTitle,
            status: false
        }

        todos.push(newTodo);
    }



    renderUI(todos);
    todo_input.value = "";
})


function renderUI(arr) {
    todo_list.innerHTML = "";

    if (arr.length == 0) {
        todo_list.innerHTML = '<p class="todo-empty">Không có công việc nào trong danh sách</p>';
        return;
    }

    for (let i = 0; i < arr.length; i++) {
        const t = arr[i];
        todo_list.innerHTML += `
            <div class="todo-item ${t.status ? `active-todo` : ``}">
                <div class="todo-item-title">
                    <input type="checkbox" ${t.status ? `checked` : ``} onClick = "toggleStatus(${t.id})">
                    <p>${t.title}</p>
                </div>
                <div class="option">
                    <button class="btn btn-update" onClick ="updateTodo(${t.id})">
                        <img src="./img/pencil.svg" alt="icon">
                    </button>
                    <button class="btn btn-delete" onClick ="deleteTodo(${t.id})">
                        <img src="./img/remove.svg" alt="icon">
                    </button>
                </div>
            </div>
        `;
    }

}

function updateTodo(id) {
    let title = "";
    for (let i = 0; i < todos.length; i++) {
        if (todos[i].id == id) {
            title = todos[i].title;
        }
    }

    btn_add.innerHTML = "CẬP NHẬT";
    todo_input.value = title;
    todo_input.focus();

    idUpdate = id;
    isUpdate = true;
}

function deleteTodo(id) {
    for (let i = 0; i < todos.length; i++) {
        if (todos[i].id == id) {
            todos.splice(i, 1);
        }
    }
    renderUI(todos);
}

function toggleStatus(id) {
    for (let i = 0; i < todos.length; i++) {
        if (todos[i].id == id) {
            todos[i].status = !todos[i].status;
        }
    }
    renderUI(todos);
}


function filterTodo() {
    let newtodos = [];
    if(todos.length==0){
        renderUI(todos);
        return;        
    }
    if (this.checked) {
        if (this.id == "all") {
            for (let i = 0; i < todos.length; i++) {
                newtodos.push(todos[i]);
            }
            renderUI(newtodos);
        }
        else if (this.id == "unactive") {
            for (let i = 0; i < todos.length; i++) {
                if (todos[i].status == false) {
                    newtodos.push(todos[i]);
                }
            }
            renderUI(newtodos);
        }
        else {
            for (let i = 0; i < todos.length; i++) {
                if (todos[i].status == true) {
                    newtodos.push(todos[i]);
                }
            }
            renderUI(newtodos);
        }
    }
    else {
        return;
    }
}
renderUI(todos);


