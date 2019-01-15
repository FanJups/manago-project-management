DELIMITER //
create function getcustomer(custId int) returns VARCHAR(255) DETERMINISTIC
BEGIN
	DECLARE fname varchar(255);
    DECLARE lname varchar(255);
    DECLARE zip varchar(255);
    DECLARE addr varchar(255);

    select c.first_name into @fname
    from customer c
    where c.customer_id = custId;

    select c.last_name into @lname
    from customer c
    where c.customer_id = custId;

    select c.zip_code into @zip
    from customer c
    where c.customer_id = custId;

    select c.address into @addr
    from customer c
    where c.customer_id = custId;

    RETURN CONCAT(@fname, ' ', @lname, ' ', @addr, ' ', @zip);
END //
DELIMITER ;