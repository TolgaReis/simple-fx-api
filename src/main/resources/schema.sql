DROP TABLE IF EXISTS TBL_CURRENCY;
DROP TABLE IF EXISTS TBL_CONVERSION;

CREATE TABLE TBL_CURRENCY(
    currency VARCHAR(3) UNIQUE
);

CREATE TABLE TBL_CONVERSION (
  id INT AUTO_INCREMENT PRIMARY KEY,
  base_currency VARCHAR(3) NOT NULL,
  target_currency VARCHAR(3) NOT NULL,
  base_amount REAL NOT NULL,
  target_amount REAL NOT NULL,
  transaction_id UUID DEFAULT RANDOM_UUID(),
  date_time DATE DEFAULT CURRENT_DATE,
  FOREIGN KEY (base_currency) REFERENCES TBL_CURRENCY (currency),
  FOREIGN KEY (target_currency) REFERENCES TBL_CURRENCY (currency)
);