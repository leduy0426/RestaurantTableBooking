-- Script để tạo các bảng cho Order và Menu
-- Database: RestaurantManagement

USE RestaurantManagement;
GO

-- Tạo bảng menu_items
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='menu_items' AND xtype='U')
BEGIN
    CREATE TABLE menu_items (
        id INT IDENTITY(1,1) PRIMARY KEY,
        name NVARCHAR(100) NOT NULL,
        description NVARCHAR(500) NULL,
        price DECIMAL(10,2) NOT NULL,
        category NVARCHAR(50) NULL,
        image_url NVARCHAR(500) NULL,
        created_at DATETIME DEFAULT GETDATE()
    );
    PRINT 'Table menu_items created successfully!';
    
    -- Insert sample menu items
    INSERT INTO menu_items (name, description, price, category, image_url) VALUES
    (N'Phở Bò', N'Phở bò truyền thống với thịt bò tái và nước dùng đậm đà', 85000, N'Món chính', NULL),
    (N'Bún Chả', N'Bún chả Hà Nội với thịt nướng thơm lừng', 75000, N'Món chính', NULL),
    (N'Gà Nướng', N'Gà nướng mật ong thơm ngon', 120000, N'Món chính', NULL),
    (N'Coca Cola', N'Nước ngọt có ga', 20000, N'Đồ uống', NULL),
    (N'Pepsi', N'Nước ngọt có ga', 20000, N'Đồ uống', NULL),
    (N'Bia Tiger', N'Bia Tiger lạnh', 35000, N'Đồ uống', NULL),
    (N'Chè Thái', N'Chè Thái mát lạnh', 30000, N'Tráng miệng', NULL),
    (N'Kem Dừa', N'Kem dừa tươi', 25000, N'Tráng miệng', NULL);
    
    PRINT 'Sample menu items inserted!';
END
ELSE
BEGIN
    PRINT 'Table menu_items already exists.';
END
GO

-- Tạo bảng orders
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='orders' AND xtype='U')
BEGIN
    CREATE TABLE orders (
        id INT IDENTITY(1,1) PRIMARY KEY,
        reservation_id INT NOT NULL,
        staff_id INT NOT NULL,
        order_time DATETIME DEFAULT GETDATE(),
        total_amount DECIMAL(10,2) NOT NULL,
        status NVARCHAR(20) DEFAULT 'pending',
        created_at DATETIME DEFAULT GETDATE(),
        FOREIGN KEY (reservation_id) REFERENCES reservation(id),
        FOREIGN KEY (staff_id) REFERENCES staff(id)
    );
    PRINT 'Table orders created successfully!';
END
ELSE
BEGIN
    PRINT 'Table orders already exists.';
END
GO

-- Tạo bảng order_items
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='order_items' AND xtype='U')
BEGIN
    CREATE TABLE order_items (
        id INT IDENTITY(1,1) PRIMARY KEY,
        order_id INT NOT NULL,
        menu_item_id INT NULL,
        item_name NVARCHAR(100) NOT NULL,
        quantity INT NOT NULL,
        price DECIMAL(10,2) NOT NULL,
        created_at DATETIME DEFAULT GETDATE(),
        FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
        FOREIGN KEY (menu_item_id) REFERENCES menu_items(id)
    );
    PRINT 'Table order_items created successfully!';
END
ELSE
BEGIN
    PRINT 'Table order_items already exists.';
END
GO

-- Kiểm tra cấu trúc các bảng
SELECT 'menu_items' AS TableName, COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, IS_NULLABLE
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'menu_items'
UNION ALL
SELECT 'orders' AS TableName, COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, IS_NULLABLE
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'orders'
UNION ALL
SELECT 'order_items' AS TableName, COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, IS_NULLABLE
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'order_items';
GO

