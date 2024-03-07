function studentModify() {
    var checkedCheckboxes = document.getElementsByClassName('checkbox');
    var countChecked = 0;
    var idModify;

    for (var i = 0; i < checkedCheckboxes.length; i++) {
        if (checkedCheckboxes[i].checked) {
            countChecked++;
            idModify = checkedCheckboxes[i].value;
        }
    }
    if (countChecked == 0) {
        alert("Please, select at least one student!!!")
        return;
    }

    if (countChecked > 1) {
        alert("Please, select only one student!!!")
        return;
    }

    document.getElementById('hiddenToModify').value = idModify;
    document.getElementById('formToModify').submit();
}