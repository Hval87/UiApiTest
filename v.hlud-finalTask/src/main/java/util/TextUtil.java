package util;

import lombok.SneakyThrows;
import nl.flotsam.xeger.Xeger;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextUtil {

    public static final String PATTERN = "[0-9]+";

    public static String findIdMatches(String originalString) {
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(originalString);
        String result = null;
        if (matcher.find()) {
            result = matcher.group(0); //prints /{item}/
        }
        return result;
    }

    public static String generateRandomString() {
        Xeger xeger = new Xeger(String.format("([:word:]){%d}", Math.toIntExact(DataManager.getConfigValue("length_of_random_string"))));
        return xeger.generate();
    }

    public static String getIP() {
        InetAddress myIP = null;
        try {
            myIP = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return myIP.getHostAddress();
    }

    @SneakyThrows
    public static String getLogFromTXT(String txtPath) {
        String path=FileProvider.getTestPath(txtPath);
        Scanner in = new Scanner(new FileReader(FileProvider.getAbsolutePath(path)));
        StringBuilder sb = new StringBuilder();
        while (in.hasNext()) {
            sb.append(in.next());
        }
        in.close();
        return sb.toString().replace("[","(").replace("]",")").replace("\\","/");
    }
}

