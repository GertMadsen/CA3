This seed contains
- a backend project with REST api
- a Web Client created with React
- a mobile app created with React-native

How to use this seed: 

When cloning this seed remember to:
- create a database 
- create a persistence-unit which refers to this database 
- If you want to test the database, and setup some test users, run the SetupTestUsers.java in the backend project.
- before running the web client and the app client for the first time use npm install or yarn install in the root of the web client project. 
- in the package.json file in the web client- and in the app client project, change the serverURL to your backend server location.

Optional:
- Activate/deactivate CORS by comment in/out the @Provider annotation in the CORS request and respons files in the backend project.

Following endpoints is available in the REST api:

api/info/user
(OPTIONS: GET)
(Access allowed with user role)

api/info/admin
(OPTIONS: GET)
(Access allowed with admin role)

api/info/people/peopleId
(PeopleId must be an integer)
(OPTIONS: GET)
(Access allowed for everybody)

api/login
(OPTIONS: POST)
(Requires json object:{ "username": XXX, "password": XXX })

Backend roles available:
- user
- admin
