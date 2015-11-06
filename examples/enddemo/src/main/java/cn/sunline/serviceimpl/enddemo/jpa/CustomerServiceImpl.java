package cn.sunline.serviceimpl.enddemo.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.sunline.domain.enddemo.jpa.Customer;
import cn.sunline.repository.enddemo.jpa.CustomerRepository;
import cn.sunline.service.enddemo.jpa.CustomerService;

//@Service用来标注业务层组件
@Service("CustomerService")
//注解标识事务管理，类中所有方法为只读事务属性
@Transactional(readOnly=true)
public class CustomerServiceImpl implements CustomerService {
	
	//注解标识自动装配，spring帮你省略引用对象的setter/getter方法
	@Autowired
	private CustomerRepository customerRepository;
	//注解标识实体管理器，用来执行持久化操作的
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false)
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findByCustId(Integer custId) {
		return customerRepository.findByCustId(custId);
	}

	@Override
	public List<Customer> findByCustName(String custName) {
		return customerRepository.findByCustName(custName);
	}

	@Override
	public List<Customer> findByAge(Integer age) {
		return customerRepository.findByAge(age);
	}

	@Override
	public List<Customer> findByAgeOrderByCustIdAsc(Integer age) {
		return customerRepository.findByAgeOrderByCustIdAsc(age);
	}

	@Override
	public List<Customer> findByAgeLessThan(Integer age) {
		return customerRepository.findByAgeLessThan(age);
	}

	@Override
	public List<Customer> findByAgeGreaterThan(Integer age) {
		return customerRepository.findByAgeGreaterThan(age);
	}

	@Override
	public List<Customer> findByCustNameLike(String custName) {
		return customerRepository.findByCustNameLike(custName);
	}

	@Override
	public int updateByCustName(String oldName, String newName) {
		return customerRepository.updateByCustName(oldName, newName);
	}

	@Override
	public Page<Object[]> findByCondition(Customer c) {
		//根据查询条件，手动拼接查询语句
		String hql = "select c from Customer c where 1=1";
		if(c.getCustId() != null){
			hql = hql + "	and c.custId = :custId";
		}
		if(c.getCustName() != null){
			hql = hql + "	and c.custName = :custName";
		}
		if(c.getSex() != null){
			hql = hql + "	and c.sex = :sex";
		}
		if(c.getAge() != null){
			hql = hql + "	and c.age = :age";
		}
		//根据查询语句hql创建查询对象q
		Query q = em.createQuery(hql);
		//设置查询参数
		if(c.getCustId() != null){
			q.setParameter("custId", c.getCustId());
		}
		if(c.getCustName() != null){
			q.setParameter("custName", c.getCustName());
		}
		if(c.getSex() != null){
			q.setParameter("sex", c.getSex());
		}
		if(c.getAge() != null){
			q.setParameter("age", c.getAge());
		}
		@SuppressWarnings("unchecked")
		Page<Object[]> page = new PageImpl<Object[]>(q.getResultList(), new PageRequest(0,1), 3);
		
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> queryByName(String custName) {
		String cSql = "{call queryCustomerByName(?)}";
		Query query = em.createNamedQuery(cSql);
		query.setParameter(1, custName);
		return query.getResultList();
	}

	@Override
	public void updateAgeById(Integer custId, Integer age) {
		Query query = em.createNativeQuery("{call updatecustomeragebyid(?,?)}");
		query.setParameter(1, custId);
		query.setParameter(2, age);
		query.executeUpdate();
	}

}
