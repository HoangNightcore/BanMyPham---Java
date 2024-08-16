--Trigger nếu thêm 1 hóa đơn mới thì khách hàng có mã tương ứng sẽ bật trạng thái (status) là true
CREATE TRIGGER UpdateCustomerStatus
ON Orders
AFTER INSERT
AS
BEGIN
    -- Update status of customers referenced in the inserted orders
    UPDATE Customer
    SET status = 1 -- Set status to 1 (True)
    WHERE EXISTS (
        SELECT 1
        FROM inserted
        WHERE inserted.customerID = Customer.customerID
    );
END;



--Trigger cập nhật tự động gán loại khách hàng dựa trên tổng số chi tiêu
CREATE TRIGGER AssignCustomerType
ON Customer
AFTER INSERT, UPDATE
AS
BEGIN
    -- Update customer type based on totalSpending
    UPDATE Customer
    SET customerTypeID = CASE 
                            WHEN inserted.totalSpending >= 500 THEN 'CT002' -- Loại 2
                            ELSE 'CT001' -- Loại 1
                        END
    FROM Customer
    INNER JOIN inserted ON Customer.customerID = inserted.customerID;
END;