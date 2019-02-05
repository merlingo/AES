/**
 * Created by yiğido on 22.12.2014.
 */
public interface Operationable {

    public Block exec(Block message);
    public void changeS_Box(int[] nsbox);
}
