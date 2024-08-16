use master;

GO
 CREATE DATABASE BanMyPham;
GO

USE BanMyPham;

CREATE TABLE UserType (
    userTypeID VARCHAR(10) PRIMARY KEY,
    typeName NVARCHAR(50) UNIQUE
);

CREATE TABLE Users (
    userID VARCHAR(10) PRIMARY KEY,
    username NVARCHAR(50) UNIQUE,
    password NVARCHAR(50),
    firstName NVARCHAR(50),
    lastName NVARCHAR(50),
    email NVARCHAR(50),
	gender NVARCHAR(5) CHECK(gender IN (N'Nam' , N'Nữ')),
	phoneNumber VARCHAR(11),
	imageUser VARCHAR(200),
	address NVARCHAR(200),
    userTypeID VARCHAR(10),
    FOREIGN KEY (userTypeID) REFERENCES UserType(userTypeID),
);

CREATE TABLE CustomerType(
	customerTypeID VARCHAR(10) PRIMARY KEY,
	customerTypeName NVARCHAR(50) UNIQUE,
);

CREATE TABLE Customer(
	customerID VARCHAR(10) PRIMARY KEY,
	firstName NVARCHAR(50),
	lastName NVARCHAR(50),
	gender NVARCHAR(5) CHECK(gender IN (N'Nam' , N'Nữ')),
	phoneNumber VARCHAR(12),
	address NVARCHAR(200),
	totalSpending DECIMAL (10,2),
	customerTypeID VARCHAR(10) FOREIGN KEY REFERENCES CustomerType(customerTypeID),
	status BIT DEFAULT 0
);

CREATE TABLE Supplier(
	supplierID VARCHAR(10) PRIMARY KEY,
	supplierName NVARCHAR(50),
	address NVARCHAR(200),
	phoneNumber VARCHAR(12),
);


CREATE TABLE ProductCategory(
	productCategoryID VARCHAR(10) PRIMARY KEY,
	productCategoryName NVARCHAR(100) UNIQUE,
);


CREATE TABLE Product(
	productID VARCHAR(10) PRIMARY KEY,
	productCategoryID VARCHAR(10) FOREIGN KEY REFERENCES ProductCategory(productCategoryID),
	productName NVARCHAR(100) UNIQUE,
	description NVARCHAR(200),
	price DECIMAL(10,2),
	quantity INT DEFAULT 0,
	image VARCHAR(200),
	units NVARCHAR(10),
);


CREATE TABLE Product_Sale(
	saleID VARCHAR(10) PRIMARY KEY,
	saleName NVARCHAR(200),
	discountPercentage DECIMAL(10,2),
	condition int,
	startDate DATE,
	endDate DATE,
	isActive BIT DEFAULT 0, --Mặc định là không được chọn
);

CREATE TABLE WarehouseReceipt(
	warehouseReceiptID VARCHAR(10) PRIMARY KEY,
	supplierID VARCHAR(10) FOREIGN KEY REFERENCES Supplier(supplierID),
	userID VARCHAR(10) FOREIGN KEY REFERENCES Users(userID),
	dateAdded DATE,
	totalCost DECIMAL (10,2),
);

CREATE TABLE WarehouseReceipt_Details(
	warehouseReceiptID VARCHAR(10) FOREIGN KEY REFERENCES WarehouseReceipt(warehouseReceiptID),
	productID VARCHAR(10) FOREIGN KEY REFERENCES Product(productID),
	quantity INT,
	price DECIMAL (10,2),
	totalCost DECIMAL (10,2),
	PRIMARY KEY (warehouseReceiptID,productID),
);

CREATE TABLE Order_Status(
	orderStatusID VARCHAR(10) PRIMARY KEY,
	orderStatusName NVARCHAR(50),
);

CREATE TABLE Orders(
	orderID VARCHAR(10) PRIMARY KEY,
	userID VARCHAR(10) FOREIGN KEY REFERENCES Users(userID),
	customerID VARCHAR(10) FOREIGN KEY REFERENCES Customer(customerID),
	orderStatusID VARCHAR(10) FOREIGN KEY REFERENCES Order_Status(orderStatusID),
	orderDate DATE,
	totalCost DECIMAL(10,2),
);

CREATE TABLE Order_Details(
	orderID VARCHAR(10) FOREIGN KEY REFERENCES Orders(orderID),
	productID VARCHAR(10) FOREIGN KEY REFERENCES Product(productID),
	PRIMARY KEY (orderID, productID),
	quantity INT,
);
