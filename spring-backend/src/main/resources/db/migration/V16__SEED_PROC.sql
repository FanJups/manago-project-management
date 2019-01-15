DELIMITER //
CREATE PROCEDURE calcforteam(In teamName varchar(255))
BEGIN
    DECLARE nc double;

    SELECT sum(e.salary) into @nc
    FROM employee e
    INNER JOIN team_employee t ON e.employee_id = t.employee_id
    WHERE t.name = teamName
    GROUP BY t.name;

    UPDATE team
    SET monthly_cost = @nc
    WHERE team.name = teamName;
END //
DELIMITER ;