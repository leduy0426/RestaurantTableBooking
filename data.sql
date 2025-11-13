
CREATE TABLE staff (
    id INT IDENTITY(1,1) PRIMARY KEY,
    full_name NVARCHAR(100) NOT NULL,
    birthdate DATE, -- Thêm trường ngày sinh
    position NVARCHAR(50), 
    phone NVARCHAR(15),
    email NVARCHAR(100),
    address NVARCHAR(255), -- Thêm trường địa chỉ
    username NVARCHAR(50) UNIQUE NOT NULL, -- Thêm ràng buộc NOT NULL
    password NVARCHAR(255) NOT NULL -- Thêm ràng buộc NOT NULL
);

CREATE TABLE admin (
    id INT IDENTITY(1,1) PRIMARY KEY,
    full_name NVARCHAR(100) NOT NULL,
    email NVARCHAR(100),
    username NVARCHAR(50) UNIQUE NOT NULL, -- Thêm ràng buộc NOT NULL
    password NVARCHAR(255) NOT NULL -- Thêm ràng buộc NOT NULL
);

CREATE TABLE reservation (
    id INT IDENTITY(1,1) PRIMARY KEY,
    customer_name NVARCHAR(100) NOT NULL,
    phone NVARCHAR(15) NOT NULL,
    table_number INT NOT NULL,
    reservation_time DATETIME NOT NULL,
    num_people INT,
    note NVARCHAR(255),
    staff_id INT, -- nhân viên phụ trách bàn này
    admin_id INT, -- admin xác nhận
    
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE SET NULL, -- Cập nhật ràng buộc
    FOREIGN KEY (admin_id) REFERENCES admin(id) ON DELETE SET NULL  -- Cập nhật ràng buộc
);

CREATE TABLE orders (
    id INT IDENTITY(1,1) PRIMARY KEY,
    reservation_id INT NOT NULL, -- liên kết đến bàn nào
    staff_id INT, -- Thêm trường nhân viên tạo đơn hàng
    order_time DATETIME DEFAULT GETDATE(),
    total_amount DECIMAL(10,2) DEFAULT 0.00, -- Thêm giá trị mặc định
    status NVARCHAR(50) DEFAULT 'pending', -- pending / served / paid
    
    FOREIGN KEY (reservation_id) REFERENCES reservation(id) ON DELETE CASCADE, -- Nếu đặt chỗ bị hủy, đơn hàng bị xóa
    FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE SET NULL, -- Nếu nhân viên bị xóa, staff_id đặt là NULL
    
    -- Thêm CHECK Constraint để giới hạn các giá trị có thể có cho cột status
    CONSTRAINT CHK_OrderStatus CHECK (status IN ('pending', 'served', 'paid', 'cancelled')) 
);

CREATE TABLE order_item (
    id INT IDENTITY(1,1) PRIMARY KEY,
    order_id INT NOT NULL,
    item_name NVARCHAR(100) NOT NULL,
    quantity INT DEFAULT 1,
    price DECIMAL(10,2) NOT NULL, -- Thêm ràng buộc NOT NULL
    
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE,
    
    -- Ràng buộc DUY NHẤT để ngăn chặn việc thêm cùng một món hai lần trong cùng một đơn hàng
    CONSTRAINT UQ_OrderItem UNIQUE (order_id, item_name)
);
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

    -- Dữ liệu mẫu
    INSERT INTO menu_items (name, description, price, category, image_url, created_at)
    VALUES
    (N'Phở Bò', N'Phở bò truyền thống', 85000.00, N'Món chính', N'static/images/pho_bo.jpeg', GETDATE()),
    (N'Bún Chả', N'Bún chả Hà Nội', 75000.00, N'Món chính', N'static/images/bun_cha.webp', GETDATE()),
    (N'Gà Nướng', N'Gà nướng mật ong', 120000.00, N'Món chính', N'static/images/ga_nuong.jpg', GETDATE()),
    (N'Coca Cola', N'Nước ngọt có ga', 20000.00, N'Đồ uống', N'static/images/coca_cola.jpg', GETDATE()),
    (N'Pepsi', N'Nước ngọt có ga', 20000.00, N'Đồ uống', N'static/images/pepsi.jpg', GETDATE()),
    (N'Bia Tiger', N'Bia Tiger lạnh', 35000.00, N'Đồ uống', N'static/images/bia_tiger.jpg', GETDATE()),
    (N'Chè Thái', N'Chè Thái mát lạnh', 30000.00, N'Tráng miệng', N'static/images/che_thai.jpg', GETDATE()),
    (N'Kem Dừa', N'Kem dừa tươi', 25000.00, N'Tráng miệng', N'static/images/kem_dua.jpg', GETDATE());
END
GO


-- Kiểm tra và thêm cột menu_item_id
IF NOT EXISTS (
    SELECT * FROM INFORMATION_SCHEMA.COLUMNS 
    WHERE TABLE_NAME = 'order_item' AND COLUMN_NAME = 'menu_item_id'
)
BEGIN
    ALTER TABLE order_item
    ADD menu_item_id INT NULL;

    ALTER TABLE order_item
    ADD CONSTRAINT FK_orderitem_menuitem 
    FOREIGN KEY (menu_item_id) REFERENCES menu_items(id);

    PRINT 'Column menu_item_id added and FK created in order_item.';
