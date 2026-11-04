# BorderLedger

## The Problem
Cross-border traders often struggle to keep consistent records of their sales. BorderLedger solves this by providing a centralized backend system to log sales and calculate an illustrative Document & Consistency Score, helping traders formalize their businesses.

## How to Run
1. Ensure you have Java 21 and Maven installed.
2. Clone the repository: `git clone https://github.com/Chiwala1230-sudo/BorderLedger.git`
3. Navigate into the project folder and run: `mvn clean compile exec:java -Dexec.mainClass="com.borderledger.App"`
4. Open your browser and go to `http://localhost:7070/index.html`

## Our Classes
- **TrackerService:** Uses a Map and Gson to save/load traders to a JSON file.
- **App:** The main Javalin server with 5 RESTful endpoints.
- **Trader:** Abstract class defining the core properties and scoring logic.
- **InformalTrader, SemiFormalTrader, RegisteredCooperative:** Polymorphic subclasses that calculate scores differently.
- **InsufficientHistoryException:** Custom exception thrown when there are too few transactions.
- **TransactionRecord:** Represents individual sales.
- **TraderTest:** JUnit 5 tests.

## Group Members
| Full Name | Student Number |
|-----------|----------------|
| Ernest Chiwala | 2410170 |
| Richard Phiri | 2410401 |
| Mwala Kalimukwa | 2410047 |
| Ndame Sepete | 2410559 |
| Kalaba Kanyanta | 2410172 |
