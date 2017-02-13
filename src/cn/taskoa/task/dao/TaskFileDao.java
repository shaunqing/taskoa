package cn.taskoa.task.dao;

import org.apache.ibatis.annotations.Param;

import cn.taskoa.common.dao.BaseDao;
import cn.taskoa.common.dao.MyBatisDao;
import cn.taskoa.task.entity.Task;
import cn.taskoa.task.entity.TaskFile;

@MyBatisDao
public interface TaskFileDao extends BaseDao {

	/**
	 * 根据任务文件id和创建者id，查询文件路径
	 * @param taskfileid
	 * @param creator_userid
	 * @return
	 * @throws Exception
	 */
	public String getByTaskFileIdAndCrearot(@Param("taskfileid") int taskfileid,
			@Param("creator_userid") int creator_userid) throws Exception;

	/**
	 * 删除该条文件记录
	 * @param taskfileid
	 * @return
	 * @throws Exception
	 */
	public int delete(@Param("taskfileid") int taskfileid) throws Exception;
	
	
	
	
	
	
	
	public TaskFile get(@Param("taskfileid") int taskfileid) throws Exception;
	
	
	public Task getWithUser(@Param("taskfileid") int taskfileid);
}
