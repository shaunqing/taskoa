package cn.taskoa.task.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.taskoa.common.dao.BaseDao;
import cn.taskoa.common.dao.MyBatisDao;
import cn.taskoa.task.entity.Task;

@MyBatisDao
public interface TaskDao extends BaseDao {

	/**
	 * 根据任务发起人id查询任务
	 * @param creator_userid 任务发起人id
	 * @param rowIndex t_task表中的序号
	 * @param rowNum 显示数据条数
	 * @return
	 */
	public List<Task> listByCreator(@Param("creator_userid") int creator_userid, @Param("rowIndex") int rowIndex,
			@Param("rowNum") int rowNum);
	
	/**
	 * 根据任务发起人id统计数据量
	 * @param creator_userid 任务发起人id
	 * @return
	 */
	public int countByCreator(@Param("creator_userid") int creator_userid);
	
	/**
	 * 根据taskid获取任务和任务附件
	 * @param creator_userid
	 * @param taskid
	 * @param taskfiletype 值为all时，返回所有类型的文件，并返回resultdesc和taskfiletype
	 * @return
	 */
	public List<Task> listByTaskid(@Param("creator_userid") int creator_userid, @Param("taskid") int taskid,
			@Param("taskfiletype") String taskfiletype);
	
	
	/**
	 * 将任务删除位置位1
	 * @param creator_userid 发起人id
	 * @param taskid 任务id
	 */
	public int deleteTask(@Param("creator_userid") int creator_userid, @Param("taskid") int taskid) throws Exception;
	
	/**
	 * 发起人更新任务信息
	 * @param creator_userid
	 * @param taskNew
	 * @return
	 */
	public int updateTaksInfo(@Param("creator_userid") int creator_userid, @Param("task") Task task)
			throws Exception;
	
	
	
	/*-------------------------------------*/
	
	
	
	/**
	 * 根据taskid获取任务、任务附件、结果描述、任务结果
	 * @param taskid
	 * @return
	 */
	public List<Task> findTaskAndResultByTaskid(@Param("creator_userid") int creator_userid,@Param("taskid") int taskid);
	
	/**
	 * 根据operator_userid统计数据量
	 * @param operator_userid
	 * @return
	 */
	public int getCountByOperator(@Param("operator_userid") int operator_userid);
	
	/**
	 * 根据operator_userid查询任务
	 * @param operator_userid
	 * @param rowIndex
	 * @param rowNum
	 * @return
	 */
	public List<Task> findByOperator(@Param("operator_userid") int operator_userid, @Param("rowIndex") int rowIndex,
			@Param("rowNum") int rowNum);
	
	/**
	 * 更新任务结果
	 * @param taskid
	 * @param resultdesc
	 * @throws Exception
	 */
	public void updateResultByTaskid(@Param("taskid") int taskid, @Param("resultdesc") String resultdesc)
			throws Exception;
	
	
}
