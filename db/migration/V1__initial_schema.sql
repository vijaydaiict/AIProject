-- Migration: V1__initial_schema.sql
-- Created cache_entry table

CREATE TABLE IF NOT EXISTS cache_entries (
    id BIGSERIAL PRIMARY KEY,
    cache_key VARCHAR(255) NOT NULL UNIQUE,
    cache_value TEXT NOT NULL,
    ttl_seconds BIGINT DEFAULT 3600,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_accessed TIMESTAMP,
    hit_count BIGINT DEFAULT 0,
    eviction_priority INT DEFAULT 0
);

CREATE INDEX idx_cache_key ON cache_entries(cache_key);
CREATE INDEX idx_last_accessed ON cache_entries(last_accessed);
