package com.readapp.backend.serviceImpls;

import com.readapp.backend.dao.ArticleDao;
import com.readapp.backend.dto.ArticleResponse;
import com.readapp.backend.models.Article;
import com.readapp.backend.services.RecommendService;
import com.readapp.backend.utils.RNG;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    ArticleDao articleDao;

    @Override
    public ArticleResponse getRecommendArticle(Long uid) throws Exception {

        long count = articleDao.count();

        if (count < 1L) return null;

        Article article = null;
        Random random = new Random();

        while (article == null) {
            article = articleDao.getOne(RNG.nextLong(random, count) + 1);
        }

        return new ArticleResponse(article);
    }
}
