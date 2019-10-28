## Tab Backend Servlet
WORK IN PROGRESS
Spring/Heroku/PostgresSQL Java backend servlet that sits in the middle of all the other Tab services.

Will be deployed via Heroku and store information in a PostGresSQL database.
Takes data from Yodlee API, processes data from Square Connection API for payments and stored payment information,
takes HTTP requests from android, iOs, and Web apps to process payment and get credit information, and uses
the Tab Credit Analysis Django API for HTTP requests for credit scores.