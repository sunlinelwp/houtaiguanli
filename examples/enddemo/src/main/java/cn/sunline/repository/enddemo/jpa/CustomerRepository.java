package cn.sunline.repository.enddemo.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import cn.sunline.domain.enddemo.jpa.Customer;


/**
 * 说明：继承Spring提供的接口就可以实现基本的数据库的访问和操作，不用自己写实现的代码
 * Spring Data JPA提供的接口
 * Repository最顶层接口，空接口，目的是统一所有Repository类型
 * CrudRepository是Repository的子接口，提供CRUD功能
 * PagingAndSortingRepository是CrudRepository子接口，添加分页和排序功能
 * JpaRepository是PagingAndSortingRepository子接口，添加批量操作等
 * 
 */

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	
	/**
	 * 根据custId获取客户信息
	 * @param custId
	 * @return 客户
	 */
	Customer findByCustId(Integer id);
	
	/**
	 * 根据custName获取客户信息
	 * @param custName
	 * @return 客户
	 */
	List<Customer> findByCustName(String name);
	
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
	@Query(value = "select c from Customer c where c.custName like %?1% order by c.custId")
	List<Customer> findByCustNameLike(String custName);
	
	/**
	 * 更新客户名称
	 * @param oldName
	 * @param newName
	 * @return 影响记录数
	 */
	//注解标识执行更新操作
	@Modifying
	//注解标识自定义查询语句，方法参数个数与@Query语句参数个数一致
	@Query(value = "update Customer c set c.custName = :newName where c.custName like %:oldName%")
	int updateByCustName(@Param("oldName")String oldName, @Param("newName")String newName);

}