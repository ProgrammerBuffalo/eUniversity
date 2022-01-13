$(document).ready(() => {
	initModal(document.getElementById('students-change'),
		document.getElementsByClassName("change-btn"),
		document.getElementById("closeStudentsChangeBtn"));

	initModal(document.getElementById('students-add'),
		document.getElementsByClassName("add-btn"),
		document.getElementById("closeStudentsAddBtn"));
})

console.log(document.getElementById("select"));
