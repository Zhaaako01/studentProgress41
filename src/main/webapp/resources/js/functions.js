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


function disciplineModify() {
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
        alert("Please, select at least one discipline!!!")
        return;
    }

    if (countChecked > 1) {
        alert("Please, select only one discipline!!!")
        return;
    }

    document.getElementById('hiddenToModify').value = idModify;
    document.getElementById('formToModify').submit();
}


function disciplineDelete() {
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
        alert("Please, select at least one discipline!!!")
        return;
    }


    document.getElementById('hiddenIdsToDelete').value = idsToDelete;
    document.getElementById('formToDelete').submit();
}


// function getSelectedDisciplinesIDs() {
//     var selectBox = document.getElementById('disciplines');
//     var countSelected = 0;
//     // 1, 4, 5
//     var idsOfSelected = "";
//
//     for (var i = 0; i < selectBox.options.length; i++) {
//         if (selectBox.options[i].selected) {
//             countSelected++;
//             idsOfSelected = idsOfSelected + selectBox.options[i].value + " ";
//         }
//     }
//     if (countSelected == 0) {
//         alert("Please, select at least one discipline!!!")
//         return;
//     }
//
//
//     document.getElementById('hiddenIdsOfSelectedDisciplines').value = idsOfSelected;
//     // document.getElementById('formToCreate').submit();
// }

function getSelectedDisciplinesIDs() {
    document.querySelector('.smstr-for').addEventListener('submit', function (e) {
        var selectBox = document.getElementById('disciplines');
        var countSelected = 0;
        var idsOfSelected = "";

        for (var i = 0; i < selectBox.options.length; i++) {
            if (selectBox.options[i].selected) {
                countSelected++;
                idsOfSelected += selectBox.options[i].value + " ";
            }
        }

        if (countSelected == 0) {
            alert("Please, select at least one discipline!!!");
            e.preventDefault();
            return;
        }
        document.getElementById('hiddenIdsOfSelectedDisciplines').value = idsOfSelected;
    });
}

document.addEventListener('DOMContentLoaded', function () {
    getSelectedDisciplinesIDs()
});



