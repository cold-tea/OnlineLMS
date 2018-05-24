function getAllInputs() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	
	var inputs = [username, password];
	return inputs;
}


function signin() {
	var inputs = getAllInputs();
	if (!inputs[0] || !inputs[1]) {
		bootbox.alert({
			message: "Fill up username and password field",
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
		if (inputs[0].length >= 1 && inputs[1].length >= 1)
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