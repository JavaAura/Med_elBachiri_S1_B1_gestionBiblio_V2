-- Create the documents table
CREATE TABLE IF NOT EXISTS documents (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(60),
    pub_date DATE,
    num_pages INT,
    borrowed BOOLEAN DEFAULT false
);

-- Create the books table, inheriting from documents
CREATE TABLE IF NOT EXISTS books (
    id SERIAL PRIMARY KEY,
    isbn TEXT,
    document_id INT REFERENCES documents(id)
) INHERITS (documents);

-- Create the magazines table, inheriting from documents
CREATE TABLE IF NOT EXISTS magazines (
    id SERIAL PRIMARY KEY,
    number INT,
    document_id INT REFERENCES documents(id)
) INHERITS (documents);

-- Create the scientific journals table, inheriting from documents
CREATE TABLE IF NOT EXISTS scientific_journals (
    id SERIAL PRIMARY KEY,
    impact_factor BIGINT,
    document_id INT REFERENCES documents(id)
) INHERITS (documents);

-- Create the university thesis table, inheriting from documents
CREATE TABLE IF NOT EXISTS university_thesis (
    id SERIAL PRIMARY KEY,
    degree_program TEXT,
    document_id INT REFERENCES documents(id)
) INHERITS (documents);

-- Create the users table
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60),
    email VARCHAR(255),
    age INT
);

-- Create the students table, inheriting from users
CREATE TABLE IF NOT EXISTS students (
    id SERIAL PRIMARY KEY,
    integration_date DATE,
    user_id INT REFERENCES users(id)
) INHERITS (users);

-- Create the professors table, inheriting from users
CREATE TABLE IF NOT EXISTS professors (
    id SERIAL PRIMARY KEY,
    department TEXT,
    user_id INT REFERENCES users(id)
) INHERITS (users);

-- Create the reservation table
CREATE TABLE IF NOT EXISTS reservations (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    document_id INT REFERENCES documents(id),
    reservation_date DATE,
    due_date DATE,
    status VARCHAR(20)
);
