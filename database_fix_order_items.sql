-- Script để cải thiện bảng order_items - thêm menu_item_id
-- Database: RestaurantManagement

USE RestaurantManagement;
GO

-- Kiểm tra xem cột menu_item_id đã tồn tại chưa
IF NOT EXISTS (
    SELECT * FROM INFORMATION_SCHEMA.COLUMNS 
    WHERE TABLE_NAME = 'order_items' AND COLUMN_NAME = 'menu_item_id'
)
BEGIN
    -- Thêm cột menu_item_id vào bảng order_items
    ALTER TABLE order_items
    ADD menu_item_id INT NULL;
    
    PRINT 'Column menu_item_id added to order_items table!';
    
    -- Thêm foreign key constraint (nullable vì có thể có món đã bị xóa khỏi menu)
    ALTER TABLE order_items
    ADD CONSTRAINT FK_order_items_menu_items 
    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id);
    
    PRINT 'Foreign key constraint added!';
END
ELSE
BEGIN
    PRINT 'Column menu_item_id already exists.';
END
GO

-- Kiểm tra cấu trúc bảng sau khi cập nhật
SELECT COLUMN_NAME, DATA_TYPE, IS_NULLABLE, COLUMN_DEFAULT
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'order_items'
ORDER BY ORDINAL_POSITION;
GO

