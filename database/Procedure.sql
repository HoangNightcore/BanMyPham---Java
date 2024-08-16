EXEC InsertProductSale N'Test6', 15.00, 2, '2024-05-01', '2024-05-10',1;
DROP PROCEDURE InsertProductSale;
--Procedure Tự Động Tìm Kiếm ID Trống Để Insert Vào Product_Sale
CREATE PROCEDURE InsertProductSale 
(
    @saleName NVARCHAR(200),
    @discountPercentage DECIMAL(10,2),
    @condition INT,
    @startDate DATE,
    @endDate DATE,
	@isActive BIT
)
AS
BEGIN
    DECLARE @newSaleID VARCHAR(10);

    -- Get the current maximum saleID
    DECLARE @maxSaleID INT;
    SELECT @maxSaleID = ISNULL(MAX(CAST(RIGHT(saleID, 3) AS INT)), 0) FROM Product_Sale;

    -- Find the missing ID in the sequence
    DECLARE @missingID INT;
    SET @missingID = 1;

    WHILE @missingID <= @maxSaleID 
    BEGIN
        IF NOT EXISTS (SELECT * FROM Product_Sale WHERE saleID = 'PS' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3))
        BEGIN
            SET @newSaleID = 'PS' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3);
            BREAK;
        END
        SET @missingID = @missingID + 1;
    END

    -- If there is no missing ID, generate a new one
    IF @newSaleID IS NULL
    BEGIN
        SET @newSaleID = 'PS' + RIGHT('00' + CAST((@maxSaleID % 1000) + 1 AS VARCHAR(3)), 3);
    END

    -- Insert new record with generated saleID
    INSERT INTO Product_Sale (saleID, saleName, discountPercentage, condition, startDate, endDate,isActive)
    VALUES (@newSaleID, @saleName, @discountPercentage, @condition, @startDate, @endDate, @isActive);
END;


EXEC InsertProduct 'PC001', N'Lotion', N'Hydrating lotion for dry skin', 15.99, 20, 'images/lotion.jpg', N'ml';
--Procedure cho việc thêm sản phẩm với chức năng tự tăng mã
CREATE PROCEDURE InsertProduct 
(
    @productCategoryID VARCHAR(10),
    @productName NVARCHAR(100),
    @description NVARCHAR(200),
    @price DECIMAL(10,2),
    @quantity INT,
    @image VARCHAR(200),
    @units NVARCHAR(10)
)
AS
BEGIN
    DECLARE @newProductID VARCHAR(10);

    -- Get the current maximum productID
    DECLARE @maxProductID INT;
    SELECT @maxProductID = ISNULL(MAX(CAST(RIGHT(productID, 3) AS INT)), 0) FROM Product;

    -- Find the missing ID in the sequence
    DECLARE @missingID INT;
    SET @missingID = 1;

    WHILE @missingID <= @maxProductID 
    BEGIN
        IF NOT EXISTS (SELECT * FROM Product WHERE productID = 'PR' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3))
        BEGIN
            SET @newProductID = 'PR' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3);
            BREAK;
        END
        SET @missingID = @missingID + 1;
    END

    -- If there is no missing ID, generate a new one
    IF @newProductID IS NULL
    BEGIN
        SET @newProductID = 'PR' + RIGHT('00' + CAST((@maxProductID % 1000) + 1 AS VARCHAR(3)), 3);
    END

    -- Insert new record with generated productID
    INSERT INTO Product (productID, productCategoryID, productName, description, price, quantity, image, units)
    VALUES (@newProductID, @productCategoryID, @productName, @description, @price, @quantity, @image, @units);
END;



