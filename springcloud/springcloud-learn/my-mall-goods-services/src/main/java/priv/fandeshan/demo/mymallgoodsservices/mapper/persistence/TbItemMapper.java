package priv.fandeshan.demo.mymallgoodsservices.mapper.persistence;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import priv.fandeshan.demo.mymallgoodsservices.mapper.entitys.TbItem;
import priv.fandeshan.demo.mymallgoodsservices.mapper.entitys.TbItemExample;

@Mapper
public interface TbItemMapper {
    long countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);

    List<TbItem> selectStockForUpdate(@Param("ids")List<Long> ids);

    int decreaseStock(@Param("record")TbItem record);
}