# CheckoutService

The project requires Java 8 and Maven.

To build the app and run all the tests please use this command from command line: <b>mvn clean install</b>

To run the project please use this command from command line: <b>mvn clean spring-boot:run</b>

Alternatively you can create a jar with this command: <b>mvn package</b>.
And you can run the jar.


The app is a Spring Boot app so you do not have to install Tomcat or any other web server. 
The project has no presentation layer since it is a REST API.
You can test the application with Postman (https://chrome.google.com/webstore/detail/postman/fhbjgbiflinjbdggehcddcbncdddomop) 

- please use this url: <b>http://localhost:8080/calculatePrice</b>
- set the request type to POST
- please see the following example requests and paste one of them to the "Body" section of Postman, 
  click to "raw" radio button and than set the data type of the body to "JSON(application/json)":

1.: There is a special pricing rule for item "A" what is applicable when the request contains 3 item "A" 
which is the case with this request. The result should be 130.
{
	"items": [{
		"identifier": "A",
		"unitPrice": 50
	},
	{
		"identifier": "A",
		"unitPrice": 50
	},
	{
		"identifier": "A",
		"unitPrice": 50
	}],
	"pricingRules": [{
		"itemIdentifier": "A",
		"itemCount": 3,
		"specialPrice": 130
	},
	{
		"itemIdentifier": "B",
		"itemCount": 2,
		"specialPrice": 45
	}]
}


2.: There is a special pricing rule for item "A" what is applicable when the request contains 3 item "A" 
There are 2 item "A" in the request so the special rule cannot be applied. The result should be 100.
{
	"items": [{
		"identifier": "A",
		"unitPrice": 50
	},
	{
		"identifier": "A",
		"unitPrice": 50
	}],
	"pricingRules": [{
		"itemIdentifier": "A",
		"itemCount": 3,
		"specialPrice": 130
	},
	{
		"itemIdentifier": "B",
		"itemCount": 2,
		"specialPrice": 45
	}]
}

3.: The pricing rule list is empty. 
There are 3 item "A" in the request but there are no pricing rule to apply. The result should be 150.
{
	"items": [{
		"identifier": "A",
		"unitPrice": 50
	},
	{
		"identifier": "A",
		"unitPrice": 50
	},
	{
		"identifier": "A",
		"unitPrice": 50
	}
	],
	"pricingRules": []
}

4.: The pricing rule list is missing. 
There are 3 item "A" in the request but there are no pricing rule to apply. The result should be 150.
{
	"items": [{
		"identifier": "A",
		"unitPrice": 50
	},
	{
		"identifier": "A",
		"unitPrice": 50
	},
	{
		"identifier": "A",
		"unitPrice": 50
	}]
}