EXEC InsertCustomer N'gdfg', N'sdfgds', N'Nam', '0987654321', N'789 Maple Ave', 0.00, 'CT001';
--Procedure cho việc thêm người dùng tự động tăng mã 
CREATE PROCEDURE InsertCustomer 
(
    @firstName NVARCHAR(50),
    @lastName NVARCHAR(50),
    @gender NVARCHAR(5),
    @phoneNumber VARCHAR(12),
    @address NVARCHAR(200),
    @totalSpending DECIMAL(10,2),
    @customerTypeID VARCHAR(10)
)
AS
BEGIN
    DECLARE @newCustomerID VARCHAR(10);

    -- Get the current maximum customerID
    DECLARE @maxCustomerID INT;
    SELECT @maxCustomerID = ISNULL(MAX(CAST(RIGHT(customerID, 3) AS INT)), 0) FROM Customer;

    -- Find the missing ID in the sequence
    DECLARE @missingID INT;
    SET @missingID = 1;

    WHILE @missingID <= @maxCustomerID 
    BEGIN
        IF NOT EXISTS (SELECT * FROM Customer WHERE customerID = 'CUS' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3))
        BEGIN
            SET @newCustomerID = 'CUS' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3);
            BREAK;
        END
        SET @missingID = @missingID + 1;
    END

    -- If there is no missing ID, generate a new one
    IF @newCustomerID IS NULL
    BEGIN
        SET @newCustomerID = 'CUS' + RIGHT('00' + CAST((@maxCustomerID % 1000) + 1 AS VARCHAR(3)), 3);
    END

    -- Insert new record with generated customerID
    INSERT INTO Customer (customerID, firstName, lastName, gender, phoneNumber, address, totalSpending, customerTypeID)
    VALUES (@newCustomerID, @firstName, @lastName, @gender, @phoneNumber, @address, @totalSpending, @customerTypeID);
END;


EXEC InsertUser 'refgerf', 'password123', 'John', 'Doe', 'john.doe@example.com', 'Nam', '1234567890', 'images/johndoe.jpg', '123 Main St', 'UT001';


--CREATE PROCEDURE InsertUser
--(
--  @username NVARCHAR(50),
--  @password NVARCHAR(50),
--  @firstName NVARCHAR(50),
--  @lastName NVARCHAR(50),
--  @email NVARCHAR(50),
--  @gender NVARCHAR(5),
--  @phoneNumber VARCHAR(11),
--  @imageUser VARCHAR(200),
--  @address NVARCHAR(200),
--  @userTypeID VARCHAR(10)
--)
--AS
--BEGIN
--  DECLARE @newUserID VARCHAR(10);

--  -- Get the current maximum numeric userID
--  DECLARE @maxUserID INT;
--  SELECT @maxUserID = ISNULL(MAX(CASE WHEN ISNUMERIC(RIGHT(userID, 4)) = 1 THEN CAST(RIGHT(userID, 4) AS INT) ELSE 0 END), 0)
--  FROM Users
--  WHERE userID LIKE 'USER%';

--  -- Find the missing ID in the sequence
--  DECLARE @missingID INT;
--  SET @missingID = 1;

--  WHILE @missingID <= @maxUserID
--  BEGIN
--    IF NOT EXISTS (SELECT * FROM Users WHERE userID = 'USER' + RIGHT('0000' + CAST(@missingID AS VARCHAR(4)), 4))
--    BEGIN
--      SET @newUserID = 'USER' + RIGHT('0000' + CAST(@missingID AS VARCHAR(4)), 4);
--      BREAK;
--    END
--    SET @missingID = @missingID + 1;
--  END

--  -- If there is no missing ID, generate a new one
--  IF @newUserID IS NULL
--  BEGIN
--    SET @newUserID = 'USER' + RIGHT('0000' + CAST((@maxUserID % 10000) + 1 AS VARCHAR(4)), 4);
--  END

--  -- Insert new record with generated userID
--  INSERT INTO Users (userID, username, password, firstName, lastName, email, gender, phoneNumber, imageUser, address, userTypeID)
--  VALUES (@newUserID, @username, @password, @firstName, @lastName, @email, @gender, @phoneNumber, @imageUser, @address, @userTypeID);
--END;



