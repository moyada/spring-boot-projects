package com.xyk.util;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * 综治队伍主要数据项
 */
public class DBPageRequest extends PageRequest {

    private Map<String, Object> params;

    public DBPageRequest(int page, int size) {
        super(page, size);
    }

    public DBPageRequest(int page, int size, Sort sort) {
        super(page, size);
    }

    public DBPageRequest(int page, int size, Sort.Direction direction, String... properties) {
        super(page, size, direction, properties);
    }


    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public DBPageRequest(Map<String, Object> params, int page, int size ) {
        super(page, size);
        this.params = params;
    }

    public DBPageRequest(Map<String, Object> params, int page, int size, Sort sort) {
        super(page, size, sort);
        this.params = params;
    }
}