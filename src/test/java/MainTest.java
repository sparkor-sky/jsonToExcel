import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wuxi.tools.JsonFileToExcel;
import org.junit.Test;

public class MainTest {

    @Test
    public void testTruncationDir(){
        System.out.println(JsonFileToExcel.truncationDir("/user/wuxi/sdf.txt"));
    }

    @Test
    public void jsonParserTest(){
        JsonParser parser = new JsonParser();
        String data = "{\"id\":\"100616\",\"reward_id\":\"100000\",\"reward_setting_id\":\"100005\",\"questionnaire_name\":\"'CNRS'\",\"customer\":\"'CTR'\",\"quantity\":\"5\",\"phone\":\"17876596389\",\"reward_phone\":\"17876596389\",\"state\":\"3\",\"reason\":\"''\",\"distributed_time\":\"'2020-01-03 11:13:06'\",\"exchanged_time\":\"1578049952938\",\"db_update_time\":\"'2019-03-04 09:41:08'\",\"db_create_time\":\"''\"}";
        JsonObject object = parser.parse(data).getAsJsonObject();
    }
}
