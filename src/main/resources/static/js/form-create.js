var i = 0 /* Set Global Variable i */

$(document).ready(function(){
	  $('[data-toggle="tooltip"]').tooltip()
	});

/* Function for automatic increment of field's "Name" and "Id" attributes. */
function increment() {
	i += 1 
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
		alert("The parent div cannot be removed.")
	} else if (document.getElementById(childDiv)) {
		var child = document.getElementById(childDiv)
		var parent = document.getElementById(parentDiv)
		
		parent.removeChild(child)
	} else {
		alert("Child div has already been removed or does not exist.")
		return false
	}
}

/*
 * ---------------------------------------------
 * 
 * Function to Add Form Elements Answers Dynamically
 * ---------------------------------------------
 * 
 */
function addElementAnswer(parentDiv) {
	var parent = document.getElementById(parentDiv)
	
	var input = document.createElement("input")
	input.setAttribute("type", "text")
	input.setAttribute("placeholder", "Answer")
	if(parent.getAttribute("id").includes("single")) {
		input.setAttribute("id", "single_answer_" + parentDiv.split(/[_ ]+/).pop() + "_" + (parent.childElementCount - 4))
		input.setAttribute("name", "single_answer_" + parentDiv.split(/[_ ]+/).pop() + "_" + (parent.childElementCount - 4))
	} else {
		input.setAttribute("id", "multiple_answer_" + parentDiv.split(/[_ ]+/).pop() + "_" + (parent.childElementCount - 4))
		input.setAttribute("name", "multiple_answer_" + parentDiv.split(/[_ ]+/).pop() + "_" + (parent.childElementCount - 4))
	}
	input.setAttribute("required", "true")
	
	parent.insertBefore(input, parent.lastChild.previousSibling)
}

/*
 * ---------------------------------------------
 * 
 * Function to Delete Form Elements Answers Dynamically
 * ---------------------------------------------
 * 
 */
function deleteElementAnswer(parentDiv) {
	var parent = document.getElementById(parentDiv)
	
	if(parent.childElementCount > 6) {
		parent.removeChild(parent.lastChild.previousSibling.previousSibling)
	}
}

