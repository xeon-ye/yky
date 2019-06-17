package com.deloitte.platform.api.portal.param;
import com.deloitte.platform.common.core.entity.param.BaseParam;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**

 * @Author : yidaojun
 * @Date : Create in 2019-04-03
 * @Description :  Notice查询参数
 * @Modified :
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NoticeQueryParam extends BaseParam {
    private static final long serialVersionUID = 1L;
    private Long noticeId;
    private String noticeTitle;
    private Long noticeHit;
    private LocalDateTime noticeDatetime;
    private LocalDateTime noticeUpdatetime;
    private String noticeAuthor;
    private String noticeContent;
    private String delFlg;

}
