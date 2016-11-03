package cn.ymq.util.split;

import javax.servlet.http.HttpServletRequest;

public class SplitPageUtil {
	private String column;
	private String keyWord;
	private Integer currentPage;
	private Integer lineSize = 5;

	public SplitPageUtil(HttpServletRequest request, String column) {
		this.column = column;
		this.setCol(request.getParameter("col"));
		this.setKw(request.getParameter("kw"));
		this.setCp(request.getParameter("cp"));
		this.setLs(request.getParameter("ls"));
	}

	public void setCol(String col) {
		if (!(col == null || "".equals(col) || "null".equalsIgnoreCase(col))) {
			this.column = col;
		}
	}

	public void setKw(String kw) {
		if ("".equals(kw)) {
			this.keyWord = null;
		} else if ("null".equalsIgnoreCase(kw)) {
			this.keyWord = null;
		} else {
			this.keyWord = kw;
		}
	}

	public void setCp(String cp) {
		try {
			this.currentPage = Integer.parseInt(cp);
		} catch (Exception e) {
			this.currentPage = 1;
		}
	}

	public void setLs(String lineSize) {
		try {
			this.lineSize = Integer.parseInt(lineSize);
		} catch (Exception e) {
		}
	}

	public String getColumn() {
		return this.column;
	}

	public String getKeyWord() {
		return this.keyWord;
	}

	public Integer getCurrentPage() {
		return this.currentPage;
	}

	public Integer getLineSize() {
		return this.lineSize;
	}

	@Override
	public String toString() {
		return "splitPageUtil [column=" + column + ", keyWord=" + keyWord + ", currentPage=" + currentPage
				+ ", lineSize=" + lineSize + "]";
	}

}
