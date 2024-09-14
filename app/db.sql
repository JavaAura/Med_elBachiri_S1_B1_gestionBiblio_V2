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
    document_id INT REFERENCES documents(id) ON DELETE CASCADE
) INHERITS (documents);

-- Create the magazines table, inheriting from documents
CREATE TABLE IF NOT EXISTS magazines (
    id SERIAL PRIMARY KEY,
    number INT,
    document_id INT REFERENCES documents(id) ON DELETE CASCADE
) INHERITS (documents);

-- Create the scientific_journals table, inheriting from documents
CREATE TABLE IF NOT EXISTS scientific_journals (
    id SERIAL PRIMARY KEY,
    impact_factor BIGINT,
    document_id INT REFERENCES documents(id) ON DELETE CASCADE
) INHERITS (documents);

-- Create the university_thesis table, inheriting from documents
CREATE TABLE IF NOT EXISTS university_thesis (
    id SERIAL PRIMARY KEY,
    degree_program TEXT,
    document_id INT REFERENCES documents(id) ON DELETE CASCADE
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
    user_id INT REFERENCES users(id) ON DELETE CASCADE
) INHERITS (users);

-- Create the professors table, inheriting from users
CREATE TABLE IF NOT EXISTS professors (
    id SERIAL PRIMARY KEY,
    department TEXT,
    user_id INT REFERENCES users(id) ON DELETE CASCADE
) INHERITS (users);

-- Create the reservations table
CREATE TABLE IF NOT EXISTS reservations (
    id SERIAL PRIMARY KEY,
    user_id INT,
    document_id INT,
    doc_type TEXT,
    user_type TEXT,
    reservation_date DATE
);
-- Create the borrows table
CREATE TABLE IF NOT EXISTS borrows (
    id SERIAL PRIMARY KEY,
    user_id INT,
    document_id INT,
    borrow_date DATE,
    doc_type TEXT,
    user_type TEXT
);