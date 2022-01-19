declare var $: any;
import 'bootstrap-select';

export function refreshSelectPicker(millisecond: number = 0) {
  setTimeout(() => {
    $('.selectpicker2').selectpicker('refresh');
  }, millisecond);
}
