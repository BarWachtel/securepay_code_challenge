package securepay.model.gsonloader;

import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;

public class GsonLoader {
    public static <T> T fromJson(InputStream jsonFileInputStream, Class<T> ofClass) {
        return new Gson().fromJson(new InputStreamReader(jsonFileInputStream), ofClass);
    }
}
