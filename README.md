# Influence Analysis Application

This Spring Boot application analyzes TED Talks data to determine:

- The most influential speaker overall.
- The most influential talk per year.

## Getting Started

### Prerequisites

- Java 17 or higher
- Docker and Docker Compose
- Gradle (or use the provided Gradle Wrapper)

### Running the Application

1. **Start the MySQL Container**

   Ensure Docker is running, then execute:

   ```bash
   docker-compose up -d
    ```
   This command starts the MySQL container in detached mode.

2. Run the Spring Boot Application
   Using Gradle Wrapper:

    ```bash
   ./gradlew bootRun
    ```
   This command compiles and runs the application using Gradle's bootRun task.

## Implementation Details
1. **Data Ingestion**
   - To efficiently handle dataset iterations, the application loads data into a MySQL database instead of reading the CSV file on each request.
   - Upon startup, the application initializes the database by importing data from the CSV file.
   - The CSV file contains a few corrupted rows (approximately 3). These rows are detected, skipped, and logged to inform developers of the issue.
     <br><br>
2. **Api Endpoints**
   - GET /tedtalks/influential-speakers
   - GET /tedtalks/most-influential-per-year
   
   These endpoints retrieve the most influential speaker overall and the most influential talk per year, respectively.
   <br><br>

3. **Performance Optimization**
   - Initially, the query for /tedtalks/most-influential-per-year had a response time of approximately 14 seconds.
   - To enhance performance, the application utilizes SQL window functions, significantly reducing query time.
   - Resources consulted for optimization:
     - [Mode SQL Tutorial on Window Functions](https://mode.com/sql-tutorial/sql-window-functions)
     - [Comprehensive Guide to SQL Window Functions](https://medium.com/@suffyan.asad1/window-functions-in-sql-a-comprehensive-guide-for-beginners-c445b6c0712d)
     - [Mastering SQL Window Functions](https://medium.com/@manutej/mastering-sql-window-functions-guide-e6dc17eb1995)
     - [StrataScratch's Ultimate Guide to SQL Window Functions](https://www.stratascratch.com/blog/the-ultimate-guide-to-sql-window-functions/)
       <br><br>
4. **Design Considerations**
   - While an alternative approach could involve loading data into memory and performing in-memory computations, this would complicate future enhancements.