END
ELSE
BEGIN
    PRINT 'Column menu_item_id already exists.';
END
GO
INSERT INTO admin (full_name, email, username, password)
VALUES (N'Nguyễn Quản Trị', N'admin@restaurant.vn', 'admin', '123456');

------------------------------------------------------------
-- BẢNG staff (10 bản ghi)
------------------------------------------------------------
INSERT INTO staff (full_name, birthdate, position, phone, email, address, username, password)
VALUES 
(N'Lê Văn Nam', '1995-03-12', N'Phục vụ', '0901234567', 'nam.staff@res.vn', N'Hà Nội', 'namnv', '123'),
(N'Nguyễn Thị Hoa', '1997-07-08', N'Thu ngân', '0902345678', 'hoa.staff@res.vn', N'Hà Nội', 'hoant', '123'),
(N'Trần Minh Tú', '1998-05-21', N'Phục vụ', '0903456789', 'tu.staff@res.vn', N'Hải Phòng', 'tutran', '123'),
(N'Phạm Văn Dũng', '1994-01-14', N'Đầu bếp', '0904567890', 'dung.staff@res.vn', N'Hà Nội', 'dungpv', '123'),
(N'Hoàng Thị Lan', '1999-10-09', N'Phục vụ', '0905678901', 'lan.staff@res.vn', N'Nam Định', 'lanht', '123'),
(N'Ngô Anh Quân', '1996-12-03', N'Phục vụ', '0906789012', 'quan.staff@res.vn', N'Hà Nội', 'quannga', '123'),
(N'Vũ Đức Bình', '1993-08-19', N'Đầu bếp', '0907890123', 'binh.staff@res.vn', N'Hà Nội', 'binhvd', '123'),
(N'Lê Hồng Hạnh', '1998-11-27', N'Thu ngân', '0908901234', 'hanh.staff@res.vn', N'Bắc Ninh', 'hanhlh', '123'),
(N'Đặng Tuấn Kiệt', '1995-02-17', N'Phục vụ', '0909012345', 'kiet.staff@res.vn', N'Hà Nội', 'kietdt', '123'),
(N'Phan Thanh Tùng', '1992-06-30', N'Phục vụ', '0910123456', 'tung.staff@res.vn', N'Hà Nội', 'tungpt', '123');

------------------------------------------------------------
-- BẢNG reservation (10 bản ghi)
------------------------------------------------------------
INSERT INTO reservation (customer_name, phone, table_number, reservation_time, num_people, note, staff_id, admin_id)
VALUES
(N'Nguyễn Văn A', '0981111111', 1, '2025-11-07 18:00', 4, N'Sinh nhật', 1, 1),
(N'Lê Thị B', '0982222222', 2, '2025-11-07 19:00', 2, N'Hẹn hò', 2, 1),
(N'Trần Văn C', '0983333333', 3, '2025-11-07 20:00', 6, N'Tiệc gia đình', 3, 1),
(N'Phạm Thị D', '0984444444', 4, '2025-11-08 18:30', 3, N'Công ty', 4, 1),
(N'Hoàng Văn E', '0985555555', 5, '2025-11-08 19:30', 5, N'Bạn bè', 5, 1),
(N'Vũ Thị F', '0986666666', 6, '2025-11-09 12:00', 4, N'Bữa trưa', 6, 1),
(N'Ngô Văn G', '0987777777', 7, '2025-11-09 19:00', 2, N'Buổi tối', 7, 1),
(N'Lý Thị H', '0988888888', 8, '2025-11-10 11:30', 8, N'Tiệc họp lớp', 8, 1),
(N'Đinh Văn I', '0989999999', 9, '2025-11-10 20:00', 3, N'Gia đình', 9, 1),
(N'Bùi Thị K', '0970000000', 10, '2025-11-11 18:45', 4, N'Bạn cũ', 10, 1);

------------------------------------------------------------
-- BẢNG orders (10 bản ghi)
------------------------------------------------------------
INSERT INTO orders (reservation_id, staff_id, order_time, total_amount, status)
VALUES
(1, 1, '2025-11-07 18:05', 250000, 'served'),
(2, 2, '2025-11-07 19:05', 180000, 'paid'),
(3, 3, '2025-11-07 20:10', 520000, 'served'),
(4, 4, '2025-11-08 18:35', 310000, 'pending'),
(5, 5, '2025-11-08 19:40', 450000, 'served'),
(6, 6, '2025-11-09 12:10', 150000, 'paid'),
(7, 7, '2025-11-09 19:10', 200000, 'pending'),
(8, 8, '2025-11-10 11:40', 680000, 'served'),
(9, 9, '2025-11-10 20:10', 390000, 'paid'),
(10, 10, '2025-11-11 18:50', 320000, 'pending');