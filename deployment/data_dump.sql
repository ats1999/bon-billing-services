copy bon_user (id, email, first_name, last_name, password_hash)
FROM
    '/docker-entrypoint-initdb.d/data/users.csv'
WITH
    (FORMAT csv, HEADER true);

copy bill (
    id,
    user_id,
    amount,
    created_at,
    due_date,
    paid_on
)
from
    '/docker-entrypoint-initdb.d/data/bills.csv'
with
    (format csv, header true);