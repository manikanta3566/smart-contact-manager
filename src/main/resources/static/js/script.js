console.log("welcome");
const toogleSideBar = () => {
  if ($(".sidebar").is(":visible")) {
    $(".sidebar").css("display", "none");
    $(".content").css("margin-left", "0%");
  } else {
    $(".sidebar").css("display", "block");
    $(".content").css("margin-left", "20%");
  }
}

const deleteContact = (id) => {
  swal({
    title: "Are you sure?",
    text: "you want to delete this contact?",
    icon: "warning",
    buttons: true,
    dangerMode: true,
  })
  .then((willDelete) => {
    if (willDelete) {
     window.location="/user/contacts/delete/"+id;
    } else {
      swal("Your contact is safe");
    }
  });
}
