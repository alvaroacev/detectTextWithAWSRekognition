## Synopsis

This is a project to learn how to play with aws-rekognition, the idea is to scan receipt images and extract details such as merchant name, receipt date, amount and currency so it can be later uploaded to a third party system 


## Motivation

Personal project and learn aws-rekognition APIs


## API Reference

http://docs.aws.amazon.com/rekognition/latest/dg/API_Reference.html


## Tests

* Just create an IAM account.
    * Copy the Access key ID
    * Copy the Secret access key
* Install [aws-cli](https://aws.amazon.com/cli/)
    * aws configure (enter the acess key id and secret access key)
* mvn clean install
* * run main class from your IDE or simply java -jar target/ExpensesWithRekognition-1.0-SNAPSHOT.jar
 

## What’s next?
- [x] Detect image details.
- [x] Extract merchant name, date, currency and amount based on text lines returned.
- [ ] Invoke third party API to upload expense details


## Contributors

Everyone is welcome to contribute here

## License

Free for use.