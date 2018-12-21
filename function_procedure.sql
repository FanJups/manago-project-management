CREATE OR REPLACE FUNCTION customerOfProject(in_name IN varchar2)
    RETURN varchar2
IS
    result varchar2;
    cId number;
BEGIN
    SELECT customerId INTO cId
    FROM customer_project
    WHERE name like in_name;

    SELECT lastName INTO result
    FROM customer
    WHERE customerId = cId;

RETURN result;
END;

CREATE OR REPLACE PROCEDURE updateMonthlyCost
AS
UPDATE team
SET monthlyCost = c.newMC
FROM (
    SELECT sum(salary) as newMC, team_employee.name as team
    FROM employee INNER JOIN team_employee
    ON employee.employeeId = team_employee.employeeId
    GROUP BY team_employee.name
) c
WHERE c.team = team.name
GO;