var i = 0; /* Set Global Variable i */
function increment() {
	i += 1; /* Function for automatic increment of field's "Name" attribute. */
}
/*
 * ---------------------------------------------
 * 
 * Function to Remove Form Elements Dynamically
 * ---------------------------------------------
 * 
 */
function removeElement(parentDiv, childDiv) {
	if (childDiv == parentDiv) {
		alert("The parent div cannot be removed.");
	} else if (document.getElementById(childDiv)) {
		var child = document.getElementById(childDiv);
		var parent = document.getElementById(parentDiv);
		parent.removeChild(child);
	} else {
		alert("Child div has already been removed or does not exist.");
		return false;
	}
}

/*
 * ----------------------------------------------------------------------------
 * 
 * Functions that will be called upon, when user click on the Name text field.
 * 
 * ----------------------------------------------------------------------------
 */
function linearScaleInputFunction() {
	increment();
	
	var container = document.createElement('span');
	
	var labelQuestion = document.createElement("input");
	labelQuestion.setAttribute("type", "text");
	labelQuestion.setAttribute("id", "label_text_box_" + i);
	labelQuestion.setAttribute("name", "label_text_box_" + i);
	labelQuestion.setAttribute("placeholder", "Question Placeholder");
	labelQuestion.setAttribute("required", "true");
	
	var inputMinVal = document.createElement("input");
	inputMinVal.setAttribute("type", "number");
	inputMinVal.setAttribute("placeholder", "minimum value");
	inputMinVal.setAttribute("id", "inputMinVal_" + i);
	inputMinVal.setAttribute("name", "inputMinVal_" + i);
	inputMinVal.setAttribute("min", "0");
	inputMinVal.setAttribute("max", "1");
	inputMinVal.setAttribute("value", "0");
	
	var inputMaxVal = document.createElement("input");
	inputMaxVal.setAttribute("type", "number");
	inputMaxVal.setAttribute("placeholder", "maximum value");
	inputMaxVal.setAttribute("id", "inputMaxVal_" + i);
	inputMaxVal.setAttribute("name", "inputMaxVal_" + i);
	inputMaxVal.setAttribute("min", "2");
	inputMaxVal.setAttribute("max", "10");
	inputMaxVal.setAttribute("value", "5");
	
	var labelLow = document.createElement("input");
	labelLow.setAttribute("type", "text");
	labelLow.setAttribute("id", "label_low_linearscale_text_box_" + i);
	labelLow.setAttribute("name", "label_low_linearscale_text_box_" + i);
	labelLow.setAttribute("placeholder", "Low Label (Optional)");
	
	var labelHigh = document.createElement("input");
	labelHigh.setAttribute("type", "text");
	labelHigh.setAttribute("id", "label_high_linearscale_text_box_" + i);
	labelHigh.setAttribute("name", "label_high_linearscale_text_box_" + i);
	labelHigh.setAttribute("placeholder", "High Label (Optional)");
	
	var inputDesired = document.createElement("input");
	inputDesired.setAttribute("type", "number");
	inputDesired.setAttribute("placeholder", "Desired value");
	inputDesired.setAttribute("id", "inputDesired_" + i);
	inputDesired.setAttribute("name", "inputDesired_" + i);
	inputDesired.setAttribute("min", "0");
	inputDesired.setAttribute("max", "10");
	
	var deleteImg = document.createElement("i");
	deleteImg.setAttribute("class", "w3-jumbo fa fa-trash");
	deleteImg.setAttribute("onclick", "removeElement('innerForm','id_" + i + "')");
	
	container.appendChild(labelQuestion);
	container.appendChild(inputMinVal);
	container.appendChild(inputMaxVal);
	container.appendChild(labelLow);
	container.appendChild(labelHigh);
	container.appendChild(inputDesired);
	container.appendChild(deleteImg);
	container.setAttribute("id", "id_" + i);
	
	document.getElementById("innerForm").appendChild(container);
}
/*
 * -----------------------------------------------------------------------------
 * 
 * Functions that will be called upon, when user click on the E-mail text field.
 * 
 * ------------------------------------------------------------------------------
 */
