CREATE TABLE IF NOT EXISTS investor (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(40) NOT NULL,
    password VARCHAR(200) NOT NULL,
    personal_id INTEGER NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_investor_personal_id ON investor(personal_id);

CREATE TABLE IF NOT EXISTS resource (
    id SERIAL PRIMARY KEY,
    code VARCHAR(40) NOT NULL,
    description VARCHAR(200) NOT NULL,
    investor_id bigint not null ,
    constraint fk_resource_investor foreign key (investor_id) references investor
);

CREATE INDEX IF NOT EXISTS idx_resource_personal_id ON investor(personal_id);

-- Create a function to update the updated_at field
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DO $$
BEGIN
    -- Create a trigger to update the updated_at field before any update
    CREATE TRIGGER set_timestamp
    BEFORE UPDATE ON investor
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();
EXCEPTION
    WHEN duplicate_object THEN
        RAISE NOTICE 'Trigger already exists. Ignoring...';
END$$;