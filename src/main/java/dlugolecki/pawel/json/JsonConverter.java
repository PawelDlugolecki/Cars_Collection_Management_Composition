package dlugolecki.pawel.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dlugolecki.pawel.exceptions.ExceptionCode;
import dlugolecki.pawel.exceptions.MyException;

import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

public abstract class JsonConverter<T> {

    private final String jsonFilename;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];

    public JsonConverter(String jsonFilename) {
        this.jsonFilename = jsonFilename;
    }

    public void toJson(final T element) {
        try (FileWriter fileWriter = new FileWriter(jsonFilename)) {
            if (element == null) {
                throw new NullPointerException("Element is null");
            }
            gson.toJson(element, fileWriter);
        } catch (Exception e) {
            throw new MyException(ExceptionCode.JSON, "TO JSON EXCEPTION CONVERSION");
        }
    }

    public Optional<T> fromJson() {
        try (FileReader fileReader = new FileReader(jsonFilename)) {
            return Optional.of(gson.fromJson(fileReader, type));
        } catch (Exception e) {
            e.printStackTrace();
            throw new MyException(ExceptionCode.JSON, "FROM JSON EXCEPTION CONVERSION");
        }
    }
}