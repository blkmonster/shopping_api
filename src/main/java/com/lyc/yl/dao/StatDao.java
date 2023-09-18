package com.lyc.yl.dao;

import java.util.List;
import java.util.Map;

public interface StatDao {
	@SuppressWarnings("rawtypes")
    List<Map> statUser();
	@SuppressWarnings("rawtypes")
    List<Map> statOrder();
	@SuppressWarnings("rawtypes")
    List<Map> statGoods();
}