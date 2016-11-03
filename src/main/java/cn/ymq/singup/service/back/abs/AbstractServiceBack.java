package cn.ymq.singup.service.back.abs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

public abstract class AbstractServiceBack {
	
	protected Map<String,Object> HandleParams(String column,String keyWord,int currentPage,int lineSize) {
		Map<String,Object> map = new HashMap<>();
		if("".equals(column) || column == null || "null".equalsIgnoreCase(column)){
			
		} else {
			map.put("column", column);
		}
		if("".equals(keyWord) || keyWord == null || "null".equalsIgnoreCase(keyWord)){
			
		} else {
			map.put("keyWord", "%"+keyWord+"%");
		}
		if((currentPage-1)*lineSize < 0){
			map.put("start", 0);
		} else {
			map.put("start", (currentPage-1)*lineSize);
		}
		map.put("lineSize", lineSize > 0 ? lineSize : 5);
			
		return map;
	}

}
