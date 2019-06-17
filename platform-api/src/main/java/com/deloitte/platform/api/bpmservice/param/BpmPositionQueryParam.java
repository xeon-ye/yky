package com.deloitte.platform.api.bpmservice.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : jackliu
 * @Date : Create in 2019-02-18
 * @Description :  BpmPosition查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BpmPositionQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String processDefineKey;
    private String processName;
    private String nodeId;
    private String nodeName;
    private Integer nodeSequence;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String ext1;
    private String ext2;
    private String ext3;
    private String ext4;
    private String ext5;
    private String name;
    private String state;

}
