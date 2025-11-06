-- Script để tạo bảng reservation (nếu chưa có)
-- Database: RestaurantManagement

USE RestaurantManagement;
GO

-- Kiểm tra và tạo bảng reservation
IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='reservation' AND xtype='U')
BEGIN
    CREATE TABLE reservation (
        id INT IDENTITY(1,1) PRIMARY KEY,
        customer_name NVARCHAR(100) NOT NULL,
        phone NVARCHAR(20) NOT NULL,
        table_number INT NOT NULL,
        reservation_time DATETIME NOT NULL,
        num_people INT NOT NULL,
        note NVARCHAR(500) NULL,
        created_at DATETIME DEFAULT GETDATE()
    );
    PRINT 'Table reservation created successfully!';
END
ELSE
BEGIN
    PRINT 'Table reservation already exists.';
END
GO

-- Kiểm tra cấu trúc bảng
SELECT COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH, IS_NULLABLE
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'reservation';
GO

