/* Fetch All Books */

fetch("http://localhost:8080/book",{

}).then(resp=> resp.json()).then(dt=>{
    addBooks(dt);
    getAllBook ();
})

function addBooks(books){
    const container =document.getElementById('data');
    books.forEach(element => {
        container.innerHTML+=`
        <div class="grid-item">
            <div class ="category-card">
                <img src="${element.image}"/>
                <h4> ${element.name} <h4>
                <h4> ${element.author} <h4>
                <a onClick="saveLocalStorage(${element.id},'${element.name}','${element.author}','${element.image}')"
                class="btn btn-danger">Add To Favorit </a>
        </div>
    </div>`;
    });

    
}
/* add  Books to local storage */
  function saveLocalStorage(id,name,author,image) {
    // Parse any JSON previously stored in allBook
    var existingBook = JSON.parse(localStorage.getItem("allBook"));
    if(existingBook == null) existingBook = [];
    var book = {
        "id": id,
        "name": name,
        "author":author,
        "image":image
    };
    localStorage.setItem("book", JSON.stringify(book));
    // Save allBook back to local storage
    existingBook.push(book);
    localStorage.setItem("allBook", JSON.stringify(existingBook));
    
};

function getallStorage() {

    const bt = localStorage.getItem("allBook")?JSON.parse(localStorage.getItem("allBook")):[]
    addFBooks(bt);
}

function getAllBook () {
    const btnFAv = document.getElementById("btnFAv");
    btnFAv.onclick = function (){
        const FavBook = document.getElementById("FavBook");
        FavBook.style.display= "block" ; 
        getallStorage();
    }
    
}

function addFBooks(books){
    const container =document.getElementById('favData');
    books.forEach(element => {
        container.innerHTML+=`
        <div class="grid-item">
            <div class ="category-card">
                <img src="${element.image}"/>
                <h4> ${element.name} <h4>
                <h4> ${element.author} <h4>
                
        </div>
    </div>`;
    });
}

function closeFav() {


        const FavBook = document.getElementById("FavBook");
        FavBook.style.display= "none" ;

}
    