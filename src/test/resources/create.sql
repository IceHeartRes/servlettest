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
);

INSERT INTO parts (part_number, part_name, vendor, qty, shipped, receive) VALUES
  ('number1', 'name1', 'vendor1', '1', '2018-1-1', '2018-1-2'),
  ('number2', 'name2', 'vendor2', '2', '2018-1-2', '2018-1-3');