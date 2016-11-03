package cn.ymq.util.encrypt;

public class MyPasswordEncrypt {
	private static final String SALT = "bWxkbmphdmE=";
	
	public static String encryptPassword(String password){
		return new MD5Code().getMD5ofStr(password+"({"+SALT+"})");
	}
}
