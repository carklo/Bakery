/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.bakery.ejbs;

import co.edu.uniandes.csw.bakery.api.ICategoryLogic;
import co.edu.uniandes.csw.bakery.entities.CategoryEntity;
import co.edu.uniandes.csw.bakery.persistence.CategoryPersistence;
import co.edu.uniandes.csw.bakery.entities.ProductEntity;
import co.edu.uniandes.csw.bakery.api.IProductLogic;
import co.edu.uniandes.csw.bakery.exceptions.BusinessLogicException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 * @generated
 */
@Stateless
public class CategoryLogic implements ICategoryLogic {

    @Inject private CategoryPersistence persistence;


    @Inject private IProductLogic productLogic;

    /**
     * Obtiene el número de registros de Category.
     *
     * @return Número de registros de Category.
     * @generated
     */
    public int countCategorys() {
        return persistence.count();
    }

    /**
     * Obtiene la lista de los registros de Category.
     *
     * @return Colección de objetos de CategoryEntity.
     * @generated
     */
    @Override
    public List<CategoryEntity> getCategorys() {
        return persistence.findAll();
    }

    /**
     * Obtiene la lista de los registros de Category indicando los datos para la paginación.
     *
     * @param page Número de página.
     * @param maxRecords Número de registros que se mostraran en cada página.
     * @return Colección de objetos de CategoryEntity.
     * @generated
     */
    @Override
    public List<CategoryEntity> getCategorys(Integer page, Integer maxRecords) {
        return persistence.findAll(page, maxRecords);
    }

    /**
     * Obtiene los datos de una instancia de Category a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CategoryEntity con los datos del Category consultado.
     * @generated
     */
    public CategoryEntity getCategory(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Category en la base de datos.
     *
     * @param entity Objeto de CategoryEntity con los datos nuevos
     * @return Objeto de CategoryEntity con los datos nuevos y su ID.
     * @throws co.edu.uniandes.csw.bakery.exceptions.BusinessLogicException
     * @generated
     */
    @Override
    public CategoryEntity createCategory(CategoryEntity entity)throws BusinessLogicException {
         for(CategoryEntity ae:this.getCategorys()){
       if(ae.getName().equals(entity.getName()))
           throw new BusinessLogicException("el nombre de Category ya existe");
         }
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Category.
     *
     * @param entity Instancia de CategoryEntity con los nuevos datos.
     * @return Instancia de CategoryEntity con los datos actualizados.
     * @generated
     */
    @Override
    public CategoryEntity updateCategory(CategoryEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Category de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @generated
     */
    @Override
    public void deleteCategory(Long id) {
        persistence.delete(id);
    }
  

    /**
     * Obtiene una colección de instancias de ProductEntity asociadas a una
     * instancia de Category
     *
     * @param categoryId Identificador de la instancia de Category
     * @return Colección de instancias de ProductEntity asociadas a la instancia de Category
     * @generated
     */
    @Override
    public List<ProductEntity> listProducts(Long categoryId) {
        return getCategory(categoryId).getProducts();
    }

    /**
     * Obtiene una instancia de ProductEntity asociada a una instancia de Category
     *
     * @param categoryId Identificador de la instancia de Category
     * @param productsId Identificador de la instancia de Product
     * @generated
     */
    @Override
    public ProductEntity getProducts(Long categoryId, Long productsId) {
        List<ProductEntity> list = getCategory(categoryId).getProducts();
        ProductEntity productsEntity = new ProductEntity();
        productsEntity.setId(productsId);
        int index = list.indexOf(productsEntity);
        if (index >= 0) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Asocia un Product existente a un Category
     *
     * @param categoryId Identificador de la instancia de Category
     * @param productsId Identificador de la instancia de Product
     * @return Instancia de ProductEntity que fue asociada a Category
     * @generated
     */
    @Override
    public ProductEntity addProducts(Long categoryId, Long productsId) {
        CategoryEntity categoryEntity = getCategory(categoryId);
        ProductEntity productsEntity = productLogic.getProduct(productsId);
        productsEntity.setCategory(categoryEntity);
        return productsEntity;
    }

    /**
     * Remplaza las instancias de Product asociadas a una instancia de Category
     *
     * @param categoryId Identificador de la instancia de Category
     * @param list Colección de instancias de ProductEntity a asociar a instancia de Category
     * @return Nueva colección de ProductEntity asociada a la instancia de Category
     * @generated
     */
    @Override
    public List<ProductEntity> replaceProducts(Long categoryId, List<ProductEntity> list) {
        CategoryEntity categoryEntity = getCategory(categoryId);
        List<ProductEntity> productList = productLogic.getProducts();
        for (ProductEntity product : productList) {
            if (list.contains(product)) {
                product.setCategory(categoryEntity);
            } else {
                if (product.getCategory() != null && product.getCategory().equals(categoryEntity)) {
                    product.setCategory(null);
                }
            }
        }
        categoryEntity.setProducts(list);
        return categoryEntity.getProducts();
    }

    /**
     * Desasocia un Product existente de un Category existente
     *
     * @param categoryId Identificador de la instancia de Category
     * @param productsId Identificador de la instancia de Product
     * @generated
     */
    @Override
    public void removeProducts(Long categoryId, Long productsId) {
        ProductEntity entity = productLogic.getProduct(productsId);
        entity.setCategory(null);
    }
}
