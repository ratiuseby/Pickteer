Survey.Survey.cssType = "bootstrap";

var surveyJSON = {
pages:[
 {
  name:"page1",elements:[{type:"text",name:"question1"}]
 }
]
}

function sendDataToServer(survey) {
    //send Ajax request to your web server.
    alert("The results are:" + JSON.stringify(survey.data));
}

var survey = new Survey.Model(surveyJSON);
$("#surveyContainer").Survey({
    model: survey,
    onComplete: sendDataToServer
});