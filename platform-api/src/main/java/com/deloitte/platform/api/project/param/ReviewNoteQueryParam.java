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
 * @Description :  ReviewNote查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewNoteQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String applicationId;
    private String reviewMan;
    private LocalDateTime reviewDate;
    private String reviewOpi;
    private LocalDateTime createTime;
    private String createBy;
    private String ext1;
    private String ext2;
    private String ext3;

}
