package com.readapp.backend.serviceImpls;

import com.qiniu.util.Auth;
import com.readapp.backend.config.QiniuConfig;
import com.readapp.backend.services.FileService;
import org.springframework.stereotype.Service;

@Service("fileService")
public class FileServiceImpl implements FileService {
    @Override
    public String getToken() {
        System.out.println(QiniuConfig.BUCKET);
        Auth auth = Auth.create(QiniuConfig.ACCESS_KEY, QiniuConfig.ACCESS_SECRET);
        String upToken = auth.uploadToken(QiniuConfig.BUCKET);
        return upToken;
    }
}
