package com.deloitte.platform.api.project.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.sql.Blob;

/**

 * @Author : zhengchun
 * @Date : Create in 2019-05-25
 * @Description :  MaintReplyNote查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MaintReplyNoteQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String relyId;
    private String maintId;
    private String projectId;
    private String applicationId;
    private String replyCode;
    private String replyName;
    private String replyAdvice;
    private String replyPartId;
    private String replyPartName;
    private String replyPersonId;
    private String replyPersonName;
    private LocalDateTime replyTime;
    private String replyLastId;
    private String replyLastName;
    private LocalDateTime createTime;
    private String createBy;
    private LocalDateTime updateTime;
    private String updateBy;
    private String ext1;
    private String ext2;

}
