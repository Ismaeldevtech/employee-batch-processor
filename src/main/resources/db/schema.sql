CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(150),
    department VARCHAR(100),
    salary NUMERIC(10,2),
    processed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);