package com.lizard.web.controller;

import com.lizard.web.response.Response;
import com.lizard.web.response.ResponseHelper;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author X
 */
@Api(value = "API测试管理", tags = "API测试管理")
@RestController("/api/test")
public class ApiTestController {

    /**
     * 导出API为Postman格式
     * postman不同版本格式的区别参照：https://blog.postman.com/travelogue-of-postman-collection-format-v2/
     * postman导入导出指导：https://learning.postman.com/docs/getting-started/importing-and-exporting-data
     *
     * @return 响应
     */
    public Response exportPostmanJson() {
        return ResponseHelper.ok();
    }
}
