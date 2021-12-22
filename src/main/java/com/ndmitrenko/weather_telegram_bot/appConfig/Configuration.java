package com.ndmitrenko.weather_telegram_bot.appConfig;

import com.ndmitrenko.weather_telegram_bot.MyWizardTelegramBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.ApiContext;

@Setter
@Getter
@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix = "telegrambot")
public class Configuration {
    private String webHookPath;
    private String botUserName;
    private String botToken;

    private DefaultBotOptions.ProxyType proxyType;
    private String proxyHost;
    private int proxyPort;

    @Bean
    public MyWizardTelegramBot MySuperTelegramBot() {
        DefaultBotOptions options = ApiContext
                .getInstance(DefaultBotOptions.class);

        options.setProxyHost(proxyHost);
        options.setProxyPort(proxyPort);
        options.setProxyType(proxyType);

        MyWizardTelegramBot mySuperTelegramBot = new MyWizardTelegramBot(options);
        mySuperTelegramBot.setBotUserName(botUserName);
        mySuperTelegramBot.setBotToken(botToken);
        mySuperTelegramBot.setWebHookPath(webHookPath);

        return mySuperTelegramBot;
    }
}
