import java.util.List;

/**
 * @author <Hoang Hua Hiep - s3979137>
 */

public interface ProcessManager <Type, Identifier> {
    void add(Type t);
    void delete(Identifier id);
    Type getOne(Identifier id);
    List<Type> getAll();
}