function emailFunction() {
	increment();
	
	var container = document.createElement('span');
	
	var label = document.createElement("input");
	label.setAttribute("type", "text");
	label.setAttribute("id", "label_text_box_" + i);
	label.setAttribute("name", "label_text_box_" + i);
	label.setAttribute("placeholder", "Question Placeholder");

	var input = document.createElement("input");
	input.setAttribute("type", "text");
	input.setAttribute("placeholder", "Email");
	input.setAttribute("id", "formelement_" + i);
	input.setAttribute("name", "formelement_" + i);
	
	var deleteImg = document.createElement("i");
	deleteImg.setAttribute("class", "w3-jumbo fa fa-trash");
	deleteImg.setAttribute("onclick", "removeElement('innerForm','id_" + i + "')");
	
	container.appendChild(label);
	container.appendChild(input);
	container.appendChild(deleteImg);
	container.setAttribute("id", "id_" + i);
	
	document.getElementById("innerForm").appendChild(container);
}
/*
 * -----------------------------------------------------------------------------
 * 
 * Functions that will be called upon, when user click on the Contact text
 * field.
 * 
 * ------------------------------------------------------------------------------
 */
function contactFunction() {
	increment();
	
	var container = document.createElement('span');
	
	var label = document.createElement("input");
	label.setAttribute("type", "text");
	label.setAttribute("id", "label_text_box_" + i);
	label.setAttribute("name", "label_text_box_" + i);
	label.setAttribute("placeholder", "Question Placeholder");

	var input = document.createElement("input");
	input.setAttribute("type", "text");
	input.setAttribute("placeholder", "Contact");
	input.setAttribute("id", "formelement_" + i);
	input.setAttribute("name", "formelement_" + i);
	
	var deleteImg = document.createElement("i");
	deleteImg.setAttribute("class", "w3-jumbo fa fa-trash");
	deleteImg.setAttribute("onclick", "removeElement('innerForm','id_" + i + "')");
	
	container.appendChild(label);
	container.appendChild(input);
	container.appendChild(deleteImg);
	container.setAttribute("id", "id_" + i);
	
	document.getElementById("innerForm").appendChild(container);
}
/*
 * -----------------------------------------------------------------------------
 * 
 * Functions that will be called upon, when user click on the Message textarea
 * field.
 * 
 * ------------------------------------------------------------------------------
 */
function textareaFunction() {
	increment();
	
	var container = document.createElement('span');
	
	var labelQuestion = document.createElement("input");
	labelQuestion.setAttribute("type", "text");
	labelQuestion.setAttribute("id", "label_text_box_" + i);
	labelQuestion.setAttribute("name", "label_text_box_" + i);
	labelQuestion.setAttribute("placeholder", "Question Placeholder");
	labelQuestion.setAttribute("required", "true");

	var input = document.createElement("textarea");
	input.setAttribute("type", "textarea");
	input.style.resize = "vertical";
	input.setAttribute("placeholder", "Desired Key Words - Separate them by ',' ");
	input.setAttribute("id", "formelement_" + i);
	input.setAttribute("name", "formelement_" + i);
	
	var deleteImg = document.createElement("i");
	deleteImg.setAttribute("class", "w3-jumbo fa fa-trash");
	deleteImg.setAttribute("onclick", "removeElement('innerForm','id_" + i + "')");
	
	container.appendChild(labelQuestion);
	container.appendChild(input);
	container.appendChild(deleteImg);
	container.setAttribute("id", "id_" + i);
	
	document.getElementById("innerForm").appendChild(container);
}
/*
 * -----------------------------------------------------------------------------
 * 
 * Functions that will be called upon, when user click on the Reset Button.
 * 
 * ------------------------------------------------------------------------------
 */
function resetElements() {
	document.getElementById('innerForm').innerHTML = '';
}
