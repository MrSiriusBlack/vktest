import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import com.vk.api.sdk.queries.users.UserField;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception{
        // получаем список id пользователей для рассылки
        ArrayList<String> ids = FileHelper.readArrayFromFile("путь_к_файлу");
        // подключаемся к vk
        TransportClient transportClient = HttpTransportClient.getInstance();
        VkApiClient vk = new VkApiClient(transportClient);
        // авторизация пользователя. Заменить 123, Secret_key и Some_code своими данными
        UserAuthResponse authResponse = vk.oauth().userAuthorizationCodeFlow(123, "Secret_key",
                "https://oauth.vk.com/blank.html", "Some_code").execute();
        UserActor actor = new UserActor(authResponse.getUserId(), authResponse.getAccessToken());
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 0; i < Math.min(20, ids.size()); ++i) {
            String id = ids.get(i);

            List<UserXtrCounters> users = vk.users().get(actor)
                    .userIds(id)
                    .fields(UserField.SEX)
                    .execute();
            String message;
            switch (users.get(0).getSex()) {
                case MALE:
                    message = String.format("%s\t%s, как дела", id, "Привет");
                    vk.messages().send(actor)
                            .userId(Integer.parseInt(id))
                            .randomId((int)(Math.random() * Integer.MAX_VALUE))
                            .message(message);
                    data.add(message);
                    break;
                case FEMALE:
                    message = String.format("%s\t%s, как дела", id, "Здравствуйте");
                    vk.messages().send(actor)
                            .userId(Integer.parseInt(id))
                            .randomId((int)(Math.random() * Integer.MAX_VALUE))
                            .message(message);
                    data.add(message);
            }
        }
        FileHelper.writeArrayToFile("outputFile.txt", data);
    }
}
