function getAllInputs() {
    var username = document.getElementById("username").value;
	var fullName = document.getElementById("fullName").value;
	var password = document.getElementById("password").value;
	var passwordConfirmation = document.getElementById("password_confirmation").value;
	var address = document.getElementById("address").value;
	var email = document.getElementById("email").value;
	var faculty = document.getElementById("faculty").value;
	var gender = document.getElementsByName("gender");
    var inputs = [username, fullName, password, passwordConfirmation, address,
                 email, faculty, gender]
    return inputs;
}


function register() {
	var inputs = getAllInputs();
    var gender = inputs[7];
    
    
	if(!inputs[0] || !inputs[1] || !inputs[2] || !inputs[3] || !inputs[4] || 
			!inputs[5] || !inputs[6]) {
		bootbox.alert({
			message: "Fill up required fields..",
			size: "small",
			backdrop: true
		});
		return false;
	}
	
    if (checkForGender(gender) === false) {
        bootbox.alert({
			message: "Choose the gender ..",
			size: "small",
			backdrop: true
		});
        return false;
    }
        
    
    if(!inputs[2] !== !inputs[3]) {
        bootbox.alert({
			message: "Password doesnot match with password confirmation ....",
			size: "small",
			backdrop: true
		});
		return false;
    }
	return true;
}

function validatePassword(event, sentParam) {
	
	var object = event.srcElement;
	var value = object.value;
	var passwordObj = document.getElementById(sentParam);
	var passwordValue = passwordObj.value;
	
	if(value !== passwordValue || !passwordValue || !value) {
		$(object).css('border-color', 'red');
		$(passwordObj).css('border-color', 'red');
        checkCondition(false);
	} else {
		$(object).css('border-color', '');
		$(passwordObj).css('border-color', '');
        checkCondition(true);
	}
}

function checkForGender(gender) {
    if(gender[0].checked == false && gender[1].checked == false)
		return false;
    return true;
}

function checkCondition(bool) {
	var inputs = getAllInputs();
    var gender = inputs[7];
	var submit = document.getElementById("submit");
	if(bool)
		if (inputs[0].length >= 1 && inputs[1].length >= 1 && inputs[2].length >= 1 &&
           inputs[3].length >= 1 && inputs[4].length >= 1 && inputs[5].length >= 1 &&
           inputs[6].length >= 1 && checkForGender(gender))
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