# Petstore API Automation Testing



**Project Overview**

This project focuses on API Automation Testing using Katalon Studio to validate Petstore API functionality, response status codes, response bodies, and positive and negative scenarios.



**Tools \& Technologies**

\- Katalon Studio

\- REST API

\- Groovy



**API Endpoints**

The following endpoints are covered in this project:



| Method | Endpoint 	| Description |



| POS    | /pet 	| Create a new pet |

| PUT    | /pet 	| Update an existing pet |

| GET    | /pet/{petId} | Retrieve pet details by ID |

| DELETE | /pet/{petId} | Delete a pet by ID |



**Test Scenarios**



Each endpoint includes positive and negative test scenarios.



**POST /pet — Create Pet**

\- Verify successful pet creation with valid request data.

\- Verify API behavior with invalid or incomplete request data.



**PUT /pet — Update Pet**

\- Verify successful pet update with valid request data.

\- Verify API behavior with invalid or incomplete request data.



**GET /pet/{petId} — Get Pet by ID**

\- Verify pet details are retrieved using a valid pet ID.

\- Verify the response when the pet ID is invalid or does not exist.



**DELETE /pet/{petId} — Delete Pet**

\- Verify successful deletion of an existing pet.

\- Verify API behavior when deleting a pet that does not exist or has an invalid ID.



**End-to-End (E2E) Flow**



The E2E flow validates the pet lifecycle:



1\. Create a pet using `POST /pet`.

2\. Retrieve the pet using `GET /pet/{petId}`.

3\. Update the pet using `PUT /pet`.

4\. Retrieve the pet again to verify the updated data.

5\. Delete the pet using `DELETE /pet/{petId}`.

6\. Verify the expected response after deletion.





