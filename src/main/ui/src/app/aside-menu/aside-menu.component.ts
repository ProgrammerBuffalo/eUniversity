import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-aside-menu',
  templateUrl: './aside-menu.component.html',
  styleUrls: ['./aside-menu.component.scss']
})
export class AsideMenuComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

	openNav() {
    document.getElementById('mySidenav')!.style.width = "250px";
	 document.getElementById('main')!.style.marginLeft = "250px";
	 document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
	}

	 closeNav() {
    document.getElementById("mySidenav")!.style.width = "0";
		document.getElementById("main")!.style.marginLeft = "0";
	 document.body.style.backgroundColor = "white";
}

}
