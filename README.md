# **API post request sending application**

## **Features:**

   - In order to run the application from command line, go to the root repository and type: `mvn package; java -jar .\target\APIPostRequestSendingApp-1.0-SNAPSHOT.jar`
   - The application can send a post request to a web API with data enclosed inside the request body.
   - The application can send multiple post requests with a single command, by specifying the file location containing the request data.
   - The application can create a log in a .csv file when sending a post request from the data of the request and the corresponding response.
   - The application can visualize to the console already created logs from a .csv file.


## **Task solutions:**

### *Commands:*
   - Every command works when running the application from command line with proper command syntax, then the application exits.
   - Every command works during runtime within an endless loop until typing the "exit" command for stopping the application.

### *Validating the response of a post request:*
   - After sending a post request to a specific url, a message with color indication is printed out to the console:
       - If the status of the response is 200 the message is green.
       - If the status of the response is not 200 the message is red.

### *Logging feature for sending post requests:*
   - A formatted string of the combination of the request and the response is printed to the console .
   - The main properties of the request and the response are saved to a .csv file.
   - Logging includes the main properties of the response:
         - Status
         - Message
         - URL
         - Body

### *Log file location:*
   - The location of the log file is by default set to "src/main/resources/Logger.csv".
   - However, there is a command to change the location of the log file.
   

## **Class Descriptions:**

###    *ApiPostRequestSenderApp.java:*
*For the application I used the ApiPostRequestSenderApp class to validate and handle input commands.*

###    *PostRequestSender.java:*
*The postRequestSender class includes methods for sending post requests but also validation for the request url and the body.*

###    *LoggerDaoMem.java*
*The LoggerDaoMem class is responsible for creating logs, writing them into,- and reading them from csv files. It is also designed
in a singleton pattern because it has a field which contains every log created in the memory since the application started running.*

###    *RequestObj.java / ResponseObj.java*
*They are both subclasses of ApiData. To make life easier when sending requests I used the RequestObj class and inserted its data to the ResponseObj class for easy access
to every property since they were all in one object, however the fact that the ResponseObj contains properties of the request may be a logical error.
I used the ResponseObj class to create logs and also to revert logs back to ResponseObj, which helped in printing the out to the console.*

###    *ApiDataFormatter.java*
*The ApiDataFormatter class is responsible for conversion between input commands, ApiData objects and logs.*


###    *Display.java*
*The Display class is responsible for printing to the console.*


###    *Util.java*
*The Util class mainly provides helper methods for reading from,- and writing into .csv files. It also contains a couple of generic validation methods.*


## **Tests:**
  
  *While developing the application one of my main focuses was on providing tests to maintain high coverage for sending a post request.
   I included writing junit tests for command input validation, and component test cases for validating the logs after sending post requests.
   Although tests cover a decent portion of the important parts of the application, there is still improvements to be made and tests to be written.*


## **Where the application can improve:**

   - Test the application on multiple operating systems, and provide solutions that work on every os.
   - Include date in logs and also add test cases for it using mocking.
   - Add junit tests for every command.
   - improve on the way file path is handled to be able to use files outside the repository folder.
   - Add a feature where the user specifies a log file path and the file is not present, it gets created.
   - Methods responsible for printing to console in the Display class could return strings, so they can be tested fe: getLogToPrint method in the Display class.
   - Validators could be structured in separate classes.
   - Improve the way messages are printed to the console.