package com.readapp.backend.serviceImpls;

import com.readapp.backend.dto.ArticleResponse;
import com.readapp.backend.services.RecommendService;
import org.springframework.stereotype.Service;

@Service("recommendService")
public class RecommendServiceImpl implements RecommendService {

    @Override
    public ArticleResponse getRecommendArticle(Long uid) throws Exception {
        return null;
    }
}
