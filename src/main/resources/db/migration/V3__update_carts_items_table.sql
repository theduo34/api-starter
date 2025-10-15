SELECT BIN_TO_UUID(id) AS cart_uuid
FROM carts
WHERE id = UUID_TO_BIN('d6bcafb2-84b9-11f0-8ebb-d09466fe1259');
