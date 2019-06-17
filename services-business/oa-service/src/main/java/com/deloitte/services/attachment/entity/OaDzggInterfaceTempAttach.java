package com.deloitte.services.attachment.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 泛微公告同步临时附件表
 * </p>
 *
 * @author jianghaixun
 * @since 2019-05-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("OA_DZGG_INTERFACE_TEMP_ATTACH")
@ApiModel(value="OaDzggInterfaceTempAttach对象", description="泛微公告同步临时附件表")
public class OaDzggInterfaceTempAttach extends Model<OaDzggInterfaceTempAttach> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "流程实例id")
    @TableField("REQUEST_ID")
    private String requestId;

    @ApiModelProperty(value = "文件名称")
    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty(value = "文件地址")
    @TableField("FILE_ADDRESS")
    private String fileAddress;


    public static final String REQUEST_ID = "REQUEST_ID";

    public static final String FILE_NAME = "FILE_NAME";

    public static final String FILE_ADDRESS = "FILE_ADDRESS";

    @Override
    protected Serializable pkVal() {
        return null;
    }

}
