package commons;

import java.io.File;

public class GlobalConstants {
	public static final String USER_PAGE_URL = "https://demo.nopcommerce.com/";
	public static final String ADMIN_PAGE_URL = "https://admin-demo.nopcommerce.com/admin/";
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String UPLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "uploadFiles";
	public static final String DOWNLOAD_FILE_FOLDER = PROJECT_PATH + File.separator + "downloadFiles";
	public static final String BROWSER_LOG_FOLDER = PROJECT_PATH + File.separator + "browserLogs";
	public static final String DRAG_DROP_HTML5 = PROJECT_PATH + File.separator + "dragDropHTML5";
	public static final String AUTO_IT_SCRIPT = PROJECT_PATH + File.separator + "autoIT";
	public static final String REPORTNG_IMAGE_PATH = PROJECT_PATH + File.separator + "reportNGImage" + File.separator;
	public static final String DB_URL = "";
	public static final String DB_DEV_USER = "";
	public static final String DB_DEV_PASS = "";
	public static final int LONG_TIMEOUT = 30;
	public static final int SHORT_TIMEOUT = 5;
	public static final String EXTENT_PATH_V2 = PROJECT_PATH + File.separator + "extentReport" + File.separator + "ExtentReportV2.html";
	public static final String EXTENT_PATH_V5 = PROJECT_PATH + File.separator + "extentReport" + File.separator + "ExtentReportV5.html";
	public static final String JAVA_VERSION = System.getProperty("java.version");
	public static final String BROWSER_EXTENSION_PATH = PROJECT_PATH + File.separator + "browserExtensions" + File.separator;
	public static final String BROWSER_LOG_PATH = PROJECT_PATH + File.separator + "browserLogs" + File.separator;
	public static final String RESOURCE_PATH = PROJECT_PATH + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator;

}
