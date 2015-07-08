package de.janhektor.community.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class is used for logging activities of the plugin.
 * A .txt file in the DataFolder of the plugin is used as log file
 * <p>
 * 'import static de.janhektor.community.utils.Log.*;' to use this class
 * <p>
 * author Scriptim
 *
 * @version 08.07.2015
 */

public final class Log{

  private static String prefix;
  private static boolean doLog;
  private static FileWriter fw;
  private static BufferedWriter bw;
  private static DateFormat dateFormat;
  private static Date date = new Date();
  private static String dateTime;

  private Log() throws IOException{
  }

  static{
    prefix = de.janhektor.community.Main.getInstance().getDescription().getName();
    doLog = true; //TODO: get from config
    try{
      fw = new FileWriter(de.janhektor.community.Main.getInstance().getDataFolder() + "/log.txt", true);
      bw = new BufferedWriter(fw);
    }catch(IOException ex){
      ex.printStackTrace();
    }
    dateFormat = new SimpleDateFormat("yy/MM/dd HH:mm:ss");
    date = new Date();
    dateTime = "[" + dateFormat.format(date) + "]";
  }

  /**
   * log a message with level 'INFO'
   *
   * @param msg   the message to be logged
   * @param print wether 'msg' schould be printed in console
   * @throws IOException could not close BufferedWriter
   */
  public static void logInfo(String msg, boolean print) throws IOException{
    if(doLog){
      bw.write(dateTime + " INFO: " + msg);
      bw.newLine();
      if(print){
        System.out.println(prefix + "INFO: \"" + msg + "\"");
      }
    }
    bw.close();
  }

  /**
   * log a message with level 'WARNING'
   *
   * @param msg   the message to be logged
   * @param print wether 'msg' schould be printed in console
   * @throws IOException could not close BufferedWriter
   */
  public static void logWarning(String msg, boolean print) throws IOException{
    if(doLog){
      bw.write(dateTime + " WARNING: " + msg);
      bw.newLine();
      if(print){
        System.err.println(prefix + "WARNING: \"" + msg + "\"");
      }
    }
    bw.close();
  }

  /**
   * log a message with level 'CONFIG'
   *
   * @param msg   the message to be logged
   * @param print wether 'msg' schould be printed in console
   * @throws IOException could not close BufferedWriter
   */
  public static void logConfig(String msg, boolean print) throws IOException{
    if(doLog){
      bw.write(dateTime + " CONFIG: " + msg);
      bw.newLine();
      if(print){
        System.out.println(prefix + "CONFIG: \"" + msg + "\"");
      }
    }
    bw.close();
  }

  /**
   * calls logInfo(msg, false)
   */
  public static void logInfo(String msg) throws IOException{
    logInfo(msg, false);
  }

  /**
   * calls logWarning(msg, false)
   */
  public static void logWarning(String msg) throws IOException{
    logWarning(msg, false);
  }

  /**
   * calls logConfig(msg, false)
   */
  public static void logConfig(String msg) throws IOException{
    logConfig(msg, false);
  }

}
