package application.entities;

import java.io.Serializable;
import java.util.stream.Stream;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class PersonGen implements IdentifierGenerator {

	String prefixEMP = "NV";
	String prefixCUS = "KH";
	String prefixMNG = "QL";
	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {

		System.out.println(object.toString());
		System.out.println(Employee.class.getName());
		System.out.println(Customer.class.getName());
		if(object.getClass().getName()==(Employee.class.getName()))
		{
			Employee employee = (Employee) object;
			if(employee.getPosition().equalsIgnoreCase("NV Bán hàng") || employee.getPosition().equalsIgnoreCase("NV Kỹ thuật"))
			{
		        String query = "SELECT e.id FROM Employee e where e.id like 'NV%' ";
		        Stream<String> ids = session.createQuery(query, String.class).stream();
		        Long max = ids.map(o -> o.replace(prefixEMP, "")).mapToLong(Long::parseLong).max().orElse(0L);
		        System.out.println(prefixEMP + (String.format("%04d", max + 1)));
		        return prefixEMP + (String.format("%04d", max + 1));
			}
			else if(employee.getPosition().equalsIgnoreCase("NV Quản lý")) {
				String query = "SELECT e.id FROM Employee e where e.id like 'QL%' ";
		        Stream<String> ids = session.createQuery(query, String.class).stream();
		        Long max = ids.map(o -> o.replace(prefixMNG, "")).mapToLong(Long::parseLong).max().orElse(0L);
		        System.out.println(prefixMNG + (String.format("%04d", max + 1)));
		        return prefixMNG + (String.format("%04d", max + 1));
			}
			
		}
		if(object.getClass().getName()==(Customer.class.getName()))
		{
			String query = "SELECT e.id FROM Customer e";
	        Stream<String> ids = session.createQuery(query, String.class).stream();
	        Long max = ids.map(o -> o.replace(prefixCUS, "")).mapToLong(Long::parseLong).max().orElse(0L);
	        System.out.println(prefixCUS + (String.format("%04d", max + 1)));
	        return prefixCUS + (String.format("%04d", max + 1));
		}
		else
		return null;
	}

}
