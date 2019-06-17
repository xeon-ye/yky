package com.deloitte.services.fssc.engine.manual.mapper;

import com.deloitte.services.fssc.engine.manual.entity.AvManualVoucherLine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 手工录入表行信息 Mapper 接口
 * </p>
 *
 * @author chenx
 * @since 2019-03-20
 */
public interface AvManualVoucherLineMapper extends BaseMapper<AvManualVoucherLine> {

    /**
     *
     * @param table
     * @param field
     * @param documentId
     * @param ID
     * @return
     */
    List<Object> getValue(@Param("table")String table, @Param("field")String field, @Param("documentId")Long documentId,@Param("ID")String ID);

    /**
     *
     * @param map
     * @return
     */
    List<Object> getMap(@Param("map")String map);

    List<Map<String,Object>> getMapByJudgement(@Param("map")String map);

    /**
     *
     * @param documentType
     * @param documentId
     * @return
     */
    Map<String,Object> selectMapLimitOne(@Param("documentType")String documentType,@Param("documentId")Long documentId);

    /**
     *
     * @param judgement
     * @param table
     * @return
     */
    List<Map<String,Object>> getJudgement(@Param("judgement")String judgement,@Param("table")String table);

    /**
     * 获取得到行列表数据信息
     * @param documentType
     * @param documentId
     * @param table
     * @return
     */
    List<Map<String,Object>> getQueryList(@Param("documentType")String documentType,@Param("documentId")Long documentId,@Param("table")String table);

    /**
     *
     * @param documentType
     * @param documentId
     * @return
     */

    List<Map<String,Object>> getHeadQueryList(@Param("documentType")String documentType,@Param("documentId")Long documentId);

    /**
     *
     * @param documentType
     * @param documentId
     * @param table
     * @param type
     * @return
     */
    List<Map<String,Object>> getQueryPayTypeList(@Param("documentType")String documentType,@Param("documentId")Long documentId,@Param("table")String table,@Param("type")String type);

    List<Map<String,Object>> getPayTypeList(@Param("headTable")String headTable,@Param("lineTable")String lineTable,@Param("documentNum")String documentNum,@Param("documentType")String documentType);

}
