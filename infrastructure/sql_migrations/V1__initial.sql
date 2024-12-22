CREATE TABLE IF NOT EXISTS investor (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    personal_id INTEGER NOT NULL,
    role VARCHAR(10) NOT NULL DEFAULT 'REGULAR',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_investor_personal_id ON investor(personal_id);

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