DROP TABLE IF EXISTS parts;

CREATE TABLE parts (
  id          SERIAL,
  part_number TEXT,
  part_name   TEXT,
  vendor      TEXT,
  qty         INTEGER,
  shipped     DATE,
  receive     DATE,
  CONSTRAINT parts_pkey PRIMARY KEY (id)
)