package com.lyc.yl.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StatVo {
    private String[] columns = new String[0];
    @SuppressWarnings("rawtypes")
	private List<Map> rows = new ArrayList<>();

    public String[] getColumns() {
        return columns;
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    @SuppressWarnings("rawtypes")
	public List<Map> getRows() {
        return rows;
    }

    @SuppressWarnings("rawtypes")
	public void setRows(List<Map> rows) {
        this.rows = rows;
    }

    @SuppressWarnings("rawtypes")
	public void add(Map... r) {
        rows.addAll(Arrays.asList(r));
    }
}
