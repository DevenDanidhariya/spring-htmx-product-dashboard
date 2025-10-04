--CREATE TABLE products (
--    id BIGINT PRIMARY KEY,
--    title VARCHAR(255),
--    handle VARCHAR(255),
--    body_html TEXT,
--    published_at TIMESTAMP,
--    created_at TIMESTAMP,
--    updated_at TIMESTAMP,
--    vendor VARCHAR(255),
--    product_type VARCHAR(255),
--    tags TEXT[]
--);
--
--CREATE TABLE variants (
--    id BIGINT PRIMARY KEY,
--    product_id BIGINT REFERENCES products(id),
--    title VARCHAR(255),
--    option1 VARCHAR(255),
--    option2 VARCHAR(255),
--    option3 VARCHAR(255),
--    sku VARCHAR(255),
--    price DECIMAL(10, 2),
--    available BOOLEAN,
--    featured_image_url TEXT,
--    created_at TIMESTAMP,
--    updated_at TIMESTAMP
--);



CREATE TABLE products (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255),
    handle VARCHAR(255),
    body_html TEXT,
    published_at VARCHAR(50),
    created_at VARCHAR(50),
    updated_at VARCHAR(50),
    vendor VARCHAR(255),
    product_type VARCHAR(255),
    tags VARCHAR(500)
);

CREATE TABLE variants (
    id BIGINT PRIMARY KEY,
    product_id BIGINT REFERENCES products(id),
    title VARCHAR(255),
    option1 VARCHAR(255),
    option2 VARCHAR(255),
    option3 VARCHAR(255),
    sku VARCHAR(255),
    price DECIMAL(10, 2),
    available BOOLEAN,
    featured_image_url TEXT,
    created_at VARCHAR(50),
    updated_at VARCHAR(50)
);
