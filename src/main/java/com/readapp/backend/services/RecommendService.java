package com.readapp.backend.services;

import com.readapp.backend.dto.ArticleResponse;

public interface RecommendService {
    ArticleResponse getRecommendArticle(Long uid) throws Exception;
}
