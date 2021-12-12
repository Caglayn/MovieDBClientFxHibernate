package utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LogUtil {
    private static LogUtil instance;

    private Logger logger;

    private LogUtil() {
    }

    public static LogUtil getInstance(){
        if(instance == null){
            instance = new LogUtil();
        }
        return instance;
    }

    public Logger getLogger(){
        if (this.logger == null){
            this.logger = LogManager.getLogger(this.getClass());
        }
        return this.logger;
    }

    public void logInfo(String log){
        this.getLogger().info(log);
    }

    public void logError(String log){
        this.getLogger().error(log);
    }

}
