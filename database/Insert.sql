INSERT INTO UserType (userTypeID, typeName)
VALUES ('UT001', N'Admin'),
       ('UT002', N'Nhân viên'),
	   ('UT003', N'Quản lý');

INSERT INTO Users (userID, username, password, firstName, lastName, email, gender, phoneNumber,imageUser, address, userTypeID) VALUES
('USER001', 'admin', 'admin', 'John', 'Doe', 'admin@example.com', N'Nam', '1234567890','images/admin.jpg', '123 Admin St', 'UT001'),
('USER002', 'staff', 'staff', 'Alice', 'Smith', 'alice@example.com', N'Nữ', '9876543210','images/staff.jpg', '456 Staff St', 'UT002'),
('USER003', 'manager', 'manager', 'Sarah', 'Brown', 'sarah@example.com', N'Nữ', '4567890123','images/manager.jpg', '345 Manager St', 'UT003');


INSERT INTO CustomerType (customerTypeID, customerTypeName)
VALUES ('CT001', N'Regular'),
       ('CT002', N'VIP');

INSERT INTO Customer (customerID, firstName, lastName, gender, phoneNumber, address, totalSpending, customerTypeID)
VALUES ('CUS001', N'David', N'Lee', N'Nam', '0987654321', N'789 Maple Ave', 0.00, 'CT001'),
       ('CUS002', N'Alice', N'Wang', N'Nữ', '0234567890', N'10 Freemont St', 100.00, 'CT002');

INSERT INTO Supplier (supplierID, supplierName, address, phoneNumber)
VALUES ('SUP001', N'ABC Company', N'555 Industry Lane', '0888777666'),
       ('SUP002', N'DEF Corporation', N'123 Business Ave', '0999888777');

INSERT INTO ProductCategory (productCategoryID, productCategoryName)
VALUES ('PC001', N'Skincare'),
       ('PC002', N'Makeup'),
       ('PC003', N'Haircare');
INSERT INTO Product (productID, productCategoryID, productName, description, price, quantity, image, units)
VALUES ('PR001', 'PC001', N'Moisturizer', N'Hydrating cream for all skin types', 25.50, 10, 'images/moisturizer.jpg', N'ml'),
       ('PR002', 'PC002', N'Foundation', N'Buildable coverage for a flawless look', 39.99, 5, 'images/foundation.jpg', N'g'),
       ('PR003', 'PC003', N'Shampoo', N'Nourishing shampoo for all hair types', 19.95, 15, 'images/shampoo.jpg', N'ml');

INSERT INTO Product_Sale (saleID, saleName, discountPercentage, condition, startDate, endDate, isActive)
VALUES 
('PS001', N'Sale 1', 10.00, 1, '2024-04-20', '2024-04-30', 1),
('PS002', N'Sale 2', 15.00, 2, '2024-05-01', '2024-05-10', 1),
('PS003', N'Sale 3', 20.00, 3, '2024-06-01', '2024-06-15', 0);

INSERT INTO WarehouseReceipt (warehouseReceiptID, supplierID, userID, dateAdded, totalCost)
VALUES ('WR001', 'SUP001', 'USER001', GETDATE(), 150.00);

INSERT INTO WarehouseReceipt_Details (warehouseReceiptID, productID, quantity, price, totalCost)
VALUES ('WR001', 'PR001', 5, 25.50, 127.50),
       ('WR001', 'PR002', 2, 39.99, 79.98),
       ('WR001', 'PR003', 3, 19.95, 59.85);

INSERT INTO Order_Status (orderStatusID, orderStatusName)
VALUES ('OS001', N'Pending'),
       ('OS002', N'Processing'),
       ('OS003', N'Shipped'),
       ('OS004', N'Completed'),
       ('OS005', N'Canceled');

INSERT INTO Orders (orderID, userID, customerID, orderStatusID, orderDate, totalCost)
VALUES ('ORD001', 'USER002', 'CUS001', 'OS001', GETDATE(), 100.00);

INSERT INTO Order_Details (orderID, productID, quantity)
VALUES ('ORD001', 'PR002', 1),
       ('ORD001', 'PR003', 2);