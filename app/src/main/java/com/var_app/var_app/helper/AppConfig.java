
package com.var_app.var_app.helper;

public class AppConfig {

    private static String ROOT_URL = "https://www.bunlab.net/var/";
    // Server user login url
    public static String URL_LOGIN = ROOT_URL + "user_login.php";
    // Server user register url
    public static String URL_REGISTER = ROOT_URL + "user_register.php";

    public static String URL_BARCODE = ROOT_URL + "scan_barcode.php";

    public static String URL_SELL_CREATE = ROOT_URL + "deal_create.php";

    public static String URL_GET_TRASH_TYPE = ROOT_URL + "calculator.php";

    public static String URL_GET_BUY = ROOT_URL + "deal_view.php";
}
