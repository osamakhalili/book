
/* Add New Book*/
  const myForm = document.getElementById("tForm");

  myForm.addEventListener("submit", function (e){
      e.preventDefault();

    let inp_name = document.getElementById("inp_name").value;
    let inp_author = document.getElementById("inp_author").value;
    let inp_image = document.getElementById("inp_image").value;
  
      fetch('http://localhost:8080/book', {
        method: 'POST', 
        body:  JSON.stringify({name:inp_name,
            author:inp_author,
            image:inp_image}),
            headers:{'content-type': 'application/json; charset=UTF-8'}
        
      }).then(function(response) {
  
          return response.json()
  
         
      }).then(function (data) {
  
        console.log(data)

    })
      alert ("book added");
      window.location.href = window.location.href;
  });

  /* Fetch All Books and Delete book*/

  fetch("http://localhost:8080/book",{

}).then(resp=> resp.json()).then(dt=>{
    addBooks(dt);
    
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
                <a onClick="ShowBox(${element.id},'${element.name}','${element.author}','${element.image}')"  class="btn btn-primary">Update</a>
                <a onClick="deleteData(${element.id})" class="btn btn-danger">Delete</a>
        </div>
    </div>`;
    });
}

function deleteData(id) {
    if (confirm('Are you sure to delete this record ?')) {
    return fetch("http://localhost:8080/book" + '/' + id, {
      method: 'delete'
    }) 
    
}
window.location.reload();

  }

  function ShowBox(id,name,author,image) {
  

    const upForm = document.getElementById("upForm");

      upForm.style.display= "flex" ; 
         
     document.getElementById("up_name").value= name ;
     document.getElementById("up_author").value= author ;
     document.getElementById("up_image").value= image ;
 
     
     updateData(id);
  }


  

  function updateData(id) {

    const upForm = document.getElementById("upForm");

    upForm.addEventListener("submit", function (e){
        e.preventDefault();
      
      fetch("http://localhost:8080/book" + '/' + id, {
      method: 'PATCH',
      body:  JSON.stringify({author: up_author.value , image: up_image.value, name: up_name.value }),
        headers:{'content-type': 'application/json; charset=UTF-8'}

    }) 
    alert ("book Updaed");
    window.location.reload();
    
})
  }
  
  function closeUp() {

    const upBook = document.getElementById("upForm");
    upBook.style.display= "none" ;

}

  

  