CREATE PROCEDURE InsertUser
(
    @username NVARCHAR(50),
    @password NVARCHAR(50),
    @firstName NVARCHAR(50),
    @lastName NVARCHAR(50),
    @email NVARCHAR(50),
    @gender NVARCHAR(5),
    @phoneNumber VARCHAR(11),
    @imageUser VARCHAR(200),
    @address NVARCHAR(200),
    @userTypeID VARCHAR(10)
)
AS
BEGIN
    DECLARE @newUserID VARCHAR(10);

    -- Get the current maximum userID
    DECLARE @maxUserID INT;
    SELECT @maxUserID = ISNULL(MAX(CAST(RIGHT(userID, 3) AS INT)), 0) FROM Users;

    -- Find the missing ID in the sequence
    DECLARE @missingID INT;
    SET @missingID = 1;

    WHILE @missingID <= @maxUserID 
    BEGIN
        IF NOT EXISTS (SELECT * FROM Users WHERE userID = 'USER' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3))
        BEGIN
            SET @newUserID = 'USER' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3);
            BREAK;
        END
        SET @missingID = @missingID + 1;
    END

    -- If there is no missing ID, generate a new one
    IF @newUserID IS NULL
    BEGIN
        SET @newUserID = 'USER' + RIGHT('00' + CAST((@maxUserID % 1000) + 1 AS VARCHAR(3)), 3);
    END

    -- Insert new record with generated userID
    INSERT INTO Users (userID, username, password, firstName, lastName, email, gender, phoneNumber, imageUser, address, userTypeID)
    VALUES (@newUserID, @username, @password, @firstName, @lastName, @email, @gender, @phoneNumber, @imageUser, @address, @userTypeID);
END;




-- Tạo stored procedure để chèn dữ liệu vào bảng Supplier
CREATE PROCEDURE InsertSupplier 
(
    @supplierName NVARCHAR(50), -- Tham số: tên nhà cung cấp
    @address NVARCHAR(200),     -- Tham số: địa chỉ nhà cung cấp
    @phoneNumber VARCHAR(12)    -- Tham số: số điện thoại nhà cung cấp
)
AS
BEGIN
    -- Biến lưu trữ ID mới được tạo ra
    DECLARE @newSupplierID VARCHAR(10);

    -- Lấy ID lớn nhất hiện có trong bảng Supplier
    DECLARE @maxSupplierID INT;
    SELECT @maxSupplierID = ISNULL(MAX(CAST(RIGHT(supplierID, 3) AS INT)), 0) FROM Supplier;

    -- Tìm ID còn trống trong trường supplierID
    DECLARE @missingID INT;
    SET @missingID = 1;

    -- Kiểm tra từng ID có tồn tại trong bảng hay không
    WHILE @missingID <= @maxSupplierID 
    BEGIN
        IF NOT EXISTS (SELECT * FROM Supplier WHERE supplierID = 'SUP' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3))
        BEGIN
            -- Nếu không tồn tại, gán ID mới và thoát khỏi vòng lặp
            SET @newSupplierID = 'SUP' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3);
            BREAK;
        END
        SET @missingID = @missingID + 1;
    END

    -- Nếu không tìm thấy ID còn trống, tạo ID mới
    IF @newSupplierID IS NULL
    BEGIN
        SET @newSupplierID = 'SUP' + RIGHT('00' + CAST((@maxSupplierID % 1000) + 1 AS VARCHAR(3)), 3);
    END

    -- Chèn dữ liệu mới vào bảng Supplier với ID mới
    INSERT INTO Supplier (supplierID, supplierName, address, phoneNumber)
    VALUES (@newSupplierID, @supplierName, @address, @phoneNumber);
END;

EXEC InsertSupplier @supplierName = N'Nhà cung cấp ABC', @address = N'123 Đường ABC, Quận XYZ, Thành phố HCM', @phoneNumber = '0123456789';



