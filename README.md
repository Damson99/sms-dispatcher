# SMS Dispatcher

## Description
A component that verifies whether a text message is a phishing attack

## Additional requirements
1. If detected ```urls is sms > 4``` then treat sms as threat.
2. Input data are validated.
3. To safe levels from google documentation will belong 2 levels:
   - SAFE
   - LOW

# ADR
Due to the requirement for rapid deployment of the service, a ```hexagonal architecture``` has been used, which will allow a simple change in the implementation of interfaces relating to:
- Database
- Communication channels
- External service to check if a link is a phishing attack.

## Api interface
### Considered options
1. Rest API
2. Messaging API

### Decision outcome
Under heavy load, the network through which http connections pass may not be reliable enough, so a broker in the form of Apache Kafka could be a good choice, but the rest api was chosen because of the short deployment time and simplicity of the solution. In case bottlenecks arise in the service, it will be possible to change the way of communication to asynchronous.

## Database
### Considered options
1. NoSQL
2. SQL

### Decision outcome
Since the service will not perform many updates on the data, they will be mainly writes of entire entities and their reads, and the data will not have relationships a NoSQL database will be used, specifically MongoDB.

