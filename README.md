	****Module – Insurance Company****
 The Insurance Company module will allow insurance company to manage the claims. The module will provide the following features.
1.	 Insurance company can close the claim submitted by the client by giving the claim amount or close claim if driving license is expired or if vehicle is overloaded at time of accident. Then the insurer cannot claim from the insurance company
2.	Allows insurance company to allocate the surveyor according to estimate level submitted by the insured. [Please note records in surveyor table are added from backend]
3.	Update the claim status to closed i.e., false, if payment for claim is done
4.	Release the payment for claim as specified by surveyor.
5.	Calculate and release the fees of the surveyor

**Stage: Database Implementation**
Design a data base as per the following ER diagram provided:-
1. **Surveyor**:
   - SurveyorId (Primary Key)
   - FirstName
   - LastName
   - EstimateLimt
 
2. **Policy**:
   - PolicyNo (Primary Key)
   - InsuredFirstName
   - InsuredLastName
   - DateOfInsurance
   - EmailId
   - VehicleNo
   - Status
 
3. **ClaimDetails**:
   - ClaimId (Primary Key)
   - PolicyNo (Foreign Key referencing Policy)
   - EstimatedLoss
   - DateOfAccident
   - ClaimStatus
   - SurveyorId (Foreign Key referencing Surveyor)
   - AmtApprovedBySurveyor
   - InsuranceCompanyApproval
   - WithdrawClaim
   - SurveyorFees
 

**3.	Enforce the following constraints on the database apart from primary key, foreign key and unique keys**
a.	InsuredFirstName, InsuredLastName must be minimum 5 characters long
b.	DateOfInsurance must not be earlier than current date
c.	ClaimStatus value can be closed/open
d.	ClaimId should be 10 characters long.
e.	Estimated loss and EstimateLimit cannot be negative
f.	Date of accident cannot be greater than current date
g.	EstimatedStartLimit should be less than EstimateEndLimit
h.	AmtApprovedBySurveyor cannot be negative
i.	Default value of WithdrawClaim and InsuranceCompanyApproval is false  
j.	Policy should always be 7 characters long

Note: Add few policies and surveyor record on the application startup

**Stage: Data Access Layer Design**
1.	Create a library project and add ORM support into it. 
2.	Use the ORM to map the entities to database as per the ER diagram provided. 
3.	Use repository per entity pattern and generate the repositories to perform the following operations	
a.	Return list of Surveyors
b.	Insert a new claim
c.	Update an existing claim
d.	Return number of pending claims in a particular month and year
e.	Return the amount approved by insurance company in a particular month and year

**Stage: Business Logic Layer Development**

1.	Develop a library which reference the Data Access Library project created earlier
2.	This class library will contain various service classes which will encapsulate the business logic for the application.
3.	Use dependency injection to in service classes to inject the required repositories.
4.	Create the service classes following the single responsibility principle which perform the given operations as follows 
a.	Return list of Surveyors
b.	Insert a new claim
c.	Update an existing claim
d.	Return number of pending claims in a particular month and year
e.	Return the amount approved by insurance company in a particular month and year

**Stage:- Following business rules must be implemented as part of the business service class**
a.	PolicyId must be auto-generated. It should be in the format XY00000. First 2 letters of last name followed by a 3-digit number vehicle number, followed by 2-digit year
b.	ClaimID is auto-generated. It should be in the format CL00000000. First 2 letters as CL followed by a 4 digit of policy number, followed by 4-digit year
c.	Cannot raise a claim request if policyID is not valid or does not exist.
d.	Client can make only one claim in a year, if limit exceeds then raise a user-defined exception as “MaximumClaimLimitReachedException”.
a.	Surveyor can only be allocated claim if the client estimate loss is in this estimated limit from Surveyor table. The surveyor records are generated from backend.
b.	Surveyor’s fee must be calculated based on the following
i.	Estimated loss >=5000 and estimated loss < 10,000   fees 1000
ii.	Estimated loss >=10,000 and estimated loss < 20,000   fees 2000
iii.	Estimated loss >=20,000 and estimated loss < 70,000   fees 7000
Note -if we want to make this logic complex, we can add conveyance changes on basis of kilometers from surveyor’s office to workshop location where accidental vehicle has gone for repair

**Stage: Unit Testing**
1.	Create a new Unit test project to test the service classes created in business logic layers
2.	Mock all the repositories using a mocking framework

**Stage: Micro-service implementation**
1.	Create an API project which references the business logic layer created earlier
2.	Implement service documentation using swagger
3.	All exceptions in the micro-service must be handled and logged using a logging library
4.	Create the following end-points and test them using postman and export the requests into a json file.


