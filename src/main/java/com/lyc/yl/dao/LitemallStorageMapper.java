package com.lyc.yl.dao;

import com.lyc.yl.entity.LitemallStorage;
import com.lyc.yl.entity.LitemallStorageExample;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface LitemallStorageMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    long countByExample(LitemallStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int insert(LitemallStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int insertSelective(LitemallStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    LitemallStorage selectOneByExample(LitemallStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    LitemallStorage selectOneByExampleSelective(@Param("example") LitemallStorageExample example, @Param("selective") LitemallStorage.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    List<LitemallStorage> selectByExampleSelective(@Param("example") LitemallStorageExample example, @Param("selective") LitemallStorage.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    List<LitemallStorage> selectByExample(LitemallStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    LitemallStorage selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") LitemallStorage.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    LitemallStorage selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    LitemallStorage selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") LitemallStorage record, @Param("example") LitemallStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") LitemallStorage record, @Param("example") LitemallStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(LitemallStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(LitemallStorage record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallStorageExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_storage
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}
