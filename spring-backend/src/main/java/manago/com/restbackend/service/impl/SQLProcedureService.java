package manago.com.restbackend.service.impl;

import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

@Service
public class SQLProcedureService {

    @PersistenceContext
    private EntityManager entityManager;

    public void updateTeam(String teamName) {

        //"login" this is the name of your procedure
        System.out.println(teamName);
        StoredProcedureQuery query = entityManager.createStoredProcedureQuery("calcforteam");

        //Declare the parameters in the same order
        query.registerStoredProcedureParameter(1, String.class, ParameterMode.IN);

        //Pass the parameter values
        query.setParameter(1, teamName);
        System.out.println(query);
        //Execute query
        query.execute();
    }
}
