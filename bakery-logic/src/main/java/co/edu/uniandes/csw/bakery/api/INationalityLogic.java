/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.bakery.api;

import co.edu.uniandes.csw.bakery.entities.NationalityEntity;
import java.util.List;

/**
 *
 * @author santi
 */
public interface INationalityLogic {
    public int countNationalitys();
    public List<NationalityEntity> getNationalitys();
    public List<NationalityEntity> getNationalitys(Integer page, Integer maxRecords);
    public NationalityEntity getNationality(Long id);
    public NationalityEntity createNationality(NationalityEntity entity);
    public NationalityEntity updateNationality(NationalityEntity entity);
    public void deleteNationality(Long id);
}
