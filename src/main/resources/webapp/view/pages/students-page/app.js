let aside = document.getElementsByClassName('aside')[0];
aside.addEventListener('mouseover', () => {
	aside.classList.add('active');
})
aside.addEventListener('mouseleave', () => {
	aside.classList.remove('active');
});

let header = document.getElementsByClassName('header')[0];
let profile = document.getElementsByClassName('profile')[0];
let header_arrow = document.getElementsByClassName('header-arrow')[0];
header_arrow.addEventListener('click', () => {
	header.classList.toggle('active');
	header_arrow.classList.toggle('active');
	profile.classList.toggle('active');
})