var psPhoneId = 0;
function addPsPhone() {
    psPhoneId++;
    var id = 'psPhone' + psPhoneId;
    var html = '<div class="input-group"> <input id="PersonalPhoneNumbers' + psPhoneId + '" name="PersonalPhoneNumbers"' +
        'type="text" class="form-control" placeholder="Enter personal phone number"><span class="input-group-btn">' +
        '<button id="psPhoneDeleteBtn' + psPhoneId + '" class="btn btn-success" type="button" onclick="javascript:removeElement(' + id + '); return false;">Delete' + '</button></span></div>';
    addElement(/*'files', 'p', 'file-' +*/'addPersonalNumberBtn', id, html);
}

var wPhoneId = 0;
function addWorkPhone() {
    wPhoneId++;
    var id = 'wPhone' + wPhoneId;
    var html = '<div class="input-group"> <input id="WorkPhoneNumbers' + wPhoneId + '" name="WorkPhoneNumbers"' +
        'type="text" class="form-control" placeholder="Enter work phone number"><span class="input-group-btn">' +
        '<button id="workPhoneDeleteBtn' + wPhoneId + '" class="btn btn-success" type="button" onclick="javascript:removeElement(' + id + '); return false;">Delete' + '</button></span></div>';
    addElement(/*'files', 'p', 'file-' +*/'addWorkNumberBtn', id, html);
}

function addElement(/*parentId, elementTag,*/idOfElInsertBefore, elementId, html) {
    // Adds an element to the document
    var btn = document.getElementById(idOfElInsertBefore);
    var newElement = document.createElement('p');
    newElement.id = elementId;
    newElement.innerHTML = html;
    btn.parentNode.insertBefore(newElement, btn);
}

function removeElement(elements) {
    if (Array.isArray(elements)) {
        var i = 0;
        while (elements[i]) {
            elements[i++].remove();
        }
    } else {
        elements.remove();
    }
}

function validatePhones() {
    var regEx = /^\+[0-9][(]([0-9]{3})[)]([0-9]{3})[-]([0-9]{2})[-]([0-9]{2})$/;
    /*var regEx = /^\+[(]$/;*/
    var phones = document.getElementsByName("PersonalPhoneNumbers");
    for (var i = 0; i < phones.length; i++) {
        if (phones[i].value.match(regEx)){
            console.log(phones[i].value);
        } else {
            var p = prepareDangerText("Incorrect phone. Pattern is +X(XXX)XXX-XX-XX");
            $(p).insertAfter(phones[i].parentNode);
        }
    }

    if (false) {
        return true;
    } else {
        //alert("Phones are bad");
        return false;
    }
}

function prepareDangerText(txt) {
    var p = document.createElement("p");
    p.className = ("bg-danger");
    p.innerHTML = txt;
    return p;
}




