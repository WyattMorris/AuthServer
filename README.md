# AuthServer

## This project is meant to be used with my StockData repository. 
## AuthServer is the backend application which handles authentication as well as fetching stock information for the frontend.

## Endpoints

### /registration/signup 
### Takes the sign up request, process the information and returns a JWT as a response.

### /registration/login
### Takes the login request and obtains the UserDetails, which is implemented by the AppUser class. 
### Checks the password and returns JWT.

### {stockTicker}/{amount}
### Allows single stock requests to be fetched.
### Along with single stocks, posting a list of tickers will return a list of stock information.

### /portfolio/save
### Sends the currently saved portfolio information to the JpaRepository and saved to the StockEntity table with the User's id'

### /portfolio/stocks
### Retrieves the current stock information of all StockEntities belonging to that user.
