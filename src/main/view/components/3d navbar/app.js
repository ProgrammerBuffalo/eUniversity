const hamburger_menu = document.querySelector(".hamburger-menu");
const container = document.querySelector(".container");
const body = document.querySelector("body");

hamburger_menu.addEventListener("click", () => {
  container.classList.toggle("active");
  body.classList.toggle("scroll");
});