package suporte;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Gerador {
    public static String dataHraParaArquivo(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyyMMhhmmss").format(ts);
    }
}