function validateDesiredValues(desiredInputFieldId, parentId) {
	var desiredInputField = document.getElementById(desiredInputFieldId)
	var parent = document.getElementById(parentId)

	if(desiredInputField != null ) {
		if(!/^[0-9,]*$/.test(desiredInputField.value)) {
			desiredInputField.setCustomValidity("Use only allowed characters!")
		} else {
			var desiredValues = Array.from(new Set(desiredInputField.value.split(',')))
					
			for (i = 0; i < desiredValues.length; i++)
				if(desiredValues[i] != ",") {
					if(parent.childElementCount - 5 < parseInt(desiredValues[i])) {
						desiredInputField.setCustomValidity("Select only existing answers!")
						break
					} else {
						desiredInputField.setCustomValidity('')
					}
				}
		}
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
	increment()
	
	var container = document.createElement('span')
	
	var labelQuestion = document.createElement("input")
	labelQuestion.setAttribute("type", "text")
	labelQuestion.setAttribute("id", "label_question_" + i)
	labelQuestion.setAttribute("name", "label_question_" + i)
	labelQuestion.setAttribute("placeholder", "Question")
	labelQuestion.setAttribute("required", "true")
	
	var inputMinVal = document.createElement("input")
	inputMinVal.setAttribute("type", "number")
	inputMinVal.setAttribute("placeholder", "minimum value")
	inputMinVal.setAttribute("id", "inputMinVal_" + i)
	inputMinVal.setAttribute("name", "inputMinVal_" + i)
	inputMinVal.setAttribute("min", "0")
	inputMinVal.setAttribute("max", "1")
	inputMinVal.setAttribute("value", "0")
	
	var inputMaxVal = document.createElement("input")
	inputMaxVal.setAttribute("type", "number")
	inputMaxVal.setAttribute("placeholder", "maximum value")
	inputMaxVal.setAttribute("id", "inputMaxVal_" + i)
	inputMaxVal.setAttribute("name", "inputMaxVal_" + i)
	inputMaxVal.setAttribute("min", "2")
	inputMaxVal.setAttribute("max", "10")
	inputMaxVal.setAttribute("value", "5")
	
	var labelLow = document.createElement("input")
	labelLow.setAttribute("type", "text")
	labelLow.setAttribute("id", "label_low_" + i)
	labelLow.setAttribute("name", "label_low_" + i)
	labelLow.setAttribute("placeholder", "Low Label (Optional)")
	
	var labelHigh = document.createElement("input")
	labelHigh.setAttribute("type", "text")
	labelHigh.setAttribute("id", "label_high_" + i)
	labelHigh.setAttribute("name", "label_high_" + i)
	labelHigh.setAttribute("placeholder", "High Label (Optional)")
	
	var inputDesired = document.createElement("input")
	inputDesired.setAttribute("type", "number")
	inputDesired.setAttribute("placeholder", "Desired value")
	inputDesired.setAttribute("id", "inputDesired_" + i)
	inputDesired.setAttribute("name", "inputDesired_" + i)
	inputDesired.setAttribute("required", "true")
	inputDesired.setAttribute("min", inputMinVal.value)
	inputMinVal.oninput = function() {inputDesired.setAttribute("min", inputMinVal.value)} 
	inputDesired.setAttribute("max", inputMaxVal.value)
	inputMaxVal.oninput = function() {inputDesired.setAttribute("max", inputMaxVal.value)}
	
	var deleteImg = document.createElement("i")
	deleteImg.setAttribute("class", "w3-jumbo fa fa-trash")
	deleteImg.setAttribute("onclick", "removeElement('innerForm','id_" + i + "')")
	
	container.appendChild(labelQuestion)
	container.appendChild(inputMinVal)
	container.appendChild(inputMaxVal)
	container.appendChild(labelLow)
	container.appendChild(labelHigh)
	container.appendChild(inputDesired)
	container.appendChild(deleteImg)
	container.setAttribute("id", "id_" + i)
	
	document.getElementById("innerForm").appendChild(container)
	
	$([document.documentElement, document.body]).animate({
        scrollTop: $("#id_" + i).offset().top
    }, 500);
}
/*
 * -----------------------------------------------------------------------------
 * 
 * Functions that will be called upon, when user click on the E-mail text field.
 * 
 * ------------------------------------------------------------------------------
 */
function singleChoiceFunction() {
	increment()
	
	var container = document.createElement('span')
	
	var labelQuestion = document.createElement("input")
	labelQuestion.setAttribute("type", "text")
	labelQuestion.setAttribute("id", "label_question_" + i)
	labelQuestion.setAttribute("name", "label_question_" + i)
	labelQuestion.setAttribute("placeholder", "Question")
	labelQuestion.setAttribute("required", "true")

	var input = document.createElement("input")
	input.setAttribute("type", "text")
	input.setAttribute("placeholder", "Answer")
	input.setAttribute("id", "single_answer_" + i + "_1")
	input.setAttribute("name", "single_answer_" + i + "_1")
	input.setAttribute("required", "true")
	
	var deleteImg = document.createElement("i")
	deleteImg.setAttribute("class", "w3-jumbo fa fa-trash")
	deleteImg.setAttribute("onclick", "removeElement('innerForm','id_single_" + i + "')")
	
	var addAnswer = document.createElement("i")
	addAnswer.setAttribute("class", "w3-xxlarge fa fa-plus")
	addAnswer.setAttribute("onclick", "addElementAnswer('id_" + i + "')")
	addAnswer.setAttribute("data-toggle", "tooltip")
	addAnswer.setAttribute("title", "Add an answer")
	addAnswer.style.position = "absolute"
	addAnswer.style.float = "right"
	addAnswer.style.margin = "23px 0px 0px 10px"
		
	var deleteAnswer = document.createElement("i")
	deleteAnswer.setAttribute("class", "w3-xxlarge fa fa-minus")
	deleteAnswer.setAttribute("onclick", "deleteElementAnswer('id_" + i + "')")
	deleteAnswer.setAttribute("data-toggle", "tooltip")
	deleteAnswer.setAttribute("title", "Delete an answer")
	deleteAnswer.style.position = "absolute"
	deleteAnswer.style.float = "right"
	deleteAnswer.style.margin = "23px 0px 0px 45px"
	
	var inputDesired = document.createElement("input")
	inputDesired.setAttribute("type", "number")
	inputDesired.setAttribute("placeholder", "Desired value")
	inputDesired.setAttribute("id", "inputDesired_" + i)
	inputDesired.setAttribute("name", "inputDesired_" + i)
	inputDesired.setAttribute("required", "true")
	inputDesired.setAttribute("min", 1)
	inputDesired.setAttribute("max", 1)
	addAnswer.onclick = function() {	addElementAnswer(container.getAttribute("id"))
										inputDesired.setAttribute("max", container.childElementCount - 5)}
	deleteAnswer.onclick = function() {	deleteElementAnswer(container.getAttribute("id"))
		inputDesired.setAttribute("max", container.childElementCount - 5)}
	
	container.appendChild(labelQuestion)
	container.appendChild(addAnswer)
	container.appendChild(deleteAnswer)
	container.appendChild(input)
	container.appendChild(inputDesired)
	container.appendChild(deleteImg)
	container.setAttribute("id", "id_single_" + i)
	
	document.getElementById("innerForm").appendChild(container)
	
	$([document.documentElement, document.body]).animate({
        scrollTop: $("#id_single_" + i).offset().top
    }, 500);
}
/*
 * -----------------------------------------------------------------------------
 * 
 * Functions that will be called upon, when user click on the Contact text
 * field.
 * 
 * ------------------------------------------------------------------------------
 */
function multipleChoiceFunction() {
	increment()
	
	var container = document.createElement('span')
	
	var labelQuestion = document.createElement("input")
	labelQuestion.setAttribute("type", "text")
	labelQuestion.setAttribute("id", "label_question_" + i)
	labelQuestion.setAttribute("name", "label_question_" + i)
	labelQuestion.setAttribute("placeholder", "Question")
	labelQuestion.setAttribute("required", "true")

	var input = document.createElement("input")
	input.setAttribute("type", "text")
	input.setAttribute("placeholder", "Answer")
	input.setAttribute("id", "multiple_answer_" + i + "_1")
	input.setAttribute("name", "multiple_answer_" + i + "_1")
	input.setAttribute("required", "true")
	
	var deleteImg = document.createElement("i")
	deleteImg.setAttribute("class", "w3-jumbo fa fa-trash")
	deleteImg.setAttribute("onclick", "removeElement('innerForm','id_multiple_" + i + "')")
	
	var addAnswer = document.createElement("i")
	addAnswer.setAttribute("class", "w3-xxlarge fa fa-plus")
	addAnswer.setAttribute("onclick", "addElementAnswer('id_" + i + "')")
	addAnswer.setAttribute("data-toggle", "tooltip")
	addAnswer.setAttribute("title", "Add an answer")
	addAnswer.style.position = "absolute"
	addAnswer.style.float = "right"
	addAnswer.style.margin = "23px 0px 0px 10px"
		
	var deleteAnswer = document.createElement("i")
	deleteAnswer.setAttribute("class", "w3-xxlarge fa fa-minus")
	deleteAnswer.setAttribute("onclick", "deleteElementAnswer('id_" + i + "')")
	deleteAnswer.setAttribute("data-toggle", "tooltip")
	deleteAnswer.setAttribute("title", "Delete an answer")
	deleteAnswer.style.position = "absolute"
	deleteAnswer.style.float = "right"
	deleteAnswer.style.margin = "23px 0px 0px 45px"
	
	var inputDesired = document.createElement("input")
	inputDesired.setAttribute("type", "text")
	inputDesired.setAttribute("placeholder", "Desired values, separated by \",\"")
	inputDesired.setAttribute("id", "inputDesired_" + i)
	inputDesired.setAttribute("name", "inputDesired_" + i)
	inputDesired.setAttribute("required", "true")
	inputDesired.oninput = function() { validateDesiredValues(inputDesired.getAttribute("id"), container.getAttribute("id"))}
	addAnswer.onclick = function() { addElementAnswer(container.getAttribute("id"))
										validateDesiredValues(inputDesired.getAttribute("id"), container.getAttribute("id"))}
	deleteAnswer.onclick = function() {	deleteElementAnswer(container.getAttribute("id"))
										validateDesiredValues(inputDesired.getAttribute("id"), container.getAttribute("id"))}
	
	container.appendChild(labelQuestion)
	container.appendChild(addAnswer)
	container.appendChild(deleteAnswer)
	container.appendChild(input)
	container.appendChild(inputDesired)
	container.appendChild(deleteImg)
	container.setAttribute("id", "id_multiple_" + i)
	
	document.getElementById("innerForm").appendChild(container)
	
	$([document.documentElement, document.body]).animate({
        scrollTop: $("#id_multiple_" + i).offset().top
    }, 500);
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
	increment()
	
	var container = document.createElement('span')
	
	var labelQuestion = document.createElement("input")
	labelQuestion.setAttribute("type", "text")
	labelQuestion.setAttribute("id", "label_question_" + i)
	labelQuestion.setAttribute("name", "label_question_" + i)
	labelQuestion.setAttribute("placeholder", "Question")
	labelQuestion.setAttribute("required", "true")

	var input = document.createElement("textarea")
	input.setAttribute("type", "textarea")
	input.style.resize = "vertical"
	input.style.minHeight = "100px"
	input.setAttribute("placeholder", "Desired Key Words - Separate them by ',' ")
	input.setAttribute("id", "inputDesired_" + i)
	input.setAttribute("name", "inputDesired_" + i)
	input.setAttribute("required", "true")
	
	var deleteImg = document.createElement("i")
	deleteImg.setAttribute("class", "w3-jumbo fa fa-trash")
	deleteImg.setAttribute("onclick", "removeElement('innerForm','id_" + i + "')")
	
	container.appendChild(labelQuestion)
	container.appendChild(input)
	container.appendChild(deleteImg)
	container.setAttribute("id", "id_" + i)
	
	document.getElementById("innerForm").appendChild(container)
	
	$([document.documentElement, document.body]).animate({
        scrollTop: $("#id_" + i).offset().top
    }, 500)
}
/*
 * -----------------------------------------------------------------------------
 * 
 * Functions that will be called upon, when user click on the Reset Button.
 * 
 * ------------------------------------------------------------------------------
 */
async function resetElements() {
	var r = confirm("Are you sure you want to reset the form?")
    if (r == true) {
    	$([document.documentElement, document.body]).animate({
            scrollTop: $("#title").offset().top
        }, 500)
    	
    	await new Promise(resolve => setTimeout(resolve, 500))
    	document.getElementById('innerForm').innerHTML = ''
    	i = 0
    }
	
}
