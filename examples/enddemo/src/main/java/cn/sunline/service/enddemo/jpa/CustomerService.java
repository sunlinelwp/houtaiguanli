package cn.sunline.service.enddemo.jpa;

import java.util.List;

import org.springframework.data.domain.Page;

import cn.sunline.domain.enddemo.jpa.Customer;


public interface CustomerService {
	
	/**
	 * 保存客户信息
	 * @param customer
	 * @return 客户
	 */
	Customer saveCustomer(Customer customer);

	/**
	 * 根据custId获取客户信息
	 * @param custId
	 * @return 客户
	 */
	Customer findByCustId(Integer custId);
	
	/**
	 * 根据custName获取客户信息
	 * @param custName
	 * @return 客户
	 */
	List<Customer> findByCustName(String custName);
	
	/**
	 * 根据age获取客户信息
	 * @param age
	 * @return 客户
	 */
	List<Customer> findByAge(Integer age);
	
	/**
	 * 根据age获取客户信息,结果按客户号升序排列
	 * @param age
	 * @return 客户
	 */
	List<Customer> findByAgeOrderByCustIdAsc(Integer age);
	
	/**
	 * 查询客户年龄小于age的客户信息
	 * @param age
	 * @return 客户
	 */
	List<Customer> findByAgeLessThan(Integer age);
	
	/**
	 * 查询客户年龄大于age的客户信息
	 * @param age
	 * @return 客户
	 */
	List<Customer> findByAgeGreaterThan(Integer age);
	
	/**
	 * 根据custName模糊查询客户信息
	 * @param custName
	 * @return 客户
	 */
	List<Customer> findByCustNameLike(String custName);
	
	/**
	 * 更新客户名称
	 * @param oldName
	 * @param newName
	 * @return 影响记录数
	 */
	int updateByCustName(String oldName, String newName);
	
	/**
	 * 根据条件查询客户
	 * @Param	查询模版
	 * @return 客户
	 */
	Page<Object[]> findByCondition(Customer c);
	
	/**
	 * 根据名称用存储过程查询客户
	 * @Param  名称
	 * @return 客户
	 */
	List<Customer> queryByName(String custName);
	
	/**
	 * 用存储过程更新客户年龄
	 * @Param  名称
	 * @Param  年龄
	 */
	void updateAgeById(Integer custId, Integer age);
	
}

