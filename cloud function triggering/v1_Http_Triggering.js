const functions = require('firebase-functions');
var admin=require('firebase-admin');
admin.initializeApp(functions.config().firebase);

var database=admin.database();
// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
exports.hibaby = functions.https.onRequest((request, response) => {
	        let params=request.body.result.parameters;
	        console.log(params);
           	database.ref().update(params);
           	global.state="";
           	var ref_state = database.ref("state");
       			ref_state.on("value", (data) => {
        		   state=data.val().toString(); 
        		   console.log(state);    		  
        		});
        		console.log(state);
        		if(state==="1")
        		{	
          			var ref_temperature = database.ref("temperature");
       				ref_temperature.on("value", (data) => {
        		   	var temperature=data.val().toString().split(" ");
        		   	response.send({
        		   	speech:"your room temperature is "+temperature[0]+" degree celcius and "+temperature[1]+" degree Farenhit"
        		   })
        		  
        		});
        		}
        		else if(state==="0")
        		{
        		var ref_humidity= database.ref("humidity");
       					ref_humidity.on("value", (data) => {
        		   		var humidity=data.val().toString();
        		   		response.send({
        		   			speech:"your room humidity is "+humidity
        		   		})


        		   	});
        		  }
        		  else
        		  {
        		  	response.send({
        		   			speech:"Ok device is controlled sucessfully"
        		   		})
        		  }

         
});
