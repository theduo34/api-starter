INSERT INTO categories (name)
VALUES ('Electronics'),
       ('Books'),
       ('Fashion'),
       ('Home & Kitchen'),
       ('Sports & Fitness'),
       ('Beauty & Health'),
       ('Toys & Games'),
       ('Groceries'),
       ('Automotive'),
       ('Pet Supplies');

INSERT INTO products (name, price, description, category_id)
VALUES ('Wireless Bluetooth Headphones', 59.99, 'High-quality sound and noise cancellation for all-day listening.', 1),
       ('The Pragmatic Programmer', 34.99, 'A classic guide to software craftsmanship and best coding practices.', 2),
       ('Men’s Casual Cotton Shirt', 24.50, 'Lightweight, breathable shirt perfect for everyday wear.', 3),
       ('Nonstick Cookware Set', 79.99, 'Durable pots and pans set with heat-resistant handles.', 4),
       ('Yoga Mat Pro', 29.99, 'Extra-thick non-slip mat for yoga, pilates, or stretching.', 5),
       ('Vitamin C Serum', 19.95, 'Brightening face serum for clear, healthy-looking skin.', 6),
       ('Remote Control Drone', 89.90, 'Compact drone with HD camera and 20-minute flight time.', 7),
       ('Organic Coffee Beans', 15.75, '100% Arabica beans with a rich, smooth flavor.', 8),
       ('Car Vacuum Cleaner', 39.99, 'Portable handheld vacuum designed for cars and tight spaces.', 9),
       ('Dog Chew Toy Pack', 22.49, 'Durable rubber chew toys for medium to large dogs.', 10);
