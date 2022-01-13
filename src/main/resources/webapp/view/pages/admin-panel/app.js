$(document).ready(() => {
	initModal(document.getElementById('students-change'),
		document.getElementsByClassName("change-btn"),
		document.getElementById("closeStudentsChangeBtn"));

	initModal(document.getElementById('students-add'),
		document.getElementsByClassName("add-btn"),
		document.getElementById("closeStudentsAddBtn"));
})


const newTr = `
<tr >
	<th contenteditable="true">Enter teacher</th>
	<td contenteditable="true">Enter day</td>
	<td contenteditable="true">Enter time</td>
	<td class="td-red">
		<div class="table-btn remove-btn">
			<i class="fas fa-times"></i>
		</div>
	</td>
</tr >`;

const addBtns = document.getElementsByClassName('add-btn');

Array.prototype.forEach.call(addBtns, (btn) => {
	btn.addEventListener('click', function (e) {
		this.closest('.table-block').querySelector('tbody').innerHTML += newTr;
	})
})