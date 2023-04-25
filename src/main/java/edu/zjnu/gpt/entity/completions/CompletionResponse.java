package edu.zjnu.gpt.entity.completions;

import edu.zjnu.gpt.entity.common.Choice;
import edu.zjnu.gpt.entity.common.OpenAiResponse;
import edu.zjnu.gpt.entity.common.Usage;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 描述： 答案类
 *
 * @author SetsunaYang
 * 2023-02-11
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CompletionResponse extends OpenAiResponse implements Serializable {
    private String id;
    private String object;
    private long created;
    private String model;
    private Choice[] choices;
    private Usage usage;
}
