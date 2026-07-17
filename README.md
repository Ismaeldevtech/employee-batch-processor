# Employee Batch Processor 🚀

Spring Batch job that reads 100,000 employees from a CSV file, filters and transforms the data, and persists the results in PostgreSQL.

Built as part of a personal portfolio to demonstrate production-level Spring Batch skills.

---

## What it does

- Reads a CSV file with 100,000 employee records
- Filters out employees with salary below 30,000€
- Transforms names to uppercase
- Persists filtered employees to PostgreSQL in chunks of 1,000 records
- Skips malformed records and writes them to a rejected file
- Logs job start, end and status via a JobExecutionListener

---

## Tech Stack

- Java 17
- Spring Boot 3.5.x
- Spring Batch
- PostgreSQL 16
- Docker & Docker Compose
- Maven
- Lombok

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.8+
- Docker Desktop
- Python 3+

### Run the project

1. Clone the repository

```bash
git clone https://github.com/Ismaeldevtech/employee-batch-processor.git
cd employee-batch-processor
```

2. Start PostgreSQL with Docker

```bash
docker compose up -d
```

3. Generate the CSV file with 100,000 employees

```bash
python3 scripts/generate_employees.py
```

4. Run the application

```bash
./mvnw spring-boot:run
```

5. Launch the job

Open your browser and go to: [http://localhost:8080/run-job](http://localhost:8080/run-job)


---

## Project Structure

```
src/main/java/com/ismaeldevtech/employee_batch_processor/
├── config/       → Job and Step configuration, JobLauncher endpoint
├── listener/     → JobExecutionListener and SkipListener
├── model/        → Employee entity
├── processor/    → Salary filter and name transformation
├── reader/       → FlatFileItemReader for CSV
└── writer/       → JdbcBatchItemWriter for PostgreSQL
```

---

## Key Concepts Demonstrated

- **FlatFileItemReader** — reads and maps CSV lines to Java objects
- **ItemProcessor** — filters and transforms data before writing
- **JdbcBatchItemWriter** — writes in batches for optimal performance
- **Chunk-oriented processing** — read/process/write in configurable chunks
- **SkipPolicy** — fault tolerance for malformed records
- **JobExecutionListener** — monitors job lifecycle
- **Docker Compose** — reproducible local environment

---

## Author

**Ismael** — Backend Developer  
[GitHub](https://github.com/Ismaeldevtech)