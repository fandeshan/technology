package org.fds.chatgpt.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;


@ConfigurationProperties("chatai.api.openai")
@Data

public class OpenaiApiConfig {

    private String appKey = "xxx";

    private String apiPrefixUrl = "https://api.openai.com/";

    private String textCompletionUrl = "v1/completions";

    private Integer maxTokens = 2048;

    private String model = "text-davinci-003";

    private BigDecimal temperature = new BigDecimal(0);

    private Integer n;

    private Integer requestMaxPromptLen = 1000;

    private String suffix;

    private BigDecimal topP;

    private Integer logprobs;

    private BigDecimal presencePenalty;

    private BigDecimal frequencyPenalty;

    private Integer bestOf;

}
