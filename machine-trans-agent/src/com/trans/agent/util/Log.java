package com.trans.agent.util;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

/**
 * The Class Log.
 *
 * @author
 * @version 1.0
 * @see <pre>
 * (Modification Information)
 *
 * ---------------  ----------------  ---------------------------
 *
 * </pre>
 * @since 2017. 3. 3
 */

public class Log{
	static {
		RuntimeMXBean rt = ManagementFactory.getRuntimeMXBean();
		String pid = rt.getName().replaceAll("@.*", "");;
		MDC.put("PID", pid);
		//MDC.put("ice.root", "c:");
	}

	private String name=null;
	private final String PATTERN = "[%d{HH:mm:ss.SSS}][%-5level][%X{PID}]%logger{0}.%M> %msg%n";
	private static final String ROOTDIR = "c:/test/log";

	/** The instance. */
    private volatile static Log instance;

    /**
  	 * Instantiates a new AmidbAction.
  	 */
	public Log() {}
	public Log(String name) {
		this.name = name;
	}

	/**
	 * Gets the single instance of Log.
	 *
	 * @return single instance of Log
	 */
    public static Logger createLog() {
		if(instance == null) {
			return createLog("plugin");
		}
		return LoggerFactory.getLogger("plugin");
	}

    public static Logger createLog(String name) {
    	if(instance == null) {
			synchronized (Log.class) {
				if(instance == null) {
					instance = new Log(name);
					instance.set(null);
				}
			}
		}
		return LoggerFactory.getLogger(name);
	}

    public static Logger createLog(Class<?> c) {
    	if(instance == null) {
			synchronized (Log.class) {
				if(instance == null) {
					instance = new Log(c.getSimpleName());
					instance.set(null);
				}
			}
		}
		return LoggerFactory.getLogger(c.getSimpleName());
	}

    public static Logger createLog(String path, String name) {
    	if(instance == null) {
			synchronized (Log.class) {
				if(instance == null) {
					instance = new Log(name);
					instance.set(path);
				}
			}
		}
		return  LoggerFactory.getLogger(name);
	}

    public static String getLogPath(String dir, String name){
		String path = dir+"/"+name+".log";
		return path;
	}

	public static String getLogPath(String name){
		return getLogPath(ROOTDIR, name);
	}

	public void set(String path) {
		LoggerContext loggerContext = null;
		try {
			loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
		}
		catch (Exception e) {
			System.out.println(e);
			return;
		}

		String pluginPath = (path==null ? getLogPath(name): getLogPath(path, name));
		String netPath = getLogPath("net");

	    // Appender 생성
	    RollingFileAppender rfAppender = createRollingFileAppender(loggerContext, pluginPath);
	    RollingFileAppender net_rfAppender = createRollingFileAppender(loggerContext, netPath);

	    // 해당 enum의 이름으로 Logger를 만들고 Appender를 연결해준다.
	    ch.qos.logback.classic.Logger logbackLogger = loggerContext.getLogger(name);
	    logbackLogger.setLevel(Level.DEBUG);
	    logbackLogger.addAppender(rfAppender);

	    ch.qos.logback.classic.Logger plogbackLogger = loggerContext.getLogger("com.padapter");
	    plogbackLogger.setLevel(Level.DEBUG);
	    plogbackLogger.setAdditive(false);
	    plogbackLogger.addAppender(rfAppender);

	    ch.qos.logback.classic.Logger edplogbackLogger = loggerContext.getLogger("com.edp");
	    edplogbackLogger.setLevel(Level.DEBUG);
	    edplogbackLogger.setAdditive(false);
	    edplogbackLogger.addAppender(rfAppender);

	    ch.qos.logback.classic.Logger corelogbackLogger = loggerContext.getLogger("com.icecore");
	    corelogbackLogger.setLevel(Level.DEBUG);
	    corelogbackLogger.setAdditive(false);
	    corelogbackLogger.addAppender(rfAppender);

	    //net 파일
	    ch.qos.logback.classic.Logger netty_logbackLogger = loggerContext.getLogger("io.netty");
	    netty_logbackLogger.setLevel(Level.WARN);
	    netty_logbackLogger.setAdditive(false);
	    netty_logbackLogger.addAppender(net_rfAppender);

	    ch.qos.logback.classic.Logger moquette_logbackLogger = loggerContext.getLogger("io.moquette");
	    moquette_logbackLogger.setLevel(Level.WARN);
	    moquette_logbackLogger.setAdditive(false);
	    moquette_logbackLogger.addAppender(net_rfAppender);

	    ch.qos.logback.classic.Logger messageLogger_logbackLogger = loggerContext.getLogger("messageLogger");
	    messageLogger_logbackLogger.setLevel(Level.WARN);
	    messageLogger_logbackLogger.setAdditive(false);
	    messageLogger_logbackLogger.addAppender(net_rfAppender);

	    ch.qos.logback.classic.Logger digitalpetri_logbackLogger = loggerContext.getLogger("com.digitalpetri");
	    digitalpetri_logbackLogger.setLevel(Level.WARN);
	    digitalpetri_logbackLogger.setAdditive(false);
	    digitalpetri_logbackLogger.addAppender(net_rfAppender);

	    ch.qos.logback.classic.Logger ghgande_logbackLogger = loggerContext.getLogger("com.ghgande");
	    ghgande_logbackLogger.setLevel(Level.WARN);
	    ghgande_logbackLogger.setAdditive(false);
	    ghgande_logbackLogger.addAppender(net_rfAppender);
	}

	private RollingFileAppender createRollingFileAppender(LoggerContext loggerContext, String filepath) {
		RollingFileAppender rfAppender = new RollingFileAppender();
	    rfAppender.setContext(loggerContext);
	    rfAppender.setFile(filepath);

	    // Policy 설정
	    TimeBasedRollingPolicy rollingPolicy = new TimeBasedRollingPolicy();
	    rollingPolicy.setContext(loggerContext);
	    rollingPolicy.setParent(rfAppender);
	    rollingPolicy.setFileNamePattern(filepath + ".%d{yyyy-MM-dd}");
	    //rollingPolicy.setMaxHistory(90);
	    rollingPolicy.start();

	    // Encoder 설정
	    PatternLayoutEncoder encoder = new PatternLayoutEncoder();
	    encoder.setContext(loggerContext);
	    encoder.setPattern(PATTERN);
	    encoder.start();

	    // Appender에 Policy와 Encoder를 연결
	    rfAppender.setEncoder(encoder);
	    rfAppender.setRollingPolicy(rollingPolicy);
	    rfAppender.start();

	    return rfAppender;
	}

	/*
	private String get(int i){
		StackTraceElement[] a = new Throwable().getStackTrace();
		String className = a[i].getClassName();
		int lastidx = className.lastIndexOf('.');
		if(lastidx < 0){
			lastidx = className.lastIndexOf('$');
		}
		int lastLen = className.length() - lastidx-1;
		return StringUtil.inst().right(className, lastLen)+"."+a[i].getMethodName()+"> ";
	}

	public void warn(String s){
		log.warn(get(2) + s);
	}

	public void info(String s){
		log.info(get(2) + s);
	}

	public void debug(String s){
		log.debug(get(2) + s);
	}

	public void error(String s){
		log.error(get(2) + s);
	}

	public void error(Exception e){
		log.error(get(2) + e.getClass().getSimpleName(), e);
	}

	public void error(String s, Exception e){
		log.error(get(2) + s, e);
	}

	public void error(Throwable t){
		log.error(get(2) + "Throwable", t);
	}
	*/
}
