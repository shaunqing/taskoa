package cn.taskoa.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.taskoa.common.dao.BaseDao;
import cn.taskoa.common.dao.MyBatisDao;
import cn.taskoa.common.utils.TaskFileDetail;
import cn.taskoa.task.entity.Task;
import cn.taskoa.task.entity.TaskFile;

@MyBatisDao
public interface TaskFileDao extends BaseDao {

	/**
	 * 获取文件名称、路径，以及任务创建人和完成人
	 * @param taskfileid
	 * @return
	 */
	public Task getWithUser(@Param("taskfileid") int taskfileid);
	
	/**
	 * 根据任务文件id和创建者id，查询文件路径
	 * @param taskfileid
	 * @param creator_userid
	 * @return
	 * @throws Exception
	 */
	public String getByIdAndCreator(@Param("taskfileid") int taskfileid,
			@Param("creator_userid") int creator_userid) throws Exception;

	/**
	 * 删除该条文件记录
	 * @param taskfileid
	 * @return
	 * @throws Exception
	 */
	public int delete(@Param("taskfileid") int taskfileid) throws Exception;
	
	/**
	 * 根据taskid、type，以及userid获取文件记录
	 * 当type是fj时，使用创建人id查找；当type是jg时，使用执行人id查找
	 * @param detail
	 * @return
	 * @throws Exception
	 */
	public List<TaskFile> getByTaskidAndType(@Param("detail") TaskFileDetail detail) throws Exception;
	
	/**
	 * 批量修改taskfileurl
	 * @param taskFiles
	 * @throws Exception
	 */
	public void batchUpdateUrl(List<TaskFile> taskFiles) throws Exception;
	
	/**
	 * 批量添加
	 * @param taskFiles
	 * @throws Exception
	 */
	public void batchInsert(List<TaskFile> taskFiles) throws Exception;
	
	/**
	 * 添加
	 * @param taskFile
	 * @throws Exception
	 */
	public void insert(TaskFile taskFile) throws Exception;
	

}
