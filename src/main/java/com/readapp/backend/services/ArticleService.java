package com.readapp.backend.services;

import com.readapp.backend.models.Article;
import java.util.List;

public interface ArticleService {
    List<Article> getLikedArticles(Long uid, int offset, int limit);
}
