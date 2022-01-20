declare var $: any;
import { Component } from '@angular/core';
import 'bootstrap-select';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'eUniversity';

  ngAfterViewChecked() {
    $('.selectpicker2').selectpicker();
  }
}