--Procedure insert warehouseReceipt
CREATE PROCEDURE InsertWarehouseReceipt 
(
    @supplierID VARCHAR(10),    -- Tham số: mã nhà cung cấp
    @userID VARCHAR(10),        -- Tham số: mã người dùng
    @dateAdded DATE,            -- Tham số: ngày thêm phiếu
    @totalCost DECIMAL(10,2)    -- Tham số: tổng chi phí
)
AS
BEGIN
    -- Biến lưu trữ ID mới được tạo ra
    DECLARE @newWarehouseReceiptID VARCHAR(10);

    -- Lấy ID lớn nhất hiện có trong bảng WarehouseReceipt
    DECLARE @maxWarehouseReceiptID INT;
    SELECT @maxWarehouseReceiptID = ISNULL(MAX(CAST(RIGHT(warehouseReceiptID, 3) AS INT)), 0) FROM WarehouseReceipt;

    -- Tìm ID còn trống trong trường warehouseReceiptID
    DECLARE @missingID INT;
    SET @missingID = 1;

    -- Kiểm tra từng ID có tồn tại trong bảng hay không
    WHILE @missingID <= @maxWarehouseReceiptID 
    BEGIN
        IF NOT EXISTS (SELECT * FROM WarehouseReceipt WHERE warehouseReceiptID = 'WR' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3))
        BEGIN
            -- Nếu không tồn tại, gán ID mới và thoát khỏi vòng lặp
SET @newWarehouseReceiptID = 'WR' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3);
            BREAK;
        END
        SET @missingID = @missingID + 1;
    END

    -- Nếu không tìm thấy ID còn trống, tạo ID mới
    IF @newWarehouseReceiptID IS NULL
    BEGIN
        SET @newWarehouseReceiptID = 'WR' + RIGHT('00' + CAST((@maxWarehouseReceiptID % 1000) + 1 AS VARCHAR(3)), 3);
    END

    -- Chèn dữ liệu mới vào bảng WarehouseReceipt với ID mới
    INSERT INTO WarehouseReceipt (warehouseReceiptID, supplierID, userID, dateAdded, totalCost)
    VALUES (@newWarehouseReceiptID, @supplierID, @userID, @dateAdded, @totalCost);
END;

EXEC InsertWarehouseReceipt @supplierID = 'SUP001', @userID = 'USER2', @dateAdded = '2024-05-22', @totalCost = 10000.00;



EXEC InsertOrder 'USER002', 'CUS002', 'OS001', '2024-05-19', 150.50
--Procedure cho việc thêm 1 hóa đơn với chức năng tạo mã tự động
CREATE PROCEDURE InsertOrder 
(
    @userID VARCHAR(10),
    @customerID VARCHAR(10),
    @orderStatusID VARCHAR(10),
    @orderDate DATE,
    @totalCost DECIMAL(10,2)
)
AS
BEGIN
    DECLARE @newOrderID VARCHAR(10);

    -- Get the current maximum orderID
    DECLARE @maxOrderID INT;
    SELECT @maxOrderID = ISNULL(MAX(CAST(RIGHT(orderID, 3) AS INT)), 0) FROM Orders;

    -- Find the missing ID in the sequence
    DECLARE @missingID INT;
    SET @missingID = 1;

    WHILE @missingID <= @maxOrderID 
    BEGIN
        IF NOT EXISTS (SELECT * FROM Orders WHERE orderID = 'ORD' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3))
        BEGIN
            SET @newOrderID = 'ORD' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3);
            BREAK;
        END
        SET @missingID = @missingID + 1;
    END

    -- If there is no missing ID, generate a new one
    IF @newOrderID IS NULL
    BEGIN
        SET @newOrderID = 'ORD' + RIGHT('00' + CAST((@maxOrderID % 1000) + 1 AS VARCHAR(3)), 3);
    END

    -- Insert new record with generated orderID
    INSERT INTO Orders (orderID, userID, customerID, orderStatusID, orderDate, totalCost)
    VALUES (@newOrderID, @userID, @customerID, @orderStatusID, @orderDate, @totalCost);
