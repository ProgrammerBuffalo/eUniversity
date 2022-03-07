let aside = document.getElementsByClassName('aside')[0];
let burger = document.getElementsByClassName('burger')[0];

burger.addEventListener('click', () => {
	aside.classList.toggle('active');
	burger.classList.toggle('active');
});