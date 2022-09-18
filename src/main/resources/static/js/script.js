console.log("welcome");
const toogleSideBar = () => {
  if ($(".sidebar").is(":visible")) {
    $(".sidebar").css("display", "none");
    $(".content").css("margin-left", "0%");
  } else {
    $(".sidebar").css("display", "block");
    $(".content").css("margin-left", "20%");
  }
};

const deleteContact = (id) => {
  swal({
    title: "Are you sure?",
    text: "you want to delete this contact?",
    icon: "warning",
    buttons: true,
    dangerMode: true,
  }).then((willDelete) => {
    if (willDelete) {
      window.location = "/user/contacts/delete/" + id;
    } else {
      swal("Your contact is safe");
    }
  });
};

const search = () => {
  const value = $("#search_input").val();
  console.log(value);
  if (!value == "") {
    const url = `http://localhost:8080/user/search/${value}`;
    fetch(url)
      .then((response) => {
        return response.json();
      })
      .then((data) => {
        if(data.length=== 0) {
          $(".search_result").hide();
        }
        let html = `<div class="list-group">`;
        data.forEach((item) => {
          html += ` <a href="/user/contacts/${item.id}" class="list-group-item list-group-item-action">${item.name}</a>`;
        });
        html += ` </div>`;
        $(".search_result").html(html);
        $(".search_result").show();
      })
      .catch((error) => {
        $(".search_result").hide();
      });
  }
};
