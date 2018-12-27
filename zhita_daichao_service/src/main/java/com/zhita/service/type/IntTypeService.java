package com.zhita.service.type;

import java.util.List;

import com.zhita.model.manage.LoanClassification;
import com.zhita.model.manage.LoansBusinesses;

public interface IntTypeService{
    //后台管理---查询贷款分类所有信息，含分页
    public List<LoanClassification> queryAllPage(Integer page);
    //后台管理---用于获取总页数
    public int pageCount();
    //后台管理---用于获取模糊查询总页数
    public int pageCountByLike(String businessClassification);
    //后台管理---模糊查询贷款分类信息,并且有分页功能
    public List<LoanClassification> queryByLike(String businessClassification,Integer page);
    //后台管理---添加贷款分类信息
    public int addAll(LoanClassification record);
    //后台管理---通过主键id查询出贷款分类信息
    public LoanClassification selectByPrimaryKey(Integer id);
    //小程序---通过贷款分类的名称，查询出当前贷款分类下的所有贷款商家的信息
    public List<LoansBusinesses> queryLoanbusinByLoanClass(String businessClassification);
    //后台管理---查询贷款分类所有信息，不含分页,做贷款商家添加功能时，下拉框取贷款分类的值时使用
    public List<LoanClassification> queryAllLoanCla();

}
