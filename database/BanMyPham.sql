USE [BanMyPham]
GO
/****** Object:  Table [dbo].[Customer]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Customer](
	[customerID] [varchar](10) NOT NULL,
	[firstName] [nvarchar](50) NULL,
	[lastName] [nvarchar](50) NULL,
	[gender] [nvarchar](5) NULL,
	[phoneNumber] [varchar](12) NULL,
	[address] [nvarchar](200) NULL,
	[totalSpending] [decimal](10, 2) NULL,
	[customerTypeID] [varchar](10) NULL,
	[status] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[customerID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CustomerType]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CustomerType](
	[customerTypeID] [varchar](10) NOT NULL,
	[customerTypeName] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[customerTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_Details]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_Details](
	[orderID] [varchar](10) NOT NULL,
	[productID] [varchar](10) NOT NULL,
	[quantity] [int] NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC,
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Order_Status]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Order_Status](
	[orderStatusID] [varchar](10) NOT NULL,
	[orderStatusName] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[orderStatusID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Orders]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Orders](
	[orderID] [varchar](10) NOT NULL,
	[userID] [varchar](10) NULL,
	[customerID] [varchar](10) NULL,
	[orderStatusID] [varchar](10) NULL,
	[orderDate] [date] NULL,
	[totalCost] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[orderID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product](
	[productID] [varchar](10) NOT NULL,
	[productCategoryID] [varchar](10) NULL,
	[productName] [nvarchar](100) NULL,
	[description] [nvarchar](200) NULL,
	[price] [decimal](10, 2) NULL,
	[quantity] [int] NULL,
	[image] [varchar](200) NULL,
	[units] [nvarchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Product_Sale]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Product_Sale](
	[saleID] [varchar](10) NOT NULL,
	[saleName] [nvarchar](200) NULL,
	[discountPercentage] [decimal](10, 2) NULL,
	[condition] [int] NULL,
	[startDate] [date] NULL,
	[endDate] [date] NULL,
	[isActive] [bit] NULL,
PRIMARY KEY CLUSTERED 
(
	[saleID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ProductCategory]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ProductCategory](
	[productCategoryID] [varchar](10) NOT NULL,
	[productCategoryName] [nvarchar](100) NULL,
PRIMARY KEY CLUSTERED 
(
	[productCategoryID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Supplier]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Supplier](
	[supplierID] [varchar](10) NOT NULL,
	[supplierName] [nvarchar](50) NULL,
	[address] [nvarchar](200) NULL,
	[phoneNumber] [varchar](12) NULL,
PRIMARY KEY CLUSTERED 
(
	[supplierID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[userID] [varchar](10) NOT NULL,
	[username] [nvarchar](50) NULL,
	[password] [nvarchar](50) NULL,
	[firstName] [nvarchar](50) NULL,
	[lastName] [nvarchar](50) NULL,
	[email] [nvarchar](50) NULL,
	[gender] [nvarchar](5) NULL,
	[phoneNumber] [varchar](11) NULL,
	[imageUser] [varchar](200) NULL,
	[address] [nvarchar](200) NULL,
	[userTypeID] [varchar](10) NULL,
PRIMARY KEY CLUSTERED 
(
	[userID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[UserType]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[UserType](
	[userTypeID] [varchar](10) NOT NULL,
	[typeName] [nvarchar](50) NULL,
PRIMARY KEY CLUSTERED 
(
	[userTypeID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WarehouseReceipt]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WarehouseReceipt](
	[warehouseReceiptID] [varchar](10) NOT NULL,
	[supplierID] [varchar](10) NULL,
	[userID] [varchar](10) NULL,
	[dateAdded] [date] NULL,
	[totalCost] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[warehouseReceiptID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[WarehouseReceipt_Details]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[WarehouseReceipt_Details](
	[warehouseReceiptID] [varchar](10) NOT NULL,
	[productID] [varchar](10) NOT NULL,
	[quantity] [int] NULL,
	[price] [decimal](10, 2) NULL,
	[totalCost] [decimal](10, 2) NULL,
PRIMARY KEY CLUSTERED 
(
	[warehouseReceiptID] ASC,
	[productID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
INSERT [dbo].[Customer] ([customerID], [firstName], [lastName], [gender], [phoneNumber], [address], [totalSpending], [customerTypeID], [status]) VALUES (N'CUS001', N'David', N'Lee', N'Nam', N'0987654321', N'789 Maple Ave', CAST(1900942.10 AS Decimal(10, 2)), N'CT002', 1)
INSERT [dbo].[Customer] ([customerID], [firstName], [lastName], [gender], [phoneNumber], [address], [totalSpending], [customerTypeID], [status]) VALUES (N'CUS002', N'Alice', N'Wang', N'Nữ', N'0234567890', N'10 Freemont St', CAST(2280500.20 AS Decimal(10, 2)), N'CT002', 1)
GO
INSERT [dbo].[CustomerType] ([customerTypeID], [customerTypeName]) VALUES (N'CT001', N'Regular')
INSERT [dbo].[CustomerType] ([customerTypeID], [customerTypeName]) VALUES (N'CT002', N'VIP')
GO
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD001', N'PR002', 1)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD001', N'PR003', 2)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD002', N'PR001', 3)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD003', N'PR002', 1)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD003', N'PR003', 5)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD006', N'PR002', 1)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD007', N'PR001', 8)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD007', N'PR002', 1)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD008', N'PR003', 5)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD009', N'PR002', 1)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD010', N'PR004', 5)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD010', N'PR005', 5)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD011', N'PR001', 3)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD012', N'PR004', 6)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD013', N'PR003', 5)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD014', N'PR002', 5)
INSERT [dbo].[Order_Details] ([orderID], [productID], [quantity]) VALUES (N'ORD014', N'PR003', 3)
GO
INSERT [dbo].[Order_Status] ([orderStatusID], [orderStatusName]) VALUES (N'OS001', N'Pending')
INSERT [dbo].[Order_Status] ([orderStatusID], [orderStatusName]) VALUES (N'OS002', N'Processing')
INSERT [dbo].[Order_Status] ([orderStatusID], [orderStatusName]) VALUES (N'OS003', N'Shipped')
INSERT [dbo].[Order_Status] ([orderStatusID], [orderStatusName]) VALUES (N'OS004', N'Completed')
INSERT [dbo].[Order_Status] ([orderStatusID], [orderStatusName]) VALUES (N'OS005', N'Canceled')
GO
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD001', N'USER002', N'CUS001', N'OS001', CAST(N'2024-05-16' AS Date), CAST(100.00 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD002', N'USER001', N'CUS001', N'OS001', CAST(N'2024-04-19' AS Date), CAST(150.50 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD003', N'USER001', N'CUS001', N'OS002', CAST(N'2024-05-20' AS Date), CAST(200.75 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD004', N'USER002', N'CUS001', N'OS003', CAST(N'2024-05-21' AS Date), CAST(300.25 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD005', N'USER001', N'CUS001', N'OS004', CAST(N'2024-04-29' AS Date), CAST(19.00 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD006', N'USER001', N'CUS002', N'OS004', CAST(N'2024-05-29' AS Date), CAST(38.00 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD007', N'USER001', N'CUS001', N'OS004', CAST(N'2024-05-30' AS Date), CAST(248.00 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD008', N'USER001', N'CUS001', N'OS004', CAST(N'2024-05-31' AS Date), CAST(95.00 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD009', N'USER001', N'CUS002', N'OS004', CAST(N'2024-05-31' AS Date), CAST(40.00 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD010', N'USER001', N'CUS001', N'OS004', CAST(N'2024-04-06' AS Date), CAST(1900475.00 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD011', N'USER001', N'CUS001', N'OS004', CAST(N'2024-06-06' AS Date), CAST(74.10 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD012', N'USER001', N'CUS002', N'OS004', CAST(N'2024-06-06' AS Date), CAST(2280000.00 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD013', N'USER001', N'CUS002', N'OS004', CAST(N'2024-06-06' AS Date), CAST(19.00 AS Decimal(10, 2)))
INSERT [dbo].[Orders] ([orderID], [userID], [customerID], [orderStatusID], [orderDate], [totalCost]) VALUES (N'ORD014', N'USER001', N'CUS002', N'OS004', CAST(N'2024-06-06' AS Date), CAST(166.40 AS Decimal(10, 2)))
GO
INSERT [dbo].[Product] ([productID], [productCategoryID], [productName], [description], [price], [quantity], [image], [units]) VALUES (N'PR001', N'PC001', N'Moisturizer', N'', CAST(26.00 AS Decimal(10, 2)), 17, N'G03-200x200.jpg', N'ml')
INSERT [dbo].[Product] ([productID], [productCategoryID], [productName], [description], [price], [quantity], [image], [units]) VALUES (N'PR002', N'PC002', N'Foundation', N'', CAST(40.00 AS Decimal(10, 2)), 0, N'G03-200x200.jpg', N'g')
INSERT [dbo].[Product] ([productID], [productCategoryID], [productName], [description], [price], [quantity], [image], [units]) VALUES (N'PR003', N'PC003', N'Shampoo', N'', CAST(4.00 AS Decimal(10, 2)), 7, N'G03-200x200.jpg', N'ml')
INSERT [dbo].[Product] ([productID], [productCategoryID], [productName], [description], [price], [quantity], [image], [units]) VALUES (N'PR004', N'PC001', N'kem chống nắng', N'', CAST(400000.00 AS Decimal(10, 2)), 200, N'avatar.jpg', N'ml')
INSERT [dbo].[Product] ([productID], [productCategoryID], [productName], [description], [price], [quantity], [image], [units]) VALUES (N'PR005', N'PC002', N'nước rửa tay', N'', CAST(100.00 AS Decimal(10, 2)), 120, N'avatar.jpg', N'ml')
INSERT [dbo].[Product] ([productID], [productCategoryID], [productName], [description], [price], [quantity], [image], [units]) VALUES (N'PR006', N'PC002', N'kem dương da', N'', CAST(80000.00 AS Decimal(10, 2)), 30, N'avatar.jpg', N'ml')
GO
INSERT [dbo].[Product_Sale] ([saleID], [saleName], [discountPercentage], [condition], [startDate], [endDate], [isActive]) VALUES (N'PS001', N'Sale 1', CAST(10.00 AS Decimal(10, 2)), 1, CAST(N'2024-04-20' AS Date), CAST(N'2024-04-30' AS Date), 1)
INSERT [dbo].[Product_Sale] ([saleID], [saleName], [discountPercentage], [condition], [startDate], [endDate], [isActive]) VALUES (N'PS002', N'Sale 2', CAST(15.00 AS Decimal(10, 2)), 2, CAST(N'2024-05-01' AS Date), CAST(N'2024-05-10' AS Date), 1)
INSERT [dbo].[Product_Sale] ([saleID], [saleName], [discountPercentage], [condition], [startDate], [endDate], [isActive]) VALUES (N'PS003', N'Sale 3', CAST(20.00 AS Decimal(10, 2)), 3, CAST(N'2024-06-01' AS Date), CAST(N'2024-06-15' AS Date), 0)
INSERT [dbo].[Product_Sale] ([saleID], [saleName], [discountPercentage], [condition], [startDate], [endDate], [isActive]) VALUES (N'PS004', N'Sale 4', CAST(20.00 AS Decimal(10, 2)), 5, CAST(N'2024-05-14' AS Date), CAST(N'2024-05-25' AS Date), 0)
INSERT [dbo].[Product_Sale] ([saleID], [saleName], [discountPercentage], [condition], [startDate], [endDate], [isActive]) VALUES (N'PS006', N'Sale 6', CAST(5.00 AS Decimal(10, 2)), 10, CAST(N'2024-05-29' AS Date), CAST(N'2024-06-26' AS Date), 0)
GO
INSERT [dbo].[ProductCategory] ([productCategoryID], [productCategoryName]) VALUES (N'PC003', N'Haircare')
INSERT [dbo].[ProductCategory] ([productCategoryID], [productCategoryName]) VALUES (N'PC002', N'Makeup')
INSERT [dbo].[ProductCategory] ([productCategoryID], [productCategoryName]) VALUES (N'PC001', N'Skincare')
GO
INSERT [dbo].[Supplier] ([supplierID], [supplierName], [address], [phoneNumber]) VALUES (N'SUP001', N'ABC Company', N'555 Industry Lane', N'0888777666')
INSERT [dbo].[Supplier] ([supplierID], [supplierName], [address], [phoneNumber]) VALUES (N'SUP002', N'DEF Corporation', N'123 Business Ave', N'0999888777')
GO
INSERT [dbo].[Users] ([userID], [username], [password], [firstName], [lastName], [email], [gender], [phoneNumber], [imageUser], [address], [userTypeID]) VALUES (N'USER001', N'admin', N'admin', N'John', N'Doe', N'admin@example.com', N'Nam', N'1234567890', N'434391559_1576294329886781_8808623104079063343_n.jpg', N'123 Admin St', N'UT001')
INSERT [dbo].[Users] ([userID], [username], [password], [firstName], [lastName], [email], [gender], [phoneNumber], [imageUser], [address], [userTypeID]) VALUES (N'USER002', N'staff', N'staff', N'Alice', N'Smith', N'alice@example.com', N'Nữ', N'9876543210', N'434391559_1576294329886781_8808623104079063343_n.jpg', N'456 Staff St', N'UT002')
INSERT [dbo].[Users] ([userID], [username], [password], [firstName], [lastName], [email], [gender], [phoneNumber], [imageUser], [address], [userTypeID]) VALUES (N'USER003', N'manager', N'manager', N'Sarah', N'Brown', N'sarah@example.com', N'Nữ', N'4567890123', N'images/manager.jpg', N'345 Manager St', N'UT003')
INSERT [dbo].[Users] ([userID], [username], [password], [firstName], [lastName], [email], [gender], [phoneNumber], [imageUser], [address], [userTypeID]) VALUES (N'USER004', N'nguyenminhhoang', N'123', NULL, NULL, N'nguyenminhhoang7503@gmail.com', NULL, NULL, NULL, NULL, N'UT001')
GO
INSERT [dbo].[UserType] ([userTypeID], [typeName]) VALUES (N'UT001', N'Admin')
INSERT [dbo].[UserType] ([userTypeID], [typeName]) VALUES (N'UT002', N'Nhân viên')
INSERT [dbo].[UserType] ([userTypeID], [typeName]) VALUES (N'UT003', N'Quản lý')
GO
INSERT [dbo].[WarehouseReceipt] ([warehouseReceiptID], [supplierID], [userID], [dateAdded], [totalCost]) VALUES (N'WR001', N'SUP001', N'USER001', CAST(N'2024-05-16' AS Date), CAST(150.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt] ([warehouseReceiptID], [supplierID], [userID], [dateAdded], [totalCost]) VALUES (N'WR002', N'SUP001', N'USER001', CAST(N'2024-06-06' AS Date), CAST(200000.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt] ([warehouseReceiptID], [supplierID], [userID], [dateAdded], [totalCost]) VALUES (N'WR003', N'SUP002', N'USER001', CAST(N'2024-06-06' AS Date), CAST(10000.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt] ([warehouseReceiptID], [supplierID], [userID], [dateAdded], [totalCost]) VALUES (N'WR004', N'SUP001', N'USER001', CAST(N'2024-06-06' AS Date), CAST(200000.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt] ([warehouseReceiptID], [supplierID], [userID], [dateAdded], [totalCost]) VALUES (N'WR005', N'SUP001', N'USER001', CAST(N'2024-06-06' AS Date), CAST(1.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt] ([warehouseReceiptID], [supplierID], [userID], [dateAdded], [totalCost]) VALUES (N'WR006', N'SUP001', N'USER001', CAST(N'2024-06-06' AS Date), CAST(1.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt] ([warehouseReceiptID], [supplierID], [userID], [dateAdded], [totalCost]) VALUES (N'WR007', N'SUP001', N'USER001', CAST(N'2024-06-06' AS Date), CAST(20603125.00 AS Decimal(10, 2)))
GO
INSERT [dbo].[WarehouseReceipt_Details] ([warehouseReceiptID], [productID], [quantity], [price], [totalCost]) VALUES (N'WR001', N'PR001', 5, CAST(25.50 AS Decimal(10, 2)), CAST(127.50 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt_Details] ([warehouseReceiptID], [productID], [quantity], [price], [totalCost]) VALUES (N'WR001', N'PR002', 2, CAST(39.99 AS Decimal(10, 2)), CAST(79.98 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt_Details] ([warehouseReceiptID], [productID], [quantity], [price], [totalCost]) VALUES (N'WR001', N'PR003', 3, CAST(19.95 AS Decimal(10, 2)), CAST(59.85 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt_Details] ([warehouseReceiptID], [productID], [quantity], [price], [totalCost]) VALUES (N'WR004', N'PR004', 10, CAST(20000.00 AS Decimal(10, 2)), CAST(200000.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt_Details] ([warehouseReceiptID], [productID], [quantity], [price], [totalCost]) VALUES (N'WR005', N'PR003', 1, CAST(1.00 AS Decimal(10, 2)), CAST(1.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt_Details] ([warehouseReceiptID], [productID], [quantity], [price], [totalCost]) VALUES (N'WR006', N'PR004', 1, CAST(1.00 AS Decimal(10, 2)), CAST(1.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt_Details] ([warehouseReceiptID], [productID], [quantity], [price], [totalCost]) VALUES (N'WR007', N'PR004', 200, CAST(100000.00 AS Decimal(10, 2)), CAST(20000000.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt_Details] ([warehouseReceiptID], [productID], [quantity], [price], [totalCost]) VALUES (N'WR007', N'PR005', 125, CAST(25.00 AS Decimal(10, 2)), CAST(3125.00 AS Decimal(10, 2)))
INSERT [dbo].[WarehouseReceipt_Details] ([warehouseReceiptID], [productID], [quantity], [price], [totalCost]) VALUES (N'WR007', N'PR006', 30, CAST(20000.00 AS Decimal(10, 2)), CAST(600000.00 AS Decimal(10, 2)))
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Customer__3A77B988C358A62E]    Script Date: 6/13/2024 00:47:35 ******/
ALTER TABLE [dbo].[CustomerType] ADD UNIQUE NONCLUSTERED 
(
	[customerTypeName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Product__0A9CBBE02E60EEE0]    Script Date: 6/13/2024 00:47:35 ******/
ALTER TABLE [dbo].[Product] ADD UNIQUE NONCLUSTERED 
(
	[productName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__ProductC__DFF30878145128B6]    Script Date: 6/13/2024 00:47:35 ******/
ALTER TABLE [dbo].[ProductCategory] ADD UNIQUE NONCLUSTERED 
(
	[productCategoryName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__Users__F3DBC572B84CF58B]    Script Date: 6/13/2024 00:47:35 ******/
ALTER TABLE [dbo].[Users] ADD UNIQUE NONCLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
SET ANSI_PADDING ON
GO
/****** Object:  Index [UQ__UserType__A20CDB5845752C84]    Script Date: 6/13/2024 00:47:35 ******/
ALTER TABLE [dbo].[UserType] ADD UNIQUE NONCLUSTERED 
(
	[typeName] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO
ALTER TABLE [dbo].[Customer] ADD  DEFAULT ((0)) FOR [status]
GO
ALTER TABLE [dbo].[Product] ADD  DEFAULT ((0)) FOR [quantity]
GO
ALTER TABLE [dbo].[Product_Sale] ADD  DEFAULT ((0)) FOR [isActive]
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD FOREIGN KEY([customerTypeID])
REFERENCES [dbo].[CustomerType] ([customerTypeID])
GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD FOREIGN KEY([orderID])
REFERENCES [dbo].[Orders] ([orderID])
GO
ALTER TABLE [dbo].[Order_Details]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([productID])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([customerID])
REFERENCES [dbo].[Customer] ([customerID])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([orderStatusID])
REFERENCES [dbo].[Order_Status] ([orderStatusID])
GO
ALTER TABLE [dbo].[Orders]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
GO
ALTER TABLE [dbo].[Product]  WITH CHECK ADD FOREIGN KEY([productCategoryID])
REFERENCES [dbo].[ProductCategory] ([productCategoryID])
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD FOREIGN KEY([userTypeID])
REFERENCES [dbo].[UserType] ([userTypeID])
GO
ALTER TABLE [dbo].[WarehouseReceipt]  WITH CHECK ADD FOREIGN KEY([supplierID])
REFERENCES [dbo].[Supplier] ([supplierID])
GO
ALTER TABLE [dbo].[WarehouseReceipt]  WITH CHECK ADD FOREIGN KEY([userID])
REFERENCES [dbo].[Users] ([userID])
GO
ALTER TABLE [dbo].[WarehouseReceipt_Details]  WITH CHECK ADD FOREIGN KEY([productID])
REFERENCES [dbo].[Product] ([productID])
GO
ALTER TABLE [dbo].[WarehouseReceipt_Details]  WITH CHECK ADD FOREIGN KEY([warehouseReceiptID])
REFERENCES [dbo].[WarehouseReceipt] ([warehouseReceiptID])
GO
ALTER TABLE [dbo].[Customer]  WITH CHECK ADD CHECK  (([gender]=N'Nữ' OR [gender]=N'Nam'))
GO
ALTER TABLE [dbo].[Users]  WITH CHECK ADD CHECK  (([gender]=N'Nữ' OR [gender]=N'Nam'))
GO
/****** Object:  StoredProcedure [dbo].[InsertCustomer]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Procedure cho việc thêm người dùng tự động tăng mã 
CREATE PROCEDURE [dbo].[InsertCustomer] 
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
GO
/****** Object:  StoredProcedure [dbo].[InsertOrder]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[InsertOrder] 
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
GO
/****** Object:  StoredProcedure [dbo].[InsertProduct]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Procedure cho việc thêm sản phẩm với chức năng tự tăng mã
CREATE PROCEDURE [dbo].[InsertProduct] 
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
GO
/****** Object:  StoredProcedure [dbo].[InsertProductSale]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Procedure Tự Động Tìm Kiếm ID Trống Để Insert Vào Product_Sale
CREATE PROCEDURE [dbo].[InsertProductSale] 
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
GO
/****** Object:  StoredProcedure [dbo].[InsertRegisterUser]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[InsertRegisterUser]
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
GO
/****** Object:  StoredProcedure [dbo].[InsertSupplier]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- Tạo stored procedure để chèn dữ liệu vào bảng Supplier
CREATE PROCEDURE [dbo].[InsertSupplier] 
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
GO
/****** Object:  StoredProcedure [dbo].[InsertUser]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[InsertUser]
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
GO
/****** Object:  StoredProcedure [dbo].[InsertUserType]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
--Procedure cho việc thêm mới UserType có chức năng tăng mã tự động
CREATE PROCEDURE [dbo].[InsertUserType] 
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
GO
/****** Object:  StoredProcedure [dbo].[InsertWarehouseReceipt]    Script Date: 6/13/2024 00:47:35 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE PROCEDURE [dbo].[InsertWarehouseReceipt] 
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
GO
