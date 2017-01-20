package com.tms.client;

import java.util.List;
import com.tms.model.ArticleCatalog;
import com.tms.model.ArticleCatalogExample;

public interface ArticleCatalogMapper {
    int countByExample(ArticleCatalogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ArticleCatalog record);

    int insertSelective(ArticleCatalog record);

    List<ArticleCatalog> selectByExample(ArticleCatalogExample example);

    ArticleCatalog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleCatalog record);

    int updateByPrimaryKey(ArticleCatalog record);
}