package wyk;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;

public class ShellUtil {
	private static Logger logger = Logger.getLogger(ShellUtil.class);

	private static final String DEFAULTCHART = "UTF-8";
	private Connection conn = null;
	private Session session = null;
	private boolean loginSuccess;

	public ShellUtil(String ip, String userName, String userPwd)
			throws Exception {
		loginSuccess = login(ip, userName, userPwd);
		if (loginSuccess) {
			session = conn.openSession();
		}
	}

	public ShellUtil() {

	}

	/**
	 * 远程登录主机
	 * 
	 * @return
	 */
	public Boolean login(String ip, String userName, String userPwd) {
		boolean flg = false;
		try {
			conn = new Connection(ip);
			conn.connect();// 连接
			flg = conn.authenticateWithPassword(userName, userPwd);// 认证
		} catch (IOException e) {
			logger.error(e.getCause(), e);
		}
		return flg;
	}

	/**
	 * 远程执行shell或命令
	 * 
	 * @param cmd
	 * @return
	 */
	public String execute(String cmd) {
		String result = "";
		try {
			if (loginSuccess) {
				session = conn.openSession();// 打开一个会话
				session.execCommand(cmd);// 执行命令
				result = processStdout(session.getStdout(), DEFAULTCHART);
				// 如果为得到标准输出为空，说明脚本执行出错了
				if (StringUtils.isBlank(result)) {
					result = processStdout(session.getStderr(), DEFAULTCHART);
				}
			} else {
				result = "login failed";
			}
		} catch (IOException e) {
			logger.error(e.getCause(), e);
		} finally {
			close();
		}
		return result;
	}

	/**
	 * 处理运行结果
	 * 
	 * @param in
	 * @param charset
	 * @return
	 */
	private String processStdout(InputStream in, String charset) {
		InputStream stdout = new StreamGobbler(in);
		StringBuffer buffer = new StringBuffer();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(stdout, charset));
			String line = null;
			while ((line = br.readLine()) != null) {
				buffer.append(line + "\n");
			}
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getCause(), e);
		} catch (IOException e) {
			logger.error(e.getCause(), e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error(e.getCause(), e);
				}
			}
		}
		return buffer.toString();
	}

	public String executeSuccess(String cmd) {
		String result = "";
		try {
			if (loginSuccess) {
				session = conn.openSession();// 打开一个会话
				session.execCommand(cmd);// 执行命令
				result = processStdout(session.getStdout(), DEFAULTCHART);
			}
		} catch (IOException e) {
			logger.error(e.getCause(), e);
		} finally {
			close();
		}
		return result;
	}

	public void close() {
		if (conn != null) {
			conn.close();
		}
		if (session != null) {
			session.close();
		}
	}

	/**
	 * 调用Shell命令:
	 * 如果把命令放到一个String[]中时，必须把命令中每个部分作为一个元素存在String[]中，或者是把命令按照空格符分割得到的String[]
	 * 
	 * @param cmd
	 * @return 状态码和返回值
	 */
	public static String[] callCMD(String[] cmd) {
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		int status = -1;
		String line = null;
		BufferedReader in = null;
		try {
			process = runtime.exec(cmd);
			status = process.waitFor();
			in = new BufferedReader(new InputStreamReader(
					process.getInputStream()));
			while ((line = in.readLine()) != null) {
				line += line + "\n";
			}
		} catch (Exception e) {
			logger.error(e.getCause(), e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.error(e.getCause(), e);
				}
			}
			if (process != null) {
				process.destroy();
			}
		}
		return new String[] { "" + status, line };
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	public static void main(String[] args) throws Exception {
		ShellUtil rec = new ShellUtil("192.168.247.131", "root", "123456");
		// 执行命令
		logger.info(rec.execute("sh /tmp/create.sh wfy hadoop"));
	}
}
