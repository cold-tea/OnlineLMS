function getAllInputs() {
    var returnDate = document.getElementById("return_date").value;

    var date = [returnDate];
    return date;
}

function createSerial() {
    var inputs = getAllInputs();
	if(!inputs[0]) {
		bootbox.alert({
			message: "Fill up required fields..",
			size: "small",
			backdrop: true
		});
		return false;
	}
    return true;
}

function checkCondition(bool) {
	var inputs = getAllInputs();
	var submit = document.getElementById("submit");
	if(bool)
		if (inputs[0].length >= 1)
			submit.disabled = false;
		else
			submit.disabled = true;
	else
		submit.disabled = true;
}

function validate(event) {
	var object = event.srcElement;
	var value = object.value;
	if (value.length < 1) {
		$(object).css('border-color', 'red');
		checkCondition(false);
	} else {
		$(object).css('border-color', '');
		checkCondition(true);
	}
}