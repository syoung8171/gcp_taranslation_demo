package com.trans.agent.util;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;


/**
 * The Class DBConnector.
 *
 * @author
 * @version 1.0
 * @see <pre>
 * (Reference Information)
 * http://blumorning0227.tistory.com/87
 * http://blog.naver.com/PostView.nhn?blogId=seogardener&logNo=140066691766
 * -----------  --------  ---------------------------
 *
 *
 * </pre>
 * @since 2017. 05. 09
 */

public class DBConn {
	//private static Logger logger = LoggerFactory.getLogger(.class);

	// DB 타입
	public static final int DBTYPE_MYSQL 	= 1;
	public static final int DBTYPE_MSSQL	= 2;
	public static final int DBTYPE_ORACLE 	= 3;

	private DataSource dataSource;

	private final int DB_TYPE;
	private final String DRIVER_CLASS_NAME;
	private final String DB_URL;
	private final String DB_USERNAME;
	private final String DB_PASSWD;

	public DBConn(int DB_TYPE, String DB_URL, String DB_USERNAME, String DB_PASSWD) {
		this.DB_TYPE = DB_TYPE;
		this.DRIVER_CLASS_NAME = getDbtypeToString();
		this.DB_URL = DB_URL;
		this.DB_USERNAME = DB_USERNAME;
		this.DB_PASSWD = DB_PASSWD;
		this.dataSource = setupDataSource();
	}

	private DataSource setupDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName(DRIVER_CLASS_NAME);
		dataSource.setUsername(DB_USERNAME);
		dataSource.setPassword(DB_PASSWD);
		dataSource.setUrl(getUrl());

		//dataSource.setDefaultAutoCommit(false);
		dataSource.setMaxIdle(10);
		dataSource.setMaxWaitMillis(10000);
		dataSource.setValidationQuery(getValidationQuery());
		dataSource.setTestOnBorrow(true);
		dataSource.setTestWhileIdle(true);
		dataSource.setTimeBetweenEvictionRunsMillis(3000);
		//dataSource.setMinEvictableIdleTimeMillis(10000);

		/*
		  	maxActive : 커넥션 풀이 제공할 최대 커넥션 개수
			maxIdle : 사용되지 않고 풀에 저장될 수 있는 최대 커넥션 개수. 음수일 경우 제한이 없다.
			maxWait : whenExhaustedAction 속성의 값이 1일 때 사용되는 대기 시간. 단위는 1/1000초이며, 0 보다 작을 경우 무한히 대기한다.
			testOnBorrow : true일 경우 커넥션 풀에서 커넥션을 가져올 때 커넥션이 유효한지의 여부를 검사한다.
			testWhileIdle : true일 경우 비활성화 커넥션을 추출할 때 커넥션이 유효한지의 여부를 검사해서 유효하지 않은 커넥션은 풀에서 제거한다.
			timeBetweenEvctionRunsMillis : 사용되지 않은 커넥션을 추출하는 쓰레드의 실행 주기를 지정한다. 양수가 아닐 경우 실행되지 않는다. 단위는 1/1000 초이다.
			minEvictableIdleTimeMillis : 사용되지 않는 커넥션을 추출할 때 이 속성에서 지정한 시간 이상 비활성화 상태인 커넥션만 추출한다. 양수가 아닌 경우 비활성화된 시간으로는 풀에서 제거되지 않는다. 시간 단위는 1/1000초이다.
		 */
		return dataSource;
	}

	public void close() throws SQLException {
		BasicDataSource basicDataSource = (BasicDataSource) dataSource;
		basicDataSource.close();
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	private String getValidationQuery(){
		//useUnicode=true&characterEncoding=utf8&

		String query = null;
		if(DB_TYPE == 1){
			query = "select 1";
		}
		else if(DB_TYPE == 2){
			query = "select 1";
		}
		else if(DB_TYPE == 3){
			query = "select 1 from dual";
		}

		return query;
	}

	private String getUrl(){
		String url = DB_URL;
		int index = url.indexOf("?");
		String appendChar = (index > -1)?"&":"?";

		if(DB_TYPE == 1){
			url += appendChar+"zeroDateTimeBehavior=convertToNull&autoReconnect=true";
		}
		else if(DB_TYPE == 2){
			url += appendChar+"autoReconnect=true";
		}
		else if(DB_TYPE == 3){
//			url += appendChar+"autoReconnect=true";
		}

		return url;
	}

	private String getDbtypeToString() {
		switch (DB_TYPE) {
		case DBConn.DBTYPE_MYSQL:
			return "com.mysql.jdbc.Driver";
		case DBConn.DBTYPE_MSSQL:
			return "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		case DBConn.DBTYPE_ORACLE:
			return "oracle.jdbc.driver.OracleDriver";
		}
		return null;
	}
	/*
	 * public static void printDataSourceStats(DataSource ds) throws
	 * SQLException { BasicDataSource bds = (BasicDataSource) ds; if
	 * (log.isInfoEnabled()) { log.info("MAX ACTIVE : " + bds.getMaxActive());
	 * log.info("MAX IDLE : " + bds.getMaxIdle()); log.info("ACTIVE : " +
	 * bds.getNumActive()); log.info("IDLE : " + bds.getNumIdle()); } }
	 */
}
