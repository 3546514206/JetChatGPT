package edu.zjnu.gpt.service;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import edu.zjnu.gpt.core.OpenAiClient;
import edu.zjnu.gpt.entity.chat.ChatChoice;
import edu.zjnu.gpt.entity.chat.ChatCompletion;
import edu.zjnu.gpt.entity.chat.ChatCompletionResponse;
import edu.zjnu.gpt.entity.chat.Message;
import edu.zjnu.gpt.function.KeyRandomStrategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author: 杨海波
 * @date: 2023-04-24 16:41:40
 * @description: GptService
 */
public class GptService {


    public String doChat(String request) {
        String response = null;
        OpenAiClient openAiClient = OpenAiClient.builder().apiKey(decryptKeys())
                //自定义key的获取策略：默认KeyRandomStrategy
                .keyStrategy(new KeyRandomStrategy()).build();

        //聊天模型：gpt-3.5
        Message message = Message.builder().role(Message.Role.USER).content(request).build();
        ChatCompletion chatCompletion = ChatCompletion.builder().messages(Collections.singletonList(message)).build();
        ChatCompletionResponse chatCompletionResponse = openAiClient.chatCompletion(chatCompletion);

        for (ChatChoice choice : chatCompletionResponse.getChoices()) {
            if (choice.getMessage() != null) {
                response = choice.getMessage().getContent();
                break;
            }
        }

        return response;
    }


    /**
     * 不加密会被封
     *
     * @return
     */
    private List<String> decryptKeys() {
        // key：AES模式下，key必须为16位
        String symmetricalKey = "plikajensixjkeos";
        // iv：偏移量，ECB模式不需要，CBC模式下必须为16位
        String iv = "qscvbnjiokhtsgeu";
        // 密文
        String encryptKey = "/7d9jdUun1LoIhgDG9kbJ5Kdu5x+ovTf2ajmjiZB+OIS1jNY0nbbLHWTePo158ZCcUirz7KTrJbbT41WzkzKFg==," + "P7O0whjkpyv2GWFk2HSXErqZgQzgvRiA/Xfc77S/UcbNtYkW0zh7ktEloZiQIo7oMJ7q3RfSjKemTYl19xTjKg==";
        String[] encryptKeys = encryptKey.split(",");

        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, symmetricalKey.getBytes(), iv.getBytes());

        List<String> keys = new ArrayList<>();
        for (String toDecryptKey : encryptKeys) {
            String key = aes.decryptStr(toDecryptKey);
            keys.add(key);
        }

        return keys;
    }
}
