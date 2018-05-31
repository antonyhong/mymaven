import java.util.UUID;

public abstract class AbsC {
    public static String unId = UUID.randomUUID().toString();

    abstract  void printUUid();
}
