package com.lyc.yl.dao;

import com.lyc.yl.entity.LitemallAdminExample;
import com.lyc.yl.entity.StoreAdmin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员表(StoreAdmin)表数据库访问层
 *
 * @author makejava
 * @since 2022-12-20 09:58:07
 */
public interface StoreAdminDao {

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    long countByExample(LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int deleteByExample(LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int insert(StoreAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int insertSelective(StoreAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    StoreAdmin selectOneByExample(LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    StoreAdmin selectOneByExampleSelective(@Param("example") LitemallAdminExample example, @Param("selective") StoreAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    List<StoreAdmin> selectByExampleSelective(@Param("example") LitemallAdminExample example, @Param("selective") StoreAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    List<StoreAdmin> selectByExample(LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    StoreAdmin selectByPrimaryKeySelective(@Param("id") Integer id, @Param("selective") StoreAdmin.Column ... selective);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    StoreAdmin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    StoreAdmin selectByPrimaryKeyWithLogicalDelete(@Param("id") Integer id, @Param("andLogicalDeleted") boolean andLogicalDeleted);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByExampleSelective(@Param("record") StoreAdmin record, @Param("example") LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByExample(@Param("record") StoreAdmin record, @Param("example") LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKeySelective(StoreAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int updateByPrimaryKey(StoreAdmin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByExample(@Param("example") LitemallAdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table litemall_admin
     *
     * @mbg.generated
     */
    int logicalDeleteByPrimaryKey(Integer id);
}
