package com.roncoo.bjui.generator.custom;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import com.baomidou.mybatisplus.plugins.Page;

/**
 * 分页
 * 
 * @author wujing
 * @param <T>
 */
public final class PageUtil<T extends Serializable> implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(PageUtil.class);

	private PageUtil() {
	}

	public static <T extends Serializable> PageBjui<T> transform(Page<?> page, Class<T> classType) {
		PageBjui<T> pb = new PageBjui<>();
		try {
			pb.setList(copy(page.getRecords(), classType));
		} catch (Exception e) {
			logger.error("transform error", e);
		}
		pb.setPageCurrent(page.getCurrent());
		pb.setPageSize(page.getSize());
		pb.setTotalCount(page.getTotal());
		pb.setTotalPage(page.getPages());
		return pb;
	}

	/**
	 * @param source
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws InstantiationException
	 */
	public static <T> List<T> copy(List<?> source, Class<T> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
		if (source.size() == 0) {
			return Collections.emptyList();
		}
		List<T> res = new ArrayList<>(source.size());
		for (Object o : source) {
			T t = clazz.newInstance();
			BeanUtils.copyProperties(o, t);
			res.add(t);
		}
		return res;
	}

}
