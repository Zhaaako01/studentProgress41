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


function studentDelete() {
    var checkedCheckboxes = document.getElementsByClassName('checkbox');
    var countChecked = 0;
    // 1, 4, 5
    var idsToDelete = "";

    for (var i = 0; i < checkedCheckboxes.length; i++) {
        if (checkedCheckboxes[i].checked) {
            countChecked++;
            idsToDelete = idsToDelete + checkedCheckboxes[i].value + " ";
        }
    }
    if (countChecked == 0) {
        alert("Please, select at least one student!!!")
        return;
    }


    document.getElementById('hiddenIdsToDelete').value = idsToDelete;
    document.getElementById('formToDelete').submit();
}

function studentProgress() {
    var checkedCheckboxes = document.getElementsByClassName('checkbox');
    var countChecked = 0;
    var idStud;

    for (var i = 0; i < checkedCheckboxes.length; i++) {
        if (checkedCheckboxes[i].checked) {
            countChecked++;
            idStud = checkedCheckboxes[i].value;
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

    document.getElementById('hiddenIdToProgress').value = idStud;
    document.getElementById('formToProgress').submit();
}