END;


CREATE PROCEDURE InsertRegisterUser
(
    @username NVARCHAR(50),
    @email NVARCHAR(50),
	@password NVARCHAR(50)
)
AS
BEGIN
    DECLARE @newUserID VARCHAR(10);

    -- Get the current maximum userID
    DECLARE @maxUserID INT;
    SELECT @maxUserID = ISNULL(MAX(CAST(RIGHT(userID, 3) AS INT)), 0) FROM Users;

    -- Find the missing ID in the sequence
    DECLARE @missingID INT;
    SET @missingID = 1;

    WHILE @missingID <= @maxUserID 
    BEGIN
        IF NOT EXISTS (SELECT * FROM Users WHERE userID = 'USER' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3))
        BEGIN
            SET @newUserID = 'USER' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3);
            BREAK;
        END
        SET @missingID = @missingID + 1;
    END

    -- If there is no missing ID, generate a new one
    IF @newUserID IS NULL
    BEGIN
        SET @newUserID = 'USER' + RIGHT('00' + CAST((@maxUserID % 1000) + 1 AS VARCHAR(3)), 3);
    END

    -- Insert new record with generated userID
    INSERT INTO Users (userID, username, password, email)
    VALUES (@newUserID, @username, @password, @email);
END;

select * from UserType order By userTypeID
--Procedure cho việc thêm mới UserType có chức năng tăng mã tự động
CREATE PROCEDURE InsertUserType 
(
    @userTypeName NVARCHAR(50)
)
AS
BEGIN
    DECLARE @newUserTypeID VARCHAR(10);

    -- Get the current maximum userTypeID
    DECLARE @maxUserTypeID INT;
    SELECT @maxUserTypeID = ISNULL(MAX(CAST(RIGHT(userTypeID, 3) AS INT)), 0) FROM UserType;

    -- Find the missing ID in the sequence
    DECLARE @missingID INT;
    SET @missingID = 1;

    WHILE @missingID <= @maxUserTypeID 
    BEGIN
        IF NOT EXISTS (SELECT * FROM UserType WHERE userTypeID = 'UT' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3))
        BEGIN
            SET @newUserTypeID = 'UT' + RIGHT('00' + CAST(@missingID AS VARCHAR(3)), 3);
            BREAK;
        END
        SET @missingID = @missingID + 1;
    END

    -- If there is no missing ID, generate a new one
    IF @newUserTypeID IS NULL
    BEGIN
        SET @newUserTypeID = 'UT' + RIGHT('00' + CAST((@maxUserTypeID % 1000) + 1 AS VARCHAR(3)), 3);
    END

    -- Insert new record with generated userTypeID
    INSERT INTO UserType (userTypeID, typeName)
    VALUES (@newUserTypeID, @userTypeName);
END;










SELECT SUM(totalCost) 
FROM Orders 
WHERE orderDate >= '2024-01-01' AND orderDate < '2024-02-01';


-- Thêm dữ liệu vào bảng Orders
INSERT INTO Orders (orderID, userID, customerID, orderStatusID, orderDate, totalCost)
VALUES ('ORD002', 'USER001', 'CUS001', 'OS001', '2024-05-19', 150.50),
       ('ORD003', 'USER001', 'CUS001', 'OS002', '2024-05-20', 200.75),
       ('ORD004', 'USER002', 'CUS001', 'OS003', '2024-05-21', 300.25);

-- Thêm dữ liệu vào bảng Order_Details
INSERT INTO Order_Details (orderID, productID, quantity)
VALUES ('ORD002', 'PR001', 3),
       ('ORD003', 'PR002', 1),
       ('ORD003', 'PR003', 5);
