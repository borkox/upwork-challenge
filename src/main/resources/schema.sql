CREATE TABLE IF NOT EXISTS "url_short"  (
  "id" VARCHAR(36) PRIMARY KEY,
  "url" VARCHAR(512),
  "expire" TIMESTAMP
);
CREATE UNIQUE INDEX IF NOT EXISTS urls_IDX1 on "url_short" ("id");


