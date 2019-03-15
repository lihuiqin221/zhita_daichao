package com.zhita.controller.news;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zhita.model.manage.Strategy;
import com.zhita.service.news.IntNewsService;
import com.zhita.util.ListPageUtil;
import com.zhita.util.OssUtil;
import com.zhita.util.PageUtil;

@Controller
@RequestMapping("/news")
public class NewsController {
	@Resource(name="newsServiceImp")
	private IntNewsService intNewsService;

	public IntNewsService getIntNewsService() {
		return intNewsService;
	}

	public void setIntNewsService(IntNewsService intNewsService) {
		this.intNewsService = intNewsService;
	}
	
	//后台管理---查询攻略表所有信息，含分页
	@ResponseBody
	@RequestMapping("/queryAllNews")
    public Map<String,Object> queryAllNews(Integer page,String string){
		string = string.replaceAll("\"", "").replace("[","").replace("]","");
		String [] company= string.split(",");
		PageUtil pageUtil=null;
		List<Strategy> list=new ArrayList<>();
		List<Strategy> listto=new ArrayList<>();
		if(company.length==1) {
			
			System.out.println("company.length==1");
			
	    	int totalCount=intNewsService.pageCount(company[0]);//该方法是查询攻略总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages);
	    	listto=intNewsService.queryAllNews(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,10,totalCount);
		}
		else if(company.length>1) {
			
			System.out.println("company.length>1");
			
    		List<Strategy> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intNewsService.queryAllNews1(company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
		}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listnews",listto);
    	map.put("pageutil", pageUtil);
    	map.put("company", company);
    	return map;
    }
	
	//后台管理---根据标题字段模糊查询攻略表所有信息，含分页
	@ResponseBody
	@RequestMapping("/queryNewsByLike")
    public Map<String,Object> queryNewsByLike(String title,Integer page,String string){
		PageUtil pageUtil=null;
		List<Strategy> list=new ArrayList<>();
		List<Strategy> listto=new ArrayList<>();
		if(string!=null&&!"".equals(string)){
			string = string.replaceAll("\"", "").replace("[","").replace("]","");
			String [] company=string.split(",");
		
		//标题为空并且公司名不为空   公司名选择的是  全部 项
		if((title==null||"".equals(title))&&(company.length>1)) {
			
			System.out.println("第一个if");
			
	   		List<Strategy> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intNewsService.queryAllNews1(company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
			
		}
		//标题为空并且公司名不为空   公司名选择的不是  全部项
		else if((title==null||"".equals(title))&&(company.length==1)) {
			
			System.out.println("第二个if");
			
	    	int totalCount=intNewsService.pageCount(company[0]);//该方法是查询攻略总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages);
	    	listto=intNewsService.queryAllNews(company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,10,totalCount);
		}
		//标题不为空并且公司名不为空   公司名选择的是  全部项
		else if((title!=null||!"".equals(title))&&(company.length>1)) {
			
			System.out.println("第三个if");
			
	   		List<Strategy> listfor=null;
			for (int i = 0; i < company.length; i++) {
		    	listfor=intNewsService.queryNewsByLike1(title,company[i]);
            	list.addAll(listfor);
			}
			
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i)+"整合后的集合");
			}
			
			System.out.println("传进工具类的page"+page);
			
			ListPageUtil listPageUtil=new ListPageUtil(list,page,10);
			listto.addAll(listPageUtil.getData());
			
			pageUtil=new PageUtil(listPageUtil.getCurrentPage(), listPageUtil.getPageSize(),listPageUtil.getTotalCount());
			
		}
		//标题不为空并且公司名不为空   公司名选择的不是  全部项
		else if((title!=null||!"".equals(title))&&(company.length==1)){
			
			System.out.println("第四个if");
			
	    	int totalCount=intNewsService.pageCountByLike(title,company[0]);//该方法是模糊查询的攻略总数量
	    	pageUtil=new PageUtil(page,10,totalCount);
	    	if(page<1) {
	    		page=1;
	    	}
	    	else if(page>pageUtil.getTotalPageCount()) {
	    		if(totalCount==0) {
	    			page=pageUtil.getTotalPageCount()+1;
	    		}else {
	    			page=pageUtil.getTotalPageCount();
	    		}
	    	}
	    	int pages=(page-1)*pageUtil.getPageSize();
	    	pageUtil.setPage(pages);
	    	listto=intNewsService.queryNewsByLike(title,company[0],pageUtil.getPage(),pageUtil.getPageSize());
	    	pageUtil=new PageUtil(page,10,totalCount);
			
		}
	}
    	HashMap<String,Object> map=new HashMap<>();
    	map.put("listnewsByLike",listto);
    	map.put("pageutil", pageUtil);
    	return map;
    }
	//后台管理---添加攻略信息
	@Transactional
	@ResponseBody
	@RequestMapping("/AddALL")
    public Map<String,Object> AddALL(Strategy strategy,MultipartFile file)throws Exception{
		Map<String, Object> map = new HashMap<>();
		if (file != null) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			InputStream iStream = file.getInputStream();
			String fileName = file.getOriginalFilename();// 文件原名称
			// 判断文件类型
			type = fileName.indexOf(".") != -1? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()): null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					path = "strategy/" + /* System.getProperty("file.separator")+ */trueFileName;
					OssUtil ossUtil = new OssUtil();
					String ossPath = ossUtil.uploadFile(iStream, path);
					if(ossPath.substring(0, 5).equals("https")) {
						System.out.println("路径为："+ossPath);
						strategy.setCover(ossPath);
						map.put("msg", "图片上传成功");
					}
					
					System.out.println("存放图片文件的路径:" + ossPath);
				} else {
					map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
					return map;
				}
			} else {
				map.put("msg", "文件类型为空");
				return map;
			}
		}else {
			map.put("msg", "请上传图片");
			return map;
		} 
		intNewsService.addAll(strategy);		
		return map;

	}
    //后台管理---通过主键id查询出攻略信息
	@ResponseBody
	@RequestMapping("/selectByPrimaryKey")
    public Strategy selectByPrimaryKey(Integer id) {
    	Strategy strategy=intNewsService.selectByPrimaryKey(id);
    	return strategy;
    }
	//后台管理---通过传过来的攻略对象，对当前对象进行修改保存
	@Transactional
	@ResponseBody
	@RequestMapping("/updateStrategy")
    public Map<String,Object> updateStrategy(Strategy strategy,MultipartFile file)throws Exception{
		Map<String, Object> map = new HashMap<>();
		if (file.getSize()!=0) {// 判断上传的文件是否为空
			String path = null;// 文件路径
			String type = null;// 文件类型
			InputStream iStream = file.getInputStream();
			String fileName = file.getOriginalFilename();// 文件原名称
			// 判断文件类型
			type = fileName.indexOf(".") != -1? fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()): null;
			if (type != null) {// 判断文件类型是否为空
				if ("GIF".equals(type.toUpperCase()) || "PNG".equals(type.toUpperCase()) || "JPG".equals(type.toUpperCase())) {
					// 自定义的文件名称
					String trueFileName = String.valueOf(System.currentTimeMillis()) + fileName;
					// 设置存放图片文件的路径
					path = "strategy/" + /* System.getProperty("file.separator")+ */trueFileName;
					OssUtil ossUtil = new OssUtil();
					String ossPath = ossUtil.uploadFile(iStream, path);
					if(ossPath.substring(0, 5).equals("https")) {
						System.out.println("路径为："+ossPath);
						strategy.setCover(ossPath);
						map.put("msg", "图片上传成功");
					}
					
					System.out.println("存放图片文件的路径:" + ossPath);
				} else {
					map.put("msg", "不是我们想要的文件类型,请按要求重新上传");
					return map;
				}
			} else {
				map.put("msg", "文件类型为空");
				return map;
			}
		}else {
			int id = strategy.getId();
			String cover = intNewsService.getCover(id); //通过传过来的攻略id，查询商标的URL
			strategy.setCover(cover);
		} 
		intNewsService.updateStrategy(strategy);
		return map;
	}
	
	
	//后台管理---通过主键id修改其当前对象的假删除状态
	@Transactional
	@ResponseBody
	@RequestMapping("/upaFalseDelById")
    public int upaFalseDelById(Integer id) {
    	int num=intNewsService.upaFalseDelById(id);
    	return num;
    }
    //后台管理---修改攻略状态
	@Transactional
	@ResponseBody
	@RequestMapping("/upaState")
	public int upaState(String state,Integer id) {
		int num=0;
		if(state.equals("1")) {
			num=intNewsService.upaStateOpen(id);
		}else {
			num=intNewsService.upaStateClose(id);
		}
		return num;
	}
}
