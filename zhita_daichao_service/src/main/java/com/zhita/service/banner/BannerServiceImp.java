package com.zhita.service.banner;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zhita.dao.manage.ShufflingFigureMapper;
import com.zhita.model.manage.ShufflingFigure;

@Transactional
@Service(value="bannerServiceImp")
public class BannerServiceImp implements IntBannerService{
	@Resource(name="shufflingFigureMapper")
	private ShufflingFigureMapper shufflingFigureMapper;

	public ShufflingFigureMapper getShufflingFigureMapper() {
		return shufflingFigureMapper;
	}

	public void setShufflingFigureMapper(ShufflingFigureMapper shufflingFigureMapper) {
		this.shufflingFigureMapper = shufflingFigureMapper;
	}
    //后台管理---查询出轮播图总数量
    public int pageCount() {
    	int count=shufflingFigureMapper.pageCount();
    	return count;
    }
    //后台管理---根据标题字段模糊查询出  轮播图总数量
    public int pageCountByLike(String title) {
    	int count=shufflingFigureMapper.pageCountByLike(title);
    	return count;
    }
    //后台管理---查询轮播图全部信息,含分页
    public List<ShufflingFigure> queryAll(Integer page){
    	List<ShufflingFigure> list=shufflingFigureMapper.queryAll(page);
    	return list;
    }
    //后台管理---根据标题字段模糊查询轮播图信息，含分页
    public List<ShufflingFigure> queryAllByLike(String title,Integer page){
    	List<ShufflingFigure> list=shufflingFigureMapper.queryAllByLike(title, page);
    	return list;
    }
    //后台管理---添加轮播图信息
    public int AddAll(ShufflingFigure shufflingFigure) {
    	int num=shufflingFigureMapper.AddAll(shufflingFigure);
    	return num;
    }
    //后台管理 ---根据主键id查询出轮播图信息
    public ShufflingFigure selectByPrimaryKey(Integer id) {
    	ShufflingFigure shufflingFigure=shufflingFigureMapper.selectByPrimaryKey(id);
    	return shufflingFigure;
    }
    //后台管理---通过传过来的轮播图对象，对当前对象进行修改保存
    public int updateShufflingFigure(ShufflingFigure shufflingFigure) {
    	int num=shufflingFigureMapper.updateShufflingFigure(shufflingFigure);
    	return num;
    }
    //后台管理---根据删除按钮，修改轮播图假删除状态
    public int upaFalseDel(Integer id) {
    	int num=shufflingFigureMapper.upaFalseDel(id);
    	return num;
    }
    //后台管理---修改轮播图的状态为开启
    public int upaStateOpen(Integer id) {
    	int num=shufflingFigureMapper.upaStateOpen(id);
    	return num;
    }
    //后台管理---修改轮播图的状态为关闭
    public int upaStateClose(Integer id) {
    	int num=shufflingFigureMapper.upaStateClose(id);
    	return num;
    }
}