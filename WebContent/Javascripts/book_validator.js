function getAllInputs() {
    var id = document.getElementById("id").value;
    var name = document.getElementById("name").value;
    var author = document.getElementById("author").value;
    var publisher = document.getElementById("publisher").value;
    var edition = document.getElementById("edition").value;
    var price = document.getElementById("price").value;
    
    var bookInfo = [id, name, author, publisher, edition, price];
    return bookInfo;
}

function createBook() {
    var inputs = getAllInputs();
	if(!inputs[0] || !inputs[1] || !inputs[2] || !inputs[3] || !inputs[4] || 
			!inputs[5]) {
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
		if (inputs[0].length >= 1 && inputs[1].length >= 1 && inputs[2].length >= 1 &&
           inputs[3].length >= 1 && inputs[4].length >= 1 && inputs[5].length >= 1)
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