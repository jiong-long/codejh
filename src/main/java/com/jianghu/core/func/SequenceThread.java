package com.jianghu.core.func;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.jianghu.core.Database;
import com.jianghu.core.Tools;

/**
 * 序列生成器
 * 
 * @author jinlong
 *
 */
public class SequenceThread {

	private String sequenceName;//序列名称

	private Lock lock = new ReentrantLock();//全局变量 锁

	private static Map<String, SequenceThread> name2instance = new HashMap<String, SequenceThread>();//名称与实体的对应

	public SequenceThread(String sequenceName) {
		this.setSequenceName(sequenceName);
	}

	/**
	 * 生成序列较为常用，每次都new对象耗费资源
	 * 
	 * @param sequenceName
	 * @return
	 */
	public static SequenceThread instance(String sequenceName) {
		SequenceThread instance = null;
		if (name2instance.containsKey(sequenceName)) {
			instance = (SequenceThread) name2instance.get(sequenceName);
		} else {
			synchronized (SequenceThread.class) {
				if (name2instance.containsKey(sequenceName)) {
					instance = (SequenceThread) name2instance.get(sequenceName);
				} else {
					instance = new SequenceThread(sequenceName);
					name2instance.put(sequenceName, instance);
				}
			}
		}
		return instance;
	}

	/**
	 * 通过序列名称获取下一个值
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月2日 上午10:47:32
	 * @param seq_nam
	 * @return
	 * @throws Exception
	 */
	public String getNextVal() throws Exception {
		String[] next = getNextVal(1);
		return next[0];
	}

	/**
	 * 获得一组序列
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月2日 上午11:00:46
	 * @param seq_nam
	 * @param count
	 * @return
	 * @throws Exception
	 */
	public String[] getNextVal(int count) {
		String[] nextArr = new String[count];
		if (count > 0) {
			try {
				lock.lock();//加锁
				//Thread.sleep(10000);//测试   睡眠10S,sleep的时候不会释放锁
				int currVal = Integer.parseInt(getCurrVal());
				nextArr = new String[count];
				for (int i = 0; i < count; i++) {
					nextArr[i] = currVal + 1 + "";
				}
				String update = "update bc_sequence set curr_val = ? where seq_nam = ?";
				Database.executeUpdate(update, currVal + count, sequenceName.toUpperCase());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();//解锁
			}
		}
		return nextArr;
	}

	/**
	 * 获得序列的当前值，没有往数据中增加
	 * 
	 * @author wangjinlong
	 * @creatTime 2017年7月2日 上午10:59:57
	 * @param seq_nam
	 * @return
	 * @throws Exception
	 */
	private String getCurrVal() throws Exception {
		String sql = "select curr_val from bc_sequence where seq_nam = ?";
		String currVal = Database.getUniqueStringValue(sql, sequenceName.toUpperCase());
		if (Tools.isEmpty(currVal)) {// 没有这个序列，增加
			String insert = "insert into bc_sequence(seq_nam,curr_val) values(?,0)";
			Database.executeUpdate(insert, sequenceName.toUpperCase());
			currVal = "0";
		}
		return currVal;
	}

	public String getSequenceName() {
		return sequenceName;
	}

	public void setSequenceName(String sequenceName) {
		this.sequenceName = sequenceName;
	}

}
