/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bakery.ejbs;

import co.edu.uniandes.csw.bakery.api.INationalityLogic;
import co.edu.uniandes.csw.bakery.entities.NationalityEntity;
import co.edu.uniandes.csw.bakery.persistence.NationalityPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author santi
 */
public class NationalityLogic implements INationalityLogic{
    
    @Inject private NationalityPersistence persistence;

    @Override
    public int countNationalitys() {
        return persistence.count();
    }

    @Override
    public List<NationalityEntity> getNationalitys() {
        return persistence.findAll();
    }

    @Override
    public List<NationalityEntity> getNationalitys(Integer page, Integer maxRecords) {
         return persistence.findAll(page, maxRecords);
    }

    @Override
    public NationalityEntity getNationality(Long id) {
        return persistence.find(id);
    }

    @Override
    public NationalityEntity createNationality(NationalityEntity entity) {
        persistence.create(entity);
        return entity;
    }

    @Override
    public NationalityEntity updateNationality(NationalityEntity entity) {
        return persistence.update(entity);
    }

    @Override
    public void deleteNationality(Long id) {
        persistence.delete(id);
    }
    
}
