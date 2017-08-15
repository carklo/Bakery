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
package co.edu.uniandes.csw.bakery.api;

import co.edu.uniandes.csw.bakery.entities.CategoryEntity;
import co.edu.uniandes.csw.bakery.entities.ProductEntity;
import co.edu.uniandes.csw.bakery.exceptions.BusinessLogicException;
import java.util.List;

public interface ICategoryLogic {
    public int countCategorys();
    public List<CategoryEntity> getCategorys();
    public List<CategoryEntity> getCategorys(Integer page, Integer maxRecords);
    public CategoryEntity getCategory(Long id);
    public CategoryEntity createCategory(CategoryEntity entity)throws BusinessLogicException; 
    public CategoryEntity updateCategory(CategoryEntity entity);
    public void deleteCategory(Long id);
    public List<ProductEntity> listProducts(Long categoryId);
    public ProductEntity getProducts(Long categoryId, Long productsId);
    public ProductEntity addProducts(Long categoryId, Long productsId);
    public List<ProductEntity> replaceProducts(Long categoryId, List<ProductEntity> list);
    public void removeProducts(Long categoryId, Long productsId);
}
