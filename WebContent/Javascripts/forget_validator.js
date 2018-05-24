function checkCondition(bool) {
	var input = document.getElementById("user");
	var submit = document.getElementById("sub");
	if(bool)
		if (input.length < 0)
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

function forgetResult() {
    var inputs = getAllInputs();
	if(!input) {
		bootbox.alert({
			message: "Fill up required fields..",
			size: "small",
			backdrop: true
		});
		return false;
	}
    return true;